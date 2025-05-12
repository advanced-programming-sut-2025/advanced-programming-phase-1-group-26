package view;

import control.game.activities.TradeController;
import model.App;
import model.Player;
import model.enums.GameObjectType;
import model.enums.TradeType;
import model.enums.regex_enums.TradeCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu implements AppMenu {

    TradeController controller = new TradeController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = TradeCommands.TRADE.getMatcher(input)) != null) {
            Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
            TradeType type = TradeType.REQUEST;
            if (matcher.group("type").equals("offer")) {
                type = TradeType.OFFER;
            }
            GameObjectType item = null;
            for (GameObjectType type1 : GameObjectType.values()) {
                if (type1.name().equals(matcher.group("item"))) {
                    item = type1;
                    break;
                }
            }
            int amount = Integer.parseInt(matcher.group("amount"));
            int price = Integer.parseInt(matcher.group("price"));
            if (price != -1) {
                System.out.println(controller.tradeWith(player, type, item, amount, price));
            } else {
                GameObjectType targetItem = null;
                for (GameObjectType type1 : GameObjectType.values()) {
                    if (type1.name().equals(matcher.group("targetItem"))) {
                        targetItem = type1;
                        break;
                    }
                }
                int targetAmount = Integer.parseInt(matcher.group("targetAmount"));
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
            controller.responseTrade(respond, id);
        } else if (TradeCommands.TRADE_HISTORY.getMatcher(input) != null) {
            controller.tradeHistory();
        } else {
            System.out.println("invalid command");
        }
    }
}
