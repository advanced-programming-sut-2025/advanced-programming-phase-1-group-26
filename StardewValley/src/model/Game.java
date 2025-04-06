package model;

import model.enums.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{
    private Time currentTime = new Time();
    private Weather currentWeather = Weather.Sunny;
    private Weather tomorrowWeather = Weather.Sunny;
    private ArrayList<NPC> NPCs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Map map;

    public Game(ArrayList<Player> players, int height, int width)
    {
        this.players = players;
        this.map = new Map(height, width);
    }

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

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }
    public void setNPCs(ArrayList<NPC> NPCs) {
        this.NPCs = NPCs;
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
