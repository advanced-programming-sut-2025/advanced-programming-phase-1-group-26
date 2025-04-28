package view;

import java.util.Scanner;

abstract class Menu
{
    private static final MainMenu mainMenu = new MainMenu();
    private static final LoginMenu loginMenu = new LoginMenu();
    private static final RegisterMenu signupMenu = new RegisterMenu();
    private static final GameMenu gameMenu = new GameMenu();
    private static final ExitMenu leaderboardMenu = new ExitMenu();
    protected static Scanner mainScanner;

    abstract CommandResult executeCommands(String command);

    static class Command {
        String name;
        String regex;
        Pattern pattern;

        // Constructor
        public Command(String name, String regex) {
            this.name = name;
            this.regex = regex;
            this.pattern = Pattern.compile(regex);
        }

        public Matcher match(String input) {
            return pattern.matcher(input);
        }
    }
}
