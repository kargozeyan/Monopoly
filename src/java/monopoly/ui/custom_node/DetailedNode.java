package monopoly.ui.custom_node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import monopoly.game.board.cell.*;

public class DetailedNode extends StackPane {
    public static final double WIDTH = BaseNode.WIDTH * 2;
    public static final double HEIGHT = BaseNode.HEIGHT * 2;

    private final Rectangle color = new Rectangle(WIDTH, 64);
    private final Text title = new Text("Park Lane");
    private final Text data = new Text("BBB");

    public DetailedNode() {
        super();
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        setStyle("-fx-background-color: #ededed");

        setAlignment(color, Pos.TOP_CENTER);

        title.setTextAlignment(TextAlignment.CENTER);
        title.setStyle("-fx-font-size: 22");
        title.setWrappingWidth(WIDTH - 32);
        setAlignment(title, Pos.TOP_CENTER);
        setMargin(title, new Insets(84, 0, 0, 0));

        data.setTextAlignment(TextAlignment.CENTER);
        data.setStyle("-fx-font-size: 14");
        data.setWrappingWidth(WIDTH - 32);
        setAlignment(data, Pos.TOP_CENTER);
        setMargin(data, new Insets(164, 0, 0, 0));
        getChildren().addAll(color, title, data);
    }

    public void setData(Cell cell) {
        if (cell instanceof CornerCell) return;
        title.setText(cell.getName().toUpperCase());
        if (!(cell instanceof PricedCell)) {
            if (cell instanceof CC_Cell) data.setText(((CC_Cell) cell).getDesc());
            if (cell instanceof Tax) data.setText(((Tax) cell).getDesc());
        } else {
            PricedCell pricedCell = (PricedCell) cell;
            data.setText(pricedCell.getData());
        }
        if (cell instanceof Property) {
            color.setFill(Color.valueOf(((Property) cell).getColorGroup().getHexColor()));
        } else {
            color.setFill(Color.BLACK);
        }
    }
}
