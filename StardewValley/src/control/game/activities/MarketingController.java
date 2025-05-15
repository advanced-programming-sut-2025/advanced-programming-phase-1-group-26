package control.game.activities;

import model.*;
import model.animal.AnimalBuilding;
import model.enums.GameObjectType;
import model.enums.NpcDetails;
import model.enums.Season;
import model.enums.ShopType;
import model.enums.animal_enums.FarmAnimals;
import model.enums.animal_enums.FarmBuilding;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenRecipe;
import model.enums.regex_enums.GameCommands;
import model.enums.regex_enums.GeneralCommands;
import model.enums.resources_enums.CropType;
import model.enums.resources_enums.ForagingCropType;
import model.enums.resources_enums.ForagingMineralType;
import model.enums.resources_enums.TreeType;
import model.enums.shop_enums.BlacksmithStockItem;
import model.enums.tool_enums.ToolType;
import model.shops.*;
import model.tools.Tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

public class MarketingController {
    public Result showAllProducts() {
        ShopType targetType = null;
        for(ShopType type : ShopType.values()){
            if(App.getCurrentGame().getCity().isNearShop(type)){
                targetType = type;
            }
        }
        if(targetType == null) {
            return new Result(false, "No shop found in this location");
        }

        Shop shop = null;

        switch(targetType) {
            case BLACK_SMITH -> shop = new Blacksmith();
            case MARINE_RANCH -> shop = new MarniesRanch();
            case JOJA_MART -> shop = new JojaMart();
            case CARPENTER_SHOP -> shop = new CarpentersShop();
            case PIERRE_GENERAL_STORE -> shop = new PierresGeneralStore();
            case FISH_SHOP -> shop = new FishShop();
            case STARDROP_SALOON -> shop = new TheStardropSaloon();
        }
        if(!shop.isOpen(App.getCurrentGame().getCurrentTime())) {
            return new Result(false, "Shop is not open");
        }
        return new Result(true, shop.showProducts());
    }

    public Result showAvailableProducts() {
        ShopType targetType = null;
        for(ShopType type : ShopType.values()){
            if(App.getCurrentGame().getCity().isNearShop(type)){
                targetType = type;
            }
        }
        if(targetType == null) {
            return new Result(false, "No shop found in this location");
        }

        Shop shop = null;

        switch(targetType) {
            case BLACK_SMITH -> shop = new Blacksmith();
            case MARINE_RANCH -> shop = new MarniesRanch();
            case JOJA_MART -> shop = new JojaMart();
            case CARPENTER_SHOP -> shop = new CarpentersShop();
            case PIERRE_GENERAL_STORE -> shop = new PierresGeneralStore();
            case FISH_SHOP -> shop = new FishShop();
            case STARDROP_SALOON -> shop = new TheStardropSaloon();
        }
        if(!shop.isOpen(App.getCurrentGame().getCurrentTime())) {
            return new Result(false, "Shop is not open");
        }
        return new Result(true, shop.showAvailableProducts());
    }

    public Result purchase(String input) {
        String productName;
        int numberOfProductsToPurchase = 0;
        if(GameCommands.PURCHASE.matches(input)) {
            productName = GameCommands.PURCHASE.getMatcher(input).group("productName");
            numberOfProductsToPurchase = 1;
        }
        else {
            productName = GameCommands.PURCHASE_N.getMatcher(input).group("productName");
            numberOfProductsToPurchase = Integer.parseInt(GameCommands.PURCHASE_N.getMatcher(input).group("count"));
        }
        GameObjectType gameObjectType = null;
        for(GameObjectType type : GameObjectType.values()){
            if(type.name().equals(productName)){
                gameObjectType = type;
            }
        }
        if(gameObjectType == null) {return new Result(false, "No such product");}
        GameObject gameObject = new GameObject(gameObjectType, numberOfProductsToPurchase);
        Shop shop = null;

        if(App.getCurrentGame().isNearShop(ShopType.MARINE_RANCH)) {
            shop = new MarniesRanch();
        } else if(App.getCurrentGame().isNearShop(ShopType.JOJA_MART)) {
            shop = new JojaMart();
        } else if(App.getCurrentGame().isNearShop(ShopType.CARPENTER_SHOP)) {
            shop = new CarpentersShop();
        } else if(App.getCurrentGame().isNearShop(ShopType.PIERRE_GENERAL_STORE)) {
            shop = new PierresGeneralStore();
        } else if(App.getCurrentGame().isNearShop(ShopType.FISH_SHOP)) {
            shop = new FishShop();
        } else if(App.getCurrentGame().isNearShop(ShopType.STARDROP_SALOON)) {
            shop = new TheStardropSaloon();
        } else if(App.getCurrentGame().isNearShop(ShopType.BLACK_SMITH)) {
            shop = new Blacksmith();
        }
        if(shop == null) {return new Result(false, "You are not in a shop");}

        if(!shop.isOpen(App.getCurrentGame().getCurrentTime())) {
            return new Result(false, "Shop is not open");
        }

        if(!shop.isCorrectShop(gameObject)) {
            return new Result(false, "This item is not in this shop");
        }

        if(!shop.isAffordable(gameObject)) {
            return new Result(false, "You don't have enough money");
        }

        if(!shop.dailyLimitCheck(gameObject)) {
            return new Result(false, "You can't purchase due to a daily limit");
        }

        shop.purchase(gameObject);
        return new Result(true, "Purchase successful");
    }

    public void cheatAddMoney(String input) {
        int amount = Integer.parseInt(GameCommands.CHEAT_ADD_MONEY.getMatcher(input).group("amount"));
        App.getCurrentGame().getCurrentPlayer().increaseMoney(amount);
    }

    public Result sell(String input) {
        String productName;
        int numberOfProductsToSell = 0;
        if(GameCommands.SELL.matches(input)) {
        productName = GameCommands.SELL.getMatcher(input).group("productName");
            numberOfProductsToSell = 1;
        }
        else {
            productName = GameCommands.SELL_N.getMatcher(input).group("productName");
            numberOfProductsToSell = Integer.parseInt(GameCommands.SELL_N.getMatcher(input).group("count"));
        }

        GameObject sellGameObject = null;
        for(GameObjectType type : GameObjectType.values()){
            if(type.name().equals(productName)){
                sellGameObject = new GameObject(type, numberOfProductsToSell);
            }
        }
        if(sellGameObject == null) {return new Result(false, "No such product");}
        int price = getPrice(sellGameObject.getObjectType());
        if(price < 0) {
            return new Result(false, "You can't sell this product");
        }
        for(AnimalBuilding building : App.getCurrentGame().getCurrentPlayer().getAnimalBuildings()) {
            if(building.getFarmBuilding().equals(FarmBuilding.SHIPPING_BIN)) {
                if(App.getCurrentGame().getCurrentPlayer().getLocation().equals(building.getLocation())) {
                    building.addFaghatVaseShipingBin(sellGameObject);
                    App.getCurrentGame().getCurrentPlayer().removeFromInventory(sellGameObject);
                    return new Result(true, "You have successfully add this product to shipping bin");
                }
            }
        }
        return new Result(false, "Shipping bin unavailable");
    }

    public static int getPrice(GameObjectType type)
    {
        for (ForagingMineralType fm : ForagingMineralType.values())
        {
            if (fm.getType().equals(type))
            {
                return fm.getSellPrice();
            }
        }

        for (ForagingCropType fc : ForagingCropType.values())
        {
            if (fc.getType().equals(type))
            {
                return fc.getBaseSellPrice();
            }
        }

        for (TreeType t : TreeType.values())
        {
            if (t.getFruit().getType().equals(type))
            {
                return t.getFruitBaseSellPrice();
            }
        }

        for (CropType c : CropType.values())
        {
            if (c.getType().equals(type))
            {
                return c.getBaseSellPrice();
            }
        }

        for (CraftingRecipeEnums r : CraftingRecipeEnums.values())
        {
            if (r.getProduct().equals(type))
            {
                return r.getPrice();
            }
        }

        for (KitchenRecipe k : KitchenRecipe.values())
        {
            if (k.getType().equals(type))
            {
                return k.getSellPrice();
            }
        }

        return -1;
    }

    public Result upgradeTool(String input) {
        String toolName = GeneralCommands.TOOLS_UPGRADE.getMatcher(input).group("toolName");
        Tool targetTool = null;
        for(GameObject eachObject : App.getCurrentGame().getCurrentPlayer().getInventory()) {
            if(eachObject instanceof Tool) {
                if(toolName.equals(((Tool)eachObject).getName())) {
                    targetTool = (Tool)eachObject;
                }
            }
        }
        for(ToolType type : ToolType.values()){
            if(type.name().equals(toolName)) {
                if(!App.getCurrentGame().getCurrentShop().getType().equals(ShopType.BLACK_SMITH)) {
                    return new Result(false, "You are not in the correct shop");
                } else {
                    Blacksmith blacksmith = new Blacksmith();
                    if(!blacksmith.isOpen(App.getCurrentGame().getCurrentTime())) {
                        return new Result(false, "Shop is not open");
                    }
                    if(!blacksmith.canWeUpgrade) return new Result(false, "You can't upgrade");
                    assert targetTool != null;
                    blacksmith.upgrade(targetTool);
                    return new Result(true, "Upgrade successful");
                }
            }
        }
        return new Result(false, "tool does not exist");
    }

    public Result meetNPC(String input) {
        String npcName = GameCommands.MEET_NPC.getMatcher(input).group("NPCname");
        for(NPC npc : App.getCurrentGame().getNPCs()) {
            if(npc.getNpcDetails().getName().equals(npcName)) {
                if(!npc.isNearPlayer(npc.getLocation(), App.getCurrentGame().getCurrentPlayer().getLocation())) {
                    return new Result(false, "You are not in a near NPC");
                }
                return new Result(true, npc.talk(npc.isTalked, App.getCurrentGame().getCurrentTime()));
            }
        }
        return new Result(false, "not a valid NPC name");
    }

    public NPC targetNPC(String input) {
        String npcName = GameCommands.MEET_NPC.getMatcher(input).group("NPCname");
        for(NPC npc : App.getCurrentGame().getNPCs()) {
            if(npc.getNpcDetails().getName().equals(npcName)) {
                return npc;
            }
        }
        return null;
    }

    public Result giftNPC(String input) {
        String npcName = GameCommands.GIFT_NPC.getMatcher(input).group("NPCname");
        String item = GameCommands.GIFT_NPC.getMatcher(input).group("item");
        GameObject myItem = new GameObject();
        for(GameObjectType type : GameObjectType.values()) {
            if(type.name().equals(item)) {
                myItem = new GameObject(type, 1);
            }
        }
        for(NPC npc : App.getCurrentGame().getNPCs()) {
            if(npc.getNpcDetails().getName().equals(npcName)) {
                if(myItem instanceof Tool) {
                    return new Result(false, "You can't gift a tool");
                } else if(!npc.isNearPlayer(npc.getLocation(), App.getCurrentGame().getCurrentPlayer().getLocation())) {
                    return new Result(false, "You are not in a near NPC");
                } else {
                    npc.giftNPC(npc.isGifted, myItem.getObjectType());
                    return new Result(true, "thank you");
                }
            }
        }
        return new Result(false, "not a valid NPC name");
    }

    public Result friendshipNPCList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Abigail :" + App.getCurrentGame().getCurrentPlayer().getAbigailFriendship().getLevel()
        + " " + App.getCurrentGame().getCurrentPlayer().getAbigailFriendship().getXp() + "\n");
        sb.append("Sebastian :" + App.getCurrentGame().getCurrentPlayer().getSebastianFriendship().getLevel()
        + " " + App.getCurrentGame().getCurrentPlayer().getSebastianFriendship().getXp() + "\n");
        sb.append("Harvey :" + App.getCurrentGame().getCurrentPlayer().getHarveyFriendship().getLevel()
        + " " + App.getCurrentGame().getCurrentPlayer().getHarveyFriendship().getXp() + "\n");
        sb.append("Lia :" + App.getCurrentGame().getCurrentPlayer().getLiaFriendship().getLevel()
        + " " + App.getCurrentGame().getCurrentPlayer().getLiaFriendship().getXp() + "\n");
        sb.append("Robin :" + App.getCurrentGame().getCurrentPlayer().getRobinFriendship().getLevel()
        + " " + App.getCurrentGame().getCurrentPlayer().getRobinFriendship().getXp() + "\n");

        return new Result(true, sb.toString());
    }

    public Result questsNPCList(NPC npc) {
        StringBuilder sb = new StringBuilder();
        for(GameObject quest : npc.updateQuests(Season.Spring, App.getCurrentGame().getCurrentTime())) {
            sb.append(quest.getObjectType().toString() + " " + quest.getNumber() + "\n");
        }

        return new Result(true, sb.toString());
    }

    public Result questsFinish(String input, NPC npc) {
        if(npc.isQuestFinish) return new Result(false, "Quest finished by someone else");
        if(!npc.isNearPlayer(npc.getLocation(), App.getCurrentGame().getCurrentPlayer().getLocation()))
            return new Result(false, "You are not in a near NPC");
        int index = Integer.parseInt(GameCommands.QUESTS_FINISH.getMatcher(input).group("index"));
        GameObject request = npc.getNpcDetails().getRequests().get(index);
        GameObject reward = npc.getNpcDetails().getRewards().get(index);
        if(npc.getFriendshipWithNpcData().getLevel() == 2) {
            reward.addNumber(reward.getNumber());
        }
        App.getCurrentGame().getCurrentPlayer().addToInventory(reward);
        App.getCurrentGame().getCurrentPlayer().removeFromInventory(request);
        npc.removeQuest(index);

        return new Result(true, "Quest finished");
    }
}
