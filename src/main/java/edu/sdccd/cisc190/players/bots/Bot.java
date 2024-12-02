package edu.sdccd.cisc190.players.bots;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Bot {
    private String name;
    private IntegerProperty money = new SimpleIntegerProperty();
    private double luck;
    private double aura;

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