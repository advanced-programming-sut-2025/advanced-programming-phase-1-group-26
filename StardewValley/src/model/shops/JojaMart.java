package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.JojaMartPermanentStock;
import model.enums.shop_enums.JojaMartSeasonalStock;

import java.util.ArrayList;
import java.util.Arrays;

public class JojaMart extends Shop{
    private JojaMartSeasonalStock seasonalStock;
    private JojaMartPermanentStock permanentStock;
    private ArrayList<JojaMartPermanentStock> permanentStocks = new ArrayList<>();
    private ArrayList<JojaMartSeasonalStock> seasonalStocks = new ArrayList<>();

    public JojaMart() {
        super(ShopType.JOJA_MART, ShopType.JOJA_MART.name(), "Morris", 9, 23);
    }

    public void setPermanentStocks() {
        this.permanentStocks.addAll(Arrays.asList(JojaMartPermanentStock.values()));
    }
    public void setSeasonalStocks() {
        this.seasonalStocks.addAll(Arrays.asList(JojaMartSeasonalStock.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(JojaMartPermanentStock item : JojaMartPermanentStock.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(JojaMartSeasonalStock item : JojaMartSeasonalStock.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(JojaMartPermanentStock item : permanentStocks) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(JojaMartSeasonalStock item : seasonalStocks) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }
}
