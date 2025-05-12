package view;

import control.ProfileController;
import model.enums.regex_enums.ProfileCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements AppMenu
{
    ProfileController controller = new ProfileController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = ProfileCommands.CHANGE_USERNAME.getMatcher(input)) != null)
        {
            String newUsername = matcher.group("newUsername").trim();
            System.out.println(controller.changeUsername(newUsername, scanner));
        } else if ((matcher = ProfileCommands.CHANGE_PASSWORD.getMatcher(input)) != null)
        {
            String newPassword = matcher.group("newPassword").trim();
            String oldPassword = matcher.group("oldPassword").trim();
            System.out.println(controller.changePassword(newPassword, oldPassword));
        } else if ((matcher = ProfileCommands.SET_PASSWORD_TO_RANDOM.getMatcher(input)) != null)
        {
            String oldPassword = matcher.group("oldPassword").trim();
            controller.setRandomPassword(oldPassword, scanner);
        } else if ((matcher = ProfileCommands.CHANGE_NICKNAME.getMatcher(input)) != null)
        {
            String newNickname = matcher.group("newNickname").trim();
            System.out.println(controller.changeNickName(newNickname));
        } else if ((matcher = ProfileCommands.CHANGE_EMAIL.getMatcher(input)) != null)
        {
            String newEmail = matcher.group("newEmail").trim();
            System.out.println(controller.changeEmail(newEmail));
        } else if ((matcher = ProfileCommands.CHANGE_GENDER.getMatcher(input)) != null)
        {
            String newGender = matcher.group("newGender").trim();
            System.out.println(controller.changeGender(newGender));
        } else if ((matcher = ProfileCommands.USER_INFO.getMatcher(input)) != null)
        {
            System.out.println(controller.userInfo());
        } else if ((matcher = ProfileCommands.MENU_EXIT.getMatcher(input)) != null)
        {
            System.out.println(controller.exitMenu());
        } else if ((matcher = ProfileCommands.SHOW_CURRENT_MENU.getMatcher(input)) != null)
        {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = ProfileCommands.MENU_ENTER.getMatcher(input)) != null)
        {
            String menuName = matcher.group("menuName").trim();
            System.out.println(controller.enterMenu(menuName));
        } else if ((matcher = ProfileCommands.MENU_BACK.getMatcher(input)) != null)
        {
            System.out.println(controller.back());
        } else if ((matcher = ProfileCommands.HELP.getMatcher(input)) != null)
        {
            System.out.println(controller.help());
        } else
        {
            System.out.println("invalid command");
        }
    }

    public static String scan(Scanner scanner)
    {
        return scanner.nextLine().trim();
    }

    public static void println(String output)
    {
        System.out.println(output);
    }
}
