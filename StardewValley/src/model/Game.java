package model;

import model.enums.Weather;

import java.util.List;
import java.util.Random;

public class Game
{
    private Time currentTime = new Time();
    private Weather currentWeather = Weather.Sunny;
    private Weather tomorrowWeather = Weather.Sunny;

    public Time getCurrentTime()
    {
        return currentTime;
    }

    public Weather getCurrentWeather()
    {
        return currentWeather;
    }

    public Weather getTomorrowWeather()
    {
        return tomorrowWeather;
    }

    public void updateTime()
    {
        currentTime.update();
        currentWeather = tomorrowWeather;

        Random random = new Random();
        List<Weather> list = currentTime.getSeason().getWeatherTypes();
        tomorrowWeather = list.get(random.nextInt(list.size()));
    }
}
