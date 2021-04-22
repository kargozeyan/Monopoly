package monopoly.game.board;

import monopoly.game.board.cell.*;

public class Board {
    private Cell[] cells;

    public Board() {
        cells = new Cell[]{
                new CornerCell("Go"),
                new Property("Vine Street", 0, ColorGroup.BROWN),
                CC_Cell.Chest(),
                new Property("Coventry Street", 0, ColorGroup.BROWN),
                new Tax("Income Tax", 100),
                new Station("Marylebone Station", 0),
                new Property("Leicester square", 0, ColorGroup.BLUE_LIGHT),
                CC_Cell.Chance(),
                new Property("Bow street", 0, ColorGroup.BLUE_LIGHT),
                new Property("Whitechapel road", 0, ColorGroup.BLUE_LIGHT),
                new CornerCell("Jail"),
                new Property("The angel islington", 0, ColorGroup.PINK),
                Utility.Electricity(),
                new Property("Trafalgar square", 0, ColorGroup.PINK),
                new Property("Northumrld avenue", 0, ColorGroup.PINK),
                new Station("Fenchurch st. station", 0),
                new Property("M'orough street", 0, ColorGroup.ORANGE),
                CC_Cell.Chest(),
                new Property("Fleet street", 0, ColorGroup.ORANGE),
                new Property("Old kent road", 0, ColorGroup.ORANGE),
                new CornerCell("Free Parking"),
                new Property("Whiteall", 0, ColorGroup.RED),
                CC_Cell.Chance(),
                new Property("Pentonville road", 0, ColorGroup.RED),
                new Property("Pall Mall", 0, ColorGroup.RED),
                new Station("Kings cross station", 0),
                new Property("BOND STREET", 0, ColorGroup.YELLOW),
                new Property("STRAND", 0, ColorGroup.YELLOW),
                Utility.Water(),
                new Property("Regent street", 0, ColorGroup.YELLOW),
                new CornerCell("go to jail"),
                new Property("Euston road", 0, ColorGroup.GREEN),
                new Property("Piccadilly", 0, ColorGroup.GREEN),
                CC_Cell.Chest(),
                new Property("Oxford Street", 0, ColorGroup.GREEN),
                new Station("Liverpool st. station", 0),
                CC_Cell.Chance(),
                new Property("Park lane", 0, ColorGroup.BLUE_DARK),
                new Tax("Super tax", 200),
                new Property("Mayfair", 0, ColorGroup.BLUE_DARK),
        };
    }

    public Cell[] getCells() {
        return cells;
    }
}
