package view;

import control.LoginController;
import control.RegisterController;
import model.App;
import model.enums.regex_enums.LoginCommands;
import model.enums.regex_enums.RegisterCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    LoginController controller = new LoginController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = LoginCommands.LOGIN.getMatcher(input)) != null) {
            System.out.println(controller.login(matcher));
        } else if ((matcher = LoginCommands.FORGET_PASSWORD.getMatcher(input)) != null) {
            System.out.println(controller.forgetPassword(matcher, scanner));
        } else if (LoginCommands.MENU_ENTER.getMatcher(input) != null) {
            String menuName = matcher.group("menuName").trim();
            System.out.println(controller.enterMenu(menuName));
        } else if (LoginCommands.MENU_EXIT.getMatcher(input) != null) {
            System.out.println(controller.exit());
        } else if (LoginCommands.MENU_BACK.getMatcher(input) != null) {
            System.out.println(controller.back());
        } else if (LoginCommands.HELP.getMatcher(input) != null) {
            System.out.println(controller.help());
        } else if (LoginCommands.SHOW_CURRENT_MENU.getMatcher(input) != null) {
            System.out.println(controller.showCurrentMenu());
        } else {
            System.out.println("invalid command");
        }
    }
}
