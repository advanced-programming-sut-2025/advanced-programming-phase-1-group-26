package model.shops;

import model.App;
import model.GameObject;
import model.enums.GameObjectType;
import model.enums.ShopType;
import model.enums.animal_enums.FarmBuilding;
import model.enums.shop_enums.CarpentersShopFarmBuilding;
import model.enums.shop_enums.CarpentersShopStock;

import java.util.ArrayList;
import java.util.Arrays;

public class CarpentersShop extends Shop {
    private ArrayList<CarpentersShopStock> stocks = new ArrayList<>();
    private ArrayList<FarmBuilding> farmBuildings = new ArrayList<>();

    public CarpentersShop() {
        super(ShopType.CARPENTER_SHOP, ShopType.CARPENTER_SHOP.name(), "Robin", 9, 20);
    }

    public void setStocks() {
        this.stocks.addAll(Arrays.asList(CarpentersShopStock.values()));
    }
    public void setFarmBuildings() {
        this.farmBuildings.addAll(Arrays.asList(FarmBuilding.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(FarmBuilding farmBuilding : FarmBuilding.values()) {
            products.append(farmBuilding.getName()).append(" ").append(farmBuilding.getPrice()).append("\n");
        }
        for(CarpentersShopStock stock : CarpentersShopStock.values()) {
            products.append(stock.getName()).append(" ").append(stock.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(FarmBuilding farmBuilding : farmBuildings) {
            products.append(farmBuilding.getName()).append(" ").append(farmBuilding.getPrice()).append("\n");
        }
        for(CarpentersShopStock stock : stocks) {
            products.append(stock.getName()).append(" ").append(stock.getPrice()).append("\n");
        }
        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject) {
        super.purchase(gameObject);
        GameObjectType targetType = gameObject.getObjectType();
        for(CarpentersShopStock stock : stocks) {
            if(stock.getGameObjectType() == targetType) {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(stock.getPrice());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
            }
        }
    }

    @Override
    public boolean isAffordable(GameObject gameObject) {
        super.isAffordable(gameObject);
        GameObjectType targetType = gameObject.getObjectType();
        for(CarpentersShopStock stock : CarpentersShopStock.values()) {
            if(stock.getGameObjectType() == targetType) {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= stock.getPrice();
            }
        }
        return false;
    }

    @Override
    public boolean isCorrectShop(GameObject gameObject) {
        super.isCorrectShop(gameObject);
        GameObjectType targetType = gameObject.getObjectType();
        for(CarpentersShopStock stock : CarpentersShopStock.values()) {
            if(stock.getGameObjectType() == targetType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dailyLimitCheck(GameObject gameObject) {
        return super.dailyLimitCheck(gameObject);
    }
}
