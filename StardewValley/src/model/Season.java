package model;

public enum Season
{
    Spring(0, "Spring"),
    Summer(1, "Summer"),
    Fall(2, "Fall"),
    Winter(3, "Winter");

    private final int id;
    private final String name;

    Season(int id, String name)
    {
        this.id = id;
        this.name = name;
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
}
