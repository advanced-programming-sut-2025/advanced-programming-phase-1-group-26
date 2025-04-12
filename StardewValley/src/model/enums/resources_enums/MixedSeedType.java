package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.List;

public enum MixedSeedType
{
    SPRING(GameObjectType.Spring_MIXED_SEED, Season.Spring, List.of(CropType.CAULIFLOWER, CropType.PARSNIP, CropType.POTATO,
            CropType.BLUE_JAZZ, CropType.TULIP)),
    SUMMER(GameObjectType.SUMMER_MIXED_SEED, Season.Summer, List.of(CropType.CORN, CropType.HOT_PEPPER, CropType.RADISH,
            CropType.WHEAT, CropType.POPPY, CropType.SUNFLOWER, CropType.SUMMER_SPANGLE)),
    FALL(GameObjectType.FALL_MIXED_SEED, Season.Fall, List.of(CropType.ARTICHOKE, CropType.CORN, CropType.EGGPLANT,
            CropType.PUMPKIN, CropType.SUNFLOWER, CropType.FAIRY_ROSE)),
    WINTER(GameObjectType.WINTER_MIXED_SEED, Season.Winter, List.of(CropType.POWDERMELON));

    private final GameObjectType type;
    private final Season season;
    private final List<CropType> possibleCrops;

    MixedSeedType(GameObjectType type, Season season, List<CropType> possibleCrops)
    {
        this.type = type;
        this.season = season;
        this.possibleCrops = possibleCrops;
    }

    public Season getSeason()
    {
        return season;
    }

    public List<CropType> getPossibleCrops()
    {
        return possibleCrops;
    }
}
