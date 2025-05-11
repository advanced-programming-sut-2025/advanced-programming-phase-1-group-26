package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;

public class DeluxeScarecrow extends CraftingItem
{
    public DeluxeScarecrow()
    {
        super(CraftingRecipeEnums.DELUXE_SCARECROW_RECIPE);
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
                    tile.makeImmuneFromCrows();
                }
            }
        }
    }
}
