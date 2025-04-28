package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.MarniesRanchLivestock;
import model.enums.shop_enums.MarniesRanchShopInventory;

public class MarniesRanch extends Shop {
    private MarniesRanchLivestock livestock;
    private MarniesRanchShopInventory shopInventory;

    public MarniesRanch(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        super(type, shopName, salesManName, startWork, endWork);
    }
}
