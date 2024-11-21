package edu.sdccd.cisc190;

public class Test {
    public static void main(String[] args) {
        try {
            method();
            System.out.println("After the method call");
        }
        catch (RuntimeException ex) {
            System.out.println("RuntimeException");
        }
        catch (Exception ex) {
            System.out.println("Exception");
        }
    }

    static void method() throws Exception {
        try {
            String s = "5.6";
            Integer.parseInt(s); // Cause a NumberFormatException

            int i = 0;
            int y = 2 / i;
            System.out.println("Welcome to Java");
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException");
            throw ex;
        }
        catch (RuntimeException ex) {
            System.out.println("RuntimeException");
        }
    }
}

