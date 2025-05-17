package model.enums.resources_enums;

import model.enums.GameObjectType;
import model.enums.Season;

import java.util.List;

public enum ForagingTreeType
{
    ACORNS(GameObjectType.ACORNS_TREE, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAPLE_SEEDS(GameObjectType.MAPLE_TREE, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    PINE_CONES(GameObjectType.PINE_TREE, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MAHOGANY_SEEDS(GameObjectType.MAHOGANY_TREE, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    MUSHROOM_TREE_SEEDS(GameObjectType.MUSHROOM_TREE, List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)),
    ;

    private final GameObjectType type;
    private final List<Season> seasons;

    ForagingTreeType(GameObjectType type, List<Season> seasons)
    {
        this.type = type;
        this.seasons = seasons;
    }

    public List<Season> getSeasons()
    {
        return seasons;
    }

    public GameObjectType getType()
    {
        return type;
    }

    public String getCraftInfo()
    {
        StringBuilder output = new StringBuilder();
        output.append("Name: ").append(this.name().replace("_", " ").toLowerCase()).append("\n");

        output.append("Season(s): ");
        for (int i = 0; i < seasons.size(); i++)
        {
            output.append(seasons.get(i).getName()).append(i == seasons.size() - 1 ? "\n" : ", ");
        }

        return output.toString().trim();
    }

    public String getName()
    {
        return this.name().replace("_", " ").toLowerCase();
    }

    public static ForagingTreeType getForagingTreeType(String name)
    {
        for (ForagingTreeType foragingTreeType : ForagingTreeType.values())
        {
            if (foragingTreeType.getName().equalsIgnoreCase(name))
            {
                return foragingTreeType;
            }
        }
        return null;
    }
}
