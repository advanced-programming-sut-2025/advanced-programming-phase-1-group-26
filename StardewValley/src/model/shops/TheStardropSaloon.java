package model.shops;

import model.App;
import model.GameObject;
import model.enums.ShopType;
import model.enums.shop_enums.TheStardropSaloonStock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TheStardropSaloon extends Shop {
    private ArrayList<TheStardropSaloonStock> stocks = new ArrayList<>();

    public TheStardropSaloon() {
        super(ShopType.STARDROP_SALOON, "Gus", 12, 24);
        setStocks();
    }

    public void setStocks() {
        this.stocks.addAll(Arrays.asList(TheStardropSaloonStock.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showProducts());

        for (TheStardropSaloonStock stock : TheStardropSaloonStock.values())
        {
            products.append("\tstock: ").append(stock.getName()).append("\n\tprice: ").append(stock.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showAvailableProducts());

        for(TheStardropSaloonStock stock : stocks)
        {
            products.append("\tstock: ").append(stock.getName()).append("\n\tprice: ").append(stock.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        Iterator<TheStardropSaloonStock> iterator = stocks.iterator();
        while (iterator.hasNext())
        {
            TheStardropSaloonStock stock = iterator.next();
            if (gameObject.getObjectType().equals(stock.getGameObjectType()))
            {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(stock.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                stock.decreaseLimit();
                if (stock.getLimit() == 0)
                {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public boolean isCorrectShop(GameObject gameObject)
    {
        for (TheStardropSaloonStock stock : stocks) {
            if(gameObject.getObjectType().equals(stock.getGameObjectType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAffordable(GameObject gameObject) {
        for(TheStardropSaloonStock stock : stocks) {
            if(gameObject.getObjectType().equals(stock.getGameObjectType())) {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= stock.getPrice() * gameObject.getNumber();
            }
        }
        return false;
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject) {
        for(TheStardropSaloonStock stock : stocks) {
            if(gameObject.getObjectType().equals(stock.getGameObjectType())) {
                if(stock.getDailyLimit() > 0 && stock.getLimit() < gameObject.getNumber()) {
                    return false;
                }
            }
        }
        return true;
    }
}
