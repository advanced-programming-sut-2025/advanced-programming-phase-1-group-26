package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.List;

public enum ForagingSeedType
{
    JAZZ_SEEDS(List.of(Season.Spring)),
    CARROT_SEEDS(List.of(Season.Spring)),
    CAULIFLOWER_SEEDS(List.of(Season.Spring)),
    COFFEE_BEAN(List.of(Season.Spring)),
    GARLIC_SEEDS(List.of(Season.Spring)),
    BEAN_STARTER(List.of(Season.Spring)),
    KALE_SEEDS(List.of(Season.Spring)),
    PARSNIP_SEEDS(List.of(Season.Spring)),
    POTATO_SEEDS(List.of(Season.Spring)),
    RHUBARB_SEEDS(List.of(Season.Spring)),
    STRAWBERRY_SEEDS(List.of(Season.Spring)),
    TULIP_BULB(List.of(Season.Spring)),
    RICE_SHOOT(List.of(Season.Spring)),

    BLUEBERRY_SEEDS(List.of(Season.Summer)),
    CORN_SEEDS(List.of(Season.Summer)),
    HOPS_STARTER(List.of(Season.Summer)),
    PEPPER_SEEDS(List.of(Season.Summer)),
    MELON_SEEDS(List.of(Season.Summer)),
    POPPY_SEEDS(List.of(Season.Summer)),
    RADISH_SEEDS(List.of(Season.Summer)),
    RED_CABBAGE_SEEDS(List.of(Season.Summer)),
    STARFRUIT_SEEDS(List.of(Season.Summer)),
    SPANGLE_SEEDS(List.of(Season.Summer)),
    SUMMER_SQUASH_SEEDS(List.of(Season.Summer)),
    SUNFLOWER_SEEDS(List.of(Season.Summer)),
    TOMATO_SEEDS(List.of(Season.Summer)),
    WHEAT_SEEDS(List.of(Season.Summer)),

    AMARANTH_SEEDS(List.of(Season.Fall)),
    ARTICHOKE_SEEDS(List.of(Season.Fall)),
    BEET_SEEDS(List.of(Season.Fall)),
    BOK_CHOY_SEEDS(List.of(Season.Fall)),
    BROCCOLI_SEEDS(List.of(Season.Fall)),
    CRANBERRY_SEEDS(List.of(Season.Fall)),
    EGGPLANT_SEEDS(List.of(Season.Fall)),
    FAIRY_SEEDS(List.of(Season.Fall)),
    GRAPE_STARTER(List.of(Season.Fall)),
    PUMPKIN_SEEDS(List.of(Season.Fall)),
    YAM_SEEDS(List.of(Season.Fall)),
    RARE_SEED(List.of(Season.Fall)),

    POWERDMELON_SEEDS(List.of(Season.Winter)),

    ANCIENT_SEEDS(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MIXED_SEEDS(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    ;

    private final List<Season> seasons;

    ForagingSeedType(List<Season> seasons)
    {
        this.seasons = seasons;
    }

    public List<Season> getSeasons()
    {
        return seasons;
    }
}
