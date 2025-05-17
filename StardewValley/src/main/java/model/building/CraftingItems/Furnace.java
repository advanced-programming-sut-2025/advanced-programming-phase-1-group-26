package model.building.CraftingItems;

import model.App;
import model.GameObject;
import model.Player;
import model.enums.GameObjectType;
import model.enums.building_enums.CraftingRecipeEnums;
import view.HomeMenu;

public class Furnace extends CraftingItem
{
    public Furnace()
    {
        super(CraftingRecipeEnums.FURNACE_RECIPE);
    }

    @Override
    public void doItsThing()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObject coal = player.getItemInInventory(GameObjectType.COAL);
        if (coal != null)
        {
            player.addToInventory(GameObjectType.GOLD_BAR, coal.getNumber());
            player.removeFromInventory(coal);
        }

        GameObject copper = player.getItemInInventory(GameObjectType.COPPER);
        if (copper != null)
        {
            player.addToInventory(GameObjectType.COPPER_BAR, copper.getNumber());
            player.removeFromInventory(copper);
        }

        GameObject iron = player.getItemInInventory(GameObjectType.IRON);
        if (iron != null)
        {
            player.addToInventory(GameObjectType.IRON_BAR, iron.getNumber());
            player.removeFromInventory(iron);
        }

        GameObject iridium = player.getItemInInventory(GameObjectType.IRIDIUM);
        if (iridium != null)
        {
            player.addToInventory(GameObjectType.IRIDIUM_BAR, iridium.getNumber());
            player.removeFromInventory(iridium);
        }

        GameObject gold = player.getItemInInventory(GameObjectType.GOLD);
        if (gold != null)
        {
            player.addToInventory(GameObjectType.GOLD_BAR, gold.getNumber());
            player.removeFromInventory(gold);
        }
    }
}
