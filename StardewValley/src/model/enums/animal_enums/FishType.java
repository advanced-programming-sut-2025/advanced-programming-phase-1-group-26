package model.enums.animal_enums;

import model.enums.Season;

import java.util.ArrayList;

public enum FishType
{
    SALMON("Salmon", 75, Season.Fall, false),
    SARDINE("Sardine", 40, Season.Fall, false),
    SHAD("Shad", 60, Season.Fall, false),
    BLUE_DISCUS("Blue Discus", 120, Season.Fall, false),
    MIDNIGHT_CARP("Midnight Carp", 150, Season.Winter, false),
    SQUID("Squid", 80, Season.Winter, false),
    TUNA("Tuna", 100, Season.Winter, false),
    PERCH("Perch", 55, Season.Winter, false),
    FLOUNDER("Flounder", 100, Season.Spring, false),
    LIONFISH("Lionfish", 100, Season.Spring, false),
    HERRING("Herring", 30, Season.Spring, false),
    GHOSTFISH("Ghostfish", 45, Season.Spring, false),
    TILAPIA("Tilapia", 75, Season.Summer, false),
    DORADO("Dorado", 100, Season.Summer, false),
    LEGEND("Legend", 5000, Season.Spring, true),
    GLACIER_FISH("Glacier Fish", 1000, Season.Winter, true),
    ANGLER("Angler", 900, Season.Fall, true),
    CRIMSON_FISH("Crimson Fish", 1500, Season.Summer, true),

    ;

    private final String displayName;
    private final int basePrice;
    private final Season season;
    private final boolean isLegendary;

    FishType(String displayName, int basePrice, Season season, boolean isLegendary) {
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
}
