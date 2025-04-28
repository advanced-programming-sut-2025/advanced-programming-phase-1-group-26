package model.resources;

import model.Game;
import model.GameObject;
import model.Resource;
import model.enums.resources_enums.ForagingCropType;
import model.enums.Season;

import java.util.List;

public class ForagingCrop extends GameObject
{
    private final ForagingCropType cropType;
    private List<Season> seasons;
    private int baseSellPrice;
    private int energy;

    public ForagingCrop(ForagingCropType type)
    {
        this.cropType = type;
        this.seasons = type.getSeasons();
        this.baseSellPrice = type.getBaseSellPrice();
        this.energy = type.getEnergy();
        this.type = type.getType();
    }
}
