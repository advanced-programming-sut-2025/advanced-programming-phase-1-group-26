package model.resources;

import model.App;
import model.GameObject;
import model.Tile;
import model.enums.Season;

import java.util.List;

public class Plant extends GameObject
{
    protected String name;
    protected Enum<?> type;
    protected Object source;
    protected List<Integer> stages;
    protected int totalHarvestTime;
    protected int baseSellPrice;
    protected boolean isEdible;
    protected int energy;
    protected List<Season> seasons;

    protected boolean hasStarted = false;
    protected int lastWatered = 1; // TODO: can this fix the problem?
    protected int currentStage = 0;
    protected int lastHarvested = 0;

    protected boolean hasHarvested = false;
    protected int harvestWaitTime;

    protected boolean isInGreenhouse = false;
    protected Tile tile = null;

    public void water()
    {
        if (!hasStarted)
        {
            hasStarted = true;
        }

        lastWatered = 0;
    }

    private void grow()
    {
        if (currentStage < totalHarvestTime)
        {
            if (seasons.contains(App.getCurrentGame().getCurrentTime().getSeason()) || isInGreenhouse)
            {
                currentStage += 1;
            }
        }
    }

    public void update()
    {
        if (hasBeenWateredToday())
        {
            grow();
        }
        lastWatered += 1; // always ages if not watered
        if (currentStage == totalHarvestTime)
        {
            lastHarvested += 1;
        }
    }

    public boolean canHarvest() // would be overridden
    {
        return false;
    }

    public Enum<?> getPlantType()
    {
        return type;
    }

    public boolean isInGreenHouse()
    {
        return isInGreenhouse;
    }

    public void putInGreenhouse()
    {
        isInGreenhouse = true;
    }

    public String showDetails(Plant plant, Tile tile)
    {
        StringBuilder output = new StringBuilder();

        if (plant instanceof Tree)
        {
            output.append(((Tree) plant).getTreeType().getCraftInfo());
        } else if (plant instanceof Crop)
        {
            output.append(((Crop) plant).getCropType().getCraftInfo());
        }

        output.append("\n");

        output.append("Remaining Time: ").append(totalHarvestTime - currentStage).append(" (days)\n");
        if (canHarvest())
        {
            output.append("Harvest Wait Time: ").append(harvestWaitTime).append(" (days)\n");
        }
        output.append("Current Stage: ").append(currentStage).append(" (days ago)\n");
        output.append("Watered Today: ").append(lastWatered == 0 ? "positive" : "negative").append("\n");
        output.append("Has Been Fertilized: ").append(tile.isFertilized() ? "positive" : "negative").append("\n");

        return output.toString().trim();
    }

    public boolean hasBeenWateredToday()
    {
        if (!hasStarted)
        {
            return false;
        }

        return lastWatered == 0;
    }

    public int getLastWatered()
    {
        if (!hasStarted)
        {
            return -1;
        }

        return lastWatered;
    }

    public Tile getTile()
    {
        return tile;
    }

    public boolean hasStarted()
    {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted)
    {
        this.hasStarted = hasStarted;
    }
}
