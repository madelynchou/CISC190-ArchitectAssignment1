package edu.sdccd.cisc190.players.bots;

public class MrBrooks extends Bot {
    private static MrBrooks instance;

    public MrBrooks() {
        this.name = "Mr.Brooks";
        this.money = 100;
        this.aura = 0.5;
        this.luck = 0.21;
    }

    public static MrBrooks getInstance() {

        if (instance == null) {
            instance = new MrBrooks();
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
