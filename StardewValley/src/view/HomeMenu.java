package view;

import control.game.activities.HomeController;
import model.enums.regex_enums.HomeCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class HomeMenu implements AppMenu
{
    HomeController controller = new HomeController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if ((matcher = HomeCommands.CRAFTING_SHOW_RECIPES.getMatcher(input)) != null)
        {
            System.out.println(controller.showCraftingRecipes());
        } else if ((matcher = HomeCommands.CRAFTING_CRAFT.getMatcher(input)) != null)
        {
            String itemName = matcher.group("itemName");
            System.out.println(controller.craftItem(itemName));
        } else if ((matcher = HomeCommands.PLACE_ITEM.getMatcher(input)) != null)
        {
            String itemName = matcher.group("itemName");
            String direction = matcher.group("direction");
            System.out.println(controller.placeItem(itemName, direction));
        } else if ((matcher = HomeCommands.CHEAT_ADD_ITEM.getMatcher(input)) != null)
        {
            String itemName = matcher.group("itemName");
            String count = matcher.group("count");
            System.out.println(controller.cheatAddItem(itemName, count));
        } else if ((matcher = HomeCommands.COOKING_PUT.getMatcher(input)) != null)
        {
            String item = matcher.group("item");
            System.out.println(controller.putInRefrigerator(item));
        } else if ((matcher = HomeCommands.COOKING_PICK.getMatcher(input)) != null)
        {
            String item = matcher.group("item");
            System.out.println(controller.pickFromRefrigerator(item));
        } else if ((matcher = HomeCommands.COOKING_SHOW_RECIPES.getMatcher(input)) != null)
        {
            System.out.println(controller.showCookingRecipes());
        } else if ((matcher = HomeCommands.COOKING_PREPARE.getMatcher(input)) != null)
        {
            String recipeName = matcher.group("recipeName");
            System.out.println(controller.cookingPrepare(recipeName));
        } else if ((matcher = HomeCommands.EAT.getMatcher(input)) != null)
        {
            String foodName = matcher.group("foodName");
            System.out.println(controller.eat(foodName));
        } else if ((matcher = HomeCommands.CHEAT_ADD_CRAFTING_RECIPE.getMatcher(input)) != null)
        {
            String recipeName = matcher.group("recipeName");
            System.out.println(controller.cheatAddCraftingRecipe(recipeName));
        } else if ((matcher = HomeCommands.CHEAT_ADD_COOKING_RECIPE.getMatcher(input)) != null)
        {
            String recipeName = matcher.group("recipeName");
            System.out.println(controller.cheatAddCookingRecipe(recipeName));
        } else if ((matcher = HomeCommands.GO_OUT.getMatcher(input)) != null)
        {
            System.out.println(controller.goOut());
        }
    }

    public static String scan()
    {
        String text = mainScanner.nextLine().trim();
        return text;
    }

    public static void println(String output)
    {
        System.out.println(output);
    }
}
