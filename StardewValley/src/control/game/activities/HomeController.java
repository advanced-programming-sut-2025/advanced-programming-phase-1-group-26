package control.game.activities;

import model.*;
import model.building.CraftingItems.CherryBomb;
import model.building.CraftingItems.CraftingItem;
import model.building.CraftingItems.CraftingItemCreator;
import model.enums.GameObjectType;
import model.enums.building_enums.CraftingRecipeEnums;
import view.HomeMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeController
{
    public Result showRecipes()
    {
        ArrayList<CraftingRecipeEnums> recipes = App.getCurrentGame().getCurrentPlayer().getCraftingRecipes();

        StringBuilder result = new StringBuilder();
        result.append("You have ").append(recipes.size()).append(" crafting recipes in total.\n");
        result.append("Here they are: \n");

        for (CraftingRecipeEnums c : recipes)
        {
            result.append(c.getInfo());
            result.append("\n");
        }

        return new Result(true, result.toString().trim());
    }

    public Result craftItem(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        CraftingRecipeEnums recipe = CraftingRecipeEnums.getRecipeFromItemName(itemName);
        if (recipe == null)
        {
            return new Result(false, "Invalid item. Either this item does not exist or there's no recipe for it.");
        }

        ArrayList<CraftingRecipeEnums> recipes = player.getCraftingRecipes();
        if (!recipes.contains(recipe))
        {
            return new Result(false, "You don't currently have access to this recipe.");
        }

        HashMap<GameObjectType, Integer> ingredients = recipe.getIngredients();
        Boolean canAfford = true;

        for (GameObjectType type : ingredients.keySet())
        {
            if (!player.hasEnoughInInventory(type, ingredients.get(type)))
            {
                HomeMenu.println("I'm sorry. You don't have enough of " + type + " in your inventory.");
                HomeMenu.println("you have: " + player.howManyInInventory(type));
                HomeMenu.println("required: " + ingredients.get(type));
                canAfford = false;
            }
        }

        if (!canAfford)
        {
            return new Result(false, "You are poor! You can't afford this recipe right now.");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "You don't have any capacity left in your backpack :(");
        }

        for (GameObjectType type : ingredients.keySet())
        {
            player.removeAmountFromInventory(type, ingredients.get(type));
        }

        player.increaseEnergy(-2);
        CraftingItem product = CraftingItemCreator.create(recipe);

        player.addToInventory(product);

        return new Result(true, recipe.getProduct() + "was added to your inventory.");
    }

    public Result placeItem(String itemName, String direction)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        GameObjectType type = GameObjectType.getGameObjectType(itemName);

        if (type == null)
        {
            return new Result(false, "This item does not exist.");
        }

        GameObject object = player.getItemInInventory(type);
        if (object == null)
        {
            return new Result(false, "You don't currently have this item in your inventory.");
        }

        Tile tile = App.getCurrentGame().getTileFromDirection(direction);
        if (tile == null)
        {
            return new Result(false, "invalid tile :(");
        }

        if (tile.getObject() != null)
        {
            return new Result(false, "This tile already has something in it.");
        }

        player.removeFromInventory(object);
        tile.setObject(object);
        return new Result(true, "Successfully placed " + itemName + " on the tile.");
    }

    public Result CHEAT_ADD_ITEM(String itemName, String count)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        GameObjectType type = GameObjectType.getGameObjectType(itemName);

        if (type == null)
        {
            return new Result(false, "This item does not exist. You can't cheat.");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "You don't have any capacity left in your backpack.\n" +
                    "What? I'm not a magician! If you want more capacity, update your f***ing backpack.");
        }

        int amount = Integer.parseInt(count);
        GameObject object = new GameObject(type, amount);
        player.addToInventory(object);

        return new Result(true, "Congrats cheater!\n" +
                "You cheat-fully added " + itemName + " to your inventory.");
    }
}
