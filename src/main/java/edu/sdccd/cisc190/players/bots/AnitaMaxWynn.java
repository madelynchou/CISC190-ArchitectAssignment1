package edu.sdccd.cisc190.players.bots;

public class AnitaMaxWynn extends Bot {
    private static AnitaMaxWynn instance;

    public AnitaMaxWynn() {
        this.name = "AnitaMaxWynn";
        this.money = 100;
        this.aura = 1.0;
        this.luck = 0.12;
    }

    public static AnitaMaxWynn getInstance() {

        if (instance == null) {
            instance = new AnitaMaxWynn();
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
