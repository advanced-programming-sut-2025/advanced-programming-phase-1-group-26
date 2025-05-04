package control;

import model.*;
import model.enums.FarmTypes;
import model.enums.Menu;
import view.GameMenu;
import view.MainMenu;

import java.util.ArrayList;

public class MainMenuController
{
    public Result logout()
    {
        return null;
    }

    public Result createNewGame()
    {
        return null;
    }

    private void chooseMaps(ArrayList<User> users)
    {
        final int farmOptionsNum = FarmTypes.values().length;
        MainMenu.println("Farm Options: ");
        for (int i = 0; i < farmOptionsNum; i++)
        {
            FarmTypes farm = FarmTypes.values()[i];
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
                        FarmTypes type = FarmTypes.values()[number - 1];
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
