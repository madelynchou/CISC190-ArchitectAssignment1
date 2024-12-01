package edu.sdccd.cisc190.players.bots;

public class ProfessorHuang extends Bot {
    private static final ProfessorHuang instance = new ProfessorHuang();

    private ProfessorHuang() {
        super("Professor Huang", 1000, 0.95, 0.6); // Initial money, luck, and aura values
    }

    public static ProfessorHuang getInstance() {
        return instance;
    }

}
