package model.shops;

import model.App;
import model.GameObject;
import model.enums.ShopType;
import model.enums.shop_enums.JojaMartPermanentStock;
import model.enums.shop_enums.JojaMartSeasonalStock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class JojaMart extends Shop{
    private ArrayList<JojaMartPermanentStock> permanentStocks = new ArrayList<>();
    private ArrayList<JojaMartSeasonalStock> seasonalStocks = new ArrayList<>();

    public JojaMart() {
        super(ShopType.JOJA_MART, "Morris", 9, 23);
        setPermanentStocks();
        setSeasonalStocks();
    }

    public void setPermanentStocks() {
        this.permanentStocks.addAll(Arrays.asList(JojaMartPermanentStock.values()));
    }
    public void setSeasonalStocks() {
        this.seasonalStocks.addAll(Arrays.asList(JojaMartSeasonalStock.values()));
    }

    @Override
    public String showProducts()
    {
        StringBuilder products = new StringBuilder();
        products.append(super.showProducts());

        for (JojaMartPermanentStock item : JojaMartPermanentStock.values())
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (JojaMartSeasonalStock item : JojaMartSeasonalStock.values())
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public String showAvailableProducts()
    {
        StringBuilder products = new StringBuilder();
        products.append(super.showAvailableProducts());

        for (JojaMartPermanentStock item : permanentStocks)
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (JojaMartSeasonalStock item : seasonalStocks)
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        Iterator<JojaMartPermanentStock> permanentIterator = permanentStocks.iterator();
        while (permanentIterator.hasNext()) {
            JojaMartPermanentStock item = permanentIterator.next();
            if (item.getGameObjectType().equals(gameObject.getObjectType())) {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(item.getPrice() * gameObject.getNumber());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                item.decreaseLimit();
                if (item.getLimit() == 0) {
                    permanentIterator.remove(); // Safe removal
                }
                break; // If only one match is allowed
            }
        }

        Iterator<JojaMartSeasonalStock> seasonalIterator = seasonalStocks.iterator();
        while (seasonalIterator.hasNext()) {
            JojaMartSeasonalStock item = seasonalIterator.next();
            if (item.getGameObjectType().equals(gameObject.getObjectType())) {
                if (!item.getSeason().equals(App.getCurrentGame().getCurrentTime().getSeason())) {
                    isInSeason = false;
                } else {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(item.getPrice() * gameObject.getNumber());
                    App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
                    item.decreaseLimit();
                    seasonalIterator.remove(); // Safe removal
                }
                break; // If only one match is expected
            }
        }

    }

    @Override
    public boolean isAffordable(GameObject gameObject)
    {
        for (JojaMartPermanentStock item : permanentStocks)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= item.getPrice() * gameObject.getNumber();
            }
        }
        for (JojaMartSeasonalStock item : seasonalStocks)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= item.getPrice() * gameObject.getNumber();
            }
        }
        return false;
    }

    @Override
    public boolean isCorrectShop(GameObject gameObject)
    {
        for (JojaMartPermanentStock item : permanentStocks)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return true;
            }
        }
        for (JojaMartSeasonalStock item : seasonalStocks)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject)
    {
        for (JojaMartPermanentStock item : permanentStocks)
        {
            if (item.getGameObjectType().equals(gameObject.getObjectType()))
            {
                if (item.getDailyLimit() > 0 && item.getLimit() < gameObject.getNumber())
                {
                    return false;
                }
            }
        }
        for (JojaMartSeasonalStock item : seasonalStocks)
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

    public boolean isInSeason = true;
}
