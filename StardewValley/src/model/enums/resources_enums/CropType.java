package model.enums.resources_enums;

import model.App;
import model.enums.GameObjectType;
import model.enums.Season;

import java.util.List;
import java.util.Random;

public enum CropType
{
    BLUE_JAZZ(GameObjectType.BLUE_JAZZ_CROP, "Blue Jazz", ForagingSeedType.JAZZ_SEEDS, List.of(1,2,2,2), 7, true, -1, 50, true, 45, List.of(Season.Spring), false),
    CARROT(GameObjectType.CARROT_CROP, "Carrot", ForagingSeedType.CARROT_SEEDS, List.of(1,1,1), 3, true, -1, 35, true, 75, List.of(Season.Spring), false),
    CAULIFLOWER(GameObjectType.CAULIFLOWER_CROP, "Cauliflower", ForagingSeedType.CAULIFLOWER_SEEDS, List.of(1,2,4,4,1), 12, true, -1, 175, true, 75, List.of(Season.Spring), true),
    COFFEE_BEAN(GameObjectType.COFFEE_BEAN_CROP, "Coffee Bean", ForagingSeedType.COFFEE_BEAN, List.of(1,2,2,3,2), 10, false, 2, 15, false, -1, List.of(Season.Spring, Season.Summer), false),
    GARLIC(GameObjectType.GARLIC_CROP, "Garlic", ForagingSeedType.GARLIC_SEEDS, List.of(1,1,1,1), 4, true, -1, 60, true, 20, List.of(Season.Spring), false),
    GREEN_BEAN(GameObjectType.GREEN_BEAN_CROP, "Green Bean", ForagingSeedType.BEAN_STARTER, List.of(1,1,1,3,4), 10, false, 3, 40, true, 25, List.of(Season.Spring), false),
    KALE(GameObjectType.KALE_CROP, "Kale", ForagingSeedType.KALE_SEEDS, List.of(1,2,2,1), 6, true, -1, 110, true, 50, List.of(Season.Spring), false),
    PARSNIP(GameObjectType.PARSNIP_CROP, "Parsnip", ForagingSeedType.PARSNIP_SEEDS, List.of(1,1,1,1), 4, true, -1, 35, true, 25, List.of(Season.Spring), false),
    POTATO(GameObjectType.POTATO_CROP, "Potato", ForagingSeedType.POTATO_SEEDS, List.of(1,1,1,2,1), 6, true, -1, 80, true, 25, List.of(Season.Spring), false),
    RHUBARB(GameObjectType.RHUBARB_CROP, "Rhubarb", ForagingSeedType.RHUBARB_SEEDS, List.of(2,2,2,3,4), 13, true, -1, 220, false, -1, List.of(Season.Spring), false),
    STRAWBERRY(GameObjectType.STRAWBERRY_CROP, "Strawberry", ForagingSeedType.STRAWBERRY_SEEDS, List.of(1,1,2,2,2), 8, false, 4, 120, true, 50, List.of(Season.Spring), false),
    TULIP(GameObjectType.TULIP_CROP, "Tulip", ForagingSeedType.TULIP_BULB, List.of(1,1,2,2), 6, true, -1, 30, true, 45, List.of(Season.Spring), false),
    UNMILLED_RICE(GameObjectType.UNMILLED_RICE_CROP, "Unmilled Rice", ForagingSeedType.RICE_SHOOT, List.of(1,2,2,3), 8, true, -1, 30, true, 3, List.of(Season.Spring), false),
    BLUEBERRY(GameObjectType.BLUEBERRY_CROP, "Blueberry", ForagingSeedType.BLUEBERRY_SEEDS, List.of(1,3,3,4,2), 13, false, 4, 50, true, 25, List.of(Season.Summer), false),
    CORN(GameObjectType.CORN_CROP, "Corn", ForagingSeedType.CORN_SEEDS, List.of(2,3,3,3,3), 14, false, 4, 50, true, 25, List.of(Season.Summer, Season.Fall), false),
    HOPS(GameObjectType.HOPS_CROP, "Hops", ForagingSeedType.HOPS_STARTER, List.of(1,1,2,3,4), 11, false, 1, 25, true, 45, List.of(Season.Summer), false),
    HOT_PEPPER(GameObjectType.HOT_PEPPER_CROP, "Hot Pepper", ForagingSeedType.PEPPER_SEEDS, List.of(1,1,1,1,1), 5, false, 3, 40, true, 13, List.of(Season.Summer), false),
    MELON(GameObjectType.MELON_CROP, "Melon", ForagingSeedType.MELON_SEEDS, List.of(1,2,3,3,3), 12, true, -1, 250, true, 113, List.of(Season.Summer), true),
    POPPY(GameObjectType.POPPY_CROP, "Poppy", ForagingSeedType.POPPY_SEEDS, List.of(1,2,2,2), 7, true, -1, 140, true, 45, List.of(Season.Summer), false),
    RADISH(GameObjectType.RADISH_CROP, "Radish", ForagingSeedType.RADISH_SEEDS, List.of(2,1,2,1), 6, true, -1, 90, true, 45, List.of(Season.Summer), false),
    RED_CABBAGE(GameObjectType.RED_CABBAGE_CROP, "Red Cabbage", ForagingSeedType.RED_CABBAGE_SEEDS, List.of(2,1,2,2,2), 9, true, -1, 260, true, 75, List.of(Season.Summer), false),
    STARFRUIT(GameObjectType.STARFRUIT_CROP, "Starfruit", ForagingSeedType.STARFRUIT_SEEDS, List.of(2,3,2,3,3), 13, true, -1, 750, true, 125, List.of(Season.Summer), false),
    SUMMER_SPANGLE(GameObjectType.SUMMER_SPANGLE_CROP, "Summer Spangle", ForagingSeedType.SPANGLE_SEEDS, List.of(1,2,3,1), 8, true, -1, 90, true, 45, List.of(Season.Summer), false),
    SUMMER_SQUASH(GameObjectType.SUMMER_SQUASH_CROP, "Summer Squash", ForagingSeedType.SUMMER_SQUASH_SEEDS, List.of(1,1,1,2,1), 6, false, 3, 45, true, 63, List.of(Season.Summer), false),
    SUNFLOWER(GameObjectType.SUNFLOWER_CROP, "Sunflower", ForagingSeedType.SUNFLOWER_SEEDS, List.of(1,2,3,2), 8, true, -1, 80, true, 45, List.of(Season.Summer, Season.Fall), false),
    TOMATO(GameObjectType.TOMATO_CROP, "Tomato", ForagingSeedType.TOMATO_SEEDS, List.of(2,2,2,2,3), 11, false, 4, 60, true, 20, List.of(Season.Summer), false),
    WHEAT(GameObjectType.WHEAT_CROP, "Wheat", ForagingSeedType.WHEAT_SEEDS, List.of(1,1,1,1), 4, true, -1, 25, false, -1, List.of(Season.Summer, Season.Fall), false),
    AMARANTH(GameObjectType.AMARANTH_CROP, "Amaranth", ForagingSeedType.AMARANTH_SEEDS, List.of(1,2,2,2), 7, true, -1, 150, true, 50, List.of(Season.Fall), false),
    ARTICHOKE(GameObjectType.ARTICHOKE_CROP, "Artichoke", ForagingSeedType.ARTICHOKE_SEEDS, List.of(2,2,1,2,1), 8, true, -1, 160, true, 30, List.of(Season.Fall), false),
    BEET(GameObjectType.BEET_CROP, "Beet", ForagingSeedType.BEET_SEEDS, List.of(1,1,2,2), 6, true, -1, 100, true, 30, List.of(Season.Fall), false),
    BOK_CHOY(GameObjectType.BOK_CHOY_CROP, "Bok Choy", ForagingSeedType.BOK_CHOY_SEEDS, List.of(1,1,1,1), 4, true, -1, 80, true, 25, List.of(Season.Fall), false),
    BROCCOLI(GameObjectType.BROCCOLI_CROP, "Broccoli", ForagingSeedType.BROCCOLI_SEEDS, List.of(2,2,2,2), 8, false, 4, 70, true, 63, List.of(Season.Fall), false),
    CRANBERRIES(GameObjectType.CRANBERRIES_CROP, "Cranberries", ForagingSeedType.CRANBERRY_SEEDS, List.of(1,2,1,1,2), 7, false, 5, 75, true, 38, List.of(Season.Fall), false),
    EGGPLANT(GameObjectType.EGGPLANT_CROP, "Eggplant", ForagingSeedType.EGGPLANT_SEEDS, List.of(1,1,1,1), 5, false, 5, 60, true, 20, List.of(Season.Fall), false),
    FAIRY_ROSE(GameObjectType.FAIRY_ROSE_CROP, "Fairy Rose", ForagingSeedType.FAIRY_SEEDS, List.of(1,4,4,3), 12, true, -1, 290, true, 45, List.of(Season.Fall), false),
    GRAPE(GameObjectType.GRAPE_CROP, "Grape", ForagingSeedType.GRAPE_STARTER, List.of(1,1,2,3,3), 10, false, 3, 80, true, 38, List.of(Season.Fall), false),
    PUMPKIN(GameObjectType.PUMPKIN_CROP, "Pumpkin", ForagingSeedType.PUMPKIN_SEEDS, List.of(1,2,3,4,3), 13, true, -1, 320, false, -1, List.of(Season.Fall), true),
    YAM(GameObjectType.YAM_CROP, "Yam", ForagingSeedType.YAM_SEEDS, List.of(1,3,3,3), 10, true, -1, 160, true, 45, List.of(Season.Fall), false),
    SWEET_GEM_BERRY(GameObjectType.SWEET_GEM_BERRY_CROP, "Sweet Gem Berry", ForagingSeedType.RARE_SEED, List.of(2,4,6,6,6), 24, true, -1, 3000, false, -1, List.of(Season.Fall), false),
    POWDERMELON(GameObjectType.POWDERMELON_CROP, "Powdermelon", ForagingSeedType.POWDERMELON_SEEDS, List.of(1,2,1,2,1), 7, true, -1, 60, true, 63, List.of(Season.Winter), true),
    ANCIENT_FRUIT(GameObjectType.ANCIENT_FRUIT_CROP, "Ancient Fruit", ForagingSeedType.ANCIENT_SEEDS, List.of(2,7,7,7,5), 28, false, 7, 550, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall), false);
    ;

    private final GameObjectType type;
//    private final GameObjectType result;
    private final String name;
    private final ForagingSeedType seedType;
    private final List<Integer> stages;
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final int growthTime;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final List<Season> seasons;
    private final boolean canBecomeGiant;

    CropType(GameObjectType type, String name, ForagingSeedType seedType, List<Integer> stages, int totalHarvestTime,
             boolean oneTime, int growthTime, int baseSellPrice, boolean isEdible, int energy, List<Season> seasons,
             boolean canBecomeGiant)
    {
        this.type = type;
        this.name = name;
        this.seedType = seedType;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.growthTime = growthTime;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
    }

    public ForagingSeedType getSeedType()
    {
        return seedType;
    }

    public List<Integer> getStages()
    {
        return stages;
    }

    public int getTotalHarvestTime()
    {
        return totalHarvestTime;
    }

    public boolean isOneTime()
    {
        return oneTime;
    }

    public int getGrowthTime()
    {
        return growthTime;
    }

    public int getBaseSellPrice()
    {
        return baseSellPrice;
    }

    public boolean isEdible()
    {
        return isEdible;
    }

    public int getEnergy()
    {
        return energy;
    }

    public List<Season> getSeasons()
    {
        return seasons;
    }

    public boolean isCanBecomeGiant()
    {
        return canBecomeGiant;
    }

    public String getName()
    {
        return name;
    }

    public String getCraftInfo()
    {
        StringBuilder output = new StringBuilder();
        output.append("Name: ").append(name).append("\n");
        output.append("Source: ").append(seedType.getName()).append("\n");

        output.append("Stages: ");
        for (int i = 0; i < getStages().size(); i++)
        {
            output.append(getStages().get(i)).append(i == getStages().size() - 1 ? "\n" : " - ");
        }

        output.append("Total Harvest Time: ").append(totalHarvestTime).append("\n");
        output.append("One Time: ").append(oneTime ? "TRUE" : "FALSE").append("\n");
        output.append("Regrowth Time: ").append(growthTime == -1 ? "-" : growthTime).append("\n");
        output.append("Base Sell Price: ").append(baseSellPrice == -1 ? "-" : growthTime).append("\n");
        output.append("Is Edible: ").append(isEdible ? "TRUE" : "FALSE").append("\n");
        output.append("Base Energy: ").append(energy == -1 ? "-" : energy).append("\n");

        output.append("Season(s): ");
        for (int i = 0; i < getSeasons().size(); i++)
        {
            output.append(getSeasons().get(i).getName()).append(i == getSeasons().size() - 1 ? "\n" : ", ");
        }

        output.append("Can Become Giant: ").append(canBecomeGiant ? "TRUE" : "FALSE").append("\n");

        return output.toString().trim();
    }

    public static CropType getCropType(String name)
    {
        for (CropType cropType : CropType.values())
        {
            if (cropType.getName().equalsIgnoreCase(name))
            {
                return cropType;
            }
        }
        return null;
    }

    private static final Random RANDOM = new Random();

    public GameObjectType getType()
    {
        return type;
    }

    public static CropType getCropBySeed(GameObjectType seedType)
    {
        if (seedType.equals(ForagingSeedType.MIXED_SEEDS.getType()))
        {
            CropType random = MixedSeedType.getRandomSeedCropBySeason(App.getCurrentGame().getCurrentTime().getSeason());
            return random;
        }

        for (CropType cropType : CropType.values())
        {
            if (cropType.getSeedType().getType().equals(seedType))
            {
                return cropType;
            }
        }
        return null;
    }

    public static CropType getCrop(GameObjectType type)
    {
        for (CropType crop : CropType.values())
        {
            if (crop.getType() == type)
            {
                return crop;
            }
        }
        return null;
    }
}
