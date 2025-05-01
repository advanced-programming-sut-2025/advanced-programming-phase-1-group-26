package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.FishShopStock;

public class FishShop extends Shop{
    private FishShopStock stock;

    public FishShop(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        super(type, shopName, salesManName, startWork, endWork);
    }
}
