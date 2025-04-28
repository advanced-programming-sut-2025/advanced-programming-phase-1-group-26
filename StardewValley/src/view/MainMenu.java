package view;

import java.util.Scanner;

public class MainMenu implements AppMenu {
    @Override
    public void check(Scanner scanner) {
    }

    public static String scan()
    {
        String text = mainScanner.nextLine().trim();
        return text;
    }

    public static void println(String output)
    {
        System.out.println(output);
    }
}
