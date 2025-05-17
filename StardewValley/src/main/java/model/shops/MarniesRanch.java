package model.shops;

import model.App;
import model.GameObject;
import model.enums.ShopType;
import model.enums.animal_enums.FarmAnimalsType;
import model.enums.shop_enums.MarniesRanchLivestock;
import model.enums.shop_enums.MarniesRanchShopInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MarniesRanch extends Shop
{
    private ArrayList<MarniesRanchShopInventory> inventory = new ArrayList<>();
    private ArrayList<FarmAnimalsType> liveStocks = new ArrayList<>();

    public MarniesRanch()
    {
        super(ShopType.MARINE_RANCH, "Marnie", 9, 16);
        setInventory();
    }

    public void setInventory() {
        this.inventory.addAll(Arrays.asList(MarniesRanchShopInventory.values()));
    }

    public void setLivestocks() {
        this.liveStocks.addAll(Arrays.asList(FarmAnimalsType.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showProducts());

        for (MarniesRanchShopInventory item : MarniesRanchShopInventory.values())
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (MarniesRanchLivestock item : MarniesRanchLivestock.values())
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showAvailableProducts());

        for(MarniesRanchShopInventory item : inventory)
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (FarmAnimalsType item : FarmAnimalsType.values())
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPurchaseCost()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        Iterator<MarniesRanchShopInventory> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            MarniesRanchShopInventory item = iterator.next();
            if (item.getGameObjectType().equals(gameObject.getObjectType())) {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(item.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                item.decreaseLimit();
                if (item.getLimit() == 0) {
                    iterator.remove(); // safe removal
                }
            }
        }

    }

    @Override
    public boolean isCorrectShop(GameObject gameObject)
    {
        for (MarniesRanchShopInventory item : inventory)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAffordable(GameObject gameObject)
    {
        for (MarniesRanchShopInventory item : inventory)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= item.getPrice() * gameObject.getNumber();
            }
        }
        return false;
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject)
    {
        for (MarniesRanchShopInventory item : inventory)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                if (item.getDailyLimit() > 0 && item.getLimit() < gameObject.getNumber())
                {
                    return false;
                }
            }
        }
        return true;
    }
}
