package monopoly.ui.custom_node;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

// CC stands for Chance/Chest
public class CCNode extends BaseNode {
    @FXML
    private ImageView img;

    @FXML
    private Text type;

    public CCNode() throws IOException {
        super("node_cc.fxml", Alignment.RIGHT);
    }

}
