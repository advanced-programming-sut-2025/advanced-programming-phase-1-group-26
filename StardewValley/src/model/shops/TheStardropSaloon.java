package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.TheStardropSaloonStock;

public class TheStardropSaloon extends Shop {
    private TheStardropSaloonStock stock;

    public TheStardropSaloon(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        super(type, shopName, salesManName, startWork, endWork);
    }
}
