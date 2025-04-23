package model;

import model.animal.Animal;
import model.player_data.FriendshipData;
import model.player_data.Skill;
import model.player_data.Trade;
import model.enums.GameObjectType;
import model.enums.SkillType;
import model.tools.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {

    User user;
    private int energy;
    private int maxEnergy = 200;
    /*TEMP*/ private boolean fainted;

    private Skill farmingSkill = new Skill(SkillType.Farming);
    private  Skill miningSkill = new Skill(SkillType.Mining);
    private  Skill gashtogozarSkill = new Skill(SkillType.Gashtogozar);
    private  Skill fishingSkill = new Skill(SkillType.Fishing);

    private HashMap<Integer, GameObject> inventory = new HashMap<>();
    private HashMap<Player, FriendshipData> friendships = new HashMap<>(); //TODO: might change to nested hashmap
    private ArrayList<Trade> requestTrades = new ArrayList<>();
    private ArrayList<Trade> offerTrades = new ArrayList<>();
    private Player zeidy;

    private Tool currentTool;

    private HashMap<Animal, Integer> animalFriendships = new HashMap<>();

    public Player(User user) {
        this.user = user;
        this.energy = maxEnergy;
        this.fainted = false;

        for (Player player : App.getCurrentGame().getPlayers()) {
            FriendshipData newFriendshipData = new FriendshipData(0, 0, false);
            this.friendships.put(player, newFriendshipData);
        }

        this.zeidy = null;

    }
    

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void increaseEnergy(int energy) {
        this.energy += energy;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public boolean isFainted() {
        return fainted;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    public Skill getFarmingSkill() {
        return farmingSkill;
    }

    public Skill getMiningSkill() {
        return miningSkill;
    }

    public Skill getGashtogozarSkill() {
        return gashtogozarSkill;
    }

    public Skill getFishingSkill() {
        return fishingSkill;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public HashMap<Integer, GameObject> getInventory() {
        return inventory;
    }

    public HashMap<Player, FriendshipData> getFriendships() {
        return friendships;
    }

    public ArrayList<Trade> getRequestTrades() {
        return requestTrades;
    }

    public ArrayList<Trade> getOfferTrades() {
        return offerTrades;
    }

    public Player getZeidy() {
        return zeidy;
    }

    public void setZeidy(Player zeidy) {
        this.zeidy = zeidy;
    }

    public ArrayList<GameObject> getInventoryItems () {
        ArrayList<GameObject> temp = new ArrayList<>();
        for (Map.Entry<Integer, GameObject> entry : this.getInventory().entrySet()) {
            GameObject type = entry.getValue();
            temp.add(type);
        }
        return temp;
    }
}
