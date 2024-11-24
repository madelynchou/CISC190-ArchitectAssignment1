package edu.sdccd.cisc190.players.bots;

import edu.sdccd.cisc190.players.HumanPlayer;

public class Chase extends Bot {
    private static Chase instance;

    public Chase() {
        name = "Chase";
        money = 100;
        aura = 0.3;
        luck = 0.09;
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
        name = username;
        money = 100;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        Bot.money = money;
    }


}
