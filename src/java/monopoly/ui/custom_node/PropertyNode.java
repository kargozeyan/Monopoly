package monopoly.ui.custom_node;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class PropertyNode extends BaseNode {
    @FXML
    private Text price;
    @FXML
    private Text name;

    public PropertyNode() throws IOException {
        super("node_property.fxml");
    }

    public PropertyNode(String name, int price) throws IOException {
        this();
        setName(name);
        setPrice(price);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPrice(int price) {
        this.price.setText(price + "$");
    }


}
