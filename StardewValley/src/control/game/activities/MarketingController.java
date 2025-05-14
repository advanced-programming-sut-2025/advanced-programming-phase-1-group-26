package control.game.activities;

import model.*;
import model.enums.GameObjectType;
import model.enums.NpcDetails;
import model.enums.Season;
import model.enums.ShopType;
import model.enums.regex_enums.GameCommands;
import model.shops.*;
import model.tools.Tool;

import java.util.ArrayList;
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
                    npc.giftNPC(npc.isGift, myItem.getObjectType());
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
