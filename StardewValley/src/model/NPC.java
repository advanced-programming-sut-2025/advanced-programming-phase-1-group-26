package model;

import model.enums.GameObjectType;
import model.enums.NpcDetails;
import model.enums.Season;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NPC {
    private Tile location;
    private int friendship;
    private int levelOfFriendship;
    public NpcDetails npcDetails;
    private String name;
    private ArrayList<String> dialogues = new ArrayList<>();
    private List<GameObjectType> favorites;
    private List<GameObject> requests;
    private List<GameObject> rewards;
    private ArrayList<GameObject> openQuests = new ArrayList<>();

    public NPC(NpcDetails npcDetails, Tile location) {
        this.location = location;
        this.friendship = 0;
        this.levelOfFriendship = 0;
        this.npcDetails = npcDetails;
        this.name = npcDetails.getName();
        this.favorites = npcDetails.getFavorites();
        this.requests = npcDetails.getRequests();
        this.rewards = npcDetails.getRewards();
        this.openQuests.add(requests.getFirst());
    }

    public NpcDetails getNpcDetails() {
        return npcDetails;
    }

    public Tile getLocation() {
        return location;
    }
    public void setLocation(Tile location) {
        this.location = location;
    }

    public int getFriendship() {
        return friendship;
    }
    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }
    public void increaseFriendship(int amount) {
        this.friendship += amount;
        if(friendship > 200) {
            setFriendship(0);
            increaseLevelOfFriendship();
        }
    }

    public int getLevelOfFriendship() {
        return levelOfFriendship;
    }
    public void setLevelOfFriendship(int levelOfFriendship) {
        this.levelOfFriendship = levelOfFriendship;
    }
    public void increaseLevelOfFriendship() {
        if(levelOfFriendship == 799) return;
        this.levelOfFriendship++;
    }

    public boolean isNearPlayer(Tile npcLocation, Tile playerLocation) {
        int xDiff = npcLocation.getX() - playerLocation.getX();
        int yDiff = npcLocation.getY() - playerLocation.getY();
        return xDiff >= -1 && xDiff <= 1 && yDiff >= -1 && yDiff <= 1;
    }

    public boolean isTalked = false;
    public boolean isGift = false; //TODO, should be reset everyday
    public String talk(Boolean isTalked, Time currentTime) {
        if(!isTalked) {
            increaseFriendship(20);
        }
        return this.getNpcDetails().getDialogue(currentTime.getSeason(), currentTime.getTimeOfDay());
    }
    public void giftNPC(boolean isGift, GameObjectType gift) {
        if(!isGift) {
            increaseFriendship(50);
        }
        if(favorites.contains(gift)) {
            increaseFriendship(200);
        }
    }

    public ArrayList<GameObject> updateQuests(Season season, Time currentTime) {
        if(levelOfFriendship == 1) {
            if(!openQuests.contains(requests.get(1))) openQuests.add(requests.get(1));
        }
        if(season != currentTime.getSeason()) {
            if(!openQuests.contains(requests.get(2))) openQuests.add(requests.get(2));
        }

        return openQuests;
    }
}
