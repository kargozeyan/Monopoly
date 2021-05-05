package monopoly.ui.scene_controllers.game;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.Optional;

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
}
