package model;

import model.enums.DayOfWeek;
import model.enums.Season;

public class Time
{
    private int hour = 9;
    private int day = 1;
    private Season season = Season.Spring;

    public void update()
    {
        hour += 1;
        if (hour == 22)
        {
            endDay();
        }
    }

    private void endDay()
    {
        hour = 9;
        day += 1;
        if (day > 28)
        {
            day = 1;
            season = season.next();
        }
        //setting energies to maximum
        for (Player player : App.getCurrentGame().getPlayers()) {
            player.setEnergy(player.getMaxEnergy());
        }
    }

    public int getHour()
    {
        return hour;
    }

    public int getDay()
    {
        return day;
    }

    public Season getSeason()
    {
        return season;
    }

    public DayOfWeek getDayOfWeek()
    {
        return DayOfWeek.values()[(day - 1) % DayOfWeek.values().length];
    }

    public void advanceHour(int amount) {}

    public void advanceDay(int amount) {}
}
