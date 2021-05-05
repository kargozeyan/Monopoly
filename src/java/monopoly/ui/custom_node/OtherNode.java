package monopoly.ui.custom_node;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import monopoly.game.board.cell.Cell;
import monopoly.game.board.cell.PricedCell;
import monopoly.game.board.cell.Tax;
import monopoly.game.board.cell.Utility;
import monopoly.ui.utils.FXUtils;

public class OtherNode extends BaseNode {
    @FXML
    private ImageView img;

    @FXML
    private Text name;

    @FXML
    private Text price;

    public OtherNode(Cell cell) {
        super("node_other.fxml", cell);

        setName(cell.getName());

        if (cell instanceof PricedCell) {
            setPrice(((PricedCell) cell).getPrice());
            if (cell instanceof Utility) {
                setImg(cell == Utility.ELECTRICITY ? "bulb.png" : "water.png");
            } else {
                setImg("train.png");
            }
        } else {
            setPrice(((Tax) cell).getTax());
            setImg("tax_" + ((Tax) cell).getTax() + ".png");
        }
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
