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

        if (input.equals("cheat god")) // for debugging, TODO: remove later
        {
            App.setCurrentGame(new Game());
            App.setCurrentMenu(Menu.GameMenu);
        } else if ((matcher = RegisterCommands.REGISTER.getMatcher(input)) != null) {
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
        } else if (RegisterCommands.GO_TO_LOGIN.getMatcher(input) != null) {
            System.out.println(controller.goToLogin());
        }
    }
}
