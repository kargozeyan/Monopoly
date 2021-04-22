package monopoly.ui.custom_node;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import monopoly.game.board.cell.Property;

import java.io.IOException;

public class PropertyNode extends BaseNode {
    @FXML
    private Text price;
    @FXML
    private Text name;

    @FXML
    private Rectangle colorGroup;

    public PropertyNode() {
        super("node_property.fxml");
    }

    public PropertyNode(Property property) {
        this();
        setName(property.getName());
        setPrice(property.getPrice());
        colorGroup.setFill(Color.valueOf(property.getColorGroup().getHexColor()));
    }

    public void setName(String name) {
        this.name.setText(name.toUpperCase());
    }

    public void setPrice(int price) {
        this.price.setText(price + "$");
    }


}
