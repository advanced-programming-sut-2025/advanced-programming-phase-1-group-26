package control;

import model.*;
import model.enums.MapTypes;
import model.enums.Menu;
import view.MainMenu;
import view.PreGameMenu;

import java.util.ArrayList;

public class PreGameController
{
    public void newGame(String[] usernames)
    {
        User user = App.getCurrentUser();
        ArrayList<User> users = new ArrayList<>();

        if (user.hasCurrentGame())
        {
            PreGameMenu.println("You are already in a game. How many do you want at once?");
            return;
        }

        users.add(user);

        for (String username : usernames)
        {
            User thisUser = getUser(username);

            if (thisUser != null && !thisUser.hasCurrentGame())
            {
                users.add(getUser(username));
            }
        }

        for (int i = 0; i < 4 - users.size(); i++)
        {
            User newUser = new User(String.format("guest" + (i + 1)));
            users.add(newUser);
        }

        PreGameMenu.println("Users selected successfully.");
        chooseMaps(users);
    }

    public Result loadGame()
    {
        User user = App.getCurrentUser();
        if (!user.hasCurrentGame())
        {
            return new Result(false, "You are not currently in a game.\n" +
                    "You can go read a book instead.");
        }

        Game game = App.getCurrentGame();
        Player player = game.getPlayerFromUser(user);
        game.setOppenheimer(player);
        game.setCurrentPlayer(player);
        App.setCurrentGame(user.getCurrentGame());
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, """
                Loading Game...
                We wanted to add loading screens to the game to make it more interesting,
                but unfortunately we didn't have time to :(""");
    }

    private void chooseMaps(ArrayList<User> users)
    {
        final int farmOptionsNum = MapTypes.values().length;
        PreGameMenu.println("Farm Options: ");
        for (int i = 0; i < farmOptionsNum; i++)
        {
            MapTypes farm = MapTypes.values()[i];
            PreGameMenu.println("\t" + (i + 1) + "- " + farm);
        }

        ArrayList<Player> players = new ArrayList<>();

        for (User user : users)
        {
            PreGameMenu.println("Choosing farm for " + user.getNickname() + ".\n" +
                    "Please enter the number of the farm you would like to choose:");
            while (true)
            {
                String input = PreGameMenu.scan();
                if (!input.matches("-?[0-9]+"))
                {
                    PreGameMenu.println("Please enter a valid number.");
                } else
                {
                    int number = Integer.parseInt(input);
                    if (number < 1 || number > farmOptionsNum + 1)
                    {
                        PreGameMenu.println("Please enter a valid number.");
                    } else
                    {
                        MapTypes type = MapTypes.values()[number - 1];
                        Player player = new Player(user, new Farm(type));
                        players.add(player);
                        break;
                    }
                }
            }

            Game game = new Game(players);
            game.setCurrentPlayer(players.get(0));
            App.addGame(game);
            App.setCurrentGame(game);
            App.setCurrentMenu(Menu.GameMenu);
            for (User u : users)
            {
                u.setCurrentGame(game);
            }

            PreGameMenu.println("New Game created.\n" +
                    "Redirecting to game menu.");
        }
    }

    private User getUser(String username)
    {
        for (User user : App.getUsers())
        {
            if (user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }
}
