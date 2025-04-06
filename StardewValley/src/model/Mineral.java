package model;

import model.enums.MineralType;

public class Mineral extends GameObject
{
    private final MineralType type;
    private String description;
    private int sellPrice;

    public Mineral(MineralType type)
    {
        this.type = type;
        this.description = type.getDescription();
        this.sellPrice = type.getSellPrice();
    }
}
