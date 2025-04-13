package model.enums.resources_enums;

import model.enums.Season;

import java.util.List;

public enum ForagingTreeType
{
    ACORNS(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAPLE_SEEDS(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    PINE_CONES(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAHOGANY_SEEDS(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MUSHROOM_TREE_SEEDS(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    ;

    private final List<Season> seasons;

    ForagingTreeType(List<Season> seasons)
    {
        this.seasons = seasons;
    }

    public List<Season> getSeasons()
    {
        return seasons;
    }
}
