package edu.sdccd.cisc190;

import java.util.ArrayList;

public abstract class Bot {

    public static String name;
    public static int money;
    public static ArrayList<Integer> amtHistory = new ArrayList<>();
    public static double luck;
    public static double aura;

    public static void main(String[] args) {

    }

    public static void set(String name, int money) {
        Bot.name = name;
        Bot.money = money;
    }

    public static void addAmtHistory() {
        amtHistory.add(money);
    }

    public static void adjustMoney(int money) {
        Bot.money += money;
    }

}
