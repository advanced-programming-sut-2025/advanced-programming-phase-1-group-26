package control;

import model.App;
import model.Result;
import model.User;
import model.enums.Gender;
import model.enums.Menu;
import model.enums.regex_enums.RegexCommands;
import model.enums.regex_enums.RegisterCommands;
import view.ProfileMenu;
import view.ProfileMenu;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class ProfileController
{
    public Result changeUsername(String newUsername, Scanner scanner)
    {
        User user = App.getCurrentUser();

        if (!validUsername(newUsername))
        {
            return new Result(false, "This username is not valid.");
        }

        if (user.getUsername().equals(newUsername))
        {
            ProfileMenu.println("You should've entered a different username.");
        } else if (!uniqueUsername(newUsername))
        {
            ProfileMenu.println("This username is already taken.");
        }

        newUsername = createNewUsername(newUsername);

        ProfileMenu.println("Your new username would be: " + newUsername);
        ProfileMenu.println("Do you confirm the change? [y/n]");
        String answer = ProfileMenu.scan(scanner);

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

    public void setRandomPassword(String oldPassword, Scanner scanner)
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
            ProfileMenu.println("Do you confirm the change? [y/n]");
            String answer = ProfileMenu.scan(scanner).toLowerCase();

            switch (answer)
            {
                case "y":
                    ProfileMenu.println("Your password has been successfully changed.");
                    user.setPassword(newPassword);
                    break outer;
                case "n":
                    ProfileMenu.println("Generating new password...");
                    break;
                case "q":
                    ProfileMenu.println("Stopping random generator...");
                    break outer;
            }
        } while (true);
    }

    public Result changeNickName(String newNickName)
    {
        User user = App.getCurrentUser();
        if (user.getNickname().equals(newNickName))
        {
            return new Result(false, "Plz enter a new nickname.");
        }

        user.setNickname(newNickName);
        return new Result(true, "Your new nickname has been successfully changed.");
    }

    public Result changeEmail(String newEmail)
    {
        User user = App.getCurrentUser();

        if (user.getEmail().equals(newEmail))
        {
            return new Result(false, "You must enter a new email.");
        }

        if (RegisterCommands.CHECK_EMAIL.getMatcher(newEmail) == null)
        {
            return new Result(false, "Your email format is incorrect.");
        }

        user.setEmail(newEmail);
        return new Result(true, "Your email has been successfully changed.");
    }

    public Result changeGender(String newGender)
    {
        User user = App.getCurrentUser();
        Gender gender = Gender.getGender(newGender);

        if (gender == null)
        {
            return new Result(false, "invalid gender :(");
        }

        if (user.getGender().equals(gender))
        {
            return new Result(false, "You already are " + gender + ".");
        }

        user.setGender(gender);
        return new Result(true, "You gender was changed successfully.");
    }

    public Result userInfo()
    {
        StringBuilder output = new StringBuilder();
        User user = App.getCurrentUser();

        output.append(user.getNickname()).append("'s Profile").append("\n");
        output.append("username: ").append(user.getUsername()).append("\n");
        output.append("email: ").append(user.getEmail()).append("\n");
        output.append("gender: ").append(user.getGender()).append("\n");
        output.append("number of games: ").append(user.getNumberOfGames()).append("\n");
        output.append("max money in a game: ").append(user.getMaxMoney()).append("\n");

        return new Result(true, output.toString().trim());
    }

    public Result exitMenu()
    {
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Exiting the game...");
    }

    public Result enterMenu(String menuName)
    {
        String output;
        if (menuName.equalsIgnoreCase("main menu") || menuName.equalsIgnoreCase("main"))
        {
            App.setCurrentMenu(Menu.MainMenu);
            output = "Switching to main menu...";
        } else if (menuName.equalsIgnoreCase("login menu") || menuName.equalsIgnoreCase("login"))
        {
            App.setCurrentUser(null);
            App.setCurrentMenu(Menu.LoginMenu);
            output = "Switching to login menu...";
        } else if (menuName.equalsIgnoreCase("register menu") || menuName.equalsIgnoreCase("register"))
        {
            App.setCurrentUser(null);
            App.setCurrentMenu(Menu.RegisterMenu);
            output = "Switching to register menu...";
        } else
        {
            output = "Menu name is invalid or you can not switch to this menu from here.";
        }

        return new Result(true, output);
    }

    public Result help()
    {
        return new Result(true, "Available commands:\n" +
                "\n" +
                "- change username -u <new-username>\n" +
                "    You can change your username to something new.\n" +
                "\n" +
                "- change password -p <new-password> -o <old-password>\n" +
                "    You can change your password. You can get a random password by typing 'random password' in -p flag.\n" +
                "\n" +
                "- change nickname -n <new-password>\n" +
                "    You can change your nickname to something new.\n" +
                "\n" +
                "- change email -n <new-email>\n" +
                "    You can change your email to a new valid email.\n" +
                "\n" +
                "- change gender -g <new-gender>\n" +
                "    You can change your gender to a different one.\n" +
                "\n" +
                "- user info\n" +
                "    Shows your user information.\n" +
                "\n" +
                "- menu enter <menu-name>\n" +
                "    Enters the specified menu. Usage: main - login - register\n" +
                "\n" +
                "- menu exit\n" +
                "    Exits from the app.\n" +
                "\n" +
                "- menu back\n" +
                "    Goes back to main menu.\n" +
                "\n" +
                "- show current menu\n" +
                "    Displays the name of the current active menu.\n" +
                "\n" +
                "- help\n" +
                "    Shows this help message.\n");
    }

    public Result back()
    {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Redirecting to main menu...");
    }

    public Result showCurrentMenu()
    {
        return new Result(true, "You are currently in your profile menu.");
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
        List<Character> chars = new ArrayList<>(sb.chars().mapToObj(c -> (char) c).toList());
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
