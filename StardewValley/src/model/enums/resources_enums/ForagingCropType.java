package model.enums.resources_enums;

import model.enums.Season;

import java.util.List;

public enum ForagingCropType
{
    COMMON_MUSHROOM_FORAGING_CROP(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter), 40, 38),
    DAFFODIL_FORAGING_CROP(List.of(Season.Spring), 30, 0),
    DANDELION_FORAGING_CROP(List.of(Season.Spring), 40, 25),
    LEEK_FORAGING_CROP(List.of(Season.Spring), 60, 40),
    MOREL_FORAGING_CROP(List.of(Season.Spring), 150, 20),
    SALMONBERRY_FORAGING_CROP(List.of(Season.Spring), 5, 25),
    SPRING_ONION_FORAGING_CROP(List.of(Season.Spring), 8, 13),
    WILD_HORSERADISH_FORAGING_CROP(List.of(Season.Spring), 50, 13),
    FIDDLEHEAD_FERN_FORAGING_CROP(List.of(Season.Summer), 90, 25),
    GRAPE_FORAGING_CROP(List.of(Season.Summer), 80, 38),
    RED_MUSHROOM_FORAGING_CROP(List.of(Season.Summer), 75, -50),
    SPICE_BERRY_FORAGING_CROP(List.of(Season.Summer), 80, 25),
    SWEET_PEA_FORAGING_CROP(List.of(Season.Summer), 50, 0),
    BLACKBERRY_FORAGING_CROP(List.of(Season.Fall), 25, 25),
    CHANTERELLE_FORAGING_CROP(List.of(Season.Fall), 160, 75),
    HAZELNUT_FORAGING_CROP(List.of(Season.Fall), 40, 38),
    PURPLE_MUSHROOM_FORAGING_CROP(List.of(Season.Fall), 90, 30),
    WILD_PLUM_FORAGING_CROP(List.of(Season.Fall), 80, 25),
    CROCUS_FORAGING_CROP(List.of(Season.Winter), 60, 0),
    CRYSTAL_FRUIT_FORAGING_CROP(List.of(Season.Winter), 150, 63),
    HOLLY_FORAGING_CROP(List.of(Season.Winter), 80, -37),
    SNOW_YAM_FORAGING_CROP(List.of(Season.Winter), 100, 30),
    WINTER_ROOT_FORAGING_CROP(List.of(Season.Winter), 70, 25);

    private final List<Season> seasons;
    private final int baseSellPrice;
    private final int energy;

    ForagingCropType(List<Season> seasons, int baseSellPrice, int energy)
    {
        this.seasons = seasons;
        this.baseSellPrice = baseSellPrice;
        this.energy = energy;
    }

    public List<Season> getSeasons()
    {
        return seasons;
    }

    public int getBaseSellPrice()
    {
        return baseSellPrice;
    }

    public int getEnergy()
    {
        return energy;
    }
}
