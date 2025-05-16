package model.enums.animal_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.ArrayList;

public enum FishType
{
    SALMON(GameObjectType.SALMON, "Salmon", 75, Season.Fall, false),
    SARDINE(GameObjectType.SARDINE, "Sardine", 40, Season.Fall, false),
    SHAD(GameObjectType.SHAD, "Shad", 60, Season.Fall, false),
    BLUE_DISCUS(GameObjectType.BLUE_DISCUS, "Blue Discus", 120, Season.Fall, false),
    MIDNIGHT_CARP(GameObjectType.MIDNIGHT_CARP, "Midnight Carp", 150, Season.Winter, false),
    SQUID(GameObjectType.SQUID, "Squid", 80, Season.Winter, false),
    TUNA(GameObjectType.TUNA, "Tuna", 100, Season.Winter, false),
    PERCH(GameObjectType.PERCH, "Perch", 55, Season.Winter, false),
    FLOUNDER(GameObjectType.FLOUNDER, "Flounder", 100, Season.Spring, false),
    LIONFISH(GameObjectType.LIONFISH, "Lionfish", 100, Season.Spring, false),
    HERRING(GameObjectType.HERRING, "Herring", 30, Season.Spring, false),
    GHOSTFISH(GameObjectType.GHOSTFISH, "Ghostfish", 45, Season.Spring, false),
    TILAPIA(GameObjectType.TILAPIA, "Tilapia", 75, Season.Summer, false),
    DORADO(GameObjectType.DORADO, "Dorado", 100, Season.Summer, false),
    LEGEND(GameObjectType.LEGEND, "Legend", 5000, Season.Spring, true),
    GLACIER_FISH(GameObjectType.GLACIER_FISH, "Glacier Fish", 1000, Season.Winter, true),
    ANGLER(GameObjectType.ANGLER, "Angler", 900, Season.Fall, true),
    CRIMSON_FISH(GameObjectType.CRIMSON_FISH, "Crimson Fish", 1500, Season.Summer, true),

    ;

    private final GameObjectType type;
    private final String displayName;
    private final int basePrice;
    private final Season season;
    private final boolean isLegendary;

    FishType(GameObjectType type, String displayName, int basePrice, Season season, boolean isLegendary) {
        this.type = type;
        this.displayName = displayName;
        this.basePrice = basePrice;
        this.season = season;
        this.isLegendary = isLegendary;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public Season getSeason() {
        return season;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public static ArrayList<FishType> getOrdinaryFishTypes(Season season)
    {
        ArrayList<FishType> fishTypes = new ArrayList<>();

        for (FishType fishType : FishType.values())
        {
            if (fishType.getSeason() == season && !fishType.isLegendary())
            {
                fishTypes.add(fishType);
            }
        }

        return fishTypes;
    }

    public static FishType getLegendaryFishType(Season season)
    {
        for (FishType fishType : FishType.values())
        {
            if (fishType.getSeason() == season && fishType.isLegendary())
            {
                return fishType;
            }
        }

        return null;
    }

    public GameObjectType getType()
    {
        return type;
    }

    public static FishType getFishFromType(GameObjectType type)
    {
        for (FishType fishType : FishType.values())
        {
            if (fishType.getType() == type)
            {
                return fishType;
            }
        }

        return null;
    }

    public boolean isCheapestOfTheSeason(FishType fishType)
    {
        for (FishType fish : FishType.values())
        {
            if (fish.getSeason() == fishType.getSeason() && fish.getBasePrice() < fishType.getBasePrice())
            {
                return false;
            }
        }

        return true;
    }
}
