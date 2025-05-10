package model.building.CraftingItems;

import model.App;
import model.GameObject;
import model.Player;
import model.enums.GameObjectType;
import model.enums.building_enums.CraftingRecipeEnums;
import view.HomeMenu;

public class BeeHouse extends CraftingItem
{
    public BeeHouse()
    {
        super(CraftingRecipeEnums.BEE_HOUSE_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        if (player.inventoryHasCapacity())
        {
            player.addToInventory(new GameObject(GameObjectType.HONEY, 10));
            HomeMenu.println("Honey (the type that you eat) has been added to the inventory.");
        } else
        {
            HomeMenu.println("Your honey (edible one) couldn't be added to the inventory.");
        }
    }
}
