package model;

import model.enums.GameObjectType;
import model.enums.building_enums.ArtisanGoodsType;

public class ArtisanGood extends GameObject
{
    private Tile tile;
    private final ArtisanGoodsType artisanType;
    private int lastStartedToWork = 0;

    public ArtisanGood(ArtisanGoodsType artisanType)
    {
        super(artisanType.getType(), 1);
        this.artisanType = artisanType;
    }

    public ArtisanGoodsType getArtisanType()
    {
        return artisanType;
    }

    public int getLastStartedToWork()
    {
        return lastStartedToWork;
    }

    public void setLastStartedToWork(int lastStartedToWork)
    {
        this.lastStartedToWork = lastStartedToWork;
    }

    public Tile getTile()
    {
        return tile;
    }


}
