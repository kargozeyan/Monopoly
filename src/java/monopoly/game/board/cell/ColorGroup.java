package monopoly.game.board.cell;

public enum ColorGroup {
    GREEN("#2dcc70"),
    BLUE_DARK("#3598db"),
    BROWN("#946038"),
    BLUE_LIGHT("#86caed"),
    PINK("#e6007e"),
    ORANGE("#f29200"),
    RED("#e84c3d"),
    YELLOW("#fdea11");

    private final String hexColor;

    ColorGroup(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getHexColor() {
        return hexColor;
    }
}
