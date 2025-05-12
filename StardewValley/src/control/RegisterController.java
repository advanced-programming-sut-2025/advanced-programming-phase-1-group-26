package control;

import model.App;
import model.Result;
import model.User;
import model.enums.Gender;
import model.enums.Menu;
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
        if (password.equals("random") && passwordConfirm.equals("password")) {
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
        Gender gender = Gender.MALE;
        if (genderName.equals("female")) {
            gender = Gender.FEMALE;
        }

        if (RegisterCommands.CHECK_USERNAME.getMatcher(username) == null) {
            return new Result(false, "username is not valid!");
        } else if (App.getPlayerByUsername(username) != null) {
            return new Result(false, "username is already taken!");
        }
        if (!(password.equals("random") && passwordConfirm.equals("password"))) {
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
            }
        }
        if (RegisterCommands.CHECK_EMAIL.getMatcher(email) == null) {
            return new Result(false, "email is invalid!");
        }
        newUser = new User(username, password, nickName, email, gender);
        App.getUsers().add(newUser);
        App.setCurrentMenu(Menu.MainMenu); // TODO: maybe this should be in pick question (?)
        App.setCurrentUser(newUser);
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
        newUser.setQuestion(questionId);
        newUser.setAnswer(answer);

        return new Result(true, "");
    }

    public Result goToLogin() {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "you are in login menu");
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
