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
        int answer = scanner.nextInt();
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

    public Result goToRegister() {
        App.setCurrentMenu(Menu.RegisterMenu);
        return new Result(true, "you are in register menu");
    }
}