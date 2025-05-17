package view;

import control.game.activities.MarketingController;
import model.App;
import model.enums.Menu;
import model.enums.regex_enums.GameCommands;
import model.enums.regex_enums.GeneralCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

//public class ShopMenu implements AppMenu{
//    MarketingController marketingController = new MarketingController();

//    @Override
//    public void check(Scanner scanner) {
//        String input = scanner.nextLine();
//        Matcher matcher;
//        if((matcher = GameCommands.SHOW_ALL_PRODUCTS.getMatcher(input)) != null) {
//            System.out.println(marketingController.showAllProducts());
//        } else if((matcher = GameCommands.SHOW_AVAILABLE_PRODUCTS.getMatcher(input)) != null) {
//            System.out.println(marketingController.showAvailableProducts());
//        } else if((matcher = GameCommands.PURCHASE.getMatcher(input)) != null) {
//            System.out.println(marketingController.purchase(input));
//        } else if((matcher = GameCommands.PURCHASE_N.getMatcher(input)) != null) {
//            System.out.println(marketingController.purchase(input));
//        } else if((matcher = GameCommands.SELL.getMatcher(input)) != null) {
//            System.out.println(marketingController.sell(input));
//        } else if((matcher = GameCommands.SELL_N.getMatcher(input)) != null) {
//            System.out.println(marketingController.sell(input));
//        } else if((matcher = GeneralCommands.TOOLS_UPGRADE.getMatcher(input)) != null) {
//            System.out.println(marketingController.upgradeTool(input));
//        } else if((matcher = GameCommands.CHEAT_ADD_MONEY.getMatcher(input)) != null) {
//            marketingController.cheatAddMoney(input);
//        } else if(input.equals("exit")) {
//            App.setCurrentMenu(Menu.GameMenu);
//        }
//    }
//}
