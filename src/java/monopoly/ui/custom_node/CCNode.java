package monopoly.ui.custom_node;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import monopoly.game.board.cell.CC_Cell;
import monopoly.utils.FXUtils;

import java.net.URL;
import java.util.ResourceBundle;

// CC stands for Chance/Chest
public class CCNode extends BaseNode {
    @FXML
    private ImageView img;

    @FXML
    private Text type;

    public CCNode(CC_Cell cell) {
        super("node_cc.fxml");

        setType(cell.getName());

        if (cell.getName().equals(CC_Cell.NAME_CHANCE)) {
            setImg("chance.png");
        } else if (cell.getName().equals(CC_Cell.NAME_CHEST)) { ;
            setImg("community_chest.png");
        }
    }

    public void setType(String type) {
        this.type.setText(type.toUpperCase());
    }

    public void setImg(String img) {
        this.img.setImage(FXUtils.getImage(img));
    }
}
