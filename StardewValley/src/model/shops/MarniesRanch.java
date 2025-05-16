package model.shops;

import model.App;
import model.GameObject;
import model.enums.ShopType;
import model.enums.animal_enums.FarmAnimalsType;
import model.enums.shop_enums.MarniesRanchLivestock;
import model.enums.shop_enums.MarniesRanchShopInventory;

import java.util.ArrayList;
import java.util.Arrays;

public class MarniesRanch extends Shop
{
    private ArrayList<MarniesRanchShopInventory> inventory = new ArrayList<>();
    private ArrayList<FarmAnimalsType> livestocks = new ArrayList<>();

    public MarniesRanch()
    {
        super(ShopType.MARINE_RANCH, ShopType.MARINE_RANCH.name(), "Marnie", 9, 16);
        setInventory();
        setLivestocks();
    }

    public void setInventory() {
        this.inventory.addAll(Arrays.asList(MarniesRanchShopInventory.values()));
    }
    public void setLivestocks() {
        this.livestocks.addAll(Arrays.asList(FarmAnimalsType.values()));
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
        for(FarmAnimalsType item : livestocks) {
            products.append(item.getName()).append(" ").append(item.getPurchaseCost()).append("\n");
        }
        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        for (MarniesRanchShopInventory item : inventory)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(item.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                item.decreaseLimit();
                if (item.getLimit() == 0) inventory.remove(item);
            }
        }
    }

    @Override
    public boolean isCorrectShop(GameObject gameObject) {
        super.isCorrectShop(gameObject);
        for(MarniesRanchShopInventory item : inventory) {
            if(item.getGameObjectType().equals(gameObject.getObjectType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAffordable(GameObject gameObject) {
        super.isAffordable(gameObject);
        for(MarniesRanchShopInventory item : inventory) {
            if(item.getGameObjectType().equals(gameObject.getObjectType())) {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= item.getPrice() * gameObject.getNumber();
            }
        }
        return false;
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject) {
        super.dailyLimitCheck(gameObject);
        for(MarniesRanchShopInventory item : inventory) {
            if(item.getGameObjectType().equals(gameObject.getObjectType())) {
                if(item.getDailyLimit() > 0 && item.getLimit() < gameObject.getNumber()) {
                    return false;
                }
            }
        }
        return true;
    }
}
