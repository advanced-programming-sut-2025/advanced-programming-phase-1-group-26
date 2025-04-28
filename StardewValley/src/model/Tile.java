package model;

import model.enums.TileTexture;

public class Tile
{
    private final int y;
    private final int x;

    private boolean hitByThunder = false;
    private TileTexture texture;
    private GameObject object;

    public Tile(int y, int x)
    {
        this.y = y;
        this.x = x;
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

    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject object) {
        this.object = object;
    }
}
