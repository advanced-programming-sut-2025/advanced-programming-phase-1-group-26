package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;

public class Scarecrow extends CraftingItem
{
    public Scarecrow()
    {
        super(CraftingRecipeEnums.SCARECROW_RECIPE);
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
                if (map.isInBounds(x + i, y + j))
                {
                    Tile tile = map.getTile(x + i, y + j);
                    tile.makeImmuneFromCrows();
                }
            }
        }
    }
}
