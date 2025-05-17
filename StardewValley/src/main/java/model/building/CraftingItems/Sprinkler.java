package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;
import model.resources.Plant;

public class Sprinkler extends CraftingItem
{
    public Sprinkler()
    {
        super(CraftingRecipeEnums.SPRINKLER_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        Point location = player.getLocation();
        int x = location.getX();
        int y = location.getY();

        int[] xDir = {-1, 1, 0, 0};
        int[] yDir = {0, 0, -1, 1};

        for (int i = 0; i < xDir.length; i++)
        {
            if (map.isInBounds(x + xDir[i], y + yDir[i]))
            {
                Tile tile = map.getTile(x + xDir[i], y + yDir[i]);
                if (tile.hasPlants())
                {
                    Plant plant = (Plant) tile.getObject();
                    plant.water();
                }
            }
        }
    }
}
