package model.enums;

import model.enums.building_enums.CraftingRecipeEnums;

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
    }}),
    Mining(new HashMap<>() {{
        put(CraftingRecipeEnums.CHERRY_BOMB_RECIPE, 1);
        put(CraftingRecipeEnums.BOMB_RECIPE, 2);
        put(CraftingRecipeEnums.MEGA_BOMB_RECIPE, 3);
    }}),
    Gashtogozar(new HashMap<>() {{}}),
    Fishing(new HashMap<>() {{}}),
    Foraging(new HashMap<>() {{
        put(CraftingRecipeEnums.CHARCOAL_KILN_RECIPE, 1);
        put(CraftingRecipeEnums.MYSTIC_TREE_SEED_RECIPE, 4);
    }});

    private final HashMap<CraftingRecipeEnums, Integer> craftingRecipes;

    SkillType(HashMap<CraftingRecipeEnums, Integer> craftingRecipes)
    {
        this.craftingRecipes = craftingRecipes;
    }

    public HashMap<CraftingRecipeEnums, Integer> getCraftingRecipes()
    {
        return craftingRecipes;
    }
}
