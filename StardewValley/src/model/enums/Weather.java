package model.enums;

public enum Weather
{
    Sunny("sunny"),
    Rain("rain"),
    Storm("storm"),
    Snow("snow");

    private final String name;

    Weather(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public static Weather getWeather(String name)
    {
        for (Weather weather : Weather.values())
        {
            if (weather.name.equals(name))
            {
                return weather;
            }
        }

        return null;
    }
}
