package edu.sdccd.cisc190.players.bots;

public class MrBrooks extends Bot {
    private static MrBrooks instance;

    public MrBrooks() {
        name = "Mr.Brooks";
        money = 100;
        aura = 0.5;
        luck = 0.21;
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
