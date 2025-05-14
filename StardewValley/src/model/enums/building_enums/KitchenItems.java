package model.enums.building_enums;

import model.GameObject;
import model.enums.GameObjectType;
import model.enums.SkillType;

import java.util.HashMap;
import java.util.Map;

public enum KitchenItems
{
    FRIED_EGG(GameObjectType.FRIED_EGG, new HashMap<>() {{
        put(GameObjectType.EGG, 1);
    }}, 50, new HashMap<>(), 35),

    BAKED_FISH(GameObjectType.BAKED_FISH, new HashMap<>() {{
        put(GameObjectType.SARDINE, 1);
        put(GameObjectType.SALMON, 1);
        put(GameObjectType.WHEAT, 1);
    }}, 75, new HashMap<>(), 100),

    SALAD(GameObjectType.SALAD, new HashMap<>() {{
        put(GameObjectType.LEEK, 1);
        put(GameObjectType.DANDELION, 1);
    }}, 113, new HashMap<>(), 110),

    OMELET(GameObjectType.OMELET, new HashMap<>() {{
        put(GameObjectType.EGG, 1);
        put(GameObjectType.MILK, 1);
    }}, 100, new HashMap<>(), 125),

    PUMPKIN_PIE(GameObjectType.PUMPKIN_PIE, new HashMap<>() {{
        put(GameObjectType.PUMPKIN, 1);
        put(GameObjectType.WHEAT_FLOWER, 1);
        put(GameObjectType.MILK, 1);
        put(GameObjectType.SUGAR, 1);
    }}, 225, new HashMap<>(), 385),

    SPAGHETTI(GameObjectType.SPAGHETTI, new HashMap<>() {{
        put(GameObjectType.WHEAT_FLOWER, 1);
        put(GameObjectType.TOMATO, 1);
    }}, 75, new HashMap<>(), 120),

    PIZZA(GameObjectType.PIZZA, new HashMap<>() {{
        put(GameObjectType.WHEAT_FLOWER, 1);
        put(GameObjectType.TOMATO, 1);
        put(GameObjectType.CHEESE, 1);
    }}, 150, new HashMap<>(), 300),

    TORTILLA(GameObjectType.TORTILLA, new HashMap<>() {{
        put(GameObjectType.CORN, 1);
    }}, 50, new HashMap<>(), 50),

    MAKI_ROLL(GameObjectType.MAKI_ROLL, new HashMap<>() {{
        put(GameObjectType.FISH, 1);
        put(GameObjectType.RICE, 1);
        put(GameObjectType.FIBER, 1);
    }}, 100, new HashMap<>(), 220),

    TRIPLE_SHOT_ESPRESSO(GameObjectType.TRIPLE_SHOT_ESPRESSO, new HashMap<>() {{
        put(GameObjectType.COFFEE, 1);
    }}, 200, new HashMap<>(), 450),

    COOKIE(GameObjectType.COOKIE, new HashMap<>() {{
        put(GameObjectType.WHEAT_FLOWER, 1);
        put(GameObjectType.SUGAR, 1);
        put(GameObjectType.EGG, 1);
    }}, 90, new HashMap<>(), 140),

    HASH_BROWNS(GameObjectType.HASH_BROWN, new HashMap<>() {{
        put(GameObjectType.POTATO, 1);
        put(GameObjectType.OIL, 1);
    }}, 90, new HashMap<>(), 120),

    PANCAKES(GameObjectType.PANCAKE, new HashMap<>() {{
        put(GameObjectType.WHEAT_FLOWER, 1);
        put(GameObjectType.EGG, 1);
    }}, 90, new HashMap<>(), 80),

    FRUIT_SALAD(GameObjectType.FRUIT_SALAD, new HashMap<>() {{
        put(GameObjectType.BLUEBERRY, 1);
        put(GameObjectType.MELON, 1);
        put(GameObjectType.APRICOT, 1);
    }}, 263, new HashMap<>(), 450),

    RED_PLATE(GameObjectType.RED_PLATE, new HashMap<>() {{
        put(GameObjectType.RED_CABBAGE, 1);
        put(GameObjectType.RADISH, 1);
    }}, 240, new HashMap<>(), 400),

    BREAD(GameObjectType.BREAD, new HashMap<>() {{
        put(GameObjectType.WHEAT_FLOWER, 1);
    }}, 50, new HashMap<>(), 60),

    SALMON_DINNER(GameObjectType.SALMON_DINNER, new HashMap<>() {{
        put(GameObjectType.SALMON, 1);
        put(GameObjectType.AMARANTH, 1);
        put(GameObjectType.KALE, 1);
    }}, 125, new HashMap<>(), 300),

    VEGETABLE_MEDLEY(GameObjectType.VEGETABLE_MEDLEY, new HashMap<>() {{
        put(GameObjectType.TOMATO, 1);
        put(GameObjectType.BEET, 1);
    }}, 165, new HashMap<>(), 120),

    FARMERS_LUNCH(GameObjectType.FARMER_LUNCH, new HashMap<>() {{
        put(GameObjectType.OMELET, 1);
        put(GameObjectType.PARSNIP, 1);
    }}, 200, new HashMap<>(), 150
    ),

    SURVIVAL_BURGER(GameObjectType.SURVIVAL_BURGER, new HashMap<>() {{
        put(GameObjectType.BREAD, 1);
        put(GameObjectType.CARROT, 1);
        put(GameObjectType.EGGPLANT, 1);
    }}, 125, new HashMap<>(), 180),

    DISH_O_THE_SEA(GameObjectType.DISH_O_THE_SEA, new HashMap<>() {{
        put(GameObjectType.SARDINE, 2);
        put(GameObjectType.HASH_BROWN, 1);
    }}, 150, new HashMap<>(), 220),

    SEAFOAM_PUDDING(GameObjectType.SEAFORM_PUDDING, new HashMap<>() {{
        put(GameObjectType.FLOUNDER, 1);
        put(GameObjectType.MIDNIGHT_CARP, 1);
    }}, 175, new HashMap<>(), 300),

    MINERS_TREAT(GameObjectType.MINER_TREAT, new HashMap<>() {{
        put(GameObjectType.CARROT, 1);
        put(GameObjectType.SUGAR, 1);
        put(GameObjectType.MILK, 1);
    }}, 125, new HashMap<>(), 200),

    ;

    private final GameObjectType type;
    private final HashMap<GameObjectType, Integer> ingredients;
    private final int energy;
    private final HashMap<SkillType, Integer> buff;
    private final int sellPrice;

    KitchenItems(GameObjectType type, HashMap<GameObjectType, Integer> ingredients, int energy,
                 HashMap<SkillType, Integer> buff, int sellPrice)
    {
        this.type = type;
        this.ingredients = ingredients;
        this.energy = energy;
        this.buff = buff;
        this.sellPrice = sellPrice;
    }

    public GameObjectType getType()
    {
        return type;
    }

    public HashMap<GameObjectType, Integer> getIngredients()
    {
        return ingredients;
    }

    public int getEnergy()
    {
        return energy;
    }

    public HashMap<SkillType, Integer> getBuff()
    {
        return buff;
    }

    public int getSellPrice()
    {
        return sellPrice;
    }

    public String getInfo()
    {
        StringBuilder output = new StringBuilder();

        output.append("Name: ").append(type).append("\n");
        output.append("------------------\n");

        output.append("Ingredients: \n");
        for (Map.Entry<GameObjectType, Integer> entry : ingredients.entrySet())
        {
            output.append("\t").append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
        }

        output.append("------------------\n");
        output.append("energy: ").append(energy).append("\n");
        output.append("sell price: ").append(sellPrice).append("\n");

        return output.toString().trim();
    }

    public static KitchenItems getKitchenRecipe(String recipeName)
    {
        for (KitchenItems kitchenItems : KitchenItems.values())
        {
            if (kitchenItems.getType().toString().equalsIgnoreCase(recipeName))
            {
                return kitchenItems;
            }
        }
        return null;
    }

    public static KitchenItems isEdible(GameObjectType type)
    {
        for (KitchenItems kitchenItems : KitchenItems.values())
        {
            if (kitchenItems.type.equals(type))
            {
                return kitchenItems;
            }
        }
        return null;
    }

//    public static boolean isEdible(GameObjectType type)
//    {
//        for (KitchenItems kitchenItems : KitchenItems.values())
//        {
//            if (kitchenItems.getType().equals(type))
//            {
//                return true;
//            }
//
//            for (GameObjectType ingredientType : kitchenItems.getIngredients().keySet())
//            {
//                if (ingredientType.equals(type))
//                {
//                    return true;
//                }
//            }
//        }
//
//        return false;
    }
}
