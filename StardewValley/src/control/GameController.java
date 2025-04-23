package control;

import model.App;
import model.Result;
import model.Time;
import model.enums.DayOfWeek;
import model.enums.Season;

public class GameController
{
    public Result showTime()
    {
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentTime().getHour()));
    }

    public Result showDate()
    {
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentTime().getDay()));
    }

    public Result cheatAdvanceTime(String timeAdvance)
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();
        int time = Integer.parseInt(timeAdvance);
        StringBuilder output = new StringBuilder();

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");

        gameTime.updateHour(time);

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");
        output.append("Time advanced successfully using cheat code :)");

        return new Result(true, output.toString());
    }

    public Result cheatAdvanceDate(String dateAdvance)
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();
        int date = Integer.parseInt(dateAdvance);
        StringBuilder output = new StringBuilder();

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");

        gameTime.updateDay(date);

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");
        output.append("Date advanced successfully using cheat code :)");

        return new Result(true, output.toString());
    }

    public Result showDateAndTime()
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();

        String output = "time: " + gameTime.getHour() +
                ", day: " + gameTime.getDay();

        return new Result(true, output);
    }

    public Result showDayOfWeek()
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();
        String dayName = DayOfWeek.getDayOfWeek((gameTime.getDay() - 1) % 7);

        return new Result(true, "Today is " + dayName + ".");
    }

    public Result showSeason()
    {
        Season season = App.getCurrentGame().getCurrentTime().getSeason();
        return new Result(true, "You're currently in " + season.toString() + ".");
    }

    public Result createNewGame(String[] usernames)
    {
        return null;
    }

    public Result selectMap(String mapNumber)
    {
        return null;
    }

    public Result loadGame()
    {
        return null;
    }

    public Result exitGame()
    {
        return null;
    }

    public Result nextTurn()
    {
        return null;
    }
}
