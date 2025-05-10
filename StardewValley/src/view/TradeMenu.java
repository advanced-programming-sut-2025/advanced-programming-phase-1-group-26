package view;

import control.GameController;
import control.game.activities.CommunicateController;
import control.game.activities.TradeController;
import model.enums.regex_enums.GameCommands;
import model.enums.regex_enums.TradeCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu implements AppMenu {

    TradeController controller = new TradeController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

    }
}
