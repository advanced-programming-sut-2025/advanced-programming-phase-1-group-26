package view;

import control.MainMenuController;
import model.enums.regex_enums.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu
{
    MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = MainMenuCommands.MENU_ENTER.getMatcher(input)) != null)
        {
            String menuName = matcher.group("menuName").trim();
            System.out.println(controller.enterMenu(menuName));
        } else if ((matcher = MainMenuCommands.MENU_EXIT.getMatcher(input)) != null)
        {
            System.out.println(controller.exit());
        } else if ((matcher = MainMenuCommands.SHOW_CURRENT_MENU.getMatcher(input)) != null)
        {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = MainMenuCommands.USER_LOGOUT.getMatcher(input)) != null)
        {
            System.out.println(controller.logout());
        } else if ((matcher = MainMenuCommands.HELP.getMatcher(input)) != null)
        {
            System.out.println(controller.help());
        } else
        {
            System.out.println("invalid command");
        }
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
