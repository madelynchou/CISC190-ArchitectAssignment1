package edu.sdccd.cisc190;

public class HumanPlayer extends Bot {

    private static HumanPlayer instance;

    private String username;

    private Integer money;

    private HumanPlayer() { /* Private constructor for singleton pattern */ }


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
    }

    public Integer getMoney() {
        return this.money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

}
