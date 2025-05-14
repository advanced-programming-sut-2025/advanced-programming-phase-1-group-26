package control.game.activities;

import model.*;
import model.building.CraftingItems.CraftingItem;
import model.building.CraftingItems.CraftingItemCreator;
import model.enums.GameObjectType;
import model.enums.Menu;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenRecipe;
import model.tools.Tool;
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

        if (recipes.size() > 0)
        {
            result.append("Here they are: \n");
            result.append("------------------------------------\n");

            for (CraftingRecipeEnums c : recipes)
            {
                result.append(c.getInfo());
                result.append("------------------------------------\n");
            }
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
                HomeMenu.println("You don't have enough of " + type + " in your inventory.");
                HomeMenu.println("\tyou have: " + player.howManyInInventory(type));
                HomeMenu.println("\trequired: " + ingredients.get(type));
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

        player.increaseTurnEnergy(-2);
        CraftingItem product = CraftingItemCreator.create(recipe);

        player.addToInventory(product);

        return new Result(true, recipe.getProduct() + " was added to your inventory.");
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

        if (object instanceof Tool)
        {
            return new Result(false, "You can't take tools out of your backpack.");
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

        if (!count.matches("\\d+"))
        {
            return new Result(false, "invalid amount");
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

        if (!KitchenRecipe.isEdible(object.getObjectType()))
        {
            return new Result(false, "This item is not edible, so you can't put it in the refrigerator.");
        }

        if (player.getRefrigerator().size() == 5)
        {
            return new Result(false, "You can't put more than 5 refrigerators.");
        }

        player.getRefrigerator().add(object);
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

        if (!KitchenRecipe.isEdible(type))
        {
            return new Result(false, "This item is not even edible");
        }

        GameObject object = player.getFromRefrigerator(type);
        if (object == null)
        {
            return new Result(false, "This item is not currently in your refrigerator.");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "You don't have any capacity left in your backpack :(");
        }

        player.addToInventory(object);
        player.getRefrigerator().remove(object);

        return new Result(true, "You successfully picked " + itemName + " from the refrigerator.");
    }

    public Result showCookingRecipes()
    {
        ArrayList<KitchenRecipe> recipes = App.getCurrentGame().getCurrentPlayer().getCookingRecipes();

        StringBuilder result = new StringBuilder();
        result.append("You have ").append(recipes.size()).append(" cooking recipes in total.\n");
        result.append("Here they are: \n\n");

        for (KitchenRecipe c : recipes)
        {
            result.append("------------------------------------\n");
            result.append(c.getInfo());
            result.append("\n------------------------------------\n\n");
        }

        return new Result(true, result.toString().trim() + "\n");
    }

    public Result cheatAddCookingRecipe(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        KitchenRecipe recipe = KitchenRecipe.getKitchenRecipe(itemName);
        if (recipe == null)
        {
            return new Result(false, "There is no cooking recipe with that name.");
        }

        ArrayList<KitchenRecipe> recipes = player.getCookingRecipes();
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

        KitchenRecipe recipe = KitchenRecipe.getKitchenRecipe(itemName);
        if (recipe == null)
        {
            return new Result(false, "There is no cooking recipe for this item.");
        }

        ArrayList<KitchenRecipe> recipes = player.getCookingRecipes();
        if (!recipes.contains(recipe))
        {
            return new Result(false, "You do not have access to this recipe.");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "You don't have any capacity left in your backpack.");
        }

        ArrayList<GameObject> refrigerator = player.getRefrigerator();
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
            return new Result(false, "You don't have enough ingredients for this recipe.");
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

        player.increaseTurnEnergy(-3);

        GameObject food = new GameObject(recipe.getType(), 1);
        player.addToInventory(food);

        return new Result(true, "Did Gordon Ramsay teach you how to cook, or is he taking notes now?\n" +
                "You just cooked one " + food.getObjectType() + ".");
    }

    public Result eat(String itemName)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObjectType type = GameObjectType.getGameObjectType(itemName);
        if (type == null)
        {
            return new Result(false, "This item does not exist.");
        }

        if (!KitchenRecipe.isEdible(type))
        {
            return new Result(false, "This item is not edible :(");
        }

        int count = player.howManyInInventory(type);
        if (count == 0)
        {
            return new Result(false, "You don't currently have this food in your backpack.");
        }

        KitchenRecipe food = KitchenRecipe.getKitchenRecipe(itemName);
        if (food == null)
        {
            return new Result(false, "This item is not edible :(");
        }

        player.removeAmountFromInventory(type, 1);
        player.increaseTurnEnergy(food.getEnergy());

        return new Result(true, "Yum Yum, you just ate " + food.getType());
    }

    public Result printMap(String inputX, String inputY, String inputSize)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);
        int size = Integer.parseInt(inputSize);
        Player player = App.getCurrentGame().getCurrentPlayer();
        return new Result(true, player.getCurrentMap().
                getMapString(player.getLocation(), new Point(x, y), size, size).trim());
    }

    public Result showAround()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        return new Result(true, map.showAround(player.getLocation()).trim());
    }

    public Result printEntireMap()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        return new Result(true,
                map.getMapString(player.getLocation(), new Point(0,0), map.getHEIGHT(), map.getWIDTH()).trim());
    }

    public Result goOut()
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.goToFarm();
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "Going to farm...");
    }

    public Result showRefrigerator()
    {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        ArrayList<GameObject> inventory = new ArrayList<>(currentPlayer.getRefrigerator());

        if (inventory.size() == 0)
        {
            return new Result(false, "Your refrigerator is empty.");
        }

        StringBuilder output = new StringBuilder();
        output.append("refrigerator items:\n");
        output.append("----\n");
        for (GameObject object : inventory) {
            output.append(object.getObjectType().toString()).append(" x").append(object.getNumber()).append("\n");
            output.append("----\n");
        }
        return new Result(true, output.toString());
    }
}
