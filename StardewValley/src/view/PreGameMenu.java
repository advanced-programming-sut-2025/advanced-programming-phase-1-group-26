package view;

import control.PreGameController;
import model.enums.regex_enums.PreGameCommands;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PreGameMenu implements AppMenu
{
    PreGameController controller = new PreGameController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = PreGameCommands.NEW_GAME.getMatcher(input)) != null)
        {
            String[] usernames = new String[3];
            usernames[0] = matcher.group("user1") == null ? "" : matcher.group("user1").trim();
            usernames[1] = matcher.group("user2") == null ? "" : matcher.group("user2").trim();
            usernames[2] = matcher.group("user3") == null ? "" : matcher.group("user3").trim();

            controller.newGame(usernames);
        } else if ((matcher = PreGameCommands.LOAD_GAME.getMatcher(input)) != null)
        {
            System.out.println(controller.loadGame());
        } else
        {
            System.out.println("invalid command");
        }
    }

    public static String scan()
    {
        return mainScanner.nextLine().trim();
    }

    public static void println(String output)
    {
        System.out.println(output);
    }

    public static void print(String output)
    {
        System.out.print(output);
    }
}
