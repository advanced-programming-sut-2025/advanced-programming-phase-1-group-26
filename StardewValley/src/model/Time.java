package model;

import model.enums.DayOfWeek;
import model.enums.Season;
import model.enums.TimeOfDay;
import model.enums.Weather;

import java.util.List;
import java.util.Random;

public class Time
{
    private int hour = 9;
    private int day = 1;
    private Season season = Season.Spring;
    private TimeOfDay timeOfDay = TimeOfDay.MORNING;

    private Weather currentWeather = Weather.Sunny;
    private Weather tomorrowWeather = Weather.Sunny;

    public void updateHour(int hourNum)
    {
        hour += hourNum;
        if(hour > 9 && hour <= 12) timeOfDay = TimeOfDay.MORNING;
        if(hour > 12 && hour <= 17) timeOfDay = TimeOfDay.AFTERNOON;
        if(hour > 17 && hour <= 23) timeOfDay = TimeOfDay.EVENING;
        if (hour >= 23)
        {
            int dayNum = ((hour - 23) / 14) + 1;
            hour = ((hour - 23) % 14) + 9;
            updateDay(dayNum);
        }
    }

    public void updateDay(int dayNum)
    {
        //setting energies to maximum
        for (Player player : App.getCurrentGame().getPlayers()) {
            player.setEnergy(player.getMaxEnergy());
        }
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

    public Weather getCurrentWeather()
    {
        return currentWeather;
    }

    public Weather getTomorrowWeather()
    {
        return tomorrowWeather;
    }

    public void setTomorrowWeather(Weather tomorrowWeather)
    {
        this.tomorrowWeather = tomorrowWeather;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    private void updateWeather()
    {
        currentWeather = tomorrowWeather;

        Random random = new Random();
        List<Weather> list = season.getWeatherTypes();
        tomorrowWeather = list.get(random.nextInt(list.size()));
    }


}
