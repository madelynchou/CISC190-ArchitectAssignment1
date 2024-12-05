package edu.sdccd.cisc190.machines;

/**
 * Diamond Dash is a type of slot in the casino
 * Uses the super constructor to set values of attributes inherited from Slots
 * low risk, varying payout slot
 */
public class DiamondDash extends Slot {
    public DiamondDash() {
        super(new String[]{"💍", "💠", "💎"}, 1000, 15, 2);
    }

    /**
     * Overrides method in Slots
     * If player does not get full match, they only lose half their bet
     * @param moneyAmount The amount of money the user currently has
     * @param spunRow the symbols array the user spun
     * @param bet The amount of money the user has bet
     * @return the user's new money after payout
     */
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