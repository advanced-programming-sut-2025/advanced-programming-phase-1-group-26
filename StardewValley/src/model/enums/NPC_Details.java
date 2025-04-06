package model.enums;

import java.util.ArrayList;
import java.util.HashMap;

public enum NPC_Details {
    Sebastian("Sebastian", new ArrayList<>(), new HashMap<>(), new HashMap<>()),
    Abigail("Abigail", new ArrayList<>(), new HashMap<>(), new HashMap<>()),
    Harvey("Harvey", new ArrayList<>(), new HashMap<>(), new HashMap<>()),
    Lia("Lia", new ArrayList<>(), new HashMap<>(), new HashMap<>()),
    Robin("Robin", new ArrayList<>(), new HashMap<>(), new HashMap<>())
    ;
    private final String name;
    private final ArrayList<GameObjectType> favorites;
    private final HashMap<GameObjectType, Integer> requests;
    private final HashMap<GameObjectType, Integer> rewards;
    NPC_Details(String name, ArrayList<GameObjectType> favorites,
                HashMap<GameObjectType, Integer> requests, HashMap<GameObjectType, Integer> rewards) {

        this.name = name;
        this.favorites = favorites;
        this.requests = requests;
        this.rewards = rewards;
    }

    public String getName() {
        return name;
    }
    public ArrayList<GameObjectType> getFavorites() {
        return favorites;
    }
    public HashMap<GameObjectType, Integer> getRequests() {
        return requests;
    }
    public HashMap<GameObjectType, Integer> getRewards() {
        return rewards;
    }
}
