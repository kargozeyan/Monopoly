package monopoly.ui.scene_controllers.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import monopoly.game.Player;
import monopoly.game.board.cell.PricedCell;

import java.util.*;

class DialogHelper {

    void displayMessage(String message) {
        Dialog<Object> dialog = new Dialog<>();
        dialog.setHeaderText(message);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.setResultConverter(buttonType -> null);
        dialog.show();
    }

    boolean askForApproval(String message) {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setHeaderText(message);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        dialog.setResultConverter(buttonType -> buttonType == ButtonType.YES);
        Optional<Boolean> result = dialog.showAndWait();

        return result.orElse(false);
    }

    Player selectPlayer(Player exclude, Collection<Player> players) {
        List<Player> playerList = new ArrayList<>();
        for (Player p: players) {
            if (p == exclude) continue;

            playerList.add(p);
        }

        ChoiceDialog<Player> dialog = new ChoiceDialog<Player>(playerList.get(0), playerList);
        dialog.setHeaderText("Select player");

        Optional<Player> res = dialog.showAndWait();
        return res.orElse(null);
    }

    void tradeDialog(Player p1, Player p2) {
        Dialog<TradeResult> dialog = new Dialog<TradeResult>();
        DialogPane dp = dialog.getDialogPane();
        dialog.setResizable(true);
        dialog.setWidth(600);
        dialog.setHeight(600);
        dp.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        VBox vBox = new VBox(12);
        vBox.setPadding(new Insets(16));
        Text text1 = new Text(p1.getName() + ":");
        text1.setStyle("-fx-font-size: 24");
        CustomSlider slider1 = new CustomSlider(p1.getBalance());
        vBox.getChildren().add(text1);
        vBox.getChildren().add(slider1);

        VBox p1Cells = new VBox(12);
        for (PricedCell c : p1.getOwedCells()) {
            p1Cells.getChildren().add(new CustomCheckBox(c));
        }
        vBox.getChildren().add(p1Cells);

        Text text2 = new Text(p2.getName()+":");
        text2.setStyle(text1.getStyle());

        CustomSlider slider2 = new CustomSlider(p2.getBalance());

        vBox.getChildren().add(text2);
        vBox.getChildren().add(slider2);
        VBox p2Cells = new VBox(12);
        for (PricedCell c : p2.getOwedCells()) {
            p2Cells.getChildren().add(new CustomCheckBox(c));
        }
        vBox.getChildren().add(p2Cells);
        dp.getChildren().add(vBox);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.APPLY) {
                List<PricedCell> toSend = new ArrayList<>();
                List<PricedCell> toReceive = new ArrayList<>();

                for (Node child : p1Cells.getChildren()) {
                    if (child instanceof CustomCheckBox && ((CustomCheckBox) child).isChecked()) {
                        toSend.add(((CustomCheckBox) child).getCell());
                    }
                }

                for (Node child : p2Cells.getChildren()) {
                    if (child instanceof CustomCheckBox && ((CustomCheckBox) child).isChecked()) {
                        toReceive.add(((CustomCheckBox) child).getCell());
                    }
                }

                return new TradeResult(toSend, slider1.getValue(), toReceive, slider2.getValue());
            }

            return null;
        });

        Optional<TradeResult> result = dialog.showAndWait();
        result.ifPresent(tradeResult -> {
            tradeResult.cellToSent.forEach(cell -> cell.setOwner(p2));
            tradeResult.cellToReceive.forEach(cell -> cell.setOwner(p1));
            p1.payMoney(tradeResult.moneyToSent);
            p2.receiveMoney(tradeResult.moneyToSent);

            p1.receiveMoney(tradeResult.moneyToReceive);
            p2.payMoney(tradeResult.moneyToReceive);
        });
    }

    private static class CustomCheckBox extends HBox {
        private final PricedCell cell;
        private final CheckBox checkBox = new CheckBox("");
        private final Text text = new Text();

        public CustomCheckBox(PricedCell cell) {
            this.cell = cell;
            setSpacing(8);
            setAlignment(Pos.CENTER_LEFT);
            text.setText(cell.getName());
            getChildren().addAll(checkBox, text);
        }

        public boolean isChecked() {
            return checkBox.isSelected();
        }

        public PricedCell getCell() {
            return cell;
        }
    }

    private static class CustomSlider extends HBox {
        private final Text text = new Text("0");
        private final Slider slider = new Slider();

        public CustomSlider(int max) {
            setSpacing(8);
            setAlignment(Pos.CENTER_LEFT);

            slider.setMin(0);
            slider.setMax(max);
            slider.setValue(0);
            slider.setMinWidth(300);

            slider.valueProperty().addListener((observableValue, oldVal, newVal) -> {
                slider.setValue(5 * Math.floor(newVal.floatValue() / 5));
                text.setText(String.valueOf((int) slider.getValue()));
            });

            getChildren().addAll(slider, text);
        }

        public int getValue() {
            return (int) slider.getValue();
        }
    }

    private static class TradeResult {
        private final List<PricedCell> cellToSent;
        private final int moneyToSent;
        private final List<PricedCell> cellToReceive;
        private final int moneyToReceive;

        public TradeResult(List<PricedCell> cellToSent, int moneyToSent, List<PricedCell> cellToReceive, int moneyToReceive) {
            this.cellToSent = cellToSent;
            this.moneyToSent = moneyToSent;
            this.cellToReceive = cellToReceive;
            this.moneyToReceive = moneyToReceive;
        }

        public List<PricedCell> getCellToSent() {
            return cellToSent;
        }

        public int getMoneyToSent() {
            return moneyToSent;
        }

        public List<PricedCell> getCellToReceive() {
            return cellToReceive;
        }

        public int getMoneyToReceive() {
            return moneyToReceive;
        }
    }
}
