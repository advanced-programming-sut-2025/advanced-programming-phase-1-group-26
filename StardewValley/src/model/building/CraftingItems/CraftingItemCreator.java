package model.building.CraftingItems;

import model.enums.building_enums.CraftingRecipeEnums;

public class CraftingItemCreator
{
    public static CraftingItem create(CraftingRecipeEnums type)
    {
        return switch (type)
        {
            case CraftingRecipeEnums.CHERRY_BOMB_RECIPE -> new CherryBomb();
            case CraftingRecipeEnums.BOMB_RECIPE -> new Bomb();
            case CraftingRecipeEnums.MEGA_BOMB_RECIPE -> new MegaBomb();
            case CraftingRecipeEnums.SPRINKLER_RECIPE -> new Sprinkler();
            case CraftingRecipeEnums.QUALITY_SPRINKLER_RECIPE -> new QualitySprinkler();
            case CraftingRecipeEnums.IRIDIUM_SPRINKLER_RECIPE -> new IridiumSprinkler();
            case CraftingRecipeEnums.CHARCOAL_KILN_RECIPE -> new CharcoalKlin();
            case CraftingRecipeEnums.FURNACE_RECIPE -> new Furnace();
            case CraftingRecipeEnums.SCARECROW_RECIPE -> new Scarecrow();
            case CraftingRecipeEnums.DELUXE_SCARECROW_RECIPE -> new DeluxeScarecrow();
            case CraftingRecipeEnums.BEE_HOUSE_RECIPE -> new BeeHouse();
            case CraftingRecipeEnums.CHEESE_PRESS_RECIPE -> new CheesePress();
            case CraftingRecipeEnums.KEG_RECIPE -> new Keg();
            case CraftingRecipeEnums.LOOM_RECIPE -> new Loom();
            case CraftingRecipeEnums.MAYONNAISE_MACHINE_RECIPE -> new MayonnaiseMachine();
            case CraftingRecipeEnums.OIL_MAKER_RECIPE -> new OilMaker();
            case CraftingRecipeEnums.PRESERVES_JAR_RECIPE -> new PreservesJar();
            case CraftingRecipeEnums.DEHYDRATOR_RECIPE -> new Dehydrator();
            case CraftingRecipeEnums.GRASS_STARTER_RECIPE -> new GrassStarter();
            case CraftingRecipeEnums.FISH_SMOKER_RECIPE -> new FishSmoker();
            case CraftingRecipeEnums.MYSTIC_TREE_SEED_RECIPE -> new MysticTreeSeed();
            default -> null;
        };
    }
}
