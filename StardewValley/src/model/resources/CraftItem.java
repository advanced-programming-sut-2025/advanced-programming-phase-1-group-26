package model.resources;

import model.enums.building_enums.CraftingRecipeEnums;

public class CraftItem
{
    private final CraftingRecipeEnums type;

    public CraftItem(CraftingRecipeEnums type)
    {
        this.type = type;
    }
}
