package monopoly.game.board;

import monopoly.game.board.cell.*;

import java.util.ArrayList;

public class Board {
    private Cell[] cells;

    public Board() {
        cells = new Cell[]{
                CornerCell.GO,
                new Property("Mediterranean Avenue", 60, ColorGroup.BROWN, new int[]{2, 10, 30, 90, 160, 250}),
                CC_Cell.CHEST,
                new Property("Baltic Avenue", 60, ColorGroup.BROWN, new int[]{4, 20, 60, 180, 320, 450}),

                new Tax("Income Tax", 100),
                new Station("Reading Railroad", 200),

                new Property("Oriental Avenue", 100, ColorGroup.BLUE_LIGHT, new int[]{6, 30, 90, 270, 400, 550}),
                CC_Cell.CHANCE,
                new Property("Vermont Avenue", 100, ColorGroup.BLUE_LIGHT, new int[]{6, 30, 90, 270, 400, 550}),
                new Property("Connecticut Avenue", 120, ColorGroup.BLUE_LIGHT, new int[]{8, 40, 100, 300, 450, 600}),

                CornerCell.JAIL,

                new Property("St. Charles Place", 140, ColorGroup.PINK, new int[]{10, 50, 150, 450, 625, 750}),
                Utility.ELECTRICITY,
                new Property("States Avenue", 150, ColorGroup.PINK, new int[]{10, 50, 150, 450, 625, 750}),
                new Property("Virginia Avenue", 160, ColorGroup.PINK, new int[]{12, 60, 180, 500, 700, 900}),

                new Station("Pennsylvania Railroad", 200),

                new Property("St. James Place", 180, ColorGroup.ORANGE, new int[]{14, 70, 200, 550, 750, 950}),
                CC_Cell.CHEST,
                new Property("Tennessee Avenue", 180, ColorGroup.ORANGE, new int[]{14, 70, 200, 550, 750, 950}),
                new Property("New York Avenue", 200, ColorGroup.ORANGE, new int[]{16, 80, 220, 600, 800, 1000}),

                CornerCell.FREE_PARKING,

                new Property("Kentucky Avenue", 220, ColorGroup.RED, new int[]{18, 90, 250, 700, 875, 1050}),
                CC_Cell.CHANCE,
                new Property("Indiana Avenue", 220, ColorGroup.RED, new int[]{18, 90, 250, 700, 875, 1050}),
                new Property("Illinois Avenue", 240, ColorGroup.RED, new int[]{20, 100, 300, 750, 925, 1100}),

                new Station("B. & O. Railroad", 200),

                new Property("Atlantic Avenue", 260, ColorGroup.YELLOW, new int[]{22, 110, 330, 800, 975, 1150}),
                new Property("Ventnor Avenue", 260, ColorGroup.YELLOW, new int[]{22, 110, 330, 800, 975, 1150}),
                Utility.WATER,
                new Property("Marvin Gardens", 280, ColorGroup.YELLOW, new int[]{24, 120, 360, 850, 1025, 1200}),

                CornerCell.GO_JAIL,

                new Property("Pacific Avenue", 300, ColorGroup.GREEN, new int[]{26, 130, 390, 900, 1100, 1275}),
                new Property("North Carolina Avenue", 300, ColorGroup.GREEN, new int[]{26, 130, 390, 900, 1100, 1275}),
                CC_Cell.CHEST,
                new Property("Pennsylvania Avenue", 320, ColorGroup.GREEN, new int[]{28, 150, 450, 1000, 1200, 1400}),

                new Station("Short Line", 200),
                CC_Cell.CHANCE,

                new Property("Park Place", 350, ColorGroup.BLUE_DARK, new int[]{35, 175, 500, 1100, 1300, 1500}),
                new Tax("Luxury Tax", 200),
                new Property("Boardwalk", 400, ColorGroup.BLUE_DARK, new int[]{50, 200, 600, 1400, 1700, 2000}),
        };
    }

    public Cell[] getCells() {
        return cells;
    }

    public ArrayList<Property> getProperties(ColorGroup colorGroup) {
        ArrayList<Property> properties = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell instanceof Property && ((Property) cell).getColorGroup() == colorGroup) {
                properties.add((Property) cell);
            }
        }

        return properties;
    }

    public int getJailIndex() {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == CornerCell.JAIL) {
                return i;
            }
        }

        return -1;
    }
}
