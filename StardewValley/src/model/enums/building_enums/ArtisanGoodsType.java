package model.enums.building_enums;

import model.Game;
import model.GameObject;
import model.enums.GameObjectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum ArtisanGoodsType
{
    HONEY(GameObjectType.HONEY, CraftingRecipeEnums.BEE_HOUSE_RECIPE, "It's a sweet syrup produced by bees.",
            75, 56, null, 350),


    CHEESE_1(GameObjectType.CHEESE, CraftingRecipeEnums.CHEESE_PRESS_RECIPE, "It's your basic cheese.", 100,
            3, new GameObject(GameObjectType.MILK, 1), 230),
    CHEESE_2(GameObjectType.CHEESE, CraftingRecipeEnums.CHEESE_PRESS_RECIPE, "It's your basic cheese.", 100,
            3, new GameObject(GameObjectType.MILK, 1), 345),

    GOAT_CHEESE_1(GameObjectType.GOAT_CHEESE, CraftingRecipeEnums.CHEESE_PRESS_RECIPE, "Soft cheese made from goat's milk.",
            100, 3, new GameObject(GameObjectType.GOAT_MILK, 1), 400),
    GOAT_CHEESE_2(GameObjectType.GOAT_CHEESE, CraftingRecipeEnums.CHEESE_PRESS_RECIPE, "Soft cheese made from goat's milk.",
            100, 3, new GameObject(GameObjectType.LARGE_GOAT_MILK, 1), 600),


    BEER(GameObjectType.BEER, CraftingRecipeEnums.KEG_RECIPE, "Drink in moderation.",
            50, 14, new GameObject(GameObjectType.WHEAT, 1), 200),

    VINEGAR(GameObjectType.VINEGAR, CraftingRecipeEnums.KEG_RECIPE, "An aged fermented liquid used in many cooking recipes.",
            13, 10, new GameObject(GameObjectType.RICE, 1), 100),

    COFFEE(GameObjectType.COFFEE, CraftingRecipeEnums.KEG_RECIPE, "It smells delicious. This is sure to give you a boost.",
            75, 2, new GameObject(GameObjectType.COFFEE_BEAN, 5), 150),

    JUICE(GameObjectType.JUICE, CraftingRecipeEnums.KEG_RECIPE, "A sweet, nutritious beverage.",
            -1, 56, null, -1),

    MEAD(GameObjectType.MEAD, CraftingRecipeEnums.KEG_RECIPE, "A fermented beverage made from honey. Drink in moderation.",
            100, 10, new GameObject(GameObjectType.HONEY, 1), 300),

    PALE_ALE(GameObjectType.PALE_ALE, CraftingRecipeEnums.KEG_RECIPE, "Drink in moderation.",
            50, 42, new GameObject(GameObjectType.HOPS_CROP, 1), 300),

    WINE(GameObjectType.WINE, CraftingRecipeEnums.KEG_RECIPE, "Drink in moderation.",
            -1, 98, null, -1),


    DRIED_MUSHROOMS(GameObjectType.DRIED_MUSHROOM, CraftingRecipeEnums.DEHYDRATOR_RECIPE, "A package of gourmet mushrooms.",
            50, -1, null, -1),

    DRIED_FRUIT(GameObjectType.DRIED_FRUIT, CraftingRecipeEnums.DEHYDRATOR_RECIPE, "Chewy pieces of dried fruit.",
            75, -1, null, -1),

    RAISINS(GameObjectType.RAISIN, CraftingRecipeEnums.DEHYDRATOR_RECIPE, "It's said to be the Junimos' favorite food.",
            125, -1, new GameObject(GameObjectType.GRAPE, 5), 600),


    COAL(GameObjectType.COAL, CraftingRecipeEnums.CHARCOAL_KILN_RECIPE, "Turns 10 pieces of wood into one piece of coal.",
            -1, 1, new GameObject(GameObjectType.WOOD, 10), 50),


    CLOTH(GameObjectType.CLOTH, CraftingRecipeEnums.LOOM_RECIPE, "A bolt of fine wool cloth.",
            -1, 4, new GameObject(GameObjectType.WOOL, 1), 470),


    MAYONNAISE_1(GameObjectType.MAYONNAISE, CraftingRecipeEnums.MAYONNAISE_MACHINE_RECIPE, "It looks spreadable.",
            50, 3, new GameObject(GameObjectType.EGG, 1), 190),

    MAYONNAISE_2(GameObjectType.MAYONNAISE, CraftingRecipeEnums.MAYONNAISE_MACHINE_RECIPE, "It looks spreadable.",
            50, 3, new GameObject(GameObjectType.LARGE_EGG, 1), 237),


    DUCK_MAYONNAISE(GameObjectType.DUCK_MAYONNAISE, CraftingRecipeEnums.MAYONNAISE_MACHINE_RECIPE, "It's a rich, yellow mayonnaise.",
            75, 3, new GameObject(GameObjectType.DUCK_EGG, 1), 37),

    DINOSAUR_MAYONNAISE(GameObjectType.DINOSAUR_MAYONNAISE, CraftingRecipeEnums.MAYONNAISE_MACHINE_RECIPE, "It's thick and creamy, with a vivid green hue. It smells like grass and leather.",
            125, 3, new GameObject(GameObjectType.DINOSAUR_EGG, 1), 800),


    TRUFFLE_OIL(GameObjectType.TRUFFLE_OIL, CraftingRecipeEnums.OIL_MAKER_RECIPE, "A gourmet cooking ingredient.",
            38, 6, new GameObject(GameObjectType.TRUFFLE, 1), 1065),

    OIL_1(GameObjectType.OIL, CraftingRecipeEnums.OIL_MAKER_RECIPE, "All purpose cooking oil.",
            13, 6, new GameObject(GameObjectType.CORN, 1), 100),

    OIL_2(GameObjectType.OIL, CraftingRecipeEnums.OIL_MAKER_RECIPE, "All purpose cooking oil.",
            13, 28, new GameObject(GameObjectType.SUNFLOWER_SEEDS, 1), 100),

    OIL_3(GameObjectType.OIL, CraftingRecipeEnums.OIL_MAKER_RECIPE, "All purpose cooking oil.",
            13, 1, new GameObject(GameObjectType.SUNFLOWER, 1), 100),


    PICKLES(GameObjectType.PICKLE, CraftingRecipeEnums.PRESERVES_JAR_RECIPE, "A jar of your home-made pickles.",
            -1, 6, null, -1),

    JELLY_1(GameObjectType.JELLY, CraftingRecipeEnums.PRESERVES_JAR_RECIPE, "Gooey.",
            -1, 42, null, -1),

    SMOKED_FISH(GameObjectType.SMOKED_FISH, CraftingRecipeEnums.FISH_SMOKER_RECIPE, "A whole fish, smoked to perfection.",
            -1, 1, null, -1),

    ANY_METAL_BAR(null, CraftingRecipeEnums.FURNACE_RECIPE, "Turns ore and coal into metal bars.",
            -1, 4, null, -1)
    ;

    private final GameObjectType type;
    private final CraftingRecipeEnums device;
    private final String description;
    private final int energy;
    private final int processTime; // hours (each day is 14 hours)
    GameObject ingredient;
    private final int sellPrice;

    ArtisanGoodsType(GameObjectType type, CraftingRecipeEnums device, String description, int energy, int processTime,
                     GameObject ingredient, int sellPrice)
    {
        this.type = type;
        this.device = device;
        this.description = description;
        this.energy = energy;
        this.processTime = processTime;
        this.ingredient = ingredient;
        this.sellPrice = sellPrice;
    }

    public GameObjectType getType()
    {
        if (type == null)
        {
            Random rand = new Random();
            int random = rand.nextInt(4);

            if (random == 0)
            {
                return GameObjectType.COPPER_BAR;
            }

            if (random == 1)
            {
                return GameObjectType.IRIDIUM_BAR;
            }

            if (random == 2)
            {
                return GameObjectType.IRON_BAR;
            }

            return GameObjectType.GOLD_BAR;
        }

        return type;
    }

    public CraftingRecipeEnums getDevice()
    {
        return device;
    }

    public String getDescription()
    {
        return description;
    }

    public int getEnergy(GameObjectType ingredient)
    {
        return energy;
    }

    public int getProcessTime()
    {
        return processTime;
    }

    public GameObject getIngredient()
    {
        return ingredient;
    }

    public int getSellPrice()
    {
        return sellPrice;
    }

    public boolean isIngredient(GameObjectType ingredient)
    {
        if (ingredient == null)
        {
            return false;
        }

        return ingredient.equals(this.ingredient.getObjectType());
    }

    public boolean isEdible()
    {
        if ( type == null || type.equals(GameObjectType.COAL) || type.equals(GameObjectType.CLOTH))
        {
            return false;
        }

        return true;
    }

    public static ArtisanGoodsType getArtisanType(String name)
    {
        CraftingRecipeEnums craft = CraftingRecipeEnums.getRecipeFromItemName(name);

        if (craft == null)
        {
            return null;
        }

        for (ArtisanGoodsType type : ArtisanGoodsType.values())
        {
            if (type.getDevice() == craft)
            {
                return type;
            }
        }

        return null;
    }
}
