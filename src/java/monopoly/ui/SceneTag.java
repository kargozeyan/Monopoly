package monopoly.ui;

public enum SceneTag {
    HOME("scene_home.fxml", 512, 512),
    REGISTER("scene_player_registration.fxml", 512, 512),
    GAME("scene_game.fxml", 1600, 900);

    private final String fxml;
    private final int minWidth;
    private final int minHeight;
    SceneTag(String fxml, int minWidth, int minHeight) {
        this.fxml = fxml;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
    }

    public String getFxml() {
        return fxml;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }
}
