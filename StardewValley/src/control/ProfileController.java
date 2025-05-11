package control;

import model.App;
import model.Result;
import model.User;
import model.enums.regex_enums.RegexCommands;
import view.MainMenu;
import view.ProfileMenu;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProfileController
{
    public Result changeUsername(String newUsername)
    {
        User user = App.getCurrentUser();

        if (!validUsername(newUsername))
        {
            return new Result(false, "This username is not valid.");
        }

        if (user.getUsername().equals(newUsername))
        {
            MainMenu.println("You should've entered a different username.");
        } else if (!uniqueUsername(newUsername))
        {
            MainMenu.println("This username is already taken.");
        }

        newUsername = createNewUsername(newUsername);

        MainMenu.println("Your new username would be: " + newUsername);
        MainMenu.println("Do you confirm the change? [y/n]");
        String answer = MainMenu.scan();

        String output = "";

        if (answer.equalsIgnoreCase("y"))
        {
            user.setUsername(newUsername);
            output = "Your username has been successfully changed.";
        } else if (answer.equalsIgnoreCase("n"))
        {
            output = "Ok, you can try again later.";
        } else
        {
            output = "Something went wrong, please try again.";
        }

        return new Result(true, output);
    }

    public Result changePassword(String newPassword, String oldPassword)
    {
        User user = App.getCurrentUser();

        if (!user.getPassword().equals(oldPassword))
        {
            return new Result(false, "Your password is incorrect.");
        }

        if (user.getPassword().equals(newPassword))
        {
            return new Result(false, "You must enter a new password.");
        }

        if (!containsUppercase(newPassword))
        {
            return new Result(false, "Your new password does not contain an uppercase letter.\n" +
                    "tip: you can set your new password to something random");
        }

        if (!containsLowercase(newPassword))
        {
            return new Result(false, "Your new password does not contain a lowercase letter.\n" +
                    "tip: you can set your new password to something random");
        }

        if (!containsDigit(newPassword))
        {
            return new Result(false, "Your new password does not contain any digits.\n" +
                    "tip: you can set your new password to something random");
        }

        if (!containsSpecialCharacter(newPassword))
        {
            return new Result(false, "Your new password does not contain any special characters.\n" +
                    "tip: you can set your new password to something random");
        }

        user.setPassword(newPassword);
        return new Result(true, "Your password has been successfully changed.");
    }

    public void setRandomPassword(String oldPassword)
    {
        User user = App.getCurrentUser();

        if (!user.getPassword().equals(oldPassword))
        {
            ProfileMenu.println("Your password is incorrect.");
            return;
        }

        String newPassword;

        outer:
        do
        {
            newPassword = generateStrongRandomPassword();
            ProfileMenu.println("Your new password would be: " + newPassword);
            MainMenu.println("Do you confirm the change? [y/n]");
            String answer = MainMenu.scan().toLowerCase();

            switch (answer)
            {
                case "y":
                    MainMenu.println("Your password has been successfully changed.");
                    user.setPassword(newPassword);
                    break outer;
                case "n":
                    MainMenu.println("Generating new password...");
                    break;
                case "q":
                    MainMenu.println("Stopping random generator...");
                    break outer;
            }
        } while (true);
    }

    public Result changeNickName(String newNickName)
    {
        return null;
    }

    public Result changeEmail(String newEmail)
    {
        return null;
    }

    public Result showUserInfo()
    {
        return null;
    }

    private boolean uniqueUsername(String newUsername)
    {
        for (User user : App.getUsers())
        {
            if (newUsername.equals(user.getUsername()))
            {
                return false;
            }
        }

        return true;
    }

    private boolean validUsername(String newUsername)
    {
        return RegexCommands.USERNAME_FORMAT.matches(newUsername);
    }

    private String createNewUsername(String newUsername)
    {
        if (uniqueUsername(newUsername))
            return newUsername;

        Random random = new Random();
        String modifiedUsername;
        do {
            int randomNumber = random.nextInt(10000); // e.g., 0–9999
            modifiedUsername = newUsername + randomNumber;
        } while (!uniqueUsername(modifiedUsername));

        return modifiedUsername;
    }

    private String generateStrongRandomPassword()
    {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String allSpecial = "?><,\"';:\\/|][}{+=)(*&^%$#!";

        String allChars = upper + lower + digits + allSpecial;
        SecureRandom rand = new SecureRandom();

        StringBuilder sb = new StringBuilder();

        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(allSpecial.charAt(rand.nextInt(allSpecial.length())));

        for (int i = 4; i < 12; i++)
        {
            sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        // Shuffle result
        List<Character> chars = sb.chars().mapToObj(c -> (char) c).toList();
        Collections.shuffle(chars, rand);

        return chars.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private boolean containsSpecialCharacter(String password)
    {
        return password.matches(".*[?><,\"';:\\/|\\[\\]{}+=)(*&^%$#!].*");
    }

    private boolean containsLowercase(String password)
    {
        return password.matches(".*[a-z].*");
    }

    private boolean containsUppercase(String password)
    {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsDigit(String password)
    {
        return password.matches(".*\\d.*");
    }
}
