package edu.sdccd.cisc190.players;

import edu.sdccd.cisc190.players.bots.Bot;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HumanPlayer {
    private static HumanPlayer instance;
    private String username;
    private final IntegerProperty money = new SimpleIntegerProperty(this, "money", 1000);

    private HumanPlayer() {}



    public static HumanPlayer getInstance() {

        if (instance == null) {
            instance = new HumanPlayer();
        }
        return instance;
    }

    // Getters and Setters for username and email


    public void setUsername(String username) {
        this.username = username;
    }

    public final void setMoney(int value) {
        money.set(value);
    }

    public final int getMoney() {
        return money.get();
    }

    public IntegerProperty moneyProperty() {
        return money;
    }

    public String getName() {
        return username;
    }
}
