package edu.sdccd.cisc190.machines;


public class MegaMoolah extends Slot {
    public MegaMoolah() {
        super(new String[]{"\uD83D\uDCB0", "\uD83E\uDD11", "\uD83D\uDCB8"}, 1000, 10, 3);
    }

    //user loses the min bet if no full match
    @Override
    public int calculatePayout(int moneyAmount, String[] spunRow, int bet) {
        int winningCondition = evaluateWinCondition(spunRow);
        return switch (winningCondition) {
            case 0 -> // No match
                    moneyAmount - minBet;
            case 3 -> // Three-symbol match
                    (int) (moneyAmount + Math.floor(bet * returnAmt));
            default -> moneyAmount;
        };
    }

}
