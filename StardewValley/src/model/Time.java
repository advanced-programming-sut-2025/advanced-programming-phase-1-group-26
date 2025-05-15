package model;

import model.enums.Season;
import model.enums.TimeOfDay;
import model.enums.Weather;
import model.player_data.FriendshipData;

import java.util.List;
import java.util.Random;

public class Time
{
    private int totalDaysPassed = 1;
    private int hour = 9;
    private int day = 1;
    private Season season = Season.Spring;
    private TimeOfDay timeOfDay = TimeOfDay.MORNING;

    private Weather currentWeather = Weather.Sunny;
    private Weather tomorrowWeather = Weather.Sunny;

    public void updateHour(int hourNum)
    {
        hour += hourNum;

        if (hour > 22)
        {
            int dayNum = ((hour - 23) / 14) + 1;
            hour = ((hour - 23) % 14) + 9;
            updateDay(dayNum, false); // TODO: if uses cheat code for advance time in large amount,
        }

        if(hour > 9 && hour <= 12) timeOfDay = TimeOfDay.MORNING;
        if(hour > 12 && hour <= 17) timeOfDay = TimeOfDay.AFTERNOON;
        if(hour > 17 && hour <= 23) timeOfDay = TimeOfDay.EVENING;
    }

    public void updateDay(int dayNum, boolean hasCheated)
    {
        // setting energies to maximum
        for (Player player : App.getCurrentGame().getPlayers())
        {
            player.setEnergy(player.getMaxEnergy());
        }

        day += dayNum;
        totalDaysPassed += dayNum;

        for (int i = 0; i < dayNum; i++)
        {
            if (hasCheated)
            {
                App.getCurrentGame().waterAllFarmPlants();
            }

            App.getCurrentGame().endDay();
        }

        updateWeather();
        friendshipUpdate();

        if (day > 28)
        {
            int seasonNum = ((day - 29) / 28) + 1;
            day = ((day - 29) % 28) + 1;
            season = season.update(seasonNum);
        }

        Game game = App.getCurrentGame();
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

    public void friendshipUpdate() {
        for (Player player1 : App.getCurrentGame().getPlayers()) {
            for (Player player2 : App.getCurrentGame().getPlayers()) {
                if (player1 != player2) {
                    FriendshipData data1 = player1.getFriendships().get(player2);
                    FriendshipData data2 = player2.getFriendships().get(player1);
                    data1.setIntrcatedToday(false);
                    data2.setIntrcatedToday(false);
                }
            }
        }
    }

    public int getTotalDaysPassed()
    {
        return totalDaysPassed;
    }
}
