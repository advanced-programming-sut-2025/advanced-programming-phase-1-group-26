package model.shops;

import model.App;
import model.GameObject;
import model.enums.GameObjectType;
import model.enums.ShopType;
import model.enums.shop_enums.BlacksmithStockItem;
import model.enums.shop_enums.BlacksmithUpgradeTools;
import model.enums.tool_enums.*;
import model.tools.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Blacksmith extends Shop {
    private ArrayList<BlacksmithUpgradeTools> upgradeToolsList = new ArrayList<>();
    private ArrayList<BlacksmithStockItem> stockItems = new ArrayList<>();

    public Blacksmith() {
        super(ShopType.BLACK_SMITH, "Clint", 9, 16);
        setStockItems();
        setUpgradeTools();
    }

    public void setUpgradeTools() {
        this.upgradeToolsList.addAll(Arrays.asList(BlacksmithUpgradeTools.values()));
    }
    public void setStockItems() {
        this.stockItems.addAll(Arrays.asList(BlacksmithStockItem.values()));
    }

    @Override
    public String showProducts()
    {
        StringBuilder products = new StringBuilder();
        products.append(super.showProducts());

        for (BlacksmithStockItem item : BlacksmithStockItem.values())
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (BlacksmithUpgradeTools tool : BlacksmithUpgradeTools.values())
        {
            products.append("\titem: ").append(tool.getName()).append("\n\tprice: ").append(tool.getCost()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        products.append(super.showAvailableProducts());

        for (BlacksmithStockItem item : stockItems)
        {
            products.append("\titem: ").append(item.getName()).append("\n\tprice: ").append(item.getPrice()).append("\n");
            products.append("--------------------------------\n");
        }

        for (BlacksmithUpgradeTools tool : upgradeToolsList)
        {
            products.append("\titem: ").append(tool.getName()).append("\n\tprice: ").append(tool.getCost()).append("\n");
            products.append("--------------------------------\n");
        }

        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject)
    {
        for (BlacksmithStockItem stockItem : stockItems)
        {
            if (stockItem.getGameObjectType().equals(gameObject.getObjectType()))
            {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(stockItem.getPrice());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
            }
        }
    }

    @Override
    public boolean isAffordable(GameObject gameObject)
    {
        for (BlacksmithStockItem stockItem : stockItems)
        {
            if (stockItem.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return App.getCurrentGame().getCurrentPlayer().getMoney() >= stockItem.getPrice();
            }
        }
        return false;
    }

    @Override
    public boolean isCorrectShop(GameObject gameObject)
    {
        for (BlacksmithStockItem stockItem : stockItems)
        {
            if (stockItem.getGameObjectType().equals(gameObject.getObjectType()))
            {
                return true;
            }
        }
        return false;
    }

    public void upgrade(Tool tool) {
        switch (tool.getToolType()) {
            case ToolType.FishingPole -> upgradeFishingPole((FishingPole) tool);
            case ToolType.Axe -> upgradeAxe((Axe) tool);
            case ToolType.WateringCan -> upgradeWateringCan((WateringCan) tool);
            case ToolType.TrashCan -> upgradeTrashCan((TrashCan) tool);
            case ToolType.Hoe -> upgradeHoe((Hoe) tool);
            case ToolType.Pickaxe -> upgradePickaxe((Pickaxe) tool);
        }
    }

    public void upgradeFishingPole(FishingPole fishingPole) {
        switch (fishingPole.getLevel()) {
            case FishingPoleLevel.Training -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= FishingPoleLevel.Bamboo.getPrice()) {
                    canWeUpgrade = true;
                    fishingPole.setLevel(FishingPoleLevel.Bamboo);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(FishingPoleLevel.Bamboo.getPrice());
                }
            }
            case FishingPoleLevel.Bamboo -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= FishingPoleLevel.FiberGlass.getPrice()) {
                    canWeUpgrade = true;
                    fishingPole.setLevel(FishingPoleLevel.FiberGlass);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(FishingPoleLevel.FiberGlass.getPrice());
                }
            }
            case FishingPoleLevel.FiberGlass -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= FishingPoleLevel.Iridium.getPrice()) {
                    canWeUpgrade = true;
                    fishingPole.setLevel(FishingPoleLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(FishingPoleLevel.Iridium.getPrice());
                }
            }
        }
    }

    public void upgradeAxe(Axe axe) {
        switch (axe.getLevel()) {
            case AxeLevel.base -> {
                if((App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TOOL.getCost()) &&
                BlacksmithUpgradeTools.COPPER_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    axe.setLevel(AxeLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TOOL.getCost());
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.COPPER_BAR, 5);
                    BlacksmithUpgradeTools.COPPER_TOOL.decreaseDailyLimit();
                }
            }
            case AxeLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TOOL.getCost() &&
                BlacksmithUpgradeTools.STEEL_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    axe.setLevel(AxeLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TOOL.getCost());
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRON_BAR, 5);
                    BlacksmithUpgradeTools.STEEL_TOOL.decreaseDailyLimit();
                }
            }
            case AxeLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TOOL.getCost() &&
                BlacksmithUpgradeTools.GOLD_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    axe.setLevel(AxeLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TOOL.getCost());
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.GOLD_BAR, 5);
                    BlacksmithUpgradeTools.GOLD_TOOL.decreaseDailyLimit();
                }
            }
            case AxeLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost() &&
                BlacksmithUpgradeTools.IRIDIUM_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(axe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    axe.setLevel(AxeLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost());
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRIDIUM_BAR, 5);
                    BlacksmithUpgradeTools.IRIDIUM_TOOL.decreaseDailyLimit();
                }
            }
        }
    }

    public void upgradePickaxe(Pickaxe pickaxe) {
        switch (pickaxe.getLevel()) {
            case PickaxeLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TOOL.getCost() &&
                BlacksmithUpgradeTools.COPPER_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    pickaxe.setLevel(PickaxeLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.COPPER_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TOOL.getCost());
                    BlacksmithUpgradeTools.COPPER_TOOL.decreaseDailyLimit();
                }
            }
            case PickaxeLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TOOL.getCost() &&
                BlacksmithUpgradeTools.STEEL_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    pickaxe.setLevel(PickaxeLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRON_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TOOL.getCost());
                    BlacksmithUpgradeTools.STEEL_TOOL.decreaseDailyLimit();
                }
            }
            case PickaxeLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TOOL.getCost() &&
                BlacksmithUpgradeTools.GOLD_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    pickaxe.setLevel(PickaxeLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.GOLD_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TOOL.getCost());
                    BlacksmithUpgradeTools.GOLD_TOOL.decreaseDailyLimit();
                }
            }
            case PickaxeLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost() &&
                BlacksmithUpgradeTools.IRIDIUM_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(pickaxe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    pickaxe.setLevel(PickaxeLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRIDIUM_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost());
                    BlacksmithUpgradeTools.IRIDIUM_TOOL.decreaseDailyLimit();
                }
            }
        }
    }

    public void upgradeHoe(Hoe hoe) {
        switch (hoe.getLevel()) {
            case HoeLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TOOL.getCost() &&
                BlacksmithUpgradeTools.COPPER_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    hoe.setLevel(HoeLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.COPPER_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TOOL.getCost());
                    BlacksmithUpgradeTools.COPPER_TOOL.decreaseDailyLimit();
                }
            }
            case HoeLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TOOL.getCost() &&
                BlacksmithUpgradeTools.STEEL_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    hoe.setLevel(HoeLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRON_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TOOL.getCost());
                    BlacksmithUpgradeTools.STEEL_TOOL.decreaseDailyLimit();
                }
            }
            case HoeLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TOOL.getCost() &&
                BlacksmithUpgradeTools.GOLD_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    hoe.setLevel(HoeLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.GOLD_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TOOL.getCost());
                    BlacksmithUpgradeTools.GOLD_TOOL.decreaseDailyLimit();
                }
            }
            case HoeLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost() &&
                BlacksmithUpgradeTools.IRIDIUM_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(hoe.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    hoe.setLevel(HoeLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRIDIUM_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost());
                    BlacksmithUpgradeTools.IRIDIUM_TOOL.decreaseDailyLimit();
                }
            }
        }
    }

    public void upgradeTrashCan(TrashCan trashCan) {
        switch (trashCan.getLevel()) {
            case TrashCanLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TRASH_CAN.getCost() &&
                BlacksmithUpgradeTools.COPPER_TRASH_CAN.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    trashCan.setLevel(TrashCanLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.COPPER_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TRASH_CAN.getCost());
                    BlacksmithUpgradeTools.COPPER_TRASH_CAN.decreaseDailyLimit();
                }
            }
            case TrashCanLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TRASH_CAN.getCost() &&
                BlacksmithUpgradeTools.STEEL_TRASH_CAN.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    trashCan.setLevel(TrashCanLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRON_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TRASH_CAN.getCost());
                    BlacksmithUpgradeTools.STEEL_TRASH_CAN.decreaseDailyLimit();
                }
            }
            case TrashCanLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TRASH_CAN.getCost() &&
                BlacksmithUpgradeTools.GOLD_TRASH_CAN.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    trashCan.setLevel(TrashCanLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.GOLD_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TRASH_CAN.getCost());
                    BlacksmithUpgradeTools.GOLD_TRASH_CAN.decreaseDailyLimit();
                }
            }
            case TrashCanLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TRASH_CAN.getCost() &&
                BlacksmithUpgradeTools.IRIDIUM_TRASH_CAN.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(trashCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    trashCan.setLevel(TrashCanLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TRASH_CAN.getCost());
                    BlacksmithUpgradeTools.IRIDIUM_TRASH_CAN.decreaseDailyLimit();
                }
            }
        }
    }

    public void upgradeWateringCan(WateringCan wateringCan) {
        switch (wateringCan.getLevel()) {
            case WateringCanLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TOOL.getCost() &&
                        BlacksmithUpgradeTools.COPPER_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    wateringCan.setLevel(WateringCanLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.COPPER_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TOOL.getCost());
                    BlacksmithUpgradeTools.COPPER_TOOL.decreaseDailyLimit();
                }
            }
            case WateringCanLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TOOL.getCost() &&
                        BlacksmithUpgradeTools.STEEL_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    wateringCan.setLevel(WateringCanLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRON_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TOOL.getCost());
                    BlacksmithUpgradeTools.STEEL_TOOL.decreaseDailyLimit();
                }
            }
            case WateringCanLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TOOL.getCost() &&
                        BlacksmithUpgradeTools.GOLD_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    wateringCan.setLevel(WateringCanLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.GOLD_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TOOL.getCost());
                    BlacksmithUpgradeTools.GOLD_TOOL.decreaseDailyLimit();
                }
            }
            case WateringCanLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost() &&
                        BlacksmithUpgradeTools.IRIDIUM_TOOL.getDailyLimit() != 0) {
                    if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()) == null ||
                            App.getCurrentGame().getCurrentPlayer().getItemInInventory(wateringCan.getObjectType()).getNumber() < 5) {
                        System.out.println("You don't have enough item tools to upgrade, Faghir");
                        return;
                    }
                    canWeUpgrade = true;
                    wateringCan.setLevel(WateringCanLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().removeAmountFromInventory(GameObjectType.IRIDIUM_BAR, 5);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost());
                    BlacksmithUpgradeTools.IRIDIUM_TOOL.decreaseDailyLimit();
                }
            }
        }
    }

    public boolean canWeUpgrade = false;

    @Override
    public boolean dailyLimitCheck(GameObject gameObject)
    {
        return true;
    }
}
