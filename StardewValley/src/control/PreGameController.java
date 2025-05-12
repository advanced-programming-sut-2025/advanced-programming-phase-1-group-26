package control;

import model.*;
import model.enums.MapTypes;
import model.enums.Menu;
import view.AppMenu;
import view.MainMenu;
import view.PreGameMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class PreGameController
{
    public void newGame(String[] usernames, Scanner scanner)
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
            if (!username.isEmpty())
            {
                User thisUser = getUser(username);

                if (thisUser != null && !thisUser.hasCurrentGame() && !users.contains(thisUser))
                {
                    users.add(getUser(username));
                } else if (thisUser != null)
                {
                    PreGameMenu.println("Couldn't add " + username + ".");
                }
            }
        }

        for (int i = 0; i < 4 - users.size(); i++)
        {
            User newUser = new User(String.format("guest" + (i + 1)));
            users.add(newUser);
        }

        PreGameMenu.println("Users selected successfully.");
        chooseMaps(users, scanner);
    }

    public Result loadGame()
    {
        User user = App.getCurrentUser();
        if (!user.hasCurrentGame())
        {
            return new Result(false, "You are not currently in a game.\n" +
                    "You can go read a book instead.");
        }

        Game game = user.getCurrentGame();
        Player player = game.getPlayerFromUser(user);
        game.setOppenheimer(player);
        game.setCurrentPlayer(player);
        App.setCurrentGame(game);
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, """
                Loading Game...
                We wanted to add loading screens to the game to make it more interesting,
                but unfortunately we didn't have time to :(""");
    }

    public Result enterMenu(String menuName)
    {
        String output;
        if (menuName.equalsIgnoreCase("main menu") || menuName.equalsIgnoreCase("main"))
        {
            App.setCurrentMenu(Menu.MainMenu);
            output = "Switching to game menu...";
        } else
        {
            output = "Menu name is invalid or you can not switch to this menu from here.";
        }

        return new Result(true, output);
    }

    public Result help()
    {
        return new Result(true, "Available commands:\n" +
                "\n" +
                "- game new -u <username-1> <username-2> <username-3>\n" +
                "    Creates the new game. You can use 1 to three usernames in front of -u flag. It can not empty. (Each game has 4 players.)\n" +
                "\n" +
                "- load game\n" +
                "    If you have an ongoing game, this command load it so you can play.\n" +
                "\n" +
                "- menu enter <menu-name>\n" +
                "    Enters the specified menu. Usage: profile - pre game\n" +
                "\n" +
                "- menu exit\n" +
                "    Exits from the app.\n" +
                "\n" +
                "- show current menu\n" +
                "    Displays the name of the current active menu.\n" +
                "\n" +
                "- menu back\n" +
                "    Goes back to main menu.\n" +
                "\n" +
                "- help\n" +
                "    Shows this help message.\n");
    }

    public Result showCurrentMenu()
    {
        return new Result(true, "You are currently in Pre Game Menu. Use 'help' for more information.");
    }

    public Result back()
    {
        App.setCurrentMenu(Menu.MainMenu);
        App.setCurrentUser(null);
        return new Result(true, "Redirecting to main menu...");
    }

    public Result exit()
    {
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Exiting the game...");
    }

    private void chooseMaps(ArrayList<User> users, Scanner scanner)
    {
        ArrayList<MapTypes> farmTypes = MapTypes.farmTypes();
        PreGameMenu.println("Farm Options: ");
        for (int i = 0; i < farmTypes.size(); i++)
        {
            MapTypes farm = MapTypes.values()[i];
            PreGameMenu.println("\t" + (i + 1) + "- " + farm);
        }

        ArrayList<Player> players = new ArrayList<>();

        for (User user : users)
        {
            PreGameMenu.print("Choosing farm for " + user.getNickname() + ": ");
            while (true)
            {
                String input = PreGameMenu.scan(scanner);
                if (!input.matches("-?[0-9]+"))
                {
                    PreGameMenu.print("Please enter a valid number: ");
                } else
                {
                    int number = Integer.parseInt(input);
                    if (number < 1 || number > farmTypes.size() + 1)
                    {
                        PreGameMenu.print("Please enter a valid number: ");
                    } else
                    {
                        MapTypes type = MapTypes.values()[number - 1];
                        Player player = new Player(user, new Farm(type));
                        players.add(player);
                        break;
                    }
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

        PreGameMenu.println("""
                New Game created.
                
                Welcome to Stardew Valley!""");
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
