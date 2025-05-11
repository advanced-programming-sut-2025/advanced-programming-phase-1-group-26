package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;
import model.resources.Plant;

public class QualitySprinkler extends CraftingItem
{
    public QualitySprinkler()
    {
        super(CraftingRecipeEnums.QUALITY_SPRINKLER_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        Point location = player.getLocation();
        int x = location.getX();
        int y = location.getY();

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (i != 0 && j != 0 && map.isInBounds(x + i, y + j))
                {
                    Tile tile = map.getTile(x + i, y + j);
                    if (tile.hasPlants())
                    {
                        Plant plant = (Plant) tile.getObject();
                        plant.water();
                    }
                }
            }
        }
    }
}
