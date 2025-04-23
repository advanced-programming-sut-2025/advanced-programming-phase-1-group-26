package model.enums.animal_enums;

import model.enums.resources_enums.ResourceItem;

import java.util.Map;
import java.util.HashMap;

public enum FarmBuilding
{
    BARN("Barn", "Houses 4 barn-dwelling animals.", 6000, mapOf(
            ResourceItem.WOOD, 350,
            ResourceItem.STONE, 150), "7x4", 1),

    BIG_BARN("Big Barn", "Houses 8 barn-dwelling animals. Unlocks goats.", 12000, mapOf(
            ResourceItem.WOOD, 450,
            ResourceItem.STONE, 200
    ), "7x4", 1),

    DELUXE_BARN("Deluxe Barn", "Houses 12 barn-dwelling animals. Unlocks sheep and pigs.", 25000, mapOf(
            ResourceItem.WOOD, 550,
            ResourceItem.STONE, 300
    ), "7x4", 1),

    COOP("Coop", "Houses 4 coop-dwelling animals.", 4000, mapOf(
            ResourceItem.WOOD, 300,
            ResourceItem.STONE, 100
    ), "6x3", 1),

    BIG_COOP("Big Coop", "Houses 8 coop-dwelling animals. Unlocks ducks.", 10000, mapOf(
            ResourceItem.WOOD, 400,
            ResourceItem.STONE, 150
    ), "6x3", 1),

    DELUXE_COOP("Deluxe Coop", "Houses 12 coop-dwelling animals. Unlocks rabbits.", 20000, mapOf(
            ResourceItem.WOOD, 500,
            ResourceItem.STONE, 200
    ), "6x3", 1),

    WELL("Well", "Provides a place for you to refill your watering can.", 1000, mapOf(
            ResourceItem.STONE, 75
    ), "3x3", 1),

    SHIPPING_BIN("Shipping Bin", "Items placed in it will be included in the nightly shipment.", 250, mapOf(
            ResourceItem.WOOD, 150
    ), "1x1", -1);

    private final String name;
    private final String description;
    private final int price;
    private final Map<ResourceItem, Integer> materials;
    private final String size;
    private final int dailyLimit;

    FarmBuilding(String name, String description, int price, Map<ResourceItem, Integer> materials, String size, int dailyLimit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.materials = materials;
        this.size = size;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Map<ResourceItem, Integer> getMaterials() {
        return materials;
    }

    public String getSize() {
        return size;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    private static Map<ResourceItem, Integer> mapOf(Object... entries) {
        Map<ResourceItem, Integer> map = new HashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            map.put((ResourceItem) entries[i], (Integer) entries[i + 1]);
        }
        return map;
    }
}
