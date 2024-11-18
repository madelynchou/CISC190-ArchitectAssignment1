package edu.sdccd.cisc190.players.bots;

import edu.sdccd.cisc190.players.HumanPlayer;

public class Chase extends Bot {
    private static Chase instance;

    public Chase() {
        this.name = "Chase";
        this.money = 100;
        this.aura = 0.3;
        this.luck = 0.09;
    }

    public static Chase getInstance() {

        if (instance == null) {
            instance = new Chase();
        }
        return instance;
    }

    // Getters and Setters for username and email

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
        this.money = 100;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }


}
