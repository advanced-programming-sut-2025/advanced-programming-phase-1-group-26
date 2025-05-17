package model.shops;

import model.App;
import model.GameObject;
import model.enums.ShopType;
import model.enums.shop_enums.PierresGeneralStoreBackpacks;
import model.enums.shop_enums.PierresGeneralStoreSeasonalStock;
import model.enums.shop_enums.PierresGeneralStoreYearRoundStock;
import model.tools.BackPack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PierresGeneralStore extends Shop{
    private ArrayList<PierresGeneralStoreSeasonalStock> seasonalStocks = new ArrayList<>();
    private ArrayList<PierresGeneralStoreYearRoundStock> yearRoundStocks = new ArrayList<>();
    private ArrayList<PierresGeneralStoreBackpacks> backpacks = new ArrayList<>();

    public PierresGeneralStore() {
        super(ShopType.PIERRE_GENERAL_STORE, "Pierre", 9, 17);
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

        for (PierresGeneralStoreBackpacks backpack : PierresGeneralStoreBackpacks.values())
        {
            products.append("\tbackpack: ").append(backpack.getName()).append("\n\tprice: ").append(backpack.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (PierresGeneralStoreSeasonalStock seasonalStock : PierresGeneralStoreSeasonalStock.values())
        {
            products.append("\tseasonal stock: ").append(seasonalStock.getName()).append("\n\tprice: ").append(seasonalStock.getBasePrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (PierresGeneralStoreYearRoundStock yearRoundStock : PierresGeneralStoreYearRoundStock.values())
        {
            products.append(yearRoundStock.getDisplayName()).append(" ").append(yearRoundStock.getPrice()).append("\n");
        }

        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showAvailableProducts());

        for(PierresGeneralStoreBackpacks backpack : backpacks)
        {
            products.append("\tbackpack: ").append(backpack.getName()).append("\n\tprice: ").append(backpack.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (PierresGeneralStoreSeasonalStock seasonalStock : seasonalStocks)
        {
            if (seasonalStock.getSeasons().contains(App.getCurrentGame().getCurrentTime().getSeason()))
            {
                products.append("\tthis season: ").append(seasonalStock.getName()).append("\n\tprice: ").append(seasonalStock.getBasePrice()).append("\n");
                products.append("--------------------------------\n");
            } else
            { // TODO: both are this season ?
                products.append("\tthis season: ").append(seasonalStock.getName()).append("\n\tprice: ").append(seasonalStock.getBasePrice()).append("\n");
                products.append("--------------------------------\n");
            }
        }

        for (PierresGeneralStoreYearRoundStock yearRoundStock : yearRoundStocks)
        {
            products.append(yearRoundStock.getDisplayName()).append(" ").append(yearRoundStock.getPrice()).append("\n");
        }

        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        Iterator<PierresGeneralStoreYearRoundStock> iterator = yearRoundStocks.iterator();
        while (iterator.hasNext()) {
            PierresGeneralStoreYearRoundStock yearRoundStock = iterator.next();
            if (gameObject.getObjectType().equals(yearRoundStock.getGameObjectType())) {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(
                        yearRoundStock.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                yearRoundStock.decreaseLimit();
                iterator.remove();  // Safe removal
                break; // If you only expect one match, consider breaking here
            }
        }

        Iterator<PierresGeneralStoreSeasonalStock> seasonalIterator = seasonalStocks.iterator();
        while (seasonalIterator.hasNext()) {
            PierresGeneralStoreSeasonalStock seasonalStock = seasonalIterator.next();
            boolean isInSeason = seasonalStock.getSeasons().contains(App.getCurrentGame().getCurrentTime().getSeason());

            if (gameObject.getObjectType().equals(seasonalStock.getType())) {
                if (!isInSeason) {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(
                            seasonalStock.getOutOfSeasonPrice() * gameObject.getNumber());
                    seasonalStock.decreaseDailyLimit();
                } else {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(
                            seasonalStock.getBasePrice() * gameObject.getNumber());
                }

                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                seasonalIterator.remove(); // Safe removal
                break; // Assuming only one matching item per purchase
            }
        }


        Iterator<PierresGeneralStoreBackpacks> backpackIterator = backpacks.iterator();
        while (backpackIterator.hasNext()) {
            PierresGeneralStoreBackpacks backpack = backpackIterator.next();
            if (gameObject.getObjectType().toString().equalsIgnoreCase(backpack.getName())) {
                App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().setLevel(backpack.getLevel());
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(backpack.getPrice());
                backpack.decreaseDailyLimit();
                backpackIterator.remove(); // Safe removal
                break; // If only one match is possible
            }
        }
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject) {
        for(PierresGeneralStoreBackpacks backpack : backpacks) {
                if(gameObject.getObjectType().toString().equalsIgnoreCase(backpack.getName())) {
                    if(backpack.getDailyLimit() >= 0 && backpack.getDailyLimit() < gameObject.getNumber()) {
                        return false;
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
        for(PierresGeneralStoreBackpacks backpack : backpacks) {
            if(gameObject.getObjectType().toString().equalsIgnoreCase(backpack.getName())) {
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
        for(PierresGeneralStoreBackpacks backpack : backpacks) {
            return App.getCurrentGame().getCurrentPlayer().getMoney() >= backpack.getPrice();
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
