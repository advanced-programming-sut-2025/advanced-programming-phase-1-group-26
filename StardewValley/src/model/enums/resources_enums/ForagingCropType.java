package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum ForagingCropType
{
    COMMON_MUSHROOM_FORAGING_CROP(GameObjectType.COMMON_MUSHROOM_FORAGING_CROP, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter), 40, 38),
    DAFFODIL_FORAGING_CROP(GameObjectType.DAFFODIL_FORAGING_CROP, List.of(Season.Spring), 30, 0),
    DANDELION_FORAGING_CROP(GameObjectType.DANDELION_FORAGING_CROP, List.of(Season.Spring), 40, 25),
    LEEK_FORAGING_CROP(GameObjectType.LEEK_FORAGING_CROP, List.of(Season.Spring), 60, 40),
    MOREL_FORAGING_CROP(GameObjectType.MOREL_FORAGING_CROP, List.of(Season.Spring), 150, 20),
    SALMONBERRY_FORAGING_CROP(GameObjectType.SALMONBERRY_FORAGING_CROP, List.of(Season.Spring), 5, 25),
    SPRING_ONION_FORAGING_CROP(GameObjectType.SPRING_ONION_FORAGING_CROP, List.of(Season.Spring), 8, 13),
    WILD_HORSERADISH_FORAGING_CROP(GameObjectType.WILD_HORSERADISH_FORAGING_CROP, List.of(Season.Spring), 50, 13),
    FIDDLEHEAD_FERN_FORAGING_CROP(GameObjectType.FIDDLEHEAD_FERN_FORAGING_CROP, List.of(Season.Summer), 90, 25),
    GRAPE_FORAGING_CROP(GameObjectType.GRAPE_FORAGING_CROP, List.of(Season.Summer), 80, 38),
    RED_MUSHROOM_FORAGING_CROP(GameObjectType.RED_MUSHROOM_FORAGING_CROP, List.of(Season.Summer), 75, -50),
    SPICE_BERRY_FORAGING_CROP(GameObjectType.SPICE_BERRY_FORAGING_CROP, List.of(Season.Summer), 80, 25),
    SWEET_PEA_FORAGING_CROP(GameObjectType.SWEET_PEA_FORAGING_CROP, List.of(Season.Summer), 50, 0),
    BLACKBERRY_FORAGING_CROP(GameObjectType.BLACKBERRY_FORAGING_CROP, List.of(Season.Fall), 25, 25),
    CHANTERELLE_FORAGING_CROP(GameObjectType.CHANTERELLE_FORAGING_CROP, List.of(Season.Fall), 160, 75),
    HAZELNUT_FORAGING_CROP(GameObjectType.HAZELNUT_FORAGING_CROP, List.of(Season.Fall), 40, 38),
    PURPLE_MUSHROOM_FORAGING_CROP(GameObjectType.PURPLE_MUSHROOM_FORAGING_CROP, List.of(Season.Fall), 90, 30),
    WILD_PLUM_FORAGING_CROP(GameObjectType.WILD_PLUM_FORAGING_CROP, List.of(Season.Fall), 80, 25),
    CROCUS_FORAGING_CROP(GameObjectType.CROCUS_FORAGING_CROP, List.of(Season.Winter), 60, 0),
    CRYSTAL_FRUIT_FORAGING_CROP(GameObjectType.CRYSTAL_FRUIT_FORAGING_CROP, List.of(Season.Winter), 150, 63),
    HOLLY_FORAGING_CROP(GameObjectType.HOLLY_FORAGING_CROP, List.of(Season.Winter), 80, -37),
    SNOW_YAM_FORAGING_CROP(GameObjectType.SNOW_YAM_FORAGING_CROP, List.of(Season.Winter), 100, 30),
    WINTER_ROOT_FORAGING_CROP(GameObjectType.WINTER_ROOT_FORAGING_CROP, List.of(Season.Winter), 70, 25);

    private final GameObjectType type;
    private final List<Season> seasons;
    private final int baseSellPrice;
    private final int energy;

    ForagingCropType(GameObjectType type, List<Season> seasons, int baseSellPrice, int energy)
    {
        this.type = type;
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

    public GameObjectType getType()
    {
        return type;
    }

    public static ArrayList<ForagingCropType> getSeasonCrops(Season season)
    {
        ArrayList<ForagingCropType> seasonCrops = new ArrayList<>();
        for (ForagingCropType foragingCropType : ForagingCropType.values())
        {
            if (foragingCropType.seasons.contains(season))
            {
                seasonCrops.add(foragingCropType);
            }
        }
        return seasonCrops;
    }

    public static ForagingCropType getRandomSeasonCrop(Season season)
    {
        ArrayList<ForagingCropType> seasonCrops = getSeasonCrops(season);
        int index = ThreadLocalRandom.current().nextInt(seasonCrops.size());
        return seasonCrops.get(index);
    }

    public String getCraftInfo()
    {
        StringBuilder output = new StringBuilder();
        output.append("Name: ").append(this.name().replace("_FORAGING_CROP", "").replace('_', ' ').toLowerCase()).append("\n");
        output.append("Base Sell Price: ").append(baseSellPrice).append("\n");
        output.append("Is Edible: ").append(energy > 0 ? "TRUE" : "FALSE").append("\n");
        output.append("Base Energy: ").append(energy).append("\n");

        output.append("Season(s): ");
        for (int i = 0; i < seasons.size(); i++)
        {
            output.append(seasons.get(i).getName()).append(i == seasons.size() - 1 ? "\n" : ", ");
        }

        return output.toString().trim();
    }

    public String getName()
    {
        return type.toString();
    }

    public static ForagingCropType getForagingCropType(String name)
    {
        for (ForagingCropType foragingCropType : ForagingCropType.values())
        {
            if (foragingCropType.getName().equalsIgnoreCase(name))
            {
                return foragingCropType;
            }
        }
        return null;
    }

    public static ForagingCropType getMushroom(GameObjectType type)
    {
        if (type == GameObjectType.COMMON_MUSHROOM_FORAGING_CROP)
        {
            return ForagingCropType.COMMON_MUSHROOM_FORAGING_CROP;
        }

        if (type == GameObjectType.RED_MUSHROOM_FORAGING_CROP)
        {
            return ForagingCropType.RED_MUSHROOM_FORAGING_CROP;
        }

        if (type == GameObjectType.PURPLE_MUSHROOM_FORAGING_CROP)
        {
            return ForagingCropType.PURPLE_MUSHROOM_FORAGING_CROP;
        }

        return null;
    }
}
