package edu.sdccd.cisc190.players.bots;

public class ProfessorHuang extends Bot {
    private static ProfessorHuang instance;

    public ProfessorHuang() {
        name = "Professor Huang (The G.O.A.T)";
        money = 100;
        aura = 1;
        luck = 0.23;
    }
    public static ProfessorHuang getInstance() {

        if (instance == null) {
            instance = new ProfessorHuang();
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
