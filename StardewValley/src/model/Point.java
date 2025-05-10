package model;

public class Point
{
    private final int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Point) && ((Point) obj).x == this.x && ((Point) obj).y == this.y;
    }
}
