package model;

import model.enums.GameObjectType;
import model.enums.NpcDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class NPC {
    private Tile location;
    private int friendship;
    private int levelOfFriendship;
    public NpcDetails npcDetails;
    private String name;
    private ArrayList<GameObjectType> favorites;
    private HashMap<GameObjectType, Integer> requests;
    private HashMap<GameObjectType, Integer> rewards;

    public NPC(NpcDetails npcDetails, Tile location, int friendship, int levelOfFriendship) {
        this.location = location;
        this.friendship = friendship;
        this.levelOfFriendship = levelOfFriendship;
        this.npcDetails = npcDetails;
        this.name = npcDetails.getName();
        this.favorites = npcDetails.getFavorites();
        this.requests = npcDetails.getRequests();
        this.rewards = npcDetails.getRewards();
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

    public int getLevelOfFriendship() {
        return levelOfFriendship;
    }
    public void setLevelOfFriendship(int levelOfFriendship) {
        this.levelOfFriendship = levelOfFriendship;
    }
}
