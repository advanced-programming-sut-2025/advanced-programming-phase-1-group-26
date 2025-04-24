package model;

import model.enums.GameObjectType;
import model.enums.ShopType;

public abstract class Shop {
    private ShopType type;
    private String shopName;
    private String salesManName;
    private int startWork;
    private int endWork;
    public void workability(GameObjectType gameObjectType){}
}
