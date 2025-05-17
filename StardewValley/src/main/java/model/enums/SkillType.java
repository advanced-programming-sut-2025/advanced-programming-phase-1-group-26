package model.enums;

import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenRecipe;

import java.util.HashMap;

public enum SkillType
{
    Farming(new HashMap<>() {{
        put(CraftingRecipeEnums.SPRINKLER_RECIPE, 1);
        put(CraftingRecipeEnums.QUALITY_SPRINKLER_RECIPE, 2);
        put(CraftingRecipeEnums.IRIDIUM_SPRINKLER_RECIPE, 3);
        put(CraftingRecipeEnums.DELUXE_SCARECROW_RECIPE, 2);
        put(CraftingRecipeEnums.BEE_HOUSE_RECIPE, 1);
        put(CraftingRecipeEnums.CHEESE_PRESS_RECIPE, 2);
        put(CraftingRecipeEnums.KEG_RECIPE, 3);
        put(CraftingRecipeEnums.LOOM_RECIPE, 3);
        put(CraftingRecipeEnums.OIL_MAKER_RECIPE, 3);
        put(CraftingRecipeEnums.PRESERVES_JAR_RECIPE, 2);
    }}, new HashMap<>() {{
        put(KitchenRecipe.FARMERS_LUNCH, 1);
    }}),
    Mining(new HashMap<>() {{
        put(CraftingRecipeEnums.CHERRY_BOMB_RECIPE, 1);
        put(CraftingRecipeEnums.BOMB_RECIPE, 2);
        put(CraftingRecipeEnums.MEGA_BOMB_RECIPE, 3);
    }}, new HashMap<>() {{
        put(KitchenRecipe.MINERS_TREAT, 1);
    }}),
    Gashtogozar(new HashMap<>() {{}}, new HashMap<>() {{}}),
    Fishing(new HashMap<>() {{}}, new HashMap<>() {{
        put(KitchenRecipe.DISH_O_THE_SEA, 2);
        put(KitchenRecipe.SEAFOAM_PUDDING, 3);
    }}),
    Foraging(new HashMap<>() {{
        put(CraftingRecipeEnums.CHARCOAL_KILN_RECIPE, 1);
        put(CraftingRecipeEnums.MYSTIC_TREE_SEED_RECIPE, 4);
    }}, new HashMap<>() {{
        put(KitchenRecipe.VEGETABLE_MEDLEY, 2);
        put(KitchenRecipe.SURVIVAL_BURGER, 3);
    }}),
    Max_Energy(new HashMap<>() {{}}, new HashMap<>() {{}});

    private final HashMap<CraftingRecipeEnums, Integer> craftingRecipes;
    private final HashMap<KitchenRecipe, Integer> cookingRecipes;

    SkillType(HashMap<CraftingRecipeEnums, Integer> craftingRecipes, HashMap<KitchenRecipe, Integer> cookingRecipes)
    {
        this.craftingRecipes = craftingRecipes;
        this.cookingRecipes = cookingRecipes;
    }

    public HashMap<CraftingRecipeEnums, Integer> getCraftingRecipes()
    {
        return craftingRecipes;
    }

    public HashMap<KitchenRecipe, Integer> getCookingRecipes()
    {
        return cookingRecipes;
    }
}
