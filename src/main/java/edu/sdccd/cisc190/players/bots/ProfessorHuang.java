package edu.sdccd.cisc190.players.bots;

public class ProfessorHuang extends Bot {
    private static ProfessorHuang instance;

    public ProfessorHuang() {
        this.name = "Professor Huang (The G.O.A.T)";
        this.money = 100;
        this.aura = 1;
        this.luck = 0.23;
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
