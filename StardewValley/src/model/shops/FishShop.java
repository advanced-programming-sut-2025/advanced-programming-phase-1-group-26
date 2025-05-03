package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.FishShopStock;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FishShop extends Shop{
    private FishShopStock stock;
    private ArrayList<FishShopStock> fishShopStocks = new ArrayList<>();

    public FishShop() {
        super(ShopType.FISH_SHOP, ShopType.FISH_SHOP.name(), "Willy", 9, 17);
    }

    public void setFishShopStocks() {
        this.fishShopStocks.addAll(Arrays.asList(FishShopStock.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(FishShopStock item : FishShopStock.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(FishShopStock item : fishShopStocks) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }
}
