package model;

import model.enums.GameObjectType;
import model.enums.building_enums.ArtisanGoodsType;

public class ArtisanGood extends GameObject
{
    private final ArtisanGoodsType artisanType;

    public ArtisanGood(ArtisanGoodsType artisanType)
    {
        super(artisanType.getType(), 1);
        this.artisanType = artisanType;
    }
}
