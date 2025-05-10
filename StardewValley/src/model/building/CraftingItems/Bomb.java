package model.building.CraftingItems;

import model.*;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.RecipeItem;

public class Bomb extends CraftingItem
{
    public Bomb()
    {
        super(CraftingRecipeEnums.BOMB_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentFarm();
        Point location = player.getLocation();
        int x = location.getX();
        int y = location.getY();

        for (int i = -4; i <= 4; i++)
        {
            for (int j = -4; j <= 4; j++)
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
