package model.enums;

public enum Weather
{
    Sunny("Sunny"),
    Rain("Rain"),
    Storm("Storm"),
    Snow("Snow");

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
}
