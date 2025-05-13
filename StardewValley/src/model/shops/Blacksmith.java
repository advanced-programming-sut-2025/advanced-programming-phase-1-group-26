package model.shops;

import model.GameObject;
import model.enums.ShopType;
import model.enums.shop_enums.BlacksmithStockItem;
import model.enums.shop_enums.BlacksmithUpgradeTools;

import java.util.ArrayList;
import java.util.Arrays;

public class Blacksmith extends Shop {
    private ArrayList<BlacksmithUpgradeTools> upgradeToolsList = new ArrayList<>();
    private ArrayList<BlacksmithStockItem> stockItems = new ArrayList<>();

    public Blacksmith() {
        super(ShopType.BLACK_SMITH, ShopType.BLACK_SMITH.name(), "Clint", 9, 16);
    }

    public void setUpgradeTools() {
        this.upgradeToolsList.addAll(Arrays.asList(BlacksmithUpgradeTools.values()));
    }
    public void setStockItems() {
        this.stockItems.addAll(Arrays.asList(BlacksmithStockItem.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(BlacksmithStockItem item : BlacksmithStockItem.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(BlacksmithUpgradeTools tool : BlacksmithUpgradeTools.values()) {
            products.append(tool.getName()).append(" ").append(tool.getCost()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(BlacksmithStockItem item : stockItems) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(BlacksmithUpgradeTools tool : upgradeToolsList) {
            products.append(tool.getName()).append(" ").append(tool.getCost()).append("\n");
        }
        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject) {
        super.purchase(gameObject);
        for(BlacksmithUpgradeTools tool : upgradeToolsList) {
            if(tool.getGameObjectType().equals(gameObject.getToolType())) {

            }
        }
    }

}
