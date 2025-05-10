package view;

import control.GameController;
import control.game.activities.HomeController;
import model.Farm;
import model.Point;
import model.enums.FarmTypes;
import model.enums.regex_enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class HomeMenu implements AppMenu
{
    HomeController controller = new HomeController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

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
