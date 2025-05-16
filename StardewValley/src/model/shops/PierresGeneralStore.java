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
            if(seasonalStock.getSeasons().contains(App.getCurrentGame().getCurrentTime().getSeason()))
            {
                products.append(seasonalStock.getName()).append(" this season ").append(seasonalStock.getBasePrice()).append("\n");
            } else {
                products.append(seasonalStock.getName()).append(" this season ").append(seasonalStock.getOutOfSeasonPrice()).append("\n");
            }
        }
        for(PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks) {
            products.append(yearRoundStock.getDisplayName()).append(" ").append(yearRoundStock.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        for (PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks)
        {
            if (gameObject.getObjectType().equals(yearRoundStock.getGameObjectType()))
            {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney
                        (yearRoundStock.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                yearRoundStock.decreaseLimit();
                yearRoundStocks.remove(yearRoundStock);
            }
        }
        for (PierresGeneralStoreSeasonalStock seasonalStock : seasonalStocks)
        {
            if (!seasonalStock.getSeasons().contains(App.getCurrentGame().getCurrentTime().getSeason()))
            {
                isInSeason = false;
                if (gameObject.getObjectType().equals(seasonalStock.getType()))
                {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (seasonalStock.getOutOfSeasonPrice() * gameObject.getNumber());
                    App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                    seasonalStock.decreaseDailyLimit();
                    seasonalStocks.remove(seasonalStock);
                }
            } else
            {
                if (gameObject.getObjectType().equals(seasonalStock.getType()))
                {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (seasonalStock.getBasePrice() * gameObject.getNumber());
                    App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                    seasonalStocks.remove(seasonalStock);
                }
            }
        }
        for (PierresGeneralStoreBackpacks backpack : PierresGeneralStoreBackpacks.values())
        {
            if (gameObject instanceof BackPack)
            {
                if (gameObject.toString().equals(backpack.getName()))
                {
                    App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().setLevel(backpack.getLevel());
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(backpack.getPrice());
                    backpack.decreaseDailyLimit();
                    backpacks.remove(backpack);
                }
            }
        }
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject) {
        super.dailyLimitCheck(gameObject);
        for(PierresGeneralStoreBackpacks backpack : backpacks) {
            if(gameObject instanceof BackPack) {
                if(gameObject.toString().equals(backpack.getName())) {
                    if(backpack.getDailyLimit() >= 0 && backpack.getDailyLimit() < gameObject.getNumber()) {
                        return false;
                    }
                }
            }
        }
        for(PierresGeneralStoreSeasonalStock seasonalStock : seasonalStocks) {
            if(gameObject.getObjectType().equals(seasonalStock.getType())) {
                if(seasonalStock.getDailyLimit() >= 0 && seasonalStock.getDailyLimit() < gameObject.getNumber()) {
                    return false;
                }
            }
        }
        for(PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks) {
            if(gameObject.getObjectType().equals(yearRoundStock.getGameObjectType())) {
                if(yearRoundStock.getDailyLimit() >= 0 && yearRoundStock.getDailyLimit() < gameObject.getNumber()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isCorrectShop(GameObject gameObject) {
        super.isCorrectShop(gameObject);
        for(PierresGeneralStoreBackpacks backpack : backpacks) {
            if(gameObject instanceof BackPack) {
                return true;
            }
        }
        for(PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks) {
            if(yearRoundStock.getGameObjectType().equals(gameObject.getObjectType())) {
                return true;
            }
        }
        for(PierresGeneralStoreSeasonalStock seasonalStock : seasonalStocks) {
            if(seasonalStock.getType().equals(gameObject.getObjectType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAffordable(GameObject gameObject) {
        super.isAffordable(gameObject);
        for(PierresGeneralStoreBackpacks backpack : backpacks) {
            if(gameObject instanceof BackPack) {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= backpack.getPrice();
            }
        }
        for(PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks) {
            if(yearRoundStock.getGameObjectType().equals(gameObject.getObjectType())) {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= yearRoundStock.getPrice();
            }
        }
        for(PierresGeneralStoreSeasonalStock seasonalStock : seasonalStocks) {
            if(seasonalStock.getType().equals(gameObject.getObjectType())) {
                if(isInSeason) {
                    return App.getCurrentGame().getCurrentPlayer().getMoney() >= seasonalStock.getBasePrice();
                } else {
                    return App.getCurrentGame().getCurrentPlayer().getMoney() >= seasonalStock.getOutOfSeasonPrice();
                }
            }
        }
        return false;
    }

    public boolean isInSeason = true;
}
