package edu.sdccd.cisc190.players.bots;

public class HondaBoyz extends Bot {
    private static HondaBoyz instance;

    public HondaBoyz() {
        this.name = "HondaBoyz";
        this.money = 100;
        this.aura = 1.0;
        this.luck = 0.06;
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
