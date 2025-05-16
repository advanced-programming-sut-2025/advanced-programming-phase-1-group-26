package model.shops;

import model.*;
import model.enums.ShopType;

import java.util.ArrayList;

public abstract class Shop
{
//    private final ArrayList<Tile> shopTiles;
    private final ShopType type;
    private final String shopName;
    private final String salesManName;
    private final int startWork;
    private final int endWork;

    public String showProducts()
    {
        return shopName + "'s Products:\n--------------------------------\n";
    }

    public String showAvailableProducts()
    {
        return shopName + "'s Available Products:\n--------------------------------\n";
    }

    public abstract void purchase(GameObject gameObject);

    public boolean isAffordable(GameObject gameObject)
    {
        return true;
    }

    public boolean isCorrectShop(GameObject gameObject)
    {
        return true;
    }

    public boolean dailyLimitCheck(GameObject gameObject)
    {
        return true;
    }


    public Shop(ShopType type, String shopName, String salesManName, int startWork, int endWork)
    {
        this.type = type;
        this.shopName = shopName;
        this.salesManName = salesManName;
        this.startWork = startWork;
        this.endWork = endWork;
    }

    public boolean isOpen(Time currentTime) {
        return currentTime.getHour() >= startWork && currentTime.getHour() <= endWork;
    }

    public ShopType getType() {
        return type;
    }
}
