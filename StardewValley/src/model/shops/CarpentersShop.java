package model.shops;

import model.enums.ShopType;
import model.enums.shop_enums.CarpentersShopFarmBuilding;
import model.enums.shop_enums.CarpentersShopStock;

import java.util.ArrayList;
import java.util.Arrays;

public class CarpentersShop extends Shop {
    private CarpentersShopFarmBuilding farmBuilding;
    private CarpentersShopStock stock;
    private ArrayList<CarpentersShopStock> stocks = new ArrayList<>();
    private ArrayList<CarpentersShopFarmBuilding> farmBuildings = new ArrayList<>();

    public CarpentersShop() {
        super(ShopType.CARPENTER_SHOP, ShopType.CARPENTER_SHOP.name(), "Robin", 9, 20);
    }

    public void setStocks() {
        this.stocks.addAll(Arrays.asList(CarpentersShopStock.values()));
    }
    public void setFarmBuildings() {
        this.farmBuildings.addAll(Arrays.asList(CarpentersShopFarmBuilding.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(CarpentersShopFarmBuilding farmBuilding : CarpentersShopFarmBuilding.values()) {
            products.append(farmBuilding.getName()).append(" ").append(farmBuilding.getGoldCost()).append("\n");
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
        for(CarpentersShopFarmBuilding farmBuilding : farmBuildings) {
            products.append(farmBuilding.getName()).append(" ").append(farmBuilding.getGoldCost()).append("\n");
        }
        for(CarpentersShopStock stock : stocks) {
            products.append(stock.getName()).append(" ").append(stock.getPrice()).append("\n");
        }
        return products.toString();
    }
}
