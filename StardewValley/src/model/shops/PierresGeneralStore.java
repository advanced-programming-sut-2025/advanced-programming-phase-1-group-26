package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.PierresGeneralStoreBackpacks;
import model.enums.shop_enums.PierresGeneralStoreSeasonalStock;
import model.enums.shop_enums.PierresGeneralStoreYearRoundStock;

public class PierresGeneralStore extends Shop{
    private PierresGeneralStoreBackpacks backpack;
    private PierresGeneralStoreSeasonalStock seasonalStock;
    private PierresGeneralStoreYearRoundStock yearRoundStock;

    public PierresGeneralStore(ShopType type, String shopName, String salesManName, int startWork, int endWork) {
        super(type, shopName, salesManName, startWork, endWork);
    }
}
