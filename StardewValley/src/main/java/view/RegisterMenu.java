package view;

import control.RegisterController;
import model.App;
import model.Game;
import model.Result;
import model.enums.Menu;
import model.enums.SecurityQuestionType;
import model.enums.regex_enums.RegisterCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu implements AppMenu
{
    RegisterController controller = new RegisterController();
    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = RegisterCommands.REGISTER.getMatcher(input)) != null) {
            Result result = controller.register(matcher, scanner);
            System.out.println(result);
            if (result.isSuccessful()) {
                System.out.println("choose your security question number: ");
                for (SecurityQuestionType type : SecurityQuestionType.values()) {
                    System.out.println(type.getId() + "- " + type.getQuestion());
                }
            }
        } else if ((matcher = RegisterCommands.PICK_QUESTION.getMatcher(input)) != null) {
            System.out.println(controller.pickQuestion(matcher));
        } else if ((matcher = RegisterCommands.MENU_ENTER.getMatcher(input)) != null) {
            String menuName = matcher.group("menuName").trim();
            System.out.println(controller.enterMenu(menuName));
        } else if (RegisterCommands.MENU_EXIT.getMatcher(input) != null) {
            System.out.println(controller.exit());
        } else if (RegisterCommands.MENU_BACK.getMatcher(input) != null) {
            System.out.println(controller.back());
        } else if (RegisterCommands.HELP.getMatcher(input) != null) {
            System.out.println(controller.help());
        } else if (RegisterCommands.SHOW_CURRENT_MENU.getMatcher(input) != null) {
            System.out.println(controller.showCurrentMenu());
        } else {
            System.out.println("invalid command");
        }
    }
}
