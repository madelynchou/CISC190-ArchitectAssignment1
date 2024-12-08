package edu.sdccd.cisc190.players.bots;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Create the behavior of the bots that will be playing in the background
 * Getters and setters to obtain the bots' name, money, luck, and aura to display and run their play
 */
public abstract class Bot {
    private final String name;
    private final IntegerProperty money = new SimpleIntegerProperty();
    private final double luck;
    private final double aura;

    public Bot(String name, int initialMoney, double luck, double aura) {
        this.name = name;
        this.money.set(initialMoney);
        this.luck = luck;
        this.aura = aura;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money.get();
    }

    public void setMoney(int value) {
        money.set(value);
    }

    public IntegerProperty moneyProperty() {
        return money;
    }

    public double getLuck() {
        return luck;
    }

    public double getAura() {
        return aura;
    }
}