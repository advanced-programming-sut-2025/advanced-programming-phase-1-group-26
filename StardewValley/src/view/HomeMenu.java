package view;

import control.GeneralController;
import control.game.activities.HomeController;
import model.enums.regex_enums.GeneralCommands;
import model.enums.regex_enums.HomeCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class HomeMenu implements AppMenu
{
    HomeController homeController = new HomeController();
    GeneralController generalController = new GeneralController();

    @Override
    public void check(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        /* Player Commands */
        if ((matcher = GeneralCommands.ENERGY_SHOW.getMatcher(input)) != null) {
            System.out.println(generalController.energyShow());
        } else if ((matcher = GeneralCommands.INVENTORY_SHOW.getMatcher(input)) != null) {
            generalController.inventoryShow();
        } else if ((matcher = GeneralCommands.INVENTORY_TRASH_NUMBER.getMatcher(input)) != null ||
                (matcher = GeneralCommands.INVENTORY_TRASH.getMatcher(input)) != null) {
            System.out.println(generalController.inventoryTrash(matcher));
        } else if ((matcher = GeneralCommands.TOOLS_EQUIP.getMatcher(input)) != null) {
            System.out.println(generalController.toolsEquip(matcher));
        } else if ((matcher = GeneralCommands.TOOLS_SHOW_CURRENT.getMatcher(input)) != null) {
            System.out.println(generalController.toolsShowCurrent());
        } else if ((matcher = GeneralCommands.TOOLS_SHOW_AVAILABLE.getMatcher(input)) != null) {
            generalController.toolsShowAvailable();
        } else if ((matcher = GeneralCommands.TOOLS_UPGRADE.getMatcher(input)) != null) {
            System.out.println(generalController.toolsUpgrade(matcher));
        }

        /* player cheat codes */
        else if ((matcher = GeneralCommands.ENERGY_SET.getMatcher(input)) != null) {
            System.out.println(generalController.energySet(matcher));
        } else if ((matcher = GeneralCommands.ENERGY_UNLIMITED.getMatcher(input)) != null) {
            System.out.println(generalController.energyUnlimited());
        }

        /* time and date commands */
        else if (GeneralCommands.SHOW_TIME.getMatcher(input) != null)
        {
            System.out.println(generalController.showTime());
        } else if (GeneralCommands.SHOW_DATE.getMatcher(input) != null)
        {
            System.out.println(generalController.showDate());
        } else if (GeneralCommands.SHOW_DATE_TIME.getMatcher(input) != null)
        {
            System.out.println(generalController.showDateAndTime());
        } else if (GeneralCommands.SHOW_DAY_OF_WEEK.getMatcher(input) != null)
        {
            System.out.println(generalController.showDayOfWeek());
        } else if (GeneralCommands.SHOW_SEASON.getMatcher(input) != null)
        {
            System.out.println(generalController.showSeason());
        }

        /* time and date cheat codes */
        else if ((matcher = GeneralCommands.CHEAT_CODE_ADVANCE_TIME.getMatcher(input)) != null)
        {
            String time = matcher.group("time");
            System.out.println(generalController.cheatAdvanceTime(time));
        } else if ((matcher = GeneralCommands.CHEAT_CODE_ADVANCE_DATE.getMatcher(input)) != null)
        {
            String date = matcher.group("date");
            System.out.println(generalController.cheatAdvanceDate(date));
        }

        /* weather  commands*/
        else if (GeneralCommands.SHOW_WEATHER.getMatcher(input) != null)
        {
            System.out.println(generalController.showWeather());
        } else if (GeneralCommands.SHOW_TOMORROW_WEATHER.getMatcher(input) != null)
        {
            System.out.println(generalController.showTomorrowWeather());
        }

        /* weather cheat codes */
        else if ((matcher = GeneralCommands.CHEAT_CODE_SET_TOMORROW_WEATHER.getMatcher(input)) != null)
        {
            String type = matcher.group("type");
            System.out.println(generalController.cheatChangeTomorrowWeather(type));
        } else if ((matcher = GeneralCommands.CHEAT_CODE_HIT_THUNDER.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(generalController.cheatHitThunder(x,y));
        }

        /* basic map commands */
        else if ((matcher = GeneralCommands.PWD.getMatcher(input)) != null)
        {
            System.out.println(generalController.pwd());
        } else if ((matcher = GeneralCommands.SHOW_AROUND.getMatcher(input)) != null)
        {
            System.out.println(generalController.showAround());
        } else if ((matcher = GeneralCommands.PRINT_MAP.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            String size = matcher.group("size");
            System.out.println(generalController.printMap(x,y,size));
        } else if ((matcher = GeneralCommands.PRINT_ENTIRE_MAP.getMatcher(input)) != null)
        {
            System.out.println(generalController.printEntireMap());
        }

        /* walk commands */
        else if ((matcher = GeneralCommands.CAN_WALK.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(generalController.canWalk(x,y));
        } else if ((matcher = GeneralCommands.WALK.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            System.out.println(generalController.walk(x,y, scanner));
        } else if ((matcher = GeneralCommands.SHOW_PATH.getMatcher(input)) != null)
        {
            String x = matcher.group("x").trim();
            String y = matcher.group("y").trim();
            System.out.println(generalController.showPath(x,y));
        }

        /* walk cheat code */
        else if ((matcher = GeneralCommands.SUDO_CD.getMatcher(input)) != null)
        {
            String x = matcher.group("x").trim();
            String y = matcher.group("y").trim();
            System.out.println(generalController.sudoCD(x,y));
        }

        /* basic general commands */
        else if ((matcher = GeneralCommands.WHOAMI.getMatcher(input)) != null)
        {
            System.out.println(generalController.whoAmI());
        } else if ((matcher = GeneralCommands.NEXT_TURN.getMatcher(input)) != null)
        {
            generalController.nextTurn();
        } else if ((matcher = GeneralCommands.SUDO_NEXT_TURN.getMatcher(input)) != null)
        {
            System.out.println(generalController.sudoNextTurn());
        } else if ((matcher = GeneralCommands.EXIT_GAME.getMatcher(input)) != null)
        {
            System.out.println(generalController.exitGame(scanner));
        } else if ((matcher = GeneralCommands.DELETE_GAME.getMatcher(input)) != null)
        {
            System.out.println(generalController.deleteGame(scanner));
        }


        else if ((matcher = HomeCommands.CRAFTING_SHOW_RECIPES.getMatcher(input)) != null)
        {
            System.out.println(homeController.showCraftingRecipes());
        } else if ((matcher = HomeCommands.CRAFTING_CRAFT.getMatcher(input)) != null)
        {
            String itemName = matcher.group("itemName");
            System.out.println(homeController.craftItem(itemName));
        } else if ((matcher = HomeCommands.PLACE_ITEM.getMatcher(input)) != null)
        {
            String itemName = matcher.group("itemName");
            String direction = matcher.group("direction");
            System.out.println(homeController.placeItem(itemName, direction));
        } else if ((matcher = HomeCommands.CHEAT_ADD_ITEM.getMatcher(input)) != null)
        {
            String itemName = matcher.group("itemName");
            String count = matcher.group("count");
            System.out.println(homeController.cheatAddItem(itemName, count));
        } else if ((matcher = HomeCommands.COOKING_PUT.getMatcher(input)) != null)
        {
            String item = matcher.group("item");
            System.out.println(homeController.putInRefrigerator(item));
        } else if ((matcher = HomeCommands.COOKING_PICK.getMatcher(input)) != null)
        {
            String item = matcher.group("item");
            System.out.println(homeController.pickFromRefrigerator(item));
        } else if ((matcher = HomeCommands.COOKING_SHOW_RECIPES.getMatcher(input)) != null)
        {
            System.out.println(homeController.showCookingRecipes());
        } else if ((matcher = HomeCommands.COOKING_PREPARE.getMatcher(input)) != null)
        {
            String recipeName = matcher.group("recipeName");
            System.out.println(homeController.cookingPrepare(recipeName));
        } else if ((matcher = HomeCommands.EAT.getMatcher(input)) != null)
        {
            String foodName = matcher.group("foodName");
            System.out.println(homeController.eat(foodName));
        } else if ((matcher = HomeCommands.CHEAT_ADD_CRAFTING_RECIPE.getMatcher(input)) != null)
        {
            String recipeName = matcher.group("recipeName");
            System.out.println(homeController.cheatAddCraftingRecipe(recipeName));
        } else if ((matcher = HomeCommands.CHEAT_ADD_COOKING_RECIPE.getMatcher(input)) != null)
        {
            String recipeName = matcher.group("recipeName");
            System.out.println(homeController.cheatAddCookingRecipe(recipeName));
        } else if ((matcher = HomeCommands.CD_FARM.getMatcher(input)) != null)
        {
            System.out.println(homeController.goOut());
        } else if ((matcher = HomeCommands.PRINT_MAP.getMatcher(input)) != null)
        {
            String x = matcher.group("x");
            String y = matcher.group("y");
            String size = matcher.group("size");
            System.out.println(homeController.printMap(x,y,size));
        } else if ((matcher = HomeCommands.SHOW_AROUND.getMatcher(input)) != null)
        {
            System.out.println(homeController.showAround());
        } else if ((matcher = HomeCommands.PRINT_ENTIRE_MAP.getMatcher(input)) != null)
        {
            System.out.println(homeController.printEntireMap());
        } else
        {
            System.out.println("invalid command");
        }
    }

    public static String scan(Scanner scanner)
    {
        return scanner.nextLine().trim();
    }

    public static void println(String output)
    {
        System.out.println(output);
    }
}
