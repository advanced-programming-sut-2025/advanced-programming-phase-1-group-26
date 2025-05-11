package model;

import model.enums.Menu;
import java.util.ArrayList;

public class App {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Game> games = new ArrayList<>();
    private static Game currentGame = null;
    private static User currentUser = null;
    private static Menu currentMenu = Menu.RegisterMenu;

    public ArrayList<User> getUsers() {
        return users;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static Menu getCurrentMenu()
    {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu)
    {
        App.currentMenu = currentMenu;
    }

    public static void addGame(Game game)
    {
        games.add(game);
    }

    public static void setCurrentUser(User currentUser)
    {
        App.currentUser = currentUser;
    }
}
