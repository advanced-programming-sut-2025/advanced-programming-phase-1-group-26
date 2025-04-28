package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.CarpentersShopFarmBuilding;
import model.enums.shop_enums.CarpentersShopStock;

public class CarpentersShop extends Shop {
    private CarpentersShopFarmBuilding farmBuilding;
    private CarpentersShopStock stock;

    public CarpentersShop(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        super(type, shopName, salesManName, startWork, endWork);
    }
}
