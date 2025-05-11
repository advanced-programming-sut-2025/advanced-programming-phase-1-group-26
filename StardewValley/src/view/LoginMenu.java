package view;

import control.LoginController;
import control.RegisterController;
import model.enums.regex_enums.LoginCommands;

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
        } else if (LoginCommands.GO_TO_REGISTER.getMatcher(input) != null) {
            System.out.println(controller.goToRegister());
        } else {
            System.out.println("invalid command");
        }
    }
}
