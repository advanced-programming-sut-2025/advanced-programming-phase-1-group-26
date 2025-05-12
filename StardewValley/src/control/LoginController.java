package control;

import model.App;
import model.Result;
import model.User;
import model.enums.Menu;
import model.enums.regex_enums.RegisterCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginController
{
    public Result login(Matcher matcher)
    {
        String username = matcher.group("username");
        String password = matcher.group("password");
        boolean stayLoggedIn = matcher.group("flag") != null;
        User user = App.getPlayerByUsername(username);
        if (user == null) {
            return new Result(false, "username doesn't exist");
        } else if (!user.getPassword().equals(password)) {
            return new Result(false, "password doesn't match");
        }

        App.setCurrentUser(user);
        App.getCurrentUser().setStay(stayLoggedIn);
        App.setCurrentMenu(Menu.MainMenu);

        return new Result(true, "logged in successfully");
    }

    public Result forgetPassword(Matcher matcher, Scanner scanner)
    {
        String username = matcher.group("username");
        User user = App.getPlayerByUsername(username);
        if (user == null) {
            return new Result(false, "user not found!");
        }

        System.out.println(user.getQuestion());
        int answer = Integer.parseInt(scanner.nextLine());
        if (user.getAnswer() == answer) {
            System.out.println("enter new password");
            String newPassword = scanner.nextLine();
            if (RegisterCommands.CHECK_PASSWORD.getMatcher(newPassword) == null) {
                return new Result(false, "password is invalid!");
            } else if (newPassword.length() < 8) {
                return new Result(false, "password is short!");
            } else if (!newPassword.matches(".*[a-z].*")) {
                return new Result(false, "your password should include small letters!");
            } else if (!newPassword.matches(".*[A-Z].*")) {
                return new Result(false, "your password should include capital letters!");
            } else if (!newPassword.matches(".*[0-9].*")) {
                return new Result(false, "your password should include numbers!");
            } else if (!newPassword.matches(".*[!#$%^&*)(=+}{\\[\\]|\\\\/:;'\",><?].*")) {
                return new Result(false, "your password should include special character!");
            }

            user.setPassword(newPassword);
            return new Result(true, "your new password is set");
        } else {
            return new Result(false, "password is incorrect");
        }
    }

    public Result showCurrentMenu()
    {
        return new Result(true, "You are currently in Login Menu. Use 'help' for more information.");
    }


    public Result enterMenu(String menuName)
    {
        String output;
        if (menuName.equalsIgnoreCase("register menu") ||
                menuName.equalsIgnoreCase("register"))
        {
            App.setCurrentMenu(Menu.RegisterMenu);
            output = "Switching to register menu...";
        } else
        {
            output = "Menu name is invalid or you can not switch to this menu from here.";
        }

        return new Result(true, output);
    }

    public Result back()
    {
        App.setCurrentMenu(Menu.RegisterMenu);
        return new Result(true, "Redirecting to register menu...");
    }

    public Result exit()
    {
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Exiting the game...");
    }

    public Result help()
    {
        return new Result(true, "Available commands:\n" +
                "\n" +
                "- login -u <username> -p <password> -stay-logged-in\n" +
                "    Logs you in and goes to main menu. stay logged in flag is optional\n" +
                "\n" +
                "- forget password -u <username>\n" +
                "    you can change your password here if you pass the security question\n" +
                "\n" +
                "- menu enter <menuName>\n" +
                "    Enters the specified menu. Example: menu enter register menu\n" +
                "\n" +
                "- menu back\n" +
                "    Exits the login menu and returns to the register menu.\n" +
                "\n" +
                "- menu exit\n" +
                "    Exits the game.\n" +
                "\n" +
                "- show current menu\n" +
                "    Displays the name of the current active menu.\n" +
                "\n" +
                "- help\n" +
                "    Shows this help message.\n");
    }
}