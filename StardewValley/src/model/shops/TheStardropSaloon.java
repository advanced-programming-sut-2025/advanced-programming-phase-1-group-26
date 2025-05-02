package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.TheStardropSaloonStock;

import java.util.ArrayList;
import java.util.Arrays;

public class TheStardropSaloon extends Shop {
    private TheStardropSaloonStock stock;
    private ArrayList<TheStardropSaloonStock> stocks;

    public TheStardropSaloon() {
        super(ShopType.STARDROP_SALOON, ShopType.STARDROP_SALOON.name(), "Gus", 12, 24);
    }

    public void setStocks() {
        this.stocks.addAll(Arrays.asList(TheStardropSaloonStock.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(TheStardropSaloonStock stock : TheStardropSaloonStock.values()) {
            products.append(stock.getName()).append(" ").append(stock.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(TheStardropSaloonStock stock : stocks) {
            products.append(stock.getName()).append(" ").append(stock.getPrice()).append("\n");
        }
        return products.toString();
    }
}
