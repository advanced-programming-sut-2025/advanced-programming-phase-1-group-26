package view;

import model.App;
import model.enums.Menu;

import java.util.Scanner;

public class AppView {
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        do
        {
            App.getCurrentMenu().checkCommand(scanner);
        } while(App.getCurrentMenu() != Menu.ExitMenu);
    }
}
