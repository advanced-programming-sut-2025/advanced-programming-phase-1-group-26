package model.enums;

import java.util.List;

public enum Season
{
    Spring(0, "Spring", List.of(Weather.Sunny, Weather.Rain, Weather.Storm)),
    Summer(1, "Summer", List.of(Weather.Sunny, Weather.Rain, Weather.Storm)),
    Fall(2, "Fall", List.of(Weather.Sunny, Weather.Rain, Weather.Storm)),
    Winter(3, "Winter", List.of(Weather.Sunny, Weather.Snow));

    private final int id;
    private final String name;
    private final List<Weather> weatherTypes;

    Season(int id, String name, List<Weather> weatherTypes)
    {
        this.id = id;
        this.name = name;
        this.weatherTypes = weatherTypes;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public Season next()
    {
        return values()[(this.id + 1) % values().length];
    }

    public List<Weather> getWeatherTypes()
    {
        return weatherTypes;
    }
}
