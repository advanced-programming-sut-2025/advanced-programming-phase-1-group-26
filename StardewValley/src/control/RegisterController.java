package control;

import model.App;
import model.Result;
import model.User;
import model.enums.Gender;
import model.enums.Menu;
import model.enums.SecurityQuestionType;
import model.enums.regex_enums.RegisterCommands;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterController
{
    private static final SecureRandom random = new SecureRandom();
    private static User newUser = null;

    public Result register(Matcher matcher, Scanner scanner)
    {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirm = matcher.group("passwordConfirm");
        String input;
        boolean random = false;
        if (password.equals("random") && passwordConfirm.equals("password")) {
            random = true;
            do {
                password = generatePassword();
                passwordConfirm = password;
                System.out.println("your password is " + password +
                        ". type y to confirm, n for another password or exit for rejection");
                input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("y")) {
                    break;
                } else if (input.equalsIgnoreCase("exit")) {
                    return new Result(false, "registration cancelled by user.");
                }

            } while (input.equalsIgnoreCase("n"));
        }
        String nickName = matcher.group("nickname");
        String email = matcher.group("email");
        String genderName = matcher.group("gender");
        Gender gender = null;
        if (genderName.equalsIgnoreCase("female")) {
            gender = Gender.FEMALE;
        } else if (genderName.equalsIgnoreCase("male")) {
            gender = Gender.MALE;
        }

        if (RegisterCommands.CHECK_USERNAME.getMatcher(username) == null) {
            return new Result(false, "username is not valid!");
        } else if (App.getPlayerByUsername(username) != null) {
            return new Result(false, "username is already taken!");
        }
        if (!random) {
            if (RegisterCommands.CHECK_PASSWORD.getMatcher(password) == null) {
                return new Result(false, "password is invalid!");
            } else if (password.length() < 8) {
                return new Result(false, "password is short!");
            } else if (!password.matches(".*[a-z].*")) {
                return new Result(false, "your password should include small letters!");
            } else if (!password.matches(".*[A-Z].*")) {
                return new Result(false, "your password should include capital letters!");
            } else if (!password.matches(".*[0-9].*")) {
                return new Result(false, "your password should include numbers!");
            } else if (!password.matches(".*[!#$%^&*)(=+}{\\[\\]|\\\\/:;'\",><?].*")) {
                return new Result(false, "your password should include special character!");
            } else if (!password.equals(passwordConfirm)) {
                return new Result(false, "your password doesn't match confirm password!");
            } else if (gender == null) {
                return new Result(false, "gender is invalid!");
            }
        }
        if (RegisterCommands.CHECK_EMAIL.getMatcher(email) == null) {
            return new Result(false, "email is invalid!");
        }
        newUser = new User(username, password, nickName, email, gender);
        App.getUsers().add(newUser);
        return new Result(true, "user created successfully!");
    }

    public Result pickQuestion(Matcher matcher)
    {
        int questionId = Integer.parseInt(matcher.group("questionNumber"));
        int answer = Integer.parseInt(matcher.group("answer"));
        int confirmAnswer = Integer.parseInt(matcher.group("answerConfirm"));
        if (questionId > 2 || questionId < 1) {
            return new Result(false, "question number invalid");
        } else if (answer > 2 || answer < 1) {
                return new Result(false, "answer is invalid");
        } else if (confirmAnswer > 2 || confirmAnswer < 1) {
            return new Result(false, "confirm answer is invalid");
        } else if (answer != confirmAnswer) {
            return new Result(false, "answer is not equal to confirm answer");
        } else if (newUser == null) {
            return new Result(false, "register first");
        }
        newUser.setQuestion(SecurityQuestionType.getQuestionById(questionId));
        newUser.setAnswer(answer);
        return new Result(true, "security question set.");
    }

    public Result enterMenu(String menuName)
    {
        String output;
        if (menuName.equalsIgnoreCase("login menu") || menuName.equalsIgnoreCase("login"))
        {
            App.setCurrentMenu(Menu.LoginMenu);
            output = "Switching to login menu...";
        } else
        {
            output = "Menu name is invalid or you can not switch to this menu from here.";
        }

        return new Result(true, output);
    }

    public Result showCurrentMenu()
    {
        return new Result(true, "You are currently in Register Menu. Use 'help' for more information.");
    }

    public Result back()
    {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "Redirecting to login menu...");
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
                "- register -u <username> -p <password> <confirmPassword> -n <nickname> -e <email> -g <gender>\n" +
                "    creates a new account. you can get a random password by typing 'random password' in -p flag\n" +
                "\n" +
                "- forget password -u <username>\n" +
                "    you can change your password here if you pass the security question\n" +
                "\n" +
                "- menu enter <menuName>\n" +
                "    Enters the specified menu. Example: menu enter login menu\n" +
                "\n" +
                "- menu back\n" +
                "    Exits the register menu and returns to the login menu.\n" +
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

    public static String generatePassword() {
        String specialChars = "!@#$%^&*-_=+{};:,.<>?";
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" + specialChars;

        int length = 8 + random.nextInt(9);
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }
}
