package model;

import model.enums.ForagingSeedType;
import model.enums.Season;

import java.util.List;

public class ForagingSeed extends GameObject
{
    private final ForagingSeedType seedType;
    private final List<Season> seasons;

    public ForagingSeed(ForagingSeedType seedType)
    {
        this.seedType = seedType;
        this.seasons = seedType.getSeasons();
    }
}
