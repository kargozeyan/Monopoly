package monopoly.ui.scene_controllers.register;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import monopoly.ui.custom_node.PlayerMarker;

class RegisteredPlayerItem extends StackPane {
    private final Circle color;
    private final Text name;

    private final Button edit = new Button("Edit");
    private final Button remove = new Button("Remove");

    private PlayerRegisterController.DialogResult data;

    RegisteredPlayerItem(PlayerRegisterController.DialogResult data) {
        this.data = data;

        setMinHeight(48);
        setMinWidth(600);

        color = new Circle(12, Color.valueOf(data.getColor().getHexColor()));
        name = new Text(data.getPlayer().getName());

//        edit.setMaxWidth(16);
        edit.setMaxHeight(16);
//
//        delete.setMaxWidth(16);
        remove.setMaxHeight(16);

        HBox infoBox = new HBox(8, color, name);
        infoBox.setAlignment(Pos.CENTER);
        Group infoPair = new Group(infoBox);

        HBox editBox = new HBox(8, edit, remove);
        infoBox.setAlignment(Pos.CENTER);
        Group editPair = new Group(editBox);

        setMargin(infoPair, new Insets(8));
        setMargin(editPair, new Insets(8));
        setMargin(name, new Insets(24, 0, 0 ,0));
        setAlignment(infoPair, Pos.CENTER_LEFT);
        setAlignment(editPair, Pos.CENTER_RIGHT);

        getChildren().addAll(infoPair, editPair);
    }

    void setOnEditClick(EventHandler<ActionEvent> handler) {
        edit.setOnAction(handler);
    }

    void setOnRemoveClick(EventHandler<ActionEvent> handler) {
        remove.setOnAction(handler);
    }

    PlayerRegisterController.DialogResult getData() {
        return data;
    }

    void updateData(PlayerRegisterController.DialogResult data) {
        this.data = data;
        this.name.setText(data.getPlayer().getName());
        this.color.setFill(Color.valueOf(data.getColor().getHexColor()));
    }
}
