package model;

import model.enums.Menu;
import model.player_data.Skill;
import view.AppMenu;

import java.awt.*;
import java.util.ArrayList;

public class App {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();
    private static Game currentGame = null;
    private static User currentUser = null;
    private static Menu currentMenu = Menu.RegisterMenu;

    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
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
}
