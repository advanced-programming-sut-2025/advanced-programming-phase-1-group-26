package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.MarniesRanchLivestock;
import model.enums.shop_enums.MarniesRanchShopInventory;

import java.util.ArrayList;
import java.util.Arrays;

public class MarniesRanch extends Shop {
    private MarniesRanchLivestock livestock;
    private MarniesRanchShopInventory shopInventory;
    private ArrayList<MarniesRanchShopInventory> inventory = new ArrayList<>();
    private ArrayList<MarniesRanchLivestock> livestocks = new ArrayList<>();

    public MarniesRanch() {
        super(ShopType.MARINE_RANCH, ShopType.MARINE_RANCH.name(), "Marnie", 9, 16);
    }

    public void setInventory() {
        this.inventory.addAll(Arrays.asList(MarniesRanchShopInventory.values()));
    }
    public void setLivestocks() {
        this.livestocks.addAll(Arrays.asList(MarniesRanchLivestock.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(MarniesRanchShopInventory item : MarniesRanchShopInventory.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(MarniesRanchLivestock item : MarniesRanchLivestock.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(MarniesRanchShopInventory item : inventory) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(MarniesRanchLivestock item : livestocks) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }
}
