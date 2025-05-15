package model.shops;

import model.App;
import model.GameObject;
import model.enums.GameObjectType;
import model.enums.ShopType;
import model.enums.shop_enums.PierresGeneralStoreBackpacks;
import model.enums.shop_enums.PierresGeneralStoreSeasonalStock;
import model.enums.shop_enums.PierresGeneralStoreYearRoundStock;
import model.enums.tool_enums.BackPackLevel;
import model.tools.BackPack;

import java.util.ArrayList;
import java.util.Arrays;

public class PierresGeneralStore extends Shop{
    private ArrayList<PierresGeneralStoreSeasonalStock> seasonalStocks = new ArrayList<>();
    private ArrayList<PierresGeneralStoreYearRoundStock> yearRoundStocks = new ArrayList<>();
    private ArrayList<PierresGeneralStoreBackpacks> backpacks = new ArrayList<>();

    public PierresGeneralStore() {
        super(ShopType.PIERRE_GENERAL_STORE, ShopType.PIERRE_GENERAL_STORE.name(), "Pierre", 9, 17);
        setSeasonalStocks();
        setYearRoundStocks();
        setBackpacks();
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

    @Override
    public void purchase(GameObject gameObject) {
        super.purchase(gameObject);
        for(PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks) {
            if(gameObject.getObjectType().equals(yearRoundStock.getGameObjectType())) {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney
                        (yearRoundStock.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                yearRoundStock.decreaseLimit();
                yearRoundStocks.remove(yearRoundStock);
            }
        }
        for(PierresGeneralStoreSeasonalStock seasonalStock : seasonalStocks) {
            if(!seasonalStock.getSeasons().contains(App.getCurrentGame().getCurrentTime().getSeason())) {
                isInSeason = false;
                if(gameObject.getObjectType().equals(seasonalStock.getType())) {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (seasonalStock.getOutOfSeasonPrice() * gameObject.getNumber());
                    App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                    seasonalStock.decreaseDailyLimit();
                    seasonalStocks.remove(seasonalStock);
                }
            } else {
                if(gameObject.getObjectType().equals(seasonalStock.getType())) {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (seasonalStock.getBasePrice() * gameObject.getNumber());
                    App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                    seasonalStocks.remove(seasonalStock);
                }
            }
        }
        for(PierresGeneralStoreBackpacks backpack : PierresGeneralStoreBackpacks.values()) {
            if(gameObject instanceof BackPack) {

            }
        }
    }

    public boolean isInSeason = true;
}
