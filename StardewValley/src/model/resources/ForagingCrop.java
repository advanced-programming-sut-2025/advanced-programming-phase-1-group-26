package model.resources;

import model.enums.resources_enums.ForagingCropType;
import model.enums.Season;

import java.util.List;

public class ForagingCrop
{
    private final ForagingCropType type;
    private List<Season> seasons;
    private int baseSellPrice;
    private int energy;

    public ForagingCrop(ForagingCropType type)
    {
        this.type = type;
        this.seasons = type.getSeasons();
        this.baseSellPrice = type.getBaseSellPrice();
        this.energy = type.getEnergy();
    }
}
