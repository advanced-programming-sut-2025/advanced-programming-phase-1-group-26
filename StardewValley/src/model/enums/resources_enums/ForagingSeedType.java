package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.List;

public enum ForagingSeedType
{
    JAZZ_SEEDS(GameObjectType.JAZZ_SEEDS, List.of(Season.Spring)),
    CARROT_SEEDS(GameObjectType.CARROT_SEEDS, List.of(Season.Spring)),
    CAULIFLOWER_SEEDS(GameObjectType.CAULIFLOWER_SEEDS, List.of(Season.Spring)),
    COFFEE_BEAN(GameObjectType.COFFEE_BEAN, List.of(Season.Spring)),
    GARLIC_SEEDS(GameObjectType.GARLIC_SEEDS, List.of(Season.Spring)),
    BEAN_STARTER(GameObjectType.BEAN_STARTER, List.of(Season.Spring)),
    KALE_SEEDS(GameObjectType.KALE_SEEDS, List.of(Season.Spring)),
    PARSNIP_SEEDS(GameObjectType.PARSNIP_SEEDS, List.of(Season.Spring)),
    POTATO_SEEDS(GameObjectType.POTATO_SEEDS, List.of(Season.Spring)),
    RHUBARB_SEEDS(GameObjectType.RHUBARB_SEEDS, List.of(Season.Spring)),
    STRAWBERRY_SEEDS(GameObjectType.STRAWBERRY_SEEDS, List.of(Season.Spring)),
    TULIP_BULB(GameObjectType.TULIP_BULB, List.of(Season.Spring)),
    RICE_SHOOT(GameObjectType.RICE_SHOOT, List.of(Season.Spring)),

    BLUEBERRY_SEEDS(GameObjectType.BLUEBERRY_SEEDS, List.of(Season.Summer)),
    CORN_SEEDS(GameObjectType.CORN_SEEDS, List.of(Season.Summer)),
    HOPS_STARTER(GameObjectType.HOPS_STARTER, List.of(Season.Summer)),
    PEPPER_SEEDS(GameObjectType.PEPPER_SEEDS, List.of(Season.Summer)),
    MELON_SEEDS(GameObjectType.MELON_SEEDS, List.of(Season.Summer)),
    POPPY_SEEDS(GameObjectType.POPPY_SEEDS, List.of(Season.Summer)),
    RADISH_SEEDS(GameObjectType.RADISH_SEEDS, List.of(Season.Summer)),
    RED_CABBAGE_SEEDS(GameObjectType.RED_CABBAGE_SEEDS, List.of(Season.Summer)),
    STARFRUIT_SEEDS(GameObjectType.STARFRUIT_SEEDS, List.of(Season.Summer)),
    SPANGLE_SEEDS(GameObjectType.SPANGLE_SEEDS, List.of(Season.Summer)),
    SUMMER_SQUASH_SEEDS(GameObjectType.SUMMER_SQUASH_SEEDS, List.of(Season.Summer)),
    SUNFLOWER_SEEDS(GameObjectType.SUNFLOWER_SEEDS, List.of(Season.Summer)),
    TOMATO_SEEDS(GameObjectType.TOMATO_SEEDS, List.of(Season.Summer)),
    WHEAT_SEEDS(GameObjectType.WHEAT_SEEDS, List.of(Season.Summer)),

    AMARANTH_SEEDS(GameObjectType.AMARANTH_SEEDS, List.of(Season.Fall)),
    ARTICHOKE_SEEDS(GameObjectType.ARTICHOKE_SEEDS, List.of(Season.Fall)),
    BEET_SEEDS(GameObjectType.BEET_SEEDS, List.of(Season.Fall)),
    BOK_CHOY_SEEDS(GameObjectType.BOK_CHOY_SEEDS, List.of(Season.Fall)),
    BROCCOLI_SEEDS(GameObjectType.BROCCOLI_SEEDS, List.of(Season.Fall)),
    CRANBERRY_SEEDS(GameObjectType.CRANBERRY_SEEDS, List.of(Season.Fall)),
    EGGPLANT_SEEDS(GameObjectType.EGGPLANT_SEEDS, List.of(Season.Fall)),
    FAIRY_SEEDS(GameObjectType.FAIRY_SEEDS, List.of(Season.Fall)),
    GRAPE_STARTER(GameObjectType.GRAPE_STARTER, List.of(Season.Fall)),
    PUMPKIN_SEEDS(GameObjectType.PUMPKIN_SEEDS, List.of(Season.Fall)),
    YAM_SEEDS(GameObjectType.YAM_SEEDS, List.of(Season.Fall)),
    RARE_SEED(GameObjectType.RARE_SEED, List.of(Season.Fall)),

    POWERDMELON_SEEDS(GameObjectType.POWERDMELON_SEEDS, List.of(Season.Winter)),

    ANCIENT_SEEDS(GameObjectType.ANCIENT_SEEDS, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MIXED_SEEDS(GameObjectType.MIXED_SEEDS, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    ;

    private final GameObjectType type;
    private final List<Season> seasons;

    ForagingSeedType(GameObjectType type, List<Season> seasons)
    {
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
}
