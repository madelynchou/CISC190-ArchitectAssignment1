package edu.sdccd.cisc190.machines;

public class DiamondDash extends Slot {
    public DiamondDash() {
        super(new String[]{"💍", "💠", "💎"}, 1000, 15, 2);
    }

    //the user will only lose have the bet they place
    @Override
    public int calculatePayout(int moneyAmount, String[] spunRow, int bet) {
        int winningCondition = evaluateWinCondition(spunRow);
        return switch (winningCondition) {
            case 0 -> // No match
                    (int) (moneyAmount - (Math.floor(bet * 0.5)));
            case 3 -> // Three-symbol match
                    (int) (moneyAmount + Math.floor(bet * returnAmt));
            default -> moneyAmount;
        };
    }
}