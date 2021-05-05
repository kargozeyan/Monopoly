package monopoly.ui.scene_controllers.register;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import monopoly.game.Player;
import monopoly.ui.SceneTag;
import monopoly.ui.custom_node.PlayerMarker;
import monopoly.ui.scene_controllers.BaseController;

import java.net.URL;
import java.util.*;

public class PlayerRegisterController extends BaseController implements Initializable {
    @FXML
    VBox players;

    @FXML
    Button addButton, startButton, backButton;

    // list of available colors
    private final List<PlayerMarker.Color> availableColors = new ArrayList<PlayerMarker.Color>(Arrays.asList(PlayerMarker.Color.values()));

    // map of player and marker. it is supposed to be sent to GameController
    private final Map<Player, PlayerMarker> markers = new HashMap<Player, PlayerMarker>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StackPane.setAlignment(addButton, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(startButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);

        StackPane.setMargin(addButton, new Insets(12));
        StackPane.setMargin(startButton, new Insets(12));
        StackPane.setMargin(backButton, new Insets(12));

        startButton.setDisable(true);
    }

    @FXML
    private void onBackClick() {
        sceneManager.changeScene(SceneTag.HOME);
    }

    @FXML
    private void onStartClick() {
        sceneManager.changeScene(SceneTag.GAME, markers);
    }

    @FXML
    private void onAddClick() {
        startDialog();
    }

    private void startDialog() {
        // same as editing dialog but with result as null
        startEditDialog(null);
    }

    private void startEditDialog(RegisteredPlayerItem registeredPlayerItem) {
        // checking whether dialog will be in edit mode
        boolean isEditMode = registeredPlayerItem != null;

        // initializing dialog
        Dialog<DialogResult> dialog = new Dialog<>();
        dialog.setTitle("Add player");

        // getting dialog layout
        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

        // creating text field and combo box
        TextField name = new TextField();
        name.setPromptText("Name");

        ComboBox<PlayerMarker.Color> colors = new ComboBox<PlayerMarker.Color>(FXCollections.observableList(availableColors));
        colors.getSelectionModel().selectFirst();

        // if the result is not null then edit the exiting result
        if (isEditMode) {
            name.setText(registeredPlayerItem.getData().getPlayer().getName());
            colors.getSelectionModel().select(registeredPlayerItem.getData().color);
            if (!availableColors.contains(registeredPlayerItem.getData().getColor()))
                availableColors.add(registeredPlayerItem.getData().getColor());
        }

        // adding text field and combo box to dialog layout
        pane.setContent(new VBox(12, name, colors));

        // adding validation to apply button. i.e. the dialog will not accept the result if the name is empty
        pane.lookupButton(ButtonType.APPLY).addEventFilter(ActionEvent.ACTION, actionEvent -> {
            if (name.getText().isBlank() || name.getText().isEmpty()) {
                actionEvent.consume();
            }
        });

        // handling button clicks
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.APPLY) {
                PlayerMarker.Color pickedColor = colors.getValue();
                availableColors.remove(pickedColor);
                if (availableColors.isEmpty()) addButton.setDisable(true);
                return new DialogResult(pickedColor, new Player(name.getText().trim()));
            }
            if (buttonType == ButtonType.CANCEL) {
                dialog.close();
            }
            return null;
        });

        // if the data is not null then adding them to map
        Optional<DialogResult> resultOptional = dialog.showAndWait();
        resultOptional.ifPresent(dialogResult -> {

            if (isEditMode) {
                markers.put(registeredPlayerItem.getData().getPlayer(), new PlayerMarker(dialogResult.getColor()));
                registeredPlayerItem.updateData(dialogResult);
                return;
            }

            markers.put(dialogResult.getPlayer(), new PlayerMarker(dialogResult.getColor()));

            // otherwise create new item
            RegisteredPlayerItem playerItem = new RegisteredPlayerItem(dialogResult);
            playerItem.setOnEditClick(actionEvent -> {
                // on edit click, open dialog in edit mode
                startEditDialog(playerItem);
            });
            playerItem.setOnRemoveClick(actionEvent -> {
                // on remove click, remove the specific item from map, availableColors, and view
                markers.remove(dialogResult.getPlayer());
                availableColors.add(dialogResult.getColor());
                players.getChildren().remove(playerItem);

                // setting visibility true in case it can be false
                addButton.setDisable(false);

                startButton.setDisable(markers.size() < 2);
            });
            startButton.setDisable(markers.size() < 2);
            // display the view
            players.getChildren().add(playerItem);
        });
    }

    static class DialogResult {
        private final PlayerMarker.Color color;
        private final Player player;

        public DialogResult(PlayerMarker.Color color, Player player) {
            this.color = color;
            this.player = player;
        }

        public PlayerMarker.Color getColor() {
            return color;
        }

        public Player getPlayer() {
            return player;
        }
    }
}
