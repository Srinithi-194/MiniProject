package com.mph.MiniProject.Util;

import java.util.Scanner;

public class InputUtil {
	
	public static final Scanner sc = new Scanner(System.in);

    public static int getInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }

    public static double getDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine());
    }

    public static String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();

}
}
