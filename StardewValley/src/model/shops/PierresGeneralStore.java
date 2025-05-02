package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.PierresGeneralStoreBackpacks;
import model.enums.shop_enums.PierresGeneralStoreSeasonalStock;
import model.enums.shop_enums.PierresGeneralStoreYearRoundStock;

import java.util.ArrayList;
import java.util.Arrays;

public class PierresGeneralStore extends Shop{
    private PierresGeneralStoreBackpacks backpack;
    private PierresGeneralStoreSeasonalStock seasonalStock;
    private PierresGeneralStoreYearRoundStock yearRoundStock;
    private ArrayList<PierresGeneralStoreSeasonalStock> seasonalStocks = new ArrayList<>();
    private ArrayList<PierresGeneralStoreYearRoundStock> yearRoundStocks = new ArrayList<>();
    private ArrayList<PierresGeneralStoreBackpacks> backpacks = new ArrayList<>();

    public PierresGeneralStore() {
        super(ShopType.PIERRE_GENERAL_STORE, ShopType.PIERRE_GENERAL_STORE.name(), "Pierre", 9, 17);
    }

    public void setSeasonalStocks() {
        this.seasonalStocks.addAll(Arrays.asList(PierresGeneralStoreSeasonalStock.values()));
    }
    public void setYearRoundStocks() {
        this.yearRoundStocks.addAll(Arrays.asList(PierresGeneralStoreYearRoundStock.values()));
    }
    public void setBackpacks() {
        this.backpacks.addAll(Arrays.asList(PierresGeneralStoreBackpacks.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(PierresGeneralStoreBackpacks backpack : PierresGeneralStoreBackpacks.values()) {
            products.append(backpack.getName()).append(" ").append(backpack.getPrice()).append("\n");
        }
        for(PierresGeneralStoreSeasonalStock seasonalStock : PierresGeneralStoreSeasonalStock.values()) {
            products.append(seasonalStock.getName()).append(" ").append(seasonalStock.getBasePrice()).append("\n");
        }
        for(PierresGeneralStoreYearRoundStock yearRoundStock : PierresGeneralStoreYearRoundStock.values()) {
            products.append(yearRoundStock.getDisplayName()).append(" ").append(yearRoundStock.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(PierresGeneralStoreBackpacks backpack : backpacks) {
            products.append(backpack.getName()).append(" ").append(backpack.getPrice()).append("\n");
        }
        for(PierresGeneralStoreSeasonalStock seasonalStock : seasonalStocks) {
            products.append(seasonalStock.getName()).append(" ").append(seasonalStock.getBasePrice()).append("\n");
        }
        for(PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks) {
            products.append(yearRoundStock.getDisplayName()).append(" ").append(yearRoundStock.getPrice()).append("\n");
        }
        return products.toString();
    }
}
