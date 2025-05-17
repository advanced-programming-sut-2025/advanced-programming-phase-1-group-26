package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;

public class CherryBomb extends CraftingItem
{
    public CherryBomb()
    {
        super(CraftingRecipeEnums.CHERRY_BOMB_RECIPE);
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
                if (map.isInBounds(x + i, y + j))
                {
                    Tile tile = map.getTile(x + i, y + j);
                    tile.setObject(null);
                }
            }
        }
    }
}
