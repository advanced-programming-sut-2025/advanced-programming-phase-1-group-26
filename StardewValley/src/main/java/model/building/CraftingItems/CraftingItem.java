package model.building.CraftingItems;


import model.GameObject;
import model.enums.GameObjectType;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.RecipeItem;

public abstract class CraftingItem extends GameObject
{
    protected final CraftingRecipeEnums craftType;

    public CraftingItem(CraftingRecipeEnums craftType)
    {
        super(craftType.getType(), 1);
        this.craftType = craftType;
    }

    public abstract void doItsThing();
}
