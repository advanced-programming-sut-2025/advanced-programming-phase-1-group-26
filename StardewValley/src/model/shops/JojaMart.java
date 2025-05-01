package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.JojaMartPermanentStock;
import model.enums.shop_enums.JojaMartSeasonalStock;

public class JojaMart extends Shop{
    private JojaMartSeasonalStock seasonalStock;
    private JojaMartPermanentStock permanentStock;

    public JojaMart(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        super(type, shopName, salesManName, startWork, endWork);
    }
}
