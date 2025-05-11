package control.game.activities;

import model.*;
import model.building.Cooking.EdibleThing;
import model.building.CraftingItems.CraftingItem;
import model.building.CraftingItems.CraftingItemCreator;
import model.enums.GameObjectType;
import model.enums.Menu;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenItems;
import view.HomeMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeController
{
    public Result showCraftingRecipes()
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

    public Result cheatAddCraftingRecipe(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        CraftingRecipeEnums recipe = CraftingRecipeEnums.getRecipeFromItemName(itemName);
        if (recipe == null)
        {
            return new Result(false, "There is no crafting recipe with that name.");
        }

        ArrayList<CraftingRecipeEnums> recipes = player.getCraftingRecipes();
        if (recipes.contains(recipe))
        {
            return new Result(false, "You already have access to this recipe.");
        }

        recipes.add(recipe);
        return new Result(true, "Recipe added to your crafting recipes.");
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

    public Result cheatAddItem(String itemName, String count)
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

    public Result putInRefrigerator(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObjectType type = GameObjectType.getGameObjectType(itemName);
        if (type == null)
        {
            return new Result(false, "This item does not exist.");
        }

        GameObject object = player.getItemInInventory(type);
        if (type == null)
        {
            return new Result(false, "You don't currently have this item in your inventory.");
        }

        KitchenItems kitchenItem = KitchenItems.isEdible(type);
        if (kitchenItem == null)
        {
            return new Result(false, "This item is not edible, so you can't put it in the refrigerator.\n" +
                    "Sorry, it's company policy.");
        }

        EdibleThing thing = new EdibleThing(kitchenItem, object.getNumber());
        player.getRefrigerator().add(thing);
        player.removeFromInventory(object);

        return new Result(true, "You successfully put " + itemName + " into your inventory.");
    }

    public Result pickFromRefrigerator(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObjectType type = GameObjectType.getGameObjectType(itemName);
        if (type == null)
        {
            return new Result(false, "This item does not exist.");
        }

        KitchenItems kitchenItem = KitchenItems.isEdible(type);
        if (kitchenItem == null)
        {
            return new Result(false, "This item is not edible");
        }

        EdibleThing thing = player.getFromRefrigerator(type);
        if (thing == null)
        {
            return new Result(false, "This item is not currently in your refrigerator.");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "You don't have any capacity left in your backpack :(");
        }

        GameObject object = new GameObject(type, thing.getNumber());
        player.addToInventory(object);
        player.getRefrigerator().remove(thing);

        return new Result(true, "You successfully picked " + itemName + " from the refrigerator.");
    }

    public Result showCookingRecipes()
    {
        ArrayList<KitchenItems> recipes = App.getCurrentGame().getCurrentPlayer().getCookingRecipes();

        StringBuilder result = new StringBuilder();
        result.append("You have ").append(recipes.size()).append(" cooking recipes in total.\n");
        result.append("Here they are: \n");

        for (KitchenItems c : recipes)
        {
            result.append(c.getInfo());
            result.append("\n");
        }

        return new Result(true, result.toString().trim());
    }

    public Result cheatAddCookingRecipe(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        KitchenItems recipe = KitchenItems.getKitchenRecipe(itemName);
        if (recipe == null)
        {
            return new Result(false, "There is no cooking recipe with that name.");
        }

        ArrayList<KitchenItems> recipes = player.getCookingRecipes();
        if (recipes.contains(recipe))
        {
            return new Result(false, "You already have access to this recipe.");
        }

        recipes.add(recipe);
        return new Result(true, "Recipe added to your cooking recipes.");
    }

    public Result cookingPrepare(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObjectType type = GameObjectType.getGameObjectType(itemName);
        if (type == null)
        {
            return new Result(false, "This item does not exist.");
        }

        KitchenItems recipe = KitchenItems.getKitchenRecipe(itemName);
        if (recipe == null)
        {
            return new Result(false, "There is no cooking recipe for this item.");
        }

        ArrayList<KitchenItems> recipes = player.getCookingRecipes();
        if (!recipes.contains(recipe))
        {
            return new Result(false, "You do not have access to this recipe.");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "You don't have any capacity left in your backpack.");
        }

        ArrayList<EdibleThing> refrigerator = player.getRefrigerator();
        HashMap<GameObjectType, Integer> ingredients = recipe.getIngredients();
        boolean canAfford = true;

        for (GameObjectType a : ingredients.keySet())
        {
            int amount = player.howManyInInventory(a) + player.howManyInRefrigerator(a);
            if (amount < ingredients.get(a))
            {
                canAfford = false;
            }
        }

        if (!canAfford)
        {
            return new Result(false, "You don't have enough ingredients for this recipe.+");
        }

        for (GameObjectType a : ingredients.keySet())
        {
            int removedFromInventory = Math.min(player.howManyInInventory(a), ingredients.get(a));
            if (removedFromInventory > 0)
            {
                player.removeAmountFromInventory(a, removedFromInventory);
            }

            int removedFromRefrigerator = ingredients.get(a) - removedFromInventory;
            if (removedFromRefrigerator > 0)
            {
                player.removeAmountFromRefrigerator(a, removedFromRefrigerator);
            }
        }

        player.increaseEnergy(-3);

        EdibleThing food = new EdibleThing(recipe, 5);
        player.addToInventory(food);

        return new Result(true, food.getType() + " was added to your backpack.");
    }

    public Result eat(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObjectType type = GameObjectType.getGameObjectType(itemName);
        if (type == null)
        {
            return new Result(false, "This item does not exist.");
        }

        KitchenItems kitchenItem = KitchenItems.isEdible(type);
        if (kitchenItem == null)
        {
            return new Result(false, "This item is not edible :(");
        }

        int count = player.howManyInInventory(type);
        if (count == 0)
        {
            return new Result(false, "You don't currently have this food in your backpack.");
        }

        player.removeAmountFromInventory(type, 1);
        player.increaseEnergy(kitchenItem.getEnergy());

        return new Result(true, "Yum Yum, you just ate " + kitchenItem.getType());
    }

    public Result goOut()
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.goToFarm();
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "Going to farm...");
    }
}
