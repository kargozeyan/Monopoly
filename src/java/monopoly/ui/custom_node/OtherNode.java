package monopoly.ui.custom_node;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import monopoly.utils.FXUtils;

import java.io.IOException;

public class OtherNode extends BaseNode {
    @FXML
    private ImageView img;

    @FXML
    private Text name;

    @FXML
    private Text price;

    public OtherNode(String name, int price) {
        super("node_other.fxml");

        setName(name);
        setPrice(price);
    }
    public void setName(String name) {
        this.name.setText(name.toUpperCase());
    }

    public void setPrice(int price) {
        this.price.setText(price + "$");
    }

    public void setImg(String img) {
        this.img.setImage(FXUtils.getImage(img));
    }
}
