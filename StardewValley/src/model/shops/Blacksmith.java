package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.BlacksmithStockItem;
import model.enums.shop_enums.BlacksmithUpgradeTools;

public class Blacksmith extends Shop {
    private BlacksmithStockItem stockItem;
    private BlacksmithUpgradeTools upgradeTools;

    public Blacksmith(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        super(type, shopName, salesManName, startWork, endWork);
    }
}
