package model;

import model.enums.GameObjectType;
import model.enums.NpcDetails;
import model.enums.Season;
import model.player_data.FriendshipWithNpcData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NPC {
    private Point location;
    public NpcDetails npcDetails;
    private String name;
    private ArrayList<String> dialogues = new ArrayList<>();
    private List<GameObjectType> favorites;
    private List<GameObject> requests;
    private List<GameObject> rewards;
    private ArrayList<GameObject> openQuests = new ArrayList<>();
    private FriendshipWithNpcData friendshipWithNpcData;

    public NPC(NpcDetails npcDetails) {
        this.friendshipWithNpcData.setXp(0);
        this.friendshipWithNpcData.setLevel(0);
        this.npcDetails = npcDetails;
        this.name = npcDetails.getName();
        switch (name) {
            case "Robin" -> App.getCurrentGame().getCurrentPlayer().setRobinFriendship(friendshipWithNpcData);
            case "Abigail" -> App.getCurrentGame().getCurrentPlayer().setAbigailFriendship(friendshipWithNpcData);
            case "Sebastian" -> App.getCurrentGame().getCurrentPlayer().setSebastianFriendship(friendshipWithNpcData);
            case "Harvey" -> App.getCurrentGame().getCurrentPlayer().setHarveyFriendship(friendshipWithNpcData);
            case "Lia" -> App.getCurrentGame().getCurrentPlayer().setLiaFriendship(friendshipWithNpcData);
        }
        this.favorites = npcDetails.getFavorites();
        this.requests = npcDetails.getRequests();
        this.rewards = npcDetails.getRewards();
        this.openQuests.add(requests.getFirst());
    }

    public NpcDetails getNpcDetails() {
        return npcDetails;
    }

    public Point getLocation() {
        return location;
    }
    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isNearPlayer(Point npcLocation, Point playerLocation) {
        int xDiff = npcLocation.getX() - playerLocation.getX();
        int yDiff = npcLocation.getY() - playerLocation.getY();
        return xDiff >= -1 && xDiff <= 1 && yDiff >= -1 && yDiff <= 1;
    }

    public boolean isQuestFinish = false;
    public boolean isTalked = false;
    public boolean isGifted = false; //TODO, should be reset everyday
    public String talk(Boolean isTalked, Time currentTime) {
        if(!isTalked) {
            friendshipWithNpcData.increaseXp(20);
        }
        return this.getNpcDetails().getDialogue(currentTime.getSeason(), currentTime.getTimeOfDay());
    }
    public void giftNPC(boolean isGift, GameObjectType gift) {
        if(!isGift) {
            friendshipWithNpcData.increaseXp(50);
        }
        if(favorites.contains(gift)) {
            friendshipWithNpcData.increaseXp(200);
        }
    }

    public ArrayList<GameObject> updateQuests(Season season, Time currentTime) {
        if(friendshipWithNpcData.getLevel() == 1) {
            if(!openQuests.contains(requests.get(1))) openQuests.add(requests.get(1));
        }
        if(season != currentTime.getSeason()) {
            if(!openQuests.contains(requests.get(2))) openQuests.add(requests.get(2));
        }

        return openQuests;
    }

    public void removeQuest(int index) {
        openQuests.remove(index);
    }

    public FriendshipWithNpcData getFriendshipWithNpcData() {
        return friendshipWithNpcData;
    }
    public void setFriendshipWithNpcData(FriendshipWithNpcData friendshipWithNpcData) {
        this.friendshipWithNpcData = friendshipWithNpcData;
    }

    public void reset() {
        isTalked = false;
        isGifted = false;
    }
}
