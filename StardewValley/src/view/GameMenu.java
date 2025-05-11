package view;

import control.GameController;
import model.*;
import model.enums.MapTypes;
import control.game.activities.CommunicateController;
import model.Farm;
import model.Point;
import model.enums.regex_enums.CommunicateCommands;
import model.enums.regex_enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu
{
    GameController controller = new GameController();
    CommunicateController comController = new CommunicateController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        //TODO: add checkFainted in game menu

        if (GameCommands.SHOW_TIME.getMatcher(input) != null)
        {
            System.out.println(controller.showTime());
        } else if (GameCommands.SHOW_DATE.getMatcher(input) != null)
        {
            System.out.println(controller.showDate());
        } else if (GameCommands.SHOW_DATE_TIME.getMatcher(input) != null)
        {
            System.out.println(controller.showDateAndTime());
        } else if (GameCommands.SHOW_DAY_OF_WEEK.getMatcher(input) != null)
        {
            System.out.println(controller.showDayOfWeek());
        } else if (GameCommands.SHOW_SEASON.getMatcher(input) != null)
        {
            System.out.println(controller.showSeason());
        } else if ((matcher = GameCommands.CHEAT_CODE_ADVANCE_TIME.getMatcher(input)) != null)
        {
            String time = matcher.group("time");
            System.out.println(controller.cheatAdvanceTime(time));
        } else if ((matcher = GameCommands.CHEAT_CODE_ADVANCE_DATE.getMatcher(input)) != null)
        {
            String date = matcher.group("date");
            System.out.println(controller.cheatAdvanceDate(date));
        } else if (GameCommands.SHOW_WEATHER.getMatcher(input) != null)
        {
            System.out.println(controller.showWeather());
        } else if (GameCommands.SHOW_TOMORROW_WEATHER.getMatcher(input) != null)
        {
            System.out.println(controller.showTomorrowWeather());
        } else if ((matcher = GameCommands.CHEAT_CODE_SET_TOMORROW_WEATHER.getMatcher(input)) != null)
        {
            String type = matcher.group("type");
            System.out.println(controller.cheatChangeTomorrowWeather(type));
        } else if ((matcher = GameCommands.CHEAT_CODE_HIT_THUNDER.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(controller.cheatHitThunder(x,y));
        } else if ((matcher = GameCommands.CAN_WALK.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(controller.canWalk(x,y));
        } else if ((matcher = GameCommands.WALK.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(controller.walk(x,y));
        } else if ((matcher = GameCommands.PRINT_MAP.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            String size = matcher.group("size");

        } else if ((matcher = GameCommands.SHOW_CRAFT_INFO.getMatcher(input)) != null)
        {
            String craftName = matcher.group("craftName").trim();
            System.out.println(controller.showCraftInfo(craftName));
        } else if ((matcher = GameCommands.PLANT_SEED.getMatcher(input)) != null)
        {
            String seed = matcher.group("seed").trim();
            String direction = matcher.group("direction").trim();
            System.out.println(controller.plantSeed(seed,direction));
        } else if ((matcher = GameCommands.FERTILIZE.getMatcher(input)) != null)
        {
            String fertilizer = matcher.group("fertilizer").trim();
            String direction = matcher.group("direction").trim();
            System.out.println(controller.fertilize(fertilizer,direction));
        } else if ((matcher = GameCommands.HOW_MUCH_WATER.getMatcher(input)) != null)
        {
            System.out.println(controller.howMuchWater());
        } else if ((matcher = GameCommands.EXIT_GAME.getMatcher(input)) != null)
        {
            System.out.println(controller.exitGame());
        } else if ((matcher = GameCommands.DELETE_GAME.getMatcher(input)) != null)
        {
            System.out.println(controller.deleteGame());
        } else if ((matcher = GameCommands.NEXT_TURN.getMatcher(input)) != null)
        {
            controller.nextTurn();
        } else if ((matcher = GameCommands.FORCE_NEXT_TURN.getMatcher(input)) != null)
        {
            System.out.println(controller.forceNextTurn());
        }
        else if ((matcher = GameCommands.GO_TO_CABIN.getMatcher(input)) != null)
        {
            System.out.println(controller.goToCabin());
        } else if ((matcher = GameCommands.WHOAMI.getMatcher(input)) != null)
        {
            System.out.println(controller.whoAmI());
        }

        else if (input.equals("1"))
        {
            for (int i = 0; i < 100; i++)
            {
                Farm farm = new Farm(MapTypes.STANDARD);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }
        }

        else if (input.equals("2"))
        {
            for (int i = 0; i < 100; i++)
            {

                Farm farm = new Farm(MapTypes.RIVERLAND);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }
        }

        else if (input.equals("3"))
        {
            for (int i = 0; i < 100; i++)
            {
                Farm farm = new Farm(MapTypes.HILL_TOP);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }
        }

        else if (input.equals("4"))
        {
            for (int i = 0; i < 100; i++)
            {
                Farm farm = new Farm(MapTypes.BEACH);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }

        } else if ((matcher = GameCommands.ENERGY_SHOW.getMatcher(input)) != null) {
            System.out.println(controller.energyShow());
        } else if ((matcher = GameCommands.ENERGY_SET.getMatcher(input)) != null) {
            System.out.println(controller.energySet(matcher));
        } else if ((matcher = GameCommands.ENERGY_UNLIMITED.getMatcher(input)) != null) {
            System.out.println(controller.energyUnlimited());
        } else if ((matcher = GameCommands.INVENTORY_SHOW.getMatcher(input)) != null) {
            controller.inventoryShow();
        } else if ((matcher = GameCommands.INVENTORY_TRASH_NUMBER.getMatcher(input)) != null ||
        (matcher = GameCommands.INVENTORY_TRASH.getMatcher(input)) != null) {
            System.out.println(controller.inventoryTrash(matcher));
        } else if ((matcher = GameCommands.TOOLS_EQUIP.getMatcher(input)) != null) {
            System.out.println(controller.toolsEquip(matcher));
        } else if ((matcher = GameCommands.TOOLS_SHOW_CURRENT.getMatcher(input)) != null) {
            System.out.println(controller.toolsShowCurrent());
        } else if ((matcher = GameCommands.TOOLS_SHOW_AVAILABLE.getMatcher(input)) != null) {
            controller.toolsShowAvailable();
        } else if ((matcher = GameCommands.TOOLS_UPGRADE.getMatcher(input)) != null) {
            System.out.println(controller.toolsUpgrade(matcher));
        } else if ((matcher = GameCommands.TOOLS_USE.getMatcher(input)) != null) {
            controller.toolsUse(matcher);
        } else if (CommunicateCommands.FRIENDSHIP.getMatcher(input) != null) {
            comController.friendships();
        } else if ((matcher = CommunicateCommands.TALK.getMatcher(input)) != null) {
            System.out.println(comController.talk(matcher));
        } else if ((matcher = CommunicateCommands.TALK_HISTORY.getMatcher(input)) != null) {
            comController.talkHistory(matcher);
        } else if ((matcher = CommunicateCommands.GIFT.getMatcher(input)) != null) {
            comController.gift(matcher);
        } else if ((matcher = CommunicateCommands.GIFT_LIST.getMatcher(input)) != null) {
            comController.giftList();
        } else if ((matcher = CommunicateCommands.GIFT_RATE.getMatcher(input)) != null) {
            comController.giftRate(matcher);
        } else if ((matcher = CommunicateCommands.GIFT_HISTORY.getMatcher(input)) != null) {
            comController.giftHistory(matcher);
        } else if ((matcher = CommunicateCommands.HUG.getMatcher(input)) != null) {
            comController.giveHug(matcher);
        } else if ((matcher = CommunicateCommands.FLOWER.getMatcher(input)) != null) {
            comController.giveFlower(matcher);
        } else if ((matcher = CommunicateCommands.ASK_MARRIAGE.getMatcher(input)) != null) {
            comController.purposeAsk(matcher);
        } else if ((matcher = CommunicateCommands.RESPOND.getMatcher(input)) != null) {
            comController.purposeRespond(matcher);
        }

        else if (input.equals("5"))
        {
            Cabin cabin = new Cabin();
            System.out.println(cabin.getMapString(new Point(0, 0), cabin.getHEIGHT(), cabin.getWIDTH()));
        }

        else if (input.equals("6"))
        {
            GreenHouse greenHouse = new GreenHouse();
            System.out.println(greenHouse.getMapString(new Point(0, 0), greenHouse.getHEIGHT(), greenHouse.getWIDTH()));
        }

        else if (input.equals("7"))
        {
            City city = new City();
            System.out.println(city.getMapString(new Point(0, 0), city.getHEIGHT(), city.getWIDTH()));
        }

        else if (input.equals("8"))
        {
            ShopMap shop = new ShopMap();
            System.out.println(shop.getMapString(new Point(0, 0), shop.getHEIGHT(), shop.getWIDTH()));
        }

        else
        {
            System.out.println("invalid command");
        }
    }

    public static String scan()
    {
        String text = mainScanner.nextLine().trim();
        return text;
    }

    public static void println(String output)
    {
        System.out.println(output);
    }
}
