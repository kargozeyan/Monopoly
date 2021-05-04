package monopoly.game.board;

import monopoly.game.board.cell.*;

public class Board {
    private Cell[] cells;

    public Board() {
        cells = new Cell[]{
                new Property("Mediterranean Avenue", 60, ColorGroup.BROWN, new int[]{2, 4, 10, 30, 90, 160, 250}),
                new Property("Baltic Avenue", 60, ColorGroup.BROWN, new int[]{4, 8, 20, 60, 180, 320, 450}),
                new Property("Arctic Avenue", 80, ColorGroup.BROWN, new int[]{5, 10, 30, 80, 240, 360, 450}),
                new Property("Massachusetts Avenue", 100, ColorGroup.PURPLE, new int[]{2, 4, 10, 30, 90, 160, 250}),
                new Property("Oriental Avenue", 100, ColorGroup.BLUE_LIGHT, new int[]{6, 12, 30, 90, 270, 400, 550}),
                new Property("Vermont Avenue", 100, ColorGroup.BLUE_LIGHT, new int[]{6, 12, 30, 90, 270, 400, 550}),
                new Property("Connecticut Avenue", 120, ColorGroup.BLUE_LIGHT, new int[]{8, 16, 40, 100, 300, 450, 600}),
                new Property("Maryland Avenue", 140, ColorGroup.PINK, new int[]{10, 20, 50, 150, 450, 625, 750}),
                new Property("St. Charles Place", 140, ColorGroup.PINK, new int[]{10, 20, 50, 150, 450, 625, 750}),
                new Property("States Avenue", 150, ColorGroup.PINK, new int[]{10, 20, 50, 150, 450, 625, 750}),
                new Property("Virginia Avenue", 160, ColorGroup.PINK, new int[]{12, 24, 60, 180, 500, 700, 900}),
                new Property("St. James Place", 180, ColorGroup.ORANGE, new int[]{14, 28, 70, 200, 550, 750, 950}),
                new Property("Tennessee Avenue", 180, ColorGroup.ORANGE, new int[]{14, 28, 70, 200, 550, 750, 950}),
                new Property("New York Avenue", 200, ColorGroup.ORANGE, new int[]{16, 32, 80, 220, 600, 800, 1000}),
                new Property("New Jersey Avenue", 200, ColorGroup.ORANGE, new int[]{16, 32, 80, 220, 600, 800, 1000}),
                new Property("Kentucky Avenue", 220, ColorGroup.RED, new int[]{18, 36, 90, 250, 700, 875, 1050}),
                new Property("Indiana Avenue", 220, ColorGroup.RED, new int[]{18, 36, 90, 250, 700, 875, 1050}),
                new Property("Illinois Avenue", 240, ColorGroup.RED, new int[]{20, 40, 100, 300, 750, 925, 1100}),
                new Property("Michigan Avenue", 240, ColorGroup.RED, new int[]{20, 40, 100, 300, 750, 925, 1100}),
                new Property("Atlantic Avenue", 260, ColorGroup.YELLOW, new int[]{22, 44, 110, 330, 800, 975, 1150}),
                new Property("Ventnor Avenue", 260, ColorGroup.YELLOW, new int[]{22, 44, 110, 330, 800, 975, 1150}),
                new Property("Marvin Gardens", 280, ColorGroup.YELLOW, new int[]{24, 48, 120, 360, 850, 1025, 1200}),
                new Property("California Avenue", 280, ColorGroup.YELLOW, new int[]{24, 48, 120, 360, 850, 1025, 1200}),
                new Property("Pacific Avenue", 300, ColorGroup.GREEN, new int[]{26, 52, 130, 390, 900, 1100, 1275}),
                new Property("South Carolina Avenue", 300, ColorGroup.GREEN, new int[]{26, 52, 130, 390, 900, 1100, 1275}),
                new Property("North Carolina Avenue", 300, ColorGroup.GREEN, new int[]{26, 52, 130, 390, 900, 1100, 1275}),
                new Property("Pennsylvania Avenue", 320, ColorGroup.GREEN, new int[]{28, 56, 150, 450, 1000, 1200, 1400}),
                new Property("Florida Avenue", 350, ColorGroup.BLUE_DARK, new int[]{35, 70, 175, 500, 1100, 1300, 1500}),
                new Property("Park Place", 350, ColorGroup.BLUE_DARK, new int[]{35, 70, 175, 500, 1100, 1300, 1500}),
                new Property("Boardwalk", 400, ColorGroup.BLUE_DARK, new int[]{50, 100, 200, 600, 1400, 1700, 2000}),
//                new Property("Reading Railroad", 200, ColorGroup.BLACK, new int[]{25, 50, 100, 200}),
//                new Property("Pennsylvania Railroad", 200, ColorGroup.BLACK, new int[]{25, 50, 100, 200}),
//                new Property("B. & O. Railroad", 200, ColorGroup.BLACK, new int[]{25, 50, 100, 200}),
//                new Property("Short Line", 200, ColorGroup.BLACK, new int[]{25, 50, 100, 200}),
//                new Property("Electric Company", 150, ColorGroup.BROWN, new int[]{2, 4, 10, 30, 90, 160, 250}),
//                new Property("Water Works", 60, ColorGroup.BROWN, new int[]{2, 4, 10, 30, 90, 160, 250}),
//                new Property("Gas Company", 60, ColorGroup.BROWN, new int[]{2, 4, 10, 30, 90, 160, 250}),
//                new Property("Communications Company", 60, ColorGroup.BROWN, new int[]{2, 4, 10, 30, 90, 160, 250}),
        };
    }

    public Cell[] getCells() {
        return cells;
    }
}
