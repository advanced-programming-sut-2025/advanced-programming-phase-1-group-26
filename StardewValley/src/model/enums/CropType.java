package model.enums;

import java.util.List;

public enum CropType
{
    BLUE_JAZZ(GameObjectType.JAZZ_SEEDS, ForagingSeedType.JAZZ_SEEDS, List.of(1,2,2,2), 7, true, -1, 50, true, 45, List.of(Season.Spring), false),
    CARROT(GameObjectType.CARROT_CROP, ForagingSeedType.CARROT_SEEDS, List.of(1,1,1), 3, true, -1, 35, true, 75, List.of(Season.Spring), false),
    CAULIFLOWER(GameObjectType.CAULIFLOWER_CROP, ForagingSeedType.CAULIFLOWER_SEEDS, List.of(1,2,4,4,1), 12, true, -1, 175, true, 75, List.of(Season.Spring), true),
    COFFEE_BEAN(GameObjectType.COFFEE_BEAN, ForagingSeedType.COFFEE_BEAN, List.of(1,2,2,3,2), 10, false, 2, 15, false, -1, List.of(Season.Spring, Season.Summer), false),
    GARLIC(GameObjectType.GARLIC_CROP, ForagingSeedType.GARLIC_SEEDS, List.of(1,1,1,1), 4, true, -1, 60, true, 20, List.of(Season.Spring), false),
    GREEN_BEAN(GameObjectType.BEAN_STARTER, ForagingSeedType.BEAN_STARTER, List.of(1,1,1,3,4), 10, false, 3, 40, true, 25, List.of(Season.Spring), false),
    KALE(GameObjectType.KALE_CROP, ForagingSeedType.KALE_SEEDS, List.of(1,2,2,1), 6, true, -1, 110, true, 50, List.of(Season.Spring), false),
    PARSNIP(GameObjectType.PARSNIP_CROP, ForagingSeedType.PARSNIP_SEEDS, List.of(1,1,1,1), 4, true, -1, 35, true, 25, List.of(Season.Spring), false),
    POTATO(GameObjectType.POTATO_CROP, ForagingSeedType.POTATO_SEEDS, List.of(1,1,1,2,1), 6, true, -1, 80, true, 25, List.of(Season.Spring), false),
    RHUBARB(GameObjectType.RHUBARB_CROP, ForagingSeedType.RHUBARB_SEEDS, List.of(2,2,2,3,4), 13, true, -1, 220, false, -1, List.of(Season.Spring), false),
    STRAWBERRY(GameObjectType.STRAWBERRY_CROP, ForagingSeedType.STRAWBERRY_SEEDS, List.of(1,1,2,2,2), 8, false, 4, 120, true, 50, List.of(Season.Spring), false),
    TULIP(GameObjectType.TULIP_BULB, ForagingSeedType.TULIP_BULB, List.of(1,1,2,2), 6, true, -1, 30, true, 45, List.of(Season.Spring), false),
    UNMILLED_RICE(GameObjectType.RICE_SHOOT, ForagingSeedType.RICE_SHOOT, List.of(1,2,2,3), 8, true, -1, 30, true, 3, List.of(Season.Spring), false),
    BLUEBERRY(GameObjectType.BLUEBERRY_CROP, ForagingSeedType.BLUEBERRY_SEEDS, List.of(1,3,3,4,2), 13, false, 4, 50, true, 25, List.of(Season.Summer), false),
    CORN(GameObjectType.CORN_CROP, ForagingSeedType.CORN_SEEDS, List.of(2,3,3,3,3), 14, false, 4, 50, true, 25, List.of(Season.Summer, Season.Fall), false),
    HOPS(GameObjectType.HOPS_STARTER, ForagingSeedType.HOPS_STARTER, List.of(1,1,2,3,4), 11, false, 1, 25, true, 45, List.of(Season.Summer), false),
    HOT_PEPPER(GameObjectType.PEPPER_SEEDS, ForagingSeedType.PEPPER_SEEDS, List.of(1,1,1,1,1), 5, false, 3, 40, true, 13, List.of(Season.Summer), false),
    MELON(GameObjectType.MELON_CROP, ForagingSeedType.MELON_SEEDS, List.of(1,2,3,3,3), 12, true, -1, 250, true, 113, List.of(Season.Summer), true),
    POPPY(GameObjectType.POPPY_CROP, ForagingSeedType.POPPY_SEEDS, List.of(1,2,2,2), 7, true, -1, 140, true, 45, List.of(Season.Summer), false),
    RADISH(GameObjectType.RADISH_CROP, ForagingSeedType.RADISH_SEEDS, List.of(2,1,2,1), 6, true, -1, 90, true, 45, List.of(Season.Summer), false),
    RED_CABBAGE(GameObjectType.RED_CABBAGE_CROP, ForagingSeedType.RED_CABBAGE_SEEDS, List.of(2,1,2,2,2), 9, true, -1, 260, true, 75, List.of(Season.Summer), false),
    STARFRUIT(GameObjectType.STARFRUIT_CROP, ForagingSeedType.STARFRUIT_SEEDS, List.of(2,3,2,3,3), 13, true, -1, 750, true, 125, List.of(Season.Summer), false),
    SUMMER_SPANGLE(GameObjectType.SUMMER_SPANGLE_CROP, ForagingSeedType.SPANGLE_SEEDS, List.of(1,2,3,1), 8, true, -1, 90, true, 45, List.of(Season.Summer), false),
    SUMMER_SQUASH(GameObjectType.SUMMER_SQUASH_CROP, ForagingSeedType.SUMMER_SQUASH_SEEDS, List.of(1,1,1,2,1), 6, false, 3, 45, true, 63, List.of(Season.Summer), false),
    SUNFLOWER(GameObjectType.SUNFLOWER_CROP, ForagingSeedType.SUNFLOWER_SEEDS, List.of(1,2,3,2), 8, true, -1, 80, true, 45, List.of(Season.Summer, Season.Fall), false),
    TOMATO(GameObjectType.TOMATO_CROP, ForagingSeedType.TOMATO_SEEDS, List.of(2,2,2,2,3), 11, false, 4, 60, true, 20, List.of(Season.Summer), false),
    WHEAT(GameObjectType.WHEAT_CROP, ForagingSeedType.WHEAT_SEEDS, List.of(1,1,1,1), 4, true, -1, 25, false, -1, List.of(Season.Summer, Season.Fall), false),
    AMARANTH(GameObjectType.AMARANTH_CROP, ForagingSeedType.AMARANTH_SEEDS, List.of(1,2,2,2), 7, true, -1, 150, true, 50, List.of(Season.Fall), false),
    ARTICHOKE(GameObjectType.ARTICHOKE_CROP, ForagingSeedType.ARTICHOKE_SEEDS, List.of(2,2,1,2,1), 8, true, -1, 160, true, 30, List.of(Season.Fall), false),
    BEET(GameObjectType.BEET_CROP, ForagingSeedType.BEET_SEEDS, List.of(1,1,2,2), 6, true, -1, 100, true, 30, List.of(Season.Fall), false),
    BOK_CHOY(GameObjectType.BOK_CHOY_CROP, ForagingSeedType.BOK_CHOY_SEEDS, List.of(1,1,1,1), 4, true, -1, 80, true, 25, List.of(Season.Fall), false),
    BROCCOLI(GameObjectType.BROCCOLI_CROP, ForagingSeedType.BROCCOLI_SEEDS, List.of(2,2,2,2), 8, false, 4, 70, true, 63, List.of(Season.Fall), false),
    CRANBERRIES(GameObjectType.CRANBERRY_SEEDS, ForagingSeedType.CRANBERRY_SEEDS, List.of(1,2,1,1,2), 7, false, 5, 75, true, 38, List.of(Season.Fall), false),
    EGGPLANT(GameObjectType.EGGPLANT_CROP, ForagingSeedType.EGGPLANT_SEEDS, List.of(1,1,1,1), 5, false, 5, 60, true, 20, List.of(Season.Fall), false),
    FAIRY_ROSE(GameObjectType.FAIRY_ROSE_CROP, ForagingSeedType.FAIRY_SEEDS, List.of(1,4,4,3), 12, true, -1, 290, true, 45, List.of(Season.Fall), false),
    GRAPE(GameObjectType.GRAPE_STARTER, ForagingSeedType.GRAPE_STARTER, List.of(1,1,2,3,3), 10, false, 3, 80, true, 38, List.of(Season.Fall), false),
    PUMPKIN(GameObjectType.PUMPKIN_CROP, ForagingSeedType.PUMPKIN_SEEDS, List.of(1,2,3,4,3), 13, true, -1, 320, false, -1, List.of(Season.Fall), true),
    YAM(GameObjectType.YAM_CROP, ForagingSeedType.YAM_SEEDS, List.of(1,3,3,3), 10, true, -1, 160, true, 45, List.of(Season.Fall), false),
    SWEET_GEM_BERRY(GameObjectType.RARE_SEED, ForagingSeedType.RARE_SEED, List.of(2,4,6,6,6), 24, true, -1, 3000, false, -1, List.of(Season.Fall), false),
    POWDERMELON(GameObjectType.POWERDMELON_SEEDS, ForagingSeedType.POWERDMELON_SEEDS, List.of(1,2,1,2,1), 7, true, -1, 60, true, 63, List.of(Season.Winter), true),
    ANCIENT_FRUIT(GameObjectType.ANCIENT_SEEDS, ForagingSeedType.ANCIENT_SEEDS, List.of(2,7,7,7,5), 28, false, 7, 550, false, -1, List.of(Season.Spring, Season.Summer, Season.Fall), false)
    ;

    private final GameObjectType type;
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

    CropType(GameObjectType type, ForagingSeedType seedType, List<Integer> stages, int totalHarvestTime,
             boolean oneTime, int growthTime, int baseSellPrice, boolean isEdible, int energy, List<Season> seasons,
             boolean canBecomeGiant)
    {
        this.type = type;
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
}
