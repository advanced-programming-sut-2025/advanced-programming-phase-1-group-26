package view;

import control.GameController;
import model.App;
import model.Point;
import model.enums.regex_enums.GameCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu
{
    GameController controller = new GameController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if (input.equals("map"))
        {
            App.getCurrentGame().getFarm().initializeFarm();
            System.out.println(App.getCurrentGame().getFarm().getMapString(new Point(0, 0), 70, 70));
        }

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
        }
        else
        {
            System.out.println("invalid command");
        }
    }
}
