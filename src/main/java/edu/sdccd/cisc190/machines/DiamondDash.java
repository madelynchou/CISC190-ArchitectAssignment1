package edu.sdccd.cisc190.machines;

/**
 * Diamond Dash is a type of slot in the casino
 * Uses the super constructor to set values of attributes inherited from Slots
 * low risk, varying payout slot
 */
public class DiamondDash extends Slot {
    public DiamondDash() {
        // TODO: Check if "💍", "💠", "💎" symbols are suitable for the game, and modify if needed.
        // TODO: Adjust the values for max payout (1000), reutn rate (15), and bet multiplayer (2) if the game balance feels off. 
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
        // TODO: Ensure evaluateWinCondition(spunRow) correctly identifies matches for the game symbols.
        int winningCondition = evaluateWinCondition(spunRow);
        return switch (winningCondition) {
            case 0 -> // TODO: Review the penalty for no match (lose half the bet). Change if too harsh or lenient.
                    (int) (moneyAmount - bet * 0.5);
            case 3 -> // TODO: Double-check calculation for three-symbol match payouts for fairness.
                    (int) (moneyAmount + Math.floor(bet * returnAmt));
            default -> // TODO: Handle other winning conditions if applicable, or add comments to clarify why defaults return unchanged money.
        };
    }
}
