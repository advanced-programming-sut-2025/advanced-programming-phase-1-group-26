package model.resources;

import model.enums.resources_enums.ForagingTreeType;
import model.enums.Season;

import java.util.List;

public class ForagingTree
{
    private final ForagingTreeType treeType;
    private List<Season> seasons;

    public ForagingTree(ForagingTreeType type)
    {
        this.treeType = type;
        this.seasons = treeType.getSeasons();
    }
}
