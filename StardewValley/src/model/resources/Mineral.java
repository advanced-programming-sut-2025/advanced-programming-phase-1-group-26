package model.resources;

import model.GameObject;
import model.enums.resources_enums.ForagingMineralType;

public class Mineral extends GameObject
{
    private final ForagingMineralType type;
    private String description;
    private int sellPrice;

    public Mineral(ForagingMineralType type)
    {
        this.type = type;
        this.description = type.getDescription();
        this.sellPrice = type.getSellPrice();
    }
}
