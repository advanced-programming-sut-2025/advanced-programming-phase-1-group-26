package model.resources;

import model.GameObject;
import model.enums.Season;

import java.util.List;

public class Plant extends GameObject
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

    protected int lastWatered = 0;
    protected int currentStage = 0;
    protected int lastHarvested = -1;

    protected int harvestWaitTime;

    public void water()
    {
        lastWatered = 0;
    }

    private void grow()
    {
        if (currentStage < totalHarvestTime)
        {
            currentStage += 1;
        }
    }

    public void update()
    {
        grow();
        lastWatered += 1;
        if (currentStage == totalHarvestTime)
        {
            lastHarvested += 1;
        }
    }

    public boolean canHarvest() // would be overridden
    {
        return false;
    }
}
