package view;

import control.GameController;
import control.game.activities.MarketingController;
import model.Farm;
import model.NPC;
import model.Point;
import model.enums.FarmTypes;
import model.enums.regex_enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu
{
    GameController controller = new GameController();
    MarketingController marketingController = new MarketingController();
    NPC npc;
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

        }

        else if (input.equals("1"))
        {
            for (int i = 0; i < 100; i++)
            {
                Farm farm = new Farm(FarmTypes.STANDARD);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }
        }

        else if (input.equals("2"))
        {
            for (int i = 0; i < 100; i++)
            {

                Farm farm = new Farm(FarmTypes.RIVERLAND);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }
        }

        else if (input.equals("3"))
        {
            for (int i = 0; i < 100; i++)
            {
                Farm farm = new Farm(FarmTypes.HILL_TOP);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }
        }

        else if (input.equals("4"))
        {
            for (int i = 0; i < 100; i++)
            {
                Farm farm = new Farm(FarmTypes.BEACH);
                System.out.println(farm.getMapString(new Point(0, 0), 70, 70));
            }
        }

        else if(GameCommands.MEET_NPC.matches(input)) {
            System.out.println(marketingController.meetNPC(input));
            if(marketingController.meetNPC(input).isSuccessful()) npc = marketingController.targetNPC(input);
        } else if(GameCommands.GIFT_NPC.matches(input)) {
            System.out.println(marketingController.giftNPC(input));
        } else if(GameCommands.QUESTS_LIST.matches(input)) {
            System.out.println(marketingController.questsNPCList(npc));
        } else if(GameCommands.QUESTS_FINISH.matches(input)) {
            System.out.println(marketingController.questsFinish(input, npc));
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
