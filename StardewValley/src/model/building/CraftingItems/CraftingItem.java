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
        this.craftType = craftType;
        this.ObjectType = craftType.getProduct();
    }

    public abstract void doItsThing();
}
