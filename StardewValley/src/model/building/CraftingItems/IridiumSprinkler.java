package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;
import model.resources.Plant;

public class IridiumSprinkler extends CraftingItem
{
    public IridiumSprinkler()
    {
        super(CraftingRecipeEnums.IRIDIUM_SPRINKLER_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        Point location = player.getLocation();
        int x = location.getX();
        int y = location.getY();

        for (int i = -2; i <= 2; i++)
        {
            for (int j = -2; j <= 2; j++)
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
