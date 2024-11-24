package edu.sdccd.cisc190.players.bots;

public class HondaBoyz extends Bot {
    private static HondaBoyz instance;

    public HondaBoyz() {
        name = "HondaBoyz";
        money = 100;
        aura = 1.0;
        luck = 0.06;
    }

    public static HondaBoyz getInstance() {

        if (instance == null) {
            instance = new HondaBoyz();
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
