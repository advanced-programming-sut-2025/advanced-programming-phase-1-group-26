package model.enums;

import view.AppMenu;
import view.*;

import java.util.Scanner;

public enum Menu
{
    RegisterMenu(new RegisterMenu(), "register menu"),
    LoginMenu(new LoginMenu(), "login menu"),
    MainMenu(new MainMenu(), "main menu"),
    GameMenu(new GameMenu(), "game menu"),
    ProfileMenu(new ProfileMenu(), "profile menu"),
    ExitMenu(new ExitMenu(), null),
    HomeMenu(new HomeMenu(), "home menu"),
    PreGameMenu(new PreGameMenu(), "pre game menu"),
    CityMenu(new CityMenu(), "city menu"),
    TradeMenu(new TradeMenu(), "trade menu"),
    ShopMenu(new ShopMenu(), "shop menu"),
    ;

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

    public String getName()
    {
        return name;
    }
}
