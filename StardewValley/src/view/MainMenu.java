package view;

import control.MainMenuController;
import model.enums.regex_enums.MainCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu
{
    MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = MainCommands.MENU_ENTER.getMatcher(input)) != null)
        {
            String menuName = matcher.group("menuName").trim();
            System.out.println(controller.enterMenu(menuName));
        } else if (MainCommands.MENU_EXIT.getMatcher(input) != null)
        {
            System.out.println(controller.exit());
        } else if (MainCommands.MENU_BACK.getMatcher(input) != null) {
            System.out.println(controller.back());
        } else if (MainCommands.SHOW_CURRENT_MENU.getMatcher(input) != null)
        {
            System.out.println(controller.showCurrentMenu());
        } else if (MainCommands.USER_LOGOUT.getMatcher(input) != null)
        {
            System.out.println(controller.logout());
        } else if (MainCommands.HELP.getMatcher(input) != null)
        {
            System.out.println(controller.help());
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
