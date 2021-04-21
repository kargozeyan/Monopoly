package monopoly.ui.custom_node;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class TaxNode extends BaseNode {
    @FXML
    private ImageView img;

    @FXML
    private Text name;

    @FXML
    private Text price;

    public TaxNode() throws IOException {
        super("node_tax.fxml");
    }
}
