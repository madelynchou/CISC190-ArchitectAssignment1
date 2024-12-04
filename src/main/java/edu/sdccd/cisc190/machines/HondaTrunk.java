package edu.sdccd.cisc190.machines;


/**
 * Honda Trunk is a type of slot in the casino
 * Uses the super constructor to set values of attributes inherited from Slots
 * low risk, varying payout slot
 */
public class HondaTrunk extends Slot {
    public HondaTrunk() {
        super(new String[]{"🚗", "🛻", "🚕"}, 1000, 1, 2);
    }

    /**
     * Overrides the evaluateWinCondition() method in Slots
     * Allows the user to win some money even if they only get a partial match
     * @param arr Array of random symbols generated from the generateSpunSymbols() method
     * @return if the user spun a 3 match, 2 match, or no match
     */
    @Override
    public int evaluateWinCondition(String[] arr) {
        if (arr[0].equals(arr[1]) && arr[1].equals(arr[2])) {
            return 3; // Full match
        } else if (arr[0].equals(arr[1]) || arr[1].equals(arr[2]) || arr[0].equals(arr[2])) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Overrides method in Slots
     * If user gets a partial match, they win a quarter of the full match payout
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
                    moneyAmount - bet;
            case 2 ->
                    (int) (moneyAmount + Math.floor(bet * returnAmt * 0.25));
            case 3 -> // Three-symbol match
                    (int) (moneyAmount + Math.floor(bet * returnAmt));
            default -> moneyAmount;
        };
    }

}
