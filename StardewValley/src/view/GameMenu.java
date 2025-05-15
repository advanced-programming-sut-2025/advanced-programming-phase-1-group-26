package view;

import control.GameController;
import control.GeneralController;
import control.game.activities.AnimalController;
import control.game.activities.MarketingController;
import model.*;
import model.enums.MapTypes;
import control.game.activities.CommunicateController;
import control.game.activities.TradeController;
import model.App;
import model.enums.Menu;
import model.enums.regex_enums.CommunicateCommands;
import model.enums.regex_enums.GameCommands;
import model.enums.regex_enums.GeneralCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu
{
    GameController gameController = new GameController();
    CommunicateController comController = new CommunicateController();
    GeneralController generalController = new GeneralController();
    AnimalController animalController = new AnimalController();
    TradeController tradeController = new TradeController();
    MarketingController marketingController = new MarketingController();
    NPC npc;

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;
        Matcher comMatcher;

        //TODO: add checkFainted in game menu

        /* Player Commands */
        if ((matcher = GeneralCommands.ENERGY_SHOW.getMatcher(input)) != null) {
            System.out.println(generalController.energyShow());
        } else if ((matcher = GeneralCommands.INVENTORY_SHOW.getMatcher(input)) != null) {
            generalController.inventoryShow();
        } else if ((matcher = GeneralCommands.INVENTORY_TRASH_NUMBER.getMatcher(input)) != null ||
                (matcher = GeneralCommands.INVENTORY_TRASH.getMatcher(input)) != null) {
            System.out.println(generalController.inventoryTrash(matcher));
        } else if ((matcher = GeneralCommands.TOOLS_EQUIP.getMatcher(input)) != null) {
            System.out.println(gameController.toolsEquip(matcher));
        } else if ((matcher = GeneralCommands.TOOLS_SHOW_CURRENT.getMatcher(input)) != null) {
            System.out.println(gameController.toolsShowCurrent());
        } else if ((matcher = GeneralCommands.TOOLS_SHOW_AVAILABLE.getMatcher(input)) != null) {
            gameController.toolsShowAvailable();
        } else if ((matcher = GeneralCommands.TOOLS_UPGRADE.getMatcher(input)) != null) {
            System.out.println(gameController.toolsUpgrade(matcher));
        } else if ((matcher = GameCommands.TOOLS_USE.getMatcher(input)) != null) {
            System.out.println(gameController.toolsUse(matcher));
        }

        /* player cheat codes */
        else if ((matcher = GeneralCommands.ENERGY_SET.getMatcher(input)) != null) {
            System.out.println(generalController.energySet(matcher));
        } else if ((matcher = GeneralCommands.ENERGY_UNLIMITED.getMatcher(input)) != null) {
            System.out.println(generalController.energyUnlimited());
        }

        /* time and date commands */
        else if (GeneralCommands.SHOW_TIME.getMatcher(input) != null)
        {
            System.out.println(generalController.showTime());
        } else if (GeneralCommands.SHOW_DATE.getMatcher(input) != null)
        {
            System.out.println(generalController.showDate());
        } else if (GeneralCommands.SHOW_DATE_TIME.getMatcher(input) != null)
        {
            System.out.println(generalController.showDateAndTime());
        } else if (GeneralCommands.SHOW_DAY_OF_WEEK.getMatcher(input) != null)
        {
            System.out.println(generalController.showDayOfWeek());
        } else if (GeneralCommands.SHOW_SEASON.getMatcher(input) != null)
        {
            System.out.println(generalController.showSeason());
        }

        /* time and date cheat codes */
        else if ((matcher = GeneralCommands.CHEAT_CODE_ADVANCE_TIME.getMatcher(input)) != null)
        {
            String time = matcher.group("time");
            System.out.println(generalController.cheatAdvanceTime(time));
        } else if ((matcher = GeneralCommands.CHEAT_CODE_ADVANCE_DATE.getMatcher(input)) != null)
        {
            String date = matcher.group("date");
            System.out.println(generalController.cheatAdvanceDate(date));
        }

        /* weather  commands*/
        else if (GeneralCommands.SHOW_WEATHER.getMatcher(input) != null)
        {
            System.out.println(generalController.showWeather());
        } else if (GeneralCommands.SHOW_TOMORROW_WEATHER.getMatcher(input) != null)
        {
            System.out.println(generalController.showTomorrowWeather());
        }

        /* weather cheat codes */
        else if ((matcher = GeneralCommands.CHEAT_CODE_SET_TOMORROW_WEATHER.getMatcher(input)) != null)
        {
            String type = matcher.group("type");
            System.out.println(generalController.cheatChangeTomorrowWeather(type));
        } else if ((matcher = GeneralCommands.CHEAT_CODE_HIT_THUNDER.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(generalController.cheatHitThunder(x,y));
        }

        /* basic map commands */
        else if ((matcher = GeneralCommands.PWD.getMatcher(input)) != null)
        {
            System.out.println(generalController.pwd());
        } else if ((matcher = GeneralCommands.SHOW_AROUND.getMatcher(input)) != null)
        {
            System.out.println(generalController.showAround());
        } else if ((matcher = GeneralCommands.PRINT_MAP.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            String size = matcher.group("size");
            System.out.println(generalController.printMap(x,y,size));
        } else if ((matcher = GeneralCommands.PRINT_ENTIRE_MAP.getMatcher(input)) != null)
        {
            System.out.println(generalController.printEntireMap());
        }

        /* walk commands */
        else if ((matcher = GeneralCommands.CAN_WALK.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(generalController.canWalk(x,y));
        } else if ((matcher = GeneralCommands.WALK.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            generalController.walk(x,y, scanner);
        } else if ((matcher = GeneralCommands.SHOW_PATH.getMatcher(input)) != null)
        {
            String x = matcher.group("x").trim();
            String y = matcher.group("y").trim();
            System.out.println(generalController.showPath(x,y));
        }

        /* walk cheat code */
        else if ((matcher = GeneralCommands.SUDO_CD.getMatcher(input)) != null)
        {
            String x = matcher.group("x").trim();
            String y = matcher.group("y").trim();
            System.out.println(generalController.sudoCD(x,y));
        }

        /* basic general commands */
        else if ((matcher = GeneralCommands.WHOAMI.getMatcher(input)) != null)
        {
            System.out.println(generalController.whoAmI());
        } else if ((matcher = GeneralCommands.NEXT_TURN.getMatcher(input)) != null)
        {
            generalController.nextTurn();
        } else if ((matcher = GeneralCommands.SUDO_NEXT_TURN.getMatcher(input)) != null)
        {
            System.out.println(generalController.sudoNextTurn());
        } else if ((matcher = GeneralCommands.EXIT_GAME.getMatcher(input)) != null)
        {
            System.out.println(generalController.exitGame(scanner));
        } else if ((matcher = GeneralCommands.DELETE_GAME.getMatcher(input)) != null)
        {
            System.out.println(generalController.deleteGame(scanner));
        }


        else if ((matcher = GameCommands.SHOW_CRAFT_INFO.getMatcher(input)) != null)
        {
            String craftName = matcher.group("craftName").trim();
            System.out.println(gameController.showCraftInfo(craftName));
        } else if ((matcher = GameCommands.PLANT_SEED.getMatcher(input)) != null)
        {
            String seed = matcher.group("seed").trim();
            String direction = matcher.group("direction").trim();
            System.out.println(gameController.plantSeed(seed,direction));
        } else if ((matcher = GameCommands.FERTILIZE.getMatcher(input)) != null)
        {
            String fertilizer = matcher.group("fertilizer").trim();
            String direction = matcher.group("direction").trim();
            System.out.println(gameController.fertilize(fertilizer,direction));
        } else if ((matcher = GameCommands.HOW_MUCH_WATER.getMatcher(input)) != null)
        {
            System.out.println(gameController.howMuchWater());
        } else if ((matcher = GameCommands.CD_PLACE.getMatcher(input)) != null)
        {
            String placeName = matcher.group("placeName").trim();
            System.out.println(gameController.goToPlace(placeName));
        } else if ((matcher = GameCommands.HELP_READ_MAP.getMatcher(input)) != null)
        {
            System.out.println(gameController.helpReadMap());
        } else if ((matcher = GameCommands.BUILD_GREENHOUSE.getMatcher(input)) != null)
        {
            System.out.println(gameController.buildGreenhouse());
        } else if ((matcher = GameCommands.CHEAT_ADD_MONEY.getMatcher(input)) != null)
        {
            String amount = matcher.group("amount").trim();
            System.out.println(gameController.cheatAddMoney(amount));
        } else if ((matcher = GameCommands.SHOW_PLANT.getMatcher(input)) != null)
        {
            String x = matcher.group("x").trim();
            String y = matcher.group("y").trim();
            System.out.println(gameController.showPlant(x,y));
        } else if (GeneralCommands.SHOW_MONEY.getMatcher(input) != null) {
            System.out.println(gameController.showMoney());
        } else if (CommunicateCommands.FRIENDSHIP.getMatcher(input) != null) {
            comController.friendships();
        } else if ((matcher = CommunicateCommands.CHEAT_UPGRADE_FRIENDSHIP.getMatcher(input)) != null) {
            System.out.println(comController.cheatUpgradeFriendship(matcher));
        } else if ((matcher = CommunicateCommands.CHEAT_UPGRADE_XP.getMatcher(input)) != null) {
            System.out.println(comController.cheatUpgradeFriendshipLevel(matcher));
        } else if ((matcher = CommunicateCommands.TALK.getMatcher(input)) != null) {
            System.out.println(comController.talk(matcher));
        } else if ((matcher = CommunicateCommands.TALK_HISTORY.getMatcher(input)) != null) {
            comController.talkHistory(matcher);
        } else if ((matcher = CommunicateCommands.GIFT.getMatcher(input)) != null) {
            comController.gift(matcher);
        } else if (CommunicateCommands.GIFT_LIST.getMatcher(input) != null) {
            comController.giftList();
        } else if ((matcher = CommunicateCommands.GIFT_RATE.getMatcher(input)) != null) {
            System.out.println(comController.giftRate(matcher));
        } else if ((matcher = CommunicateCommands.GIFT_HISTORY.getMatcher(input)) != null) {
            comController.giftHistory(matcher);
        } else if ((matcher = CommunicateCommands.HUG.getMatcher(input)) != null) {
            System.out.println(comController.giveHug(matcher));
        } else if ((matcher = CommunicateCommands.FLOWER.getMatcher(input)) != null) {
            System.out.println(comController.giveFlower(matcher));
        } else if ((matcher = CommunicateCommands.ASK_MARRIAGE.getMatcher(input)) != null) {
            System.out.println(comController.purposeAsk(matcher));
        } else if ((matcher = CommunicateCommands.RESPOND.getMatcher(input)) != null) {
            comController.purposeRespond(matcher);
        }

        else if (input.equals("5"))
        {
            Cabin cabin = new Cabin();
            System.out.println(cabin.getMapString(null, new Point(0, 0), cabin.getHEIGHT(), cabin.getWIDTH()));
        }

        else if (input.equals("6"))
        {
            GreenHouse greenHouse = new GreenHouse();
            System.out.println(greenHouse.getMapString(null, new Point(0, 0), greenHouse.getHEIGHT(), greenHouse.getWIDTH()));
        }

        else if (input.equals("7"))
        {
            City city = new City();
            System.out.println(city.getMapString(null, new Point(0, 0), city.getHEIGHT(), city.getWIDTH()));
        }

        else if (input.equals("8"))
        {
            ShopMap shop = new ShopMap();
            System.out.println(shop.getMapString(null, new Point(0, 0), shop.getHEIGHT(), shop.getWIDTH()));
        }


        else if((matcher = GameCommands.BUILD_ANIMAL_HOUSE.getMatcher(input)) != null) {
            System.out.println(animalController.buildAnimalBuilding(input));
        } else if((matcher = GameCommands.BUY_ANIMAL.getMatcher(input)) != null) {
            System.out.println(animalController.buyAnimal(input));
        } else if((matcher = GameCommands.PET_ANIMAL.getMatcher(input)) != null) {
            System.out.println(animalController.pet(input));
        } else if((matcher = GameCommands.ANIMAL_INFOS.getMatcher(input)) != null) {
            System.out.println(animalController.pet(input));
        } else if((matcher = GameCommands.SHEPHERD_ANIMAL.getMatcher(input)) != null) {
            System.out.println(animalController.shepherdAnimal(input));
        } else if((matcher = GameCommands.FEED_HAY.getMatcher(input)) != null) {
            System.out.println(animalController.feedHay(input));
        } else if((matcher = GameCommands.PRODUCES.getMatcher(input)) != null) {
            System.out.println(animalController.showProducts());
        } else if((matcher = GameCommands.SELL_ANIMAL.getMatcher(input)) != null) {
            System.out.println(animalController.sellAnimal(input));
        } else if((matcher = GameCommands.SET_FRIENDSHIP.getMatcher(input)) != null) {
            animalController.cheatSetFriendship(input);
        } else if((matcher = GameCommands.FISHING.getMatcher(input)) != null) {
            animalController.fishing(input);
        }


        else if((matcher = GameCommands.MEET_NPC.getMatcher(input)) != null) {
            System.out.println(marketingController.meetNPC(input));
            if(marketingController.meetNPC(input).isSuccessful()) npc = marketingController.targetNPC(input);
        } else if((matcher = GameCommands.GIFT_NPC.getMatcher(input)) != null) {
            System.out.println(marketingController.giftNPC(input));
        } else if((matcher = GameCommands.QUESTS_LIST.getMatcher(input)) != null) {
            System.out.println(marketingController.questsNPCList(npc));
        } else if((matcher = GameCommands.QUESTS_FINISH.getMatcher(input)) != null) {
            System.out.println(marketingController.questsFinish(input, npc));
        } else if (GeneralCommands.START_TRADE.getMatcher(input) != null) {
            App.setCurrentMenu(Menu.TradeMenu);
            System.out.println("redirecting to trade menu...");
            tradeController.tradeList();
        }

        else if ((matcher = GeneralCommands.CHEAT_TOOL_CHECK.getMatcher(input)) != null) {
            System.out.println(gameController.cheatToolCheck(matcher));
        }

        else
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

    public static void print(String output)
    {
        System.out.print(output);
    }
}
