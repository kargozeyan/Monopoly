package monopoly.ui.custom_node;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import monopoly.util.Function;

public class Die extends Canvas {
    private static final String RED = "#cb0512";
    private static final String WHITE = "#e6e6e6";

    private static final int DOT_RADIUS = 6;
    private static final int DOT_MARGIN = 8;

    private String backgroundColor;
    private String dotColor;

    private GraphicsContext graphics;
    private DieAnimation animation = new DieAnimation();

    public Die(boolean isBackgroundRed) {
        super(64, 64);

        backgroundColor = isBackgroundRed ? RED : WHITE;
        dotColor = isBackgroundRed ? WHITE : RED;

        graphics = getGraphicsContext2D();
    }

    private void drawBackground() {
        graphics.setFill(Color.valueOf(backgroundColor));
        graphics.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
    }

    private void drawDots(int number) {
        graphics.setFill(Color.valueOf(dotColor));
        if (number % 2 == 1) {
            graphics.fillOval(getWidth() / 2 - DOT_RADIUS, getHeight() / 2 - DOT_RADIUS, DOT_RADIUS * 2, DOT_RADIUS * 2);
        }
        if (number >= 2) {
            graphics.fillOval(0 + DOT_MARGIN, 0 + DOT_MARGIN, DOT_RADIUS * 2, DOT_RADIUS * 2);
            graphics.fillOval(getWidth() - DOT_RADIUS * 2 - DOT_MARGIN, getHeight() - DOT_RADIUS * 2 - DOT_MARGIN, DOT_RADIUS * 2, DOT_RADIUS * 2);
        }
        if (number >= 4) {
            graphics.fillOval(getWidth() - DOT_RADIUS * 2 - DOT_MARGIN, 0 + DOT_MARGIN, DOT_RADIUS * 2, DOT_RADIUS * 2);
            graphics.fillOval(0 + DOT_MARGIN, getHeight() - DOT_RADIUS * 2 - DOT_MARGIN, DOT_RADIUS * 2, DOT_RADIUS * 2);
        }
        if (number == 6) {
            graphics.fillOval(0 + DOT_MARGIN, getHeight() / 2 - DOT_RADIUS, DOT_RADIUS * 2, DOT_RADIUS * 2);
            graphics.fillOval(getWidth() - DOT_RADIUS * 2 - DOT_MARGIN, getHeight() / 2 - DOT_RADIUS, DOT_RADIUS * 2, DOT_RADIUS * 2);
        }
    }

    public void setNumber(int number) {
        animation.start(() -> {
            drawBackground();
            drawDots(number);
        });
    }

    public void setNumber(int number, Function onOver) {
        animation.start(() -> {
            drawBackground();
            drawDots(number);
            onOver.invoke();
        });
    }
    public void animate() {
        animation.start();
    }

    private class DieAnimation extends AnimationTimer {

        private static final int REPETITONS = 60;
        private Function onAnimationEnd = () -> {};
        private int counter = 0;


        public void start(Function onAnimationEnd) {
            this.onAnimationEnd = onAnimationEnd;
            super.start();
        }

        @Override
        public void handle(long l) {
            drawBackground();
            drawDots((int) (6 * Math.random() + 1));
            counter++;
            if (counter >= 60) {
                counter = 0;
                onAnimationEnd.invoke();
                stop();
            }
        }

    }
}
