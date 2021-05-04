package monopoly.game;

public class Player {
    private final String name;

    private int balance = 1500;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void receiveMoney(int money) {
        balance += money;
    }
}
