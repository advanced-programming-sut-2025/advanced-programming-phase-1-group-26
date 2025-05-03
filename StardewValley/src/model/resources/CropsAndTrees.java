package model.resources;

import model.GameObject;
import model.enums.Season;

import java.util.List;

public class CropsAndTrees extends GameObject
{
    protected String name;
    protected Object type;
    protected Object source;
    protected List<Integer> stages;
    protected int totalHarvestTime;
    protected int baseSellPrice;
    protected boolean isEdible;
    protected int energy;
    protected List<Season> seasons;
}
