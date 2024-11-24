package edu.sdccd.cisc190.players;

import edu.sdccd.cisc190.players.bots.Bot;

public class HumanPlayer {
    private static HumanPlayer instance;
    private String username;
    private Integer money;
    private HumanPlayer() {}


    public static HumanPlayer getInstance() {

        if (instance == null) {
            instance = new HumanPlayer();
        }
        return instance;
    }

    // Getters and Setters for username and email

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.money = 200;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

}
