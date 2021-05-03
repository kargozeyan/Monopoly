package monopoly.ui.custom_node;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerMarker extends Circle {
    private static final int RADIUS = 24;

    public PlayerMarker(Color color) {
        super(RADIUS, javafx.scene.paint.Color.valueOf(color.getHexColor()));
    }

    public void moveTo(double x, double y) {

    }

    public enum Color {
        BLUE("#0f52ba"),
        RED("#ed2939"),
        YELLOW("#fce205"),
        GREEN("#2e8b57");
        private final String hexColor;

        Color(String hexColor) {
            this.hexColor = hexColor;
        }

        public String getHexColor() {
            return hexColor;
        }
    }
}
