package model.shops;

import model.App;
import model.GameObject;
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
        super(ShopType.BLACK_SMITH, ShopType.BLACK_SMITH.name(), "Clint", 9, 16);
    }

    public void setUpgradeTools() {
        this.upgradeToolsList.addAll(Arrays.asList(BlacksmithUpgradeTools.values()));
    }
    public void setStockItems() {
        this.stockItems.addAll(Arrays.asList(BlacksmithStockItem.values()));
    }

    @Override
    public String showProducts() {
        StringBuilder products = new StringBuilder();
        super.showProducts();
        for(BlacksmithStockItem item : BlacksmithStockItem.values()) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(BlacksmithUpgradeTools tool : BlacksmithUpgradeTools.values()) {
            products.append(tool.getName()).append(" ").append(tool.getCost()).append("\n");
        }
        return products.toString();
    }

    @Override
    public String showAvailableProducts() {
        StringBuilder products = new StringBuilder();
        super.showAvailableProducts();
        for(BlacksmithStockItem item : stockItems) {
            products.append(item.getName()).append(" ").append(item.getPrice()).append("\n");
        }
        for(BlacksmithUpgradeTools tool : upgradeToolsList) {
            products.append(tool.getName()).append(" ").append(tool.getCost()).append("\n");
        }
        return products.toString();
    }

    @Override
    public void purchase(GameObject gameObject) {
        super.purchase(gameObject);
        for(BlacksmithStockItem stockItem : stockItems) {
            if(stockItem.getGameObjectType().equals(gameObject.getObjectType())) {
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(stockItem.getPrice());
                App.getCurrentGame().getCurrentPlayer().addToInventory(gameObject);
            }
        }
    }

    public void upgrade(Tool tool) {
        switch (tool.getToolType()) {
            case ToolType.FishingPole -> upgradeFishingPole((FishingPole) tool);
            case ToolType.Axe -> upgradeAxe((Axe) tool);
            case ToolType.TrashCan -> upgradeTrashCan((TrashCan) tool);
            case ToolType.Hoe -> upgradeHoe((Hoe) tool);
            case ToolType.Pickaxe -> upgradePickaxe((Pickaxe) tool);
        }
    }

    public void upgradeFishingPole(FishingPole fishingPole) {
        switch (fishingPole.getLevel()) {
            case FishingPoleLevel.Training -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= FishingPoleLevel.Bamboo.getPrice()) {
                    fishingPole.setLevel(FishingPoleLevel.Bamboo);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(FishingPoleLevel.Bamboo.getPrice());
                }
            }
            case FishingPoleLevel.Bamboo -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= FishingPoleLevel.FiberGlass.getPrice()) {
                    fishingPole.setLevel(FishingPoleLevel.FiberGlass);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(FishingPoleLevel.FiberGlass.getPrice());
                }
            }
            case FishingPoleLevel.FiberGlass -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= FishingPoleLevel.Iridium.getPrice()) {
                    fishingPole.setLevel(FishingPoleLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(FishingPoleLevel.Iridium.getPrice());
                }
            }
        }
    }

    public void upgradeAxe(Axe axe) {
        switch (axe.getLevel()) {
            case AxeLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TOOL.getCost()) {
                    axe.setLevel(AxeLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TOOL.getCost());
                }
            }
            case AxeLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TOOL.getCost()) {
                    axe.setLevel(AxeLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TOOL.getCost());
                }
            }
            case AxeLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TOOL.getCost()) {
                    axe.setLevel(AxeLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TOOL.getCost());
                }
            }
            case AxeLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost()) {
                    axe.setLevel(AxeLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost());
                }
            }
        }
    }

    public void upgradePickaxe(Pickaxe pickaxe) {
        switch (pickaxe.getLevel()) {
            case PickaxeLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TOOL.getCost()) {
                    pickaxe.setLevel(PickaxeLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TOOL.getCost());
                }
            }
            case PickaxeLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TOOL.getCost()) {
                    pickaxe.setLevel(PickaxeLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TOOL.getCost());
                }
            }
            case PickaxeLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TOOL.getCost()) {
                    pickaxe.setLevel(PickaxeLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TOOL.getCost());
                }
            }
            case PickaxeLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost()) {
                    pickaxe.setLevel(PickaxeLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost());
                }
            }
        }
    }

    public void upgradeHoe(Hoe hoe) {
        switch (hoe.getLevel()) {
            case HoeLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TOOL.getCost()) {
                    hoe.setLevel(HoeLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TOOL.getCost());
                }
            }
            case HoeLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TOOL.getCost()) {
                    hoe.setLevel(HoeLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TOOL.getCost());
                }
            }
            case HoeLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TOOL.getCost()) {
                    hoe.setLevel(HoeLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TOOL.getCost());
                }
            }
            case HoeLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost()) {
                    hoe.setLevel(HoeLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TOOL.getCost());
                }
            }
        }
    }

    public void upgradeTrashCan(TrashCan trashCan) {
        switch (trashCan.getLevel()) {
            case TrashCanLevel.base -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.COPPER_TRASH_CAN.getCost()) {
                    trashCan.setLevel(TrashCanLevel.Copper);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.COPPER_TRASH_CAN.getCost());
                }
            }
            case TrashCanLevel.Copper -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.STEEL_TRASH_CAN.getCost()) {
                    trashCan.setLevel(TrashCanLevel.Iron);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.STEEL_TRASH_CAN.getCost());
                }
            }
            case TrashCanLevel.Iron -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.GOLD_TRASH_CAN.getCost()) {
                    trashCan.setLevel(TrashCanLevel.Golden);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.GOLD_TRASH_CAN.getCost());
                }
            }
            case TrashCanLevel.Golden -> {
                if(App.getCurrentGame().getCurrentPlayer().getMoney() >= BlacksmithUpgradeTools.IRIDIUM_TRASH_CAN.getCost()) {
                    trashCan.setLevel(TrashCanLevel.Iridium);
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(BlacksmithUpgradeTools.IRIDIUM_TRASH_CAN.getCost());
                }
            }
        }
    }


}
