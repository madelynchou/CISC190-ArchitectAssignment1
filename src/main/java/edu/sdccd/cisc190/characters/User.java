package edu.sdccd.cisc190.characters;

import java.util.ArrayList;

public class User {

    public static String name;
    public static int money;
    public static ArrayList<Integer> amtHistory = new ArrayList<>();
    public static double luck;
    public static double aura;

    public static void main(String[] args) {

    }

    public static void set(String name, int money) {
        User.name = name;
        User.money = money;
    }

    public static void addAmtHistory() {
        amtHistory.add(money);
    }

    //method

}
