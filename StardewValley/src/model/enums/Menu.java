package model.enums;

import view.*;

import java.util.Scanner;

public enum Menu
{
    RegisterMenu(new RegisterMenu(), "register menu"),
    LoginMenu(new LoginMenu(), "login menu"),
    MainMenu(new MainMenu(), "main menu"),
    GameMenu(new GameMenu(), "game menu"),
    ProfileMenu(new ProfileMenu(), "profile menu"),
    ExitMenu(new ExitMenu(), null);

    private final AppMenu menu;
    private final String name;

    Menu(AppMenu menu, String name)
    {
        this.menu = menu;
        this.name = name;
    }

    public void checkCommand(Scanner scanner)
    {
        this.menu.check(scanner);
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
