package edu.sdccd.cisc190.players.bots;

/**
 * Professor Huang is a bot that will be playing in the background
 * instantiate a new instance of Professor Huang to implement in the application
 * High aura and solid luck attributes = greater potential for winning
 */
public class ProfessorHuang extends Bot {
    private static final ProfessorHuang instance = new ProfessorHuang();

    private ProfessorHuang() {
        super("Professor Huang", 1000, 0.95, 0.6); // Initial money, luck, and aura values
    }

    public static ProfessorHuang getInstance() {
        return instance;
    }

}
