package model;

import model.animal.Animal;
import model.building.Cooking.EdibleThing;
import model.enums.GameObjectType;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenItems;
import model.enums.tool_enums.ToolType;
import model.player_data.FriendshipData;
import model.player_data.Skill;
import model.player_data.Trade;
import model.enums.SkillType;
import model.tools.BackPack;
import model.tools.Tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Player {

    private final User user;
    private final Farm farm;

    private Point location = null;
    private Farm currentFarm = null;

    private int energy;
    private int maxEnergy = 200;
    /*TEMP*/ private boolean fainted;

    private Skill farmingSkill = new Skill(SkillType.Farming);
    private Skill miningSkill = new Skill(SkillType.Mining);
    private Skill gashtogozarSkill = new Skill(SkillType.Gashtogozar);
    private Skill fishingSkill = new Skill(SkillType.Fishing);

    BackPack currentBackPack = new BackPack();
    private HashMap<Player, FriendshipData> friendships = new HashMap<>(); //TODO: might change to nested hashmap
    private ArrayList<Trade> sentTrades = new ArrayList<>();
    private ArrayList<Trade> receivedTrades = new ArrayList<>();
    private ArrayList<Trade> archiveTrades = new ArrayList<>();

    private ArrayList<Gift> newGifts = new ArrayList<>();
    private ArrayList<Gift> archiveGifts = new ArrayList<>();
    private ArrayList<Gift> givenGifts = new ArrayList<>();

    private HashMap<Player, GameObject> purposeList = new HashMap<>();
    private Player zeidy;

    private Tool currentTool;
    private double money;

    private HashMap<Animal, Integer> animalFriendships = new HashMap<>();

    private ArrayList<CraftingRecipeEnums> craftingRecipes = new ArrayList<>();

    private ArrayList<KitchenItems> cookingRecipes = new ArrayList<>(
            Arrays.asList(KitchenItems.FRIED_EGG,
                    KitchenItems.BAKED_FISH,
                    KitchenItems.SALAD));
    private ArrayList<EdibleThing> refrigerator = new ArrayList<>();

    public Player(User user, Farm farm) {
        this.user = user;
        this.farm = farm;
        this.energy = 200;
        this.fainted = false;
        this.money = 0;

        for (Player player : App.getCurrentGame().getPlayers()) {
            FriendshipData newFriendshipData = new FriendshipData(0, 0, false);
            this.friendships.put(player, newFriendshipData);
        }

        this.zeidy = null;
        this.location = farm.getStartingPoint();
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void increaseMoney(double money) {
        this.money += money;
    }

    public ArrayList<GameObject> getInventory() {
        return currentBackPack.getInventory();
    }

    public HashMap<Player, FriendshipData> getFriendships() {
        return friendships;
    }

    public ArrayList<Trade> getSentTrades() {
        return sentTrades;
    }

    public ArrayList<Trade> getReceivedTrades() {
        return receivedTrades;
    }

    public ArrayList<Trade> getArchiveTrades() {
        return archiveTrades;
    }

    public Player getZeidy() {
        return zeidy;
    }

    public void setZeidy(Player zeidy) {
        this.zeidy = zeidy;
    }

    public User getUser() {
        return user;
    }

    public String getNickName() {
        return user.getNickname();
    }

    @Override
    public String toString() {
        return user.getNickname();
    }

    public Point getLocation()
    {
        return location;
    }

    public Farm getCurrentFarm()
    {
        return currentFarm;
    }

    public void setLocation(Point location)
    {
        this.location = location;
    }

    public void setCurrentFarm(Farm currentFarm)
    {
        this.currentFarm = currentFarm;
    }

    public GameObject findObjectType(Enum<?> type)
    {
        for (GameObject obj : currentBackPack.getInventory())
        {
            Enum<?> inventoryItemType = obj.getType();

            if (inventoryItemType.equals(type))
            {
                return obj;
            }
        }
        return null;
    }

    public Trade getTradeById (int id) {
        for (Trade trade : this.getReceivedTrades()) {
            if (trade.getId() == id) {
                return trade;
            }
        }
        return null;
    }

    public GameObject getItemInInventory(GameObjectType objectType) {
        for (GameObject object : this.currentBackPack.getInventory()) {
            if (object.getObjectType().equals(objectType)) {
                return object;
            }
        }
        return null;
    }

    public void removeFromInventory(GameObject object)
    {
        if (this.currentBackPack.getInventory().contains(object))
        {
            object.addNumber(-1);
            if (object.number == 0)
            {
                this.currentBackPack.getInventory().remove(object);
            }
        }
    }

    public Gift getGiftById(int id) {
        for (Gift gift : this.archiveGifts) {
            if (gift.getId() == id) {
                return gift;
            }
        }
        return null;
    }

    public Tool getTool(ToolType type)
    {
        for (GameObject object : currentBackPack.getInventory())
        {
            if (object instanceof Tool tool)
            {
                if (tool.getType().equals(type))
                {
                    return tool;
                }
            }
        }
        return null;
    }

    public void addToInventory(GameObject object)
    {
        currentBackPack.getInventory().add(object);
    }

    public ArrayList<CraftingRecipeEnums> getCraftingRecipes()
    {
        return craftingRecipes;
    }

    public boolean hasEnoughInInventory(GameObjectType objectType, int amount)
    {
        for (GameObject object : currentBackPack.getInventory())
        {
            if (object.getObjectType().equals(objectType) && object.getNumber() >= amount)
            {
                return true;
            }
        }
        return false;
    }

    public int howManyInInventory(GameObjectType objectType)
    {
        for (GameObject object : currentBackPack.getInventory())
        {
            if (object.getObjectType().equals(objectType))
            {
                return object.getNumber();
            }
        }
        return 0;
    }

    public void removeAmountFromInventory(GameObjectType objectType, int amount)
    {
        for (GameObject object : currentBackPack.getInventory())
        {
            if (object.getObjectType().equals(objectType))
            {
                object.addNumber(-amount);
                if (object.number <= 0)
                {
                    this.currentBackPack.getInventory().remove(object);
                }
                break;
            }
        }
    }

    public boolean inventoryHasCapacity()
    {
       int capacity = currentBackPack.getCapacity();

       if (capacity == -1) // unlimited
       {
           return true;
       }

       if (capacity > currentBackPack.getSize())
       {
           return true;
       }

       return false;
    }

    public BackPack getCurrentBackPack()
    {
        return currentBackPack;
    }

    public void addToInventory(GameObjectType objectType, int amount)
    {
        GameObject object = getItemInInventory(objectType);
        if (object != null)
        {
            object.addNumber(amount);
        } else
        {
            if (inventoryHasCapacity())
            {
                GameObject newObject = new GameObject(objectType, amount);
                currentBackPack.getInventory().add(newObject);
            }
        }
    }

    public int getInventoryCapacity()
    {
        int capacity = currentBackPack.getCapacity();

        if (capacity == -1) // unlimited
        {
            return -1;
        }

        return capacity - currentBackPack.getSize();
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public ArrayList<Gift> getArchiveGifts() {
        return archiveGifts;
    }

    public ArrayList<Gift> getGivenGifts() {
        return givenGifts;
    }

    public HashMap<Player, GameObject> getPurposeList() {
        return purposeList;
    }

    public ArrayList<EdibleThing> getRefrigerator()
    {
        return refrigerator;
    }

    public ArrayList<KitchenItems> getCookingRecipes()
    {
        return cookingRecipes;
    }

    public boolean isNear(Point location)
    {
        int playerX = location.getX();
        int playerY = location.getY();

        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                if (x != 0 && y != 0)
                {
                    if (location.getX() == playerX + x && location.getY() == playerY + y)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public EdibleThing getFromRefrigerator (GameObjectType type)
    {
        for (EdibleThing thing : refrigerator)
        {
            if (thing.getObjectType().equals(type))
            {
                return thing;
            }
        }
        return null;
    }

    public int howManyInRefrigerator(GameObjectType type)
    {
        for (EdibleThing thing : refrigerator)
        {
            if (thing.getObjectType().equals(type))
            {
                return thing.getNumber();
            }
        }
        return 0;
    }

    public void removeAmountFromRefrigerator(GameObjectType type, int amount)
    {
        for (EdibleThing thing : refrigerator)
        {
            if (thing.getObjectType().equals(type))
            {
                thing.addNumber(-1 * amount);
                if (thing.getNumber() <= 0)
                {
                    refrigerator.remove(thing);
                }
                break;
            }
        }
    }
}
