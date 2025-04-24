package model;

import model.enums.TileTexture;

public class Tile
{
    private final Point point;

    private boolean hitByThunder = false;
    private TileTexture texture = null;

    private GameObject object = null;

    public Tile(Point point)
    {
        this.point = point;
    }

    public int getY()
    {
        return point.getY();
    }

    public int getX()
    {
        return point.getX();
    }

    public boolean isHitByThunder()
    {
        return hitByThunder;
    }

    public void hitByThunder()
    {
        hitByThunder = true;
    }

    public TileTexture getTexture()
    {
        return texture;
    }

    public void setTexture(TileTexture texture)
    {
        this.texture = texture;
    }

    public GameObject getObject()
    {
        return object;
    }
}
