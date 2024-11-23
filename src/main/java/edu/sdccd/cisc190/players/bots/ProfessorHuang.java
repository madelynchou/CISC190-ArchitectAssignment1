package edu.sdccd.cisc190.players.bots;

public class ProfessorHuang extends Bot {
    private static ProfessorHuang instance;

    public ProfessorHuang() {
        this.name = "Professor Huang (The G.O.A.T)";
        this.money = 100;
        this.aura = 0.4;
        this.luck = 0.8;
    }
    public static ProfessorHuang getInstance() {

        if (instance == null) {
            instance = new ProfessorHuang();
        }
        return instance;
    }
}
