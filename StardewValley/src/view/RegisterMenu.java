package view;

import model.App;
import model.Game;
import model.enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu implements AppMenu
{
    LoginMenu loginMenu;
    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if (input.equals("game"))
        {
            App.setCurrentGame(new Game());
            App.setCurrentMenu(Menu.GameMenu);
        }
    }
}
