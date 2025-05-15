package model.shops;

import model.GameObject;
import model.Point;
import model.Time;
import model.enums.ShopType;

public abstract class Shop {
    //private Point location;
    private ShopType type;
    private String shopName;
    private String salesManName;
    private int startWork;
    private int endWork;
    public String showProducts(){return "products:\n";}
    public String showAvailableProducts(){return "available products:\n";}
    public void purchase(GameObject gameObject) {}
    public boolean isAffordable(GameObject gameObject) {
        return true;
    }
    public boolean isCorrectShop(GameObject gameObject) {
        return true;
    }


    public Shop(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        this.type = type;
        this.shopName = shopName;
        this.salesManName = salesManName;
        this.startWork = startWork;
        this.endWork = endWork;
    }

    public boolean isOpen(Time currentTime) {
        return currentTime.getHour() >= startWork && currentTime.getHour() <= endWork;
    }
}
