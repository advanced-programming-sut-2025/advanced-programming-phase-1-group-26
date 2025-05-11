package control;

import model.*;
import model.enums.MapTypes;
import model.enums.Menu;
import view.MainMenu;

import java.util.ArrayList;

public class MainMenuController
{
    public Result logout()
    {
        App.setCurrentMenu(Menu.LoginMenu);
        App.setCurrentUser(null);
        return new Result(true, "You are now logged out.");
    }

    public Result enterMenu(String menuName)
    {
        String output = "";
        if (menuName.equalsIgnoreCase("game menu") || menuName.equalsIgnoreCase("game"))
        {
            App.setCurrentMenu(Menu.GameMenu);
            output = "Switching to game menu...";
        } else if (menuName.equalsIgnoreCase("profile menu") || menuName.equalsIgnoreCase("profile"))
        {
            App.setCurrentMenu(Menu.ProfileMenu);
            output = "Switching to profile menu...";
        } else
        {
            output = "Menu name is invalid or you can not switch to this menu from here.";
        }

        return new Result(true, output);
    }

    public Result showCurrentMenu()
    {
        return new Result(true, "You are currently in Main Menu. Use 'help' for more information.");
    }

    public Result help()
    {
        return new Result(true, "Available commands:\n" +
                "\n" +
                "- user logout\n" +
                "    Logs you out of the system.\n" +
                "\n" +
                "- menu enter <menuName>\n" +
                "    Enters the specified menu. Example: menu enter login\n" +
                "\n" +
                "- menu exit\n" +
                "    Exits the current menu and returns to the previous one.\n" +
                "\n" +
                "- show current menu\n" +
                "    Displays the name of the current active menu.\n" +
                "\n" +
                "- help\n" +
                "    Shows this help message.\n");
    }

    public Result exit()
    {
        App.setCurrentMenu(Menu.LoginMenu);
        App.setCurrentUser(null);
        return new Result(true, "Redirecting to login menu...");
    }

    private void chooseMaps(ArrayList<User> users)
    {
        final int farmOptionsNum = MapTypes.values().length;
        MainMenu.println("Farm Options: ");
        for (int i = 0; i < farmOptionsNum; i++)
        {
            MapTypes farm = MapTypes.values()[i];
            MainMenu.println("\t" + (i + 1) + "- " + farm);
        }

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < users.size(); i++)
        {
            MainMenu.println("Choosing farm for " + users.get(i).getNickname() + ".\n" +
                    "Please enter the number of the farm you would like to choose:");
            while (true)
            {
                String input = MainMenu.scan();
                if (!input.matches("-?[0-9]+"))
                {
                    MainMenu.println("Please enter a valid number.");
                } else
                {
                    int number = Integer.parseInt(input);
                    if (number < 1 || number > farmOptionsNum + 1)
                    {
                        MainMenu.println("Please enter a valid number.");
                    } else
                    {
                        MapTypes type = MapTypes.values()[number - 1];
                        Player player = new Player(users.get(i), new Farm(type));
                        players.add(player);
                        break;
                    }
                }
            }
            Game game = new Game(players);
            App.addGame(game);
            App.setCurrentGame(game);
            App.setCurrentMenu(Menu.GameMenu);
            MainMenu.println("New Game created.\nRedirecting to game menu.");
        }
    }
}
