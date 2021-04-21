package monopoly.ui.custom_node;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import monopoly.utils.FXUtils;

import java.io.IOException;

class BaseNode extends VBox {
    private Alignment alignment = Alignment.BOTTOM;

    public BaseNode(String fxml) throws IOException {
        this(fxml, Alignment.BOTTOM);
    }

    public BaseNode(String fxml, Alignment alignment) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        System.out.println(FXUtils.getFXML(fxml).toExternalForm());
        loader.setLocation(FXUtils.getFXML(fxml));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        alignTo(alignment);
    }


    public void alignTo(Alignment alignment) {
        double currentDegree = this.alignment == null ? 0 : this.alignment.getDegree();
        if (Math.abs((currentDegree - alignment.getDegree())) / 90 % 2 == 1) {
            // if alignment is changed by odd multiple of 90 degrees (e.g. +-90 or +-270...), then swap its width and height
            double tmp = getPrefWidth();
            setPrefWidth(getPrefHeight());
            setPrefHeight(tmp);
        }

        setRotate(alignment.getDegree());
        this.alignment = alignment;
    }
}
