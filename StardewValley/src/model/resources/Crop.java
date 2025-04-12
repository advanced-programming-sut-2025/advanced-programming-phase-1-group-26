package model.resources;

import model.GameObject;
import model.enums.resources_enums.CropType;
import model.enums.resources_enums.ForagingSeedType;
import model.enums.Season;

import java.util.List;

public class Crop extends GameObject
{
    private CropType cropType;
    private ForagingSeedType seedType;
    private List<Integer> stages;
    private int totalHarvestTime;
    private boolean oneTime;
    private int growthTime;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private List<Season> seasons;
    private boolean canBecomeGiant;

    public Crop(CropType cropType)
    {
        this.cropType = cropType;
        this.seedType = cropType.getSeedType();
        this.stages = cropType.getStages();
        this.totalHarvestTime = cropType.getTotalHarvestTime();
        this.oneTime = cropType.isOneTime();
        this.growthTime = cropType.getGrowthTime();
        this.baseSellPrice = cropType.getBaseSellPrice();
        this.isEdible = cropType.isEdible();
        this.energy = cropType.getEnergy();
        this.seasons = cropType.getSeasons();
        this.canBecomeGiant = cropType.isCanBecomeGiant();
    }
}
