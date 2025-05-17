package view;

import control.GeneralController;
import control.game.activities.TradeController;
import model.App;
import model.Player;
import model.enums.GameObjectType;
import model.enums.Menu;
import model.enums.TradeType;
import model.enums.regex_enums.GeneralCommands;
import model.enums.regex_enums.TradeCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu implements AppMenu {

    TradeController controller = new TradeController();
    GeneralController generalController = new GeneralController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = TradeCommands.TRADE.getMatcher(input)) != null) {
            Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
            TradeType type = null;
            String typeName = matcher.group("type");
            if (typeName.equalsIgnoreCase("offer")) {
                type = TradeType.OFFER;
            } else if (typeName.equalsIgnoreCase("request")) {
                type = TradeType.REQUEST;
            }
            GameObjectType item = null;
            for (GameObjectType type1 : GameObjectType.values()) {
                if (type1.name().equalsIgnoreCase(matcher.group("item"))) {
                    item = type1;
                    break;
                }
            }
            int amount = -1;
            try {
                amount = Integer.parseInt(matcher.group("amount"));
            } catch (Exception ignored) {}
            int price = -1;
            try {
                price = Integer.parseInt(matcher.group("price"));
            } catch (Exception ignored) {}

            if (price != -1) {
                System.out.println(controller.tradeWith(player, type, item, amount, price));
            } else {
                GameObjectType targetItem = null;
                for (GameObjectType type1 : GameObjectType.values()) {
                    if (type1.name().equalsIgnoreCase(matcher.group("targetItem"))) {
                        targetItem = type1;
                        break;
                    }
                }
                int targetAmount = -1;
                try {
                    targetAmount = Integer.parseInt(matcher.group("targetAmount"));
                } catch (Exception ignored) {}


                System.out.println(controller.tradeWith(player, type, item, amount, targetItem, targetAmount));
            }
        } else if (TradeCommands.TRADE_LIST.getMatcher(input) != null) {
            controller.tradeList();
        } else if ((matcher = TradeCommands.TRADE_RESPOND.getMatcher(input)) != null) {
            int id = Integer.parseInt(matcher.group("id"));
            boolean respond = true;
            if (matcher.group("respond").equals("reject")) {
                respond = false;
            }
            System.out.println(controller.responseTrade(respond, id));
        } else if (TradeCommands.TRADE_HISTORY.getMatcher(input) != null) {
            controller.tradeHistory();
        } else if (TradeCommands.END_TRADE.getMatcher(input) != null) {
            App.setCurrentMenu(Menu.GameMenu);
            System.out.println("redirecting to game menu...");
        } else if (GeneralCommands.NEXT_TURN.getMatcher(input) != null)
        {
            generalController.nextTurn();
        } else {
            System.out.println("invalid command");
        }
    }
}
