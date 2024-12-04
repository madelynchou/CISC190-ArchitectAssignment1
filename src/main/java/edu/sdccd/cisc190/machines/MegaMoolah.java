package edu.sdccd.cisc190.machines;


/**
 * Mega Moolah is a type of slot in the casino
 * Uses the super constructor to set values of attributes inherited from Slots
 * Medium risk, medium reward slot
 */
public class MegaMoolah extends Slot {
    public MegaMoolah() {
        super(new String[]{"\uD83D\uDCB0", "\uD83E\uDD11", "\uD83D\uDCB8"}, 1000, 10, 5);
    }

    /**
     * Overrides the calculatePayout method in Slots
     * User only lose $15 if they do not get a full match, else they win 3 times their bet
     * @param moneyAmount The amount of money the user currently has
     * @param spunRow The
     * @param bet The amount of money the user has bet
     * @return the player's new money after payout
     */
    @Override
    public int calculatePayout(int moneyAmount, String[] spunRow, int bet) {
        int winningCondition = evaluateWinCondition(spunRow);
        return switch (winningCondition) {
            case 0 -> // No match
                    (int) (moneyAmount - Math.floor(minBet * returnAmt * 0.5));
            case 3 -> // Three-symbol match
                    (int) (moneyAmount + Math.floor(bet * returnAmt));
            default -> moneyAmount;
        };
    }

}
