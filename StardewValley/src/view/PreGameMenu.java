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
            String[] tokens = input.trim().split("\\s+");
            String[] usernames = Arrays.copyOfRange(tokens, 3, tokens.length);

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
}
