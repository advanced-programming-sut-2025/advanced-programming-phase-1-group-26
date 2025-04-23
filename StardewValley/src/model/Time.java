package model;

import model.enums.DayOfWeek;
import model.enums.Season;

public class Time
{
    private int hour = 9;
    private int day = 1;
    private Season season = Season.Spring;

    public void updateHour(int hourNum)
    {
        hour += hourNum;
        if (hour >= 23)
        {
            int dayNum = ((hour - 23) / 14) + 1;
            hour = ((hour - 23) % 14) + 9;
            updateDay(dayNum);
        }
    }

    public void updateDay(int dayNum)
    {
        day += dayNum;
        if (day >= 29)
        {
            int seasonNum = ((day - 29) / 28) + 1;
            day = ((day - 29) % 28) + 1;
            season = season.update(seasonNum);
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
