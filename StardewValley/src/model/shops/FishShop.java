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

    @Override
    public void purchase(GameObject gameObject) {
        super.purchase(gameObject);
        if(gameObject.getObjectType().equals(GameObjectType.FISHING_POLE)) {
            for(GameObject inventoryItem : App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().getInventory()) {
                if(inventoryItem.getObjectType().equals(GameObjectType.FISHING_POLE)) {
                    App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().getInventory().remove(inventoryItem);
                }
            }
        }
        switch (gameObject.getObjectType()) {
            case FISH_SMOKER_RECIPE -> {
                FishSmoker fishSmoker = new FishSmoker();
                App.getCurrentGame().getCurrentPlayer().decreaseMoney
                        (FishShopStock.FISH_SMOKER_RECIPE.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().getInventory().add(fishSmoker);
            }
            case TROUT_SOUP -> {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney
                        (FishShopStock.TROUT_SOUP.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
            }
            case BAMBOO_POLE -> {
                if(App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.BAMBOO_POLE.getFishingSkillRequired()) {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (FishShopStock.BAMBOO_POLE.getPrice() * gameObject.getNumber());
                    FishingPole fishingPole = new FishingPole(FishingPoleLevel.Bamboo);
                    App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                }
            }
            case TRAINING_ROD -> {
                if(App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.TRAINING_ROD.getFishingSkillRequired()) {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (FishShopStock.TRAINING_ROD.getPrice() * gameObject.getNumber());
                    FishingPole fishingPole = new FishingPole(FishingPoleLevel.Training);
                    App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                }
            }
            case FIBERGLASS_ROD -> {
                if(App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.FIBERGLASS_ROD.getFishingSkillRequired()) {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (FishShopStock.FIBERGLASS_ROD.getPrice() * gameObject.getNumber());
                    FishingPole fishingPole = new FishingPole(FishingPoleLevel.FiberGlass);
                    App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                }
            }
            case IRIDIUM_ROD -> {
                if(App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel() >=
                        FishShopStock.IRIDIUM_ROD.getFishingSkillRequired()) {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney
                            (FishShopStock.IRIDIUM_ROD.getPrice() * gameObject.getNumber());
                    FishingPole fishingPole = new FishingPole(FishingPoleLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().addToInventory(fishingPole);
                }
            }
        }
    }
}
