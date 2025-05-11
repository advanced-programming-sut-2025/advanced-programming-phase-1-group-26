package view;

import java.util.Scanner;

public class ProfileMenu implements AppMenu
{
    @Override
    public void check(Scanner scanner)
    {

    }

    public static String scan()
    {
        return mainScanner.nextLine().trim();
    }

    public static void println(String output)
    {
        System.out.println(output);
    }
}
