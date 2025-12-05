package com.demo.MiniProject1.util;

import java.util.Scanner;

import com.demo.MiniProject1.exception.InvalidInputException;

public abstract class InputUtil {


private static final Scanner sc = new Scanner(System.in);

    public static int getInt(String msg) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid integer.");
        }
    }

    public static double getDouble(String msg) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid number.");
        }
    }

    public static String getString(String msg) {
        System.out.print(msg);
        String s = sc.nextLine();
        return s;
    }

    // Convenience validators

    public static int getPositiveInt(String msg) {
        int val = getInt(msg);
        if (val <= 0) throw new InvalidInputException("Value must be positive.");
        return val;
    }

    public static double getPositiveDouble(String msg) {
        double val = getDouble(msg);
        if (val <= 0) throw new InvalidInputException("Value must be positive.");
        return val;
    }

    public static boolean getYesNo(String msg) {
        System.out.print(msg);
        String s = sc.nextLine().trim().toUpperCase();
        if ("Y".equals(s)) return true;
        if ("N".equals(s)) return false;
        throw new InvalidInputException("Enter Y or N.");
    }

    public static String getNonEmptyString(String msg) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        if (s.isEmpty()) throw new InvalidInputException("Value cannot be empty.");
        return s;
    }

    public static String getRegex(String msg, String pattern, String errMsg) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        if (!s.matches(pattern)) throw new InvalidInputException(errMsg);
        return s;
    }

    public static int getIntInRange(String msg, int min, int max) {
        int v = getInt(msg);
        if (v < min || v > max) {
            throw new InvalidInputException(String.format("Value must be between %d and %d.", min, max));
        }
        return v;
    }

    public static double getDoubleInRange(String msg, double min, double max) {
        double v = getDouble(msg);
        if (v < min || v > max) {
            throw new InvalidInputException(String.format("Value must be between %.2f and %.2f.", min, max));
        }
        return v;
    }

	
}
