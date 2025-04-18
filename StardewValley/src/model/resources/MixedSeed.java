package model.resources;

import model.enums.resources_enums.CropType;
import model.enums.resources_enums.MixedSeedType;
import model.enums.Season;

import java.util.List;

public class MixedSeed
{
    private final MixedSeedType seedType;
    private Season season;
    private List<CropType> possibleCrops;

    public MixedSeed(MixedSeedType seedType)
    {
        this.seedType = seedType;
        this.season = seedType.getSeason();
        this.possibleCrops = seedType.getPossibleCrops();
    }
}
