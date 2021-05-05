package monopoly.game;

import monopoly.game.board.cell.*;
import monopoly.game.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;

    private int balance = 1500;

    private int position = 0;

    private IGame game;
    private Dice dice;

    private int turnCounter = 0;

    private boolean isRollingAgain = false;

    private int jailFreeCards = 0;

    private int skippingTurns = 0;

    public Player(String name) {
        this.name = name;
    }

    public void initGame(IGame game) {
        this.game = game;
    }

    public void initDice(Dice dice) {
        this.dice = dice;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void receiveMoney(int money) {
        setBalance(balance + money);
    }

    public void getJailFreeCard() {
        jailFreeCards++;
    }

    public void useJailFreeCard() {
        jailFreeCards--;
    }

    private void setBalance(int balance) {
        this.balance = balance;
        game.updateBalances();
    }

    public void rollDice() {
        turnCounter++;

        int[] result = dice.roll();
        if (turnCounter == 3 && result[0] == result[1]) {
            goToJail();
            return;
        }
        position += result[0] + result[1];

        if (position >= game.getBoard().getCells().length) {
            position -= game.getBoard().getCells().length;
            receiveMoney(200);
        }

        isRollingAgain = result[0] == result[1];

        Cell cell = game.getBoard().getCells()[position];
        game.showDiceAndMove(() -> {
            handleCellLand(cell);
        }, result);
    }

    public void goToJail() {
        if (jailFreeCards > 0) {
            useJailFreeCard();
            resetTurnCounter();
            return;
        }

        position = game.getBoard().getJailIndex();
        isRollingAgain = false;
        skippingTurns = 3;
        resetTurnCounter();
        game.movePlayer(this);
    }

    public boolean canUpgrade(Property property) {
        if (hasAllColorGroupProperties(property.getColorGroup())) {
            int lvl = property.getLvl();

            for (Property p : game.getBoard().getProperties(property.getColorGroup())) {
                if (p.getLvl() != lvl) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public boolean isSkipping() {
        return skippingTurns > 0;
    }

    public void skip() {
        skippingTurns--;
    }

    private void handleCellLand(Cell cell) {
        if (cell instanceof CC_Cell) {
            Card card = game.takeCard(cell);
            game.showMessage(card.getMessage());
            card.open(this);
        } else if (cell instanceof Tax) {
            game.showMessage("You have to pay tax: " + ((Tax) cell).getTax());
            payMoney(((Tax) cell).getTax());
        } else if (cell == CornerCell.GO_JAIL) {
            goToJail();
        }
    }

    public boolean hasAllColorGroupProperties(ColorGroup colorGroup) {
        for (Property property : game.getBoard().getProperties(colorGroup)) {
            if (!property.hasOwner() || property.getOwner() != this) {
                return false;
            }
        }

        return true;
    }

    public void resetTurnCounter() {
        turnCounter = 0;
    }

    public void payMoney(int money) {
        if (money > balance) {
            loseGame(null);
            return;
        }

        setBalance(balance - money);
    }

    public int getNumberOfStations() {
        int number = 0;
        for (Cell cell : game.getBoard().getCells()) {
            if (cell instanceof Station && ((Station) cell).getOwner() == this) {
                number++;
            }
        }
        return number;
    }

    public int getNumberOfUtilities() {
        int number = 0;
        for (Cell cell : game.getBoard().getCells()) {
            if (cell instanceof Utility && ((Utility) cell).getOwner() == this) {
                number++;
            }
        }

        return number;
    }

    public int lastRoll() {
        return dice.getLastRoll()[0] + dice.getLastRoll()[1];
    }

    public void buy(PricedCell cell) {
        if (cell.getPrice() >= balance) {
            return;
        }
        setBalance(balance - cell.getPrice());
        cell.setOwner(this);

    }

    public void upgradeProperty(Property property) {
        if (property.getPrice() / 2 >= balance) {
            // no enough money
            return;
        }

        setBalance(balance - property.getPrice() / 2);
        property.upgrade();
    }

    public void sell(PricedCell cell) {
        cell.setOwner(null);

        receiveMoney(cell.getPrice() / 2);
    }

    public void payRentFor(PricedCell cell) {
        if (cell.getRent() >= balance) {
            loseGame(cell.getOwner());
            return;
        }

        cell.getOwner().receiveMoney(cell.getRent());
        setBalance(balance - cell.getRent());
    }

    public boolean isRollingAgain() {
        return isRollingAgain;
    }

    public void loseGame(Player to) {
        game.gameLost(this);
        for (Cell cell : game.getBoard().getCells()) {
            if (cell instanceof PricedCell && ((PricedCell) cell).getOwner() == this) {
                ((PricedCell) cell).setOwner(to);
            }
        }
    }

    public List<PricedCell> getOwedCells() {
        List<PricedCell> owedCells = new ArrayList<>();

        for (Cell cell: game.getBoard().getCells()) {
            if (cell instanceof PricedCell && ((PricedCell) cell).getOwner() == this) {
                owedCells.add((PricedCell) cell);
            }
        }

        return owedCells;
    }

    public void win() {
        game.announceWinner(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
