package model.building.CraftingItems;

import control.game.activities.HomeController;
import model.App;
import model.GameObject;
import model.Player;
import model.enums.GameObjectType;
import model.enums.building_enums.CraftingRecipeEnums;
import view.HomeMenu;

public class CharcoalKlin extends CraftingItem
{
    public CharcoalKlin()
    {
        super(CraftingRecipeEnums.CHARCOAL_KILN_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        GameObject wood = player.getItemInInventory(GameObjectType.WOOD);
        if (wood != null && (player.hasEnoughInInventory(GameObjectType.COAL, 1) ||
                (player.getInventoryCapacity() == -1 || player.getInventoryCapacity() >= 0)))
        {
            int coalAmount = Math.min(10, wood.getNumber());
            player.removeAmountFromInventory(GameObjectType.WOOD, coalAmount);
            player.addToInventory(GameObjectType.COAL, coalAmount);
        } else
        {
            HomeMenu.println("You can't use this device right now!");
        }
    }
}
