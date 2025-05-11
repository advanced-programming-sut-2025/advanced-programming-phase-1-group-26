package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;

public class MegaBomb extends CraftingItem
{
    public MegaBomb()
    {
        super(CraftingRecipeEnums.MEGA_BOMB_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        Point location = player.getLocation();
        int x = location.getX();
        int y = location.getY();

        for (int i = -6; i <= 6; i++)
        {
            for (int j = -6; j <= 6; j++)
            {
                if (map.isInBounds(x + i, y + j))
                {
                    Tile tile = map.getTile(x + i, y + j);
                    tile.setObject(null);
                }
            }
        }
    }
}
