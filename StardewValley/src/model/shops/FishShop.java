package model.shops;

import model.App;
import model.Game;
import model.GameObject;
import model.building.CraftingItems.FishSmoker;
import model.enums.GameObjectType;
import model.enums.ShopType;
import model.enums.shop_enums.FishShopStock;
import model.enums.tool_enums.FishingPoleLevel;
import model.tools.FishingPole;

import java.util.ArrayList;
import java.util.Arrays;

public class FishShop extends Shop{
    private ArrayList<FishShopStock> fishShopStocks = new ArrayList<>();

    public FishShop() {
        super(ShopType.FISH_SHOP, ShopType.FISH_SHOP.name(), "Willy", 9, 17);
        setFishShopStocks();
    }

    public void setFishShopStocks() {
        this.fishShopStocks.addAll(Arrays.asList(FishShopStock.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showProducts());
        for(FishShopStock item : FishShopStock.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showAvailableProducts());
        for(FishShopStock item : fishShopStocks) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        if (gameObject.getObjectType().equals(GameObjectType.FISHING_POLE))
        {
            for (GameObject inventoryItem : App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().getInventory())
            {
                if (inventoryItem.getObjectType().equals(GameObjectType.FISHING_POLE))
                {
                    App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().getInventory().remove(inventoryItem);
                }
            }
        }
        switch (gameObject.getObjectType())
        {
            case FISH_SMOKER_RECIPE ->
            {
                FishSmoker fishSmoker = new FishSmoker();
                if (FishShopStock.FISH_SMOKER_RECIPE.getDailyLimit() != 0)
                {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (FishShopStock.FISH_SMOKER_RECIPE.getPrice() * gameObject.getNumber());
                    App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().getInventory().add(fishSmoker);
                    FishShopStock.FISH_SMOKER_RECIPE.decreaseDailyLimit();
                    fishShopStocks.remove(FishShopStock.FISH_SMOKER_RECIPE);
                }
            }
            case TROUT_SOUP ->
            {
                if (FishShopStock.TROUT_SOUP.getDailyLimit() != 0)
                {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (FishShopStock.TROUT_SOUP.getPrice() * gameObject.getNumber());
                    App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                    FishShopStock.TROUT_SOUP.decreaseDailyLimit();
                    fishShopStocks.remove(FishShopStock.TROUT_SOUP);
                }

            }
            case BAMBOO_POLE ->
            {
                if (App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.BAMBOO_POLE.getFishingSkillRequired())
                {
                    if (FishShopStock.BAMBOO_POLE.getDailyLimit() != 0)
                    {
                        App.getCurrentGame().getCurrentPlayer().decreaseMoney
                                (FishShopStock.BAMBOO_POLE.getPrice() * gameObject.getNumber());
                        FishingPole fishingPole = new FishingPole(FishingPoleLevel.Bamboo);
                        App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                        FishShopStock.BAMBOO_POLE.decreaseDailyLimit();
                        fishShopStocks.remove(FishShopStock.BAMBOO_POLE);
                    }
                }
            }
            case TRAINING_ROD ->
            {
                if (App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.TRAINING_ROD.getFishingSkillRequired())
                {
                    if (FishShopStock.TRAINING_ROD.getDailyLimit() != 0)
                    {
                        App.getCurrentGame().getCurrentPlayer().decreaseMoney
                                (FishShopStock.TRAINING_ROD.getPrice() * gameObject.getNumber());
                        FishingPole fishingPole = new FishingPole(FishingPoleLevel.Training);
                        App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                        FishShopStock.TRAINING_ROD.decreaseDailyLimit();
                        fishShopStocks.remove(FishShopStock.TRAINING_ROD);
                    }

                }
            }
            case FIBERGLASS_ROD ->
            {
                if (App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.FIBERGLASS_ROD.getFishingSkillRequired())
                {
                    if (FishShopStock.FIBERGLASS_ROD.getDailyLimit() != 0)
                    {
                        App.getCurrentGame().getCurrentPlayer().decreaseMoney
                                (FishShopStock.FIBERGLASS_ROD.getPrice() * gameObject.getNumber());
                        FishingPole fishingPole = new FishingPole(FishingPoleLevel.FiberGlass);
                        App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                        FishShopStock.FIBERGLASS_ROD.decreaseDailyLimit();
                        fishShopStocks.remove(FishShopStock.FIBERGLASS_ROD);
                    }
                }
            }
            case IRIDIUM_ROD ->
            {
                if (App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.IRIDIUM_ROD.getFishingSkillRequired())
                {
                    if (FishShopStock.IRIDIUM_ROD.getDailyLimit() != 0)
                    {
                        App.getCurrentGame().getCurrentPlayer().decreaseMoney
                                (FishShopStock.IRIDIUM_ROD.getPrice() * gameObject.getNumber());
                        FishingPole fishingPole = new FishingPole(FishingPoleLevel.Iridium);
                        App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                        FishShopStock.IRIDIUM_ROD.decreaseDailyLimit();
                        fishShopStocks.remove(FishShopStock.IRIDIUM_ROD);
                    }
                }
            }
        }
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject) {
        super.dailyLimitCheck(gameObject);
        for(FishShopStock stock : fishShopStocks) {
            if(stock.getGameObjectType() == gameObject.getObjectType()) {
                if(stock.getDailyLimit() >= 0 && stock.getDailyLimit() < gameObject.getNumber()) {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public boolean isCorrectShop(GameObject gameObject) {
        super.isCorrectShop(gameObject);
        for(FishShopStock stock : fishShopStocks) {
            if(stock.getGameObjectType() == gameObject.getObjectType()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAffordable(GameObject gameObject) {
        super.isAffordable(gameObject);
        for(FishShopStock stock : fishShopStocks) {
            if(stock.getGameObjectType() == gameObject.getObjectType()) {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= stock.getPrice() * gameObject.getNumber();
            }
        }
        return false;
    }
}
