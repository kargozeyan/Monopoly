package monopoly.ui.custom_node;

public enum Alignment {
    BOTTOM(0),
    LEFT(90),
    RIGHT(270),
    TOP(180);

    private final double degree;
    Alignment(double degree) {
        this.degree = degree;
    }

    public double getDegree() {
        return degree;
    }
}
