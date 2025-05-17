package model.enums.resources_enums;

import model.enums.Season;

import java.util.List;
import java.util.Random;

public enum MixedSeedType
{
    SPRING(Season.Spring, List.of(CropType.CAULIFLOWER, CropType.PARSNIP, CropType.POTATO,
            CropType.BLUE_JAZZ, CropType.TULIP)),
    SUMMER(Season.Summer, List.of(CropType.CORN, CropType.HOT_PEPPER, CropType.RADISH,
            CropType.WHEAT, CropType.POPPY, CropType.SUNFLOWER, CropType.SUMMER_SPANGLE)),
    FALL(Season.Fall, List.of(CropType.ARTICHOKE, CropType.CORN, CropType.EGGPLANT,
            CropType.PUMPKIN, CropType.SUNFLOWER, CropType.FAIRY_ROSE)),
    WINTER(Season.Winter, List.of(CropType.POWDERMELON));

    private final Season season;
    private final List<CropType> possibleCrops;

    MixedSeedType(Season season, List<CropType> possibleCrops)
    {
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

    public static CropType getRandomSeedCropBySeason(Season season)
    {
        for (MixedSeedType mixedSeedType : MixedSeedType.values())
        {
            if (mixedSeedType.getSeason().equals(season))
            {
                Random random = new Random();
                return mixedSeedType.getPossibleCrops().get(random.nextInt(mixedSeedType.getPossibleCrops().size()));
            }
        }
        return null;
    }
}
