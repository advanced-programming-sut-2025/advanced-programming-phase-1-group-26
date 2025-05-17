package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum ForagingSeedType
{
    JAZZ_SEEDS("Jazz Seeds", GameObjectType.JAZZ_SEEDS, List.of(Season.Spring)),
    CARROT_SEEDS("Carrot Seeds", GameObjectType.CARROT_SEEDS, List.of(Season.Spring)),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", GameObjectType.CAULIFLOWER_SEEDS, List.of(Season.Spring)),
    COFFEE_BEAN("Coffee Bean", GameObjectType.COFFEE_BEAN, List.of(Season.Spring)),
    GARLIC_SEEDS("Garlic Seeds", GameObjectType.GARLIC_SEEDS, List.of(Season.Spring)),
    BEAN_STARTER("Bean Starter", GameObjectType.BEAN_STARTER, List.of(Season.Spring)),
    KALE_SEEDS("Kale Seeds", GameObjectType.KALE_SEEDS, List.of(Season.Spring)),
    PARSNIP_SEEDS("Parsnip Seeds", GameObjectType.PARSNIP_SEEDS, List.of(Season.Spring)),
    POTATO_SEEDS("Potato Seeds", GameObjectType.POTATO_SEEDS, List.of(Season.Spring)),
    RHUBARB_SEEDS("Rhubarb Seeds", GameObjectType.RHUBARB_SEEDS, List.of(Season.Spring)),
    STRAWBERRY_SEEDS("Strawberry Seeds", GameObjectType.STRAWBERRY_SEEDS, List.of(Season.Spring)),
    TULIP_BULB("Tulip Bulb", GameObjectType.TULIP_BULB, List.of(Season.Spring)),
    RICE_SHOOT("Rice Shoot", GameObjectType.RICE_SHOOT, List.of(Season.Spring)),

    BLUEBERRY_SEEDS("Blueberry Seeds", GameObjectType.BLUEBERRY_SEEDS, List.of(Season.Summer)),
    CORN_SEEDS("Corn Seeds", GameObjectType.CORN_SEEDS, List.of(Season.Summer)),
    HOPS_STARTER("Hops Starter", GameObjectType.HOPS_STARTER, List.of(Season.Summer)),
    PEPPER_SEEDS("Pepper Seeds", GameObjectType.PEPPER_SEEDS, List.of(Season.Summer)),
    MELON_SEEDS("Melon Seeds", GameObjectType.MELON_SEEDS, List.of(Season.Summer)),
    POPPY_SEEDS("Poppy Seeds", GameObjectType.POPPY_SEEDS, List.of(Season.Summer)),
    RADISH_SEEDS("Radish Seeds", GameObjectType.RADISH_SEEDS, List.of(Season.Summer)),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", GameObjectType.RED_CABBAGE_SEEDS, List.of(Season.Summer)),
    STARFRUIT_SEEDS("Starfruit Seeds", GameObjectType.STARFRUIT_SEEDS, List.of(Season.Summer)),
    SPANGLE_SEEDS("Spangle Seeds", GameObjectType.SPANGLE_SEEDS, List.of(Season.Summer)),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", GameObjectType.SUMMER_SQUASH_SEEDS, List.of(Season.Summer)),
    SUNFLOWER_SEEDS("Sunflower Seeds", GameObjectType.SUNFLOWER_SEEDS, List.of(Season.Summer)),
    TOMATO_SEEDS("Tomato Seeds", GameObjectType.TOMATO_SEEDS, List.of(Season.Summer)),
    WHEAT_SEEDS("Wheat Seeds", GameObjectType.WHEAT_SEEDS, List.of(Season.Summer)),

    AMARANTH_SEEDS("Amaranth Seeds", GameObjectType.AMARANTH_SEEDS, List.of(Season.Fall)),
    ARTICHOKE_SEEDS("Artichoke Seeds", GameObjectType.ARTICHOKE_SEEDS, List.of(Season.Fall)),
    BEET_SEEDS("Beet Seeds", GameObjectType.BEET_SEEDS, List.of(Season.Fall)),
    BOK_CHOY_SEEDS("Bok Choy Seeds", GameObjectType.BOK_CHOY_SEEDS, List.of(Season.Fall)),
    BROCCOLI_SEEDS("Broccoli Seeds", GameObjectType.BROCCOLI_SEEDS, List.of(Season.Fall)),
    CRANBERRY_SEEDS("Cranberry Seeds", GameObjectType.CRANBERRY_SEEDS, List.of(Season.Fall)),
    EGGPLANT_SEEDS("Eggplant Seeds", GameObjectType.EGGPLANT_SEEDS, List.of(Season.Fall)),
    FAIRY_SEEDS("Fairy Seeds", GameObjectType.FAIRY_SEEDS, List.of(Season.Fall)),
    GRAPE_STARTER("Grape Starter", GameObjectType.GRAPE_STARTER, List.of(Season.Fall)),
    PUMPKIN_SEEDS("Pumpkin Seeds", GameObjectType.PUMPKIN_SEEDS, List.of(Season.Fall)),
    YAM_SEEDS("Yam Seeds", GameObjectType.YAM_SEEDS, List.of(Season.Fall)),
    RARE_SEED("Rare Seed", GameObjectType.RARE_SEED, List.of(Season.Fall)),

    POWDERMELON_SEEDS("Powdermelon Seeds", GameObjectType.POWERDMELON_SEEDS, List.of(Season.Winter)),

    ANCIENT_SEEDS("Ancient Seeds", GameObjectType.ANCIENT_SEEDS, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MIXED_SEEDS("Mixed Seeds", GameObjectType.MIXED_SEEDS, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter));
    ;

    private final String name;
    private final GameObjectType type;
    private final List<Season> seasons;

    ForagingSeedType(String name, GameObjectType type, List<Season> seasons)
    {
        this.name = name;
        this.type = type;
        this.seasons = seasons;
    }

    public GameObjectType getType()
    {
        return type;
    }

    public List<Season> getSeasons()
    {
        return seasons;
    }

    @Override
    public String toString()
    {
        return type.toString();
    }

    public String getName()
    {
        return name;
    }

    public static ForagingSeedType getSeedType(String name)
    {
        for (ForagingSeedType type : ForagingSeedType.values())
        {
            if (type.getName().equalsIgnoreCase(name))
            {
                return type;
            }
        }
        return null;
    }

    public static ArrayList<ForagingSeedType> getSeasonSeeds(Season season)
    {
        ArrayList<ForagingSeedType> seasonSeeds = new ArrayList<>();
        for (ForagingSeedType foragingSeedType : ForagingSeedType.values())
        {
            if (foragingSeedType.seasons.contains(season))
            {
                seasonSeeds.add(foragingSeedType);
            }
        }
        return seasonSeeds;
    }

    public static ForagingSeedType getRandomSeasonSeed(Season season)
    {
        ArrayList<ForagingSeedType> seasonSeeds = getSeasonSeeds(season);
        int index = ThreadLocalRandom.current().nextInt(seasonSeeds.size());
        return seasonSeeds.get(index);
    }
}
