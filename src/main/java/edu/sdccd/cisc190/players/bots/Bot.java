package edu.sdccd.cisc190.players.bots;

import edu.sdccd.cisc190.players.HumanPlayer;

import java.util.ArrayList;

public abstract class Bot {
    public  String name;
    public  int money;
    public  double luck;
    public  double aura;

    public String getName() {
        return name;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void setMoney(Integer money) { this.money = money; }

}
