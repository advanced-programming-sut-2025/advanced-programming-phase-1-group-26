package model.building.Cooking;

import model.GameObject;
import model.enums.GameObjectType;
import model.enums.building_enums.KitchenItems;

public class EdibleThing extends GameObject
{
    private final KitchenItems itemType;

    public EdibleThing(KitchenItems itemType, int number)
    {
        super(itemType.getType(), number);
        this.itemType = itemType;
    }

    public KitchenItems getItemType()
    {
        return itemType;
    }
}
