package control.game.activities;

import model.*;
import model.animal.AnimalBuilding;
import model.enums.GameObjectType;
import model.enums.NpcDetails;
import model.enums.ShopType;
import model.enums.animal_enums.FarmBuildingType;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenRecipe;
import model.enums.regex_enums.CityCommands;
import model.enums.regex_enums.GameCommands;
import model.enums.regex_enums.GeneralCommands;
import model.enums.resources_enums.CropType;
import model.enums.resources_enums.ForagingCropType;
import model.enums.resources_enums.ForagingMineralType;
import model.enums.resources_enums.TreeType;
import model.enums.tool_enums.ToolType;
import model.player_data.FriendshipWithNpcData;
import model.shops.*;
import model.tools.Tool;
import view.CityMenu;
import view.GameMenu;

import java.util.ArrayList;

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

    public Result purchase(String input, boolean flagged) {
        String productName;
        int numberOfProductsToPurchase = 0;

        if (flagged) {
            productName = GameCommands.PURCHASE_N.getMatcher(input).group("productName").trim();
            numberOfProductsToPurchase = Integer.parseInt(GameCommands.PURCHASE_N.getMatcher(input).group("count").trim());
        } else {
            productName = GameCommands.PURCHASE.getMatcher(input).group("productName").trim();
            numberOfProductsToPurchase = 1;
        }

        GameObjectType gameObjectType = null;
        for(GameObjectType type : GameObjectType.values()){
            if(type.toString().equals(productName)){
                gameObjectType = type;
            }
        }
        if(gameObjectType == null) {return new Result(false, "No such product");}
        GameObject gameObject = new GameObject(gameObjectType, numberOfProductsToPurchase);
        Shop shop = null;

        City city = App.getCurrentGame().getCity();

        if(city.isNearShop(ShopType.MARINE_RANCH)) {
            shop = new MarniesRanch();
        } else if(city.isNearShop(ShopType.JOJA_MART)) {
            shop = new JojaMart();
        } else if(city.isNearShop(ShopType.CARPENTER_SHOP)) {
            shop = new CarpentersShop();
        } else if(city.isNearShop(ShopType.PIERRE_GENERAL_STORE)) {
            shop = new PierresGeneralStore();
        } else if(city.isNearShop(ShopType.FISH_SHOP)) {
            shop = new FishShop();
        } else if(city.isNearShop(ShopType.STARDROP_SALOON)) {
            shop = new TheStardropSaloon();
        } else if(city.isNearShop(ShopType.BLACK_SMITH)) {
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
//        for(AnimalBuilding building : App.getCurrentGame().getCurrentPlayer().getAnimalBuildings()) {
//            if(building.getFarmBuilding().equals(FarmBuildingType.SHIPPING_BIN)) {
//                if(App.getCurrentGame().getCurrentPlayer().getLocation().equals(building.getLocation())) {
//                    building.addFaghatVaseShipingBin(sellGameObject);
//                    App.getCurrentGame().getCurrentPlayer().removeFromInventory(sellGameObject);
//                    return new Result(true, "You have successfully add this product to shipping bin");
//                }
//            }
//        }
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
                if(!App.getCurrentGame().getCity().isNearShop(ShopType.BLACK_SMITH)) {
                    return new Result(false, "You are not in the correct shop");
                } else {
                    Blacksmith blacksmith = new Blacksmith();
                    if(!blacksmith.isOpen(App.getCurrentGame().getCurrentTime())) {
                        return new Result(false, "Shop is not open");
                    }
//                    if(!blacksmith.canWeUpgrade) return new Result(false, "You can't upgrade");
                    if(false) return new Result(false, "You can't upgrade");
                    assert targetTool != null;
                    blacksmith.upgrade(targetTool);
                    return new Result(true, "Upgrade successful");
                }
            }
        }
        return new Result(false, "tool does not exist");
    }

    public Result meetNPC(String input)
    {
        String npcName = CityCommands.MEET_NPC.getMatcher(input).group("NPCname").trim();
        Player player = App.getCurrentGame().getCurrentPlayer();

        NpcDetails details = NpcDetails.getNpcByName(npcName);
        if (details == null)
        {
            return new Result(false, "There's no such NPC in this city.");
        }

        NPC npc = App.getCurrentGame().getNpc(details);
        if (npc == null)
        {
            return new Result(false, "There's no such NPC in this city.");
        }

        if (!npc.isNearPlayer(App.getCurrentGame().getCurrentPlayer().getLocation()))
        {
            return new Result(false, "You are not near " + npc.getName() + ".");
        }

        FriendshipWithNpcData friendship = player.getNpcFriendship(npc);
        if (friendship == null)
        {
            return new Result(false, "ERROR, this should NOT happen.");
        }

        player.setCurrentNPC(npc);
        friendship.talk();
        return new Result(true, "\n" + npc.getName() + ": " + npc.talk());
    }

    public void giftNPC(String input)
    {
        String npcName = CityCommands.GIFT_NPC.getMatcher(input).group("NPCname").trim();
        String item = CityCommands.GIFT_NPC.getMatcher(input).group("item").trim();

        Player player = App.getCurrentGame().getCurrentPlayer();

        NpcDetails details = NpcDetails.getNpcByName(npcName);
        if (details == null)
        {
           GameMenu.println("There's no such NPC in this city.");
           return;
        }

        NPC npc = App.getCurrentGame().getNpc(details);
        if (npc == null)
        {
            GameMenu.println("There's no such NPC in this city.");
            return;
        }

        if (!npc.isNearPlayer(App.getCurrentGame().getCurrentPlayer().getLocation()))
        {
            GameMenu.println("You are not near " + npc.getName() + ".");
            return;
        }

        FriendshipWithNpcData friendship = player.getNpcFriendship(npc);
        if (friendship == null)
        {
            GameMenu.println("ERROR, this should NOT happen.");
            return;
        }

        GameObjectType type = GameObjectType.getGameObjectType(item);
        if (type == null)
        {
            GameMenu.println("This item does not exist.");
            return;
        }

        GameObject gameObject = player.getItemInInventory(type);
        if (gameObject == null)
        {
            GameMenu.println("You don't have this item in your inventory :(");
            return;
        }

        if (gameObject instanceof Tool)
        {
            GameMenu.println("Are you kidding me? You can't give a " + item + " as a gift >:(");
            return;
        }

        if (gameObject.getNumber() < 1)
        {
            GameMenu.println("You don't have enough " + item + " in your inventory :(");
            return;
        }

        player.removeAmountFromInventory(type, 1);
        GameMenu.println("You gifted " + npc.getName() + ".");

        boolean isFavorite = npc.isFavorite(type);
        friendship.gift(isFavorite);
    }

    public Result showFriendshipNPCList()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Game game = App.getCurrentGame();
        StringBuilder output = new StringBuilder();

        output.append("Your Friends\n");
        output.append("--------------------------------\n");

        for (NPC npc : game.getNPCs())
        {
            FriendshipWithNpcData friendship = player.getNpcFriendship(npc);
            output.append(npc.getName()).append("\n ");
            output.append("\txp: ").append(friendship.getXp()).append("\n");
            output.append("\tlevel: ").append(friendship.getLevel()).append("\n");
            output.append("--------------------------------\n");
        }

        return new Result(true, output.toString());
    }

    public void questsNPCList()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        NPC npc = player.getCurrentNPC();

        if (npc == null)
        {
            GameMenu.println("You haven't talked to any NPC today.");
            return;
        }

        GameMenu.println(npc.getName() + "'s Available quests for you:");
        GameMenu.println("--------------------------------");

        if (npc.isFirstQuestAvailable())
        {
            GameMenu.println(npc.getQuestDescription(0));
        }

        if (npc.isSecondQuestAvailable())
        {
            GameMenu.println(npc.getQuestDescription(1));
        }

        if (npc.isThirdQuestAvailable())
        {
            GameMenu.println(npc.getQuestDescription(2));
        }
    }

    public Result questsFinish(String input)
    {
        String questIndex = CityCommands.QUESTS_FINISH.getMatcher(input).group("index").trim();
        int index = Integer.parseInt(questIndex) - 1;

        Player player = App.getCurrentGame().getCurrentPlayer();
        NPC npc = player.getCurrentNPC();

        if (npc == null)
        {
            GameMenu.println("You haven't talked to any NPC today.");
        }

        if (!npc.isNearPlayer(player.getLocation()))
        {
            return new Result(false, "You are not near " + npc.getName() + ".");
        }

        if (index != 0 && index != 1 && index != 2)
        {
            return new Result(false, "invalid quest index");
        }

        GameObject request = npc.getNpcDetails().getQuestRequest(index);
        GameObject reward = npc.getNpcDetails().getQuestReward(index);

        if (index == 0)
        {
            if (!npc.isFirstQuestAvailable())
            {
                return new Result(false, "This quest is not available.");
            }
        }

        if (index == 1)
        {
            if (!npc.isSecondQuestAvailable())
            {
                return new Result(false, "This quest is not available.");
            }
        }

        if (index == 2)
        {
            if (!npc.isThirdQuestAvailable())
            {
                return new Result(false, "This quest is not available.");
            }
        }

        if (!player.hasEnoughInInventory(request.getObjectType(), request.getNumber()))
        {
            return new Result(false, "You don't have " + request.getObjectType().toString() + " in your inventory.\n" +
                    "\trequired: " + request.getNumber() + "\n" +
                    "\tyou have: " + player.howManyInInventory(request.getObjectType()));
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "You don't have any capacity in your inventory. You can't recieve the prize.");
        }

        player.removeAmountFromInventory(request.getObjectType(), request.getNumber());
        player.addToInventory(reward);

        switch (index)
        {
            case 0:
                npc.firstQuestDone();
                break;
            case 1:
                npc.secondQuestDone();
                break;
            case 2:
                npc.thirdQuestDone();
                break;
        }

        return new Result(true, "Congratulations! You were the first person to do this quest.\n" +
                "You just received " + reward.getNumber() + " " + reward.getObjectType().toString() + ".");
    }

    public void showNpcGifts()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        ArrayList<NPC> NPCs = player.getNpcGiftsNPC();
        ArrayList<GameObject> gifts = player.getNpcGiftsObject();

        if (NPCs.isEmpty())
        {
            CityMenu.println("You didn't get any gifts :(");
        }

        for (int i = 0; i < NPCs.size(); i++)
        {
            NPC npc = NPCs.get(i);
            GameObject gift = gifts.get(i);

            CityMenu.println(npc.getName() + " gave you a " + gift.getObjectType().toString() + ".");
        }
    }

    public Result openGifts()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        ArrayList<NPC> NPCs = player.getNpcGiftsNPC();
        ArrayList<GameObject> gifts = player.getNpcGiftsObject();

        if (NPCs.isEmpty())
        {
            CityMenu.println("You have no gifts.");
        }

        Boolean success = player.recieveNpcGifts();

        if (!success)
        {
            return new Result(false, "You couldn't open any gifts.");
        }

        return new Result(true, "That's all for now.");
    }

    public Result showNpcLocation(String npcName)
    {
        NpcDetails details = NpcDetails.getNpcByName(npcName);
        if (details == null)
        {
            return new Result(false, "There's no such NPC in this city.");
        }

        NPC npc = App.getCurrentGame().getNpc(details);
        if (npc == null)
        {
            return new Result(false, "There's no such NPC in this city.");
        }

        Point location = npc.getLocation();
        return new Result(true, "city/X:" + location.getX() + "/Y:" + location.getY());
    }
}
