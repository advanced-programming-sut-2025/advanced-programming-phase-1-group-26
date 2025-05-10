package model;

public abstract class Map
{
    protected Tile[][] tiles;
    protected int width, height;
    protected Point startingPoint;

    public abstract Tile getTile(int x, int y);
    public abstract boolean isInBounds(int x, int y);
    public abstract String getMapString(Point location, int height, int width);
    public Point getStartingPoint() { return startingPoint; }
}

