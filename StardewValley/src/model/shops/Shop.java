package model.shops;

import model.enums.GameObjectType;
import model.enums.ShopType;

public abstract class Shop {
    private ShopType type;
    private String shopName;
    private String salesManName;
    private int startWork;
    private int endWork;
    public void workability(GameObjectType gameObjectType){}

    public Shop(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        this.type = type;
        this.shopName = shopName;
        this.salesManName = salesManName;
        this.startWork = startWork;
        this.endWork = endWork;
    }
}
