package monopoly.ui.custom_node;

import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Arrays;

public class PlayerMarker extends Circle {
    public static final int RADIUS = 12;
    private final TranslateTransition transition = new TranslateTransition(Duration.seconds(.5), this);
    private double marginX;
    private double marginY;

    private double x, y;

    public PlayerMarker(Color color) {
        super(RADIUS, javafx.scene.paint.Color.valueOf(color.getHexColor()));
    }

    public void setPosition(int pos, double x, double y) {
        marginX = pos % 2 == 0 ? -8 - RADIUS : 8 + RADIUS;
        marginY = pos >= 2 ? -8 - RADIUS : 8 + RADIUS;
        this.x = (x + marginX);
        this.y = (y + marginY);

        setLayoutX(this.x);
        setLayoutY(this.y);
    }

    public void moveTo(double[] p) {
        setLayoutX(p[0] + marginX);
        setLayoutY(p[1] + marginY);
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
