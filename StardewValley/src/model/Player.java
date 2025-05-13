package model;

import model.animal.Animal;
import model.building.Cooking.EdibleThing;
import model.enums.GameObjectType;
import model.enums.Gender;
import model.enums.Weather;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenItems;
import model.enums.tool_enums.ToolType;
import model.player_data.FriendshipData;
import model.player_data.FriendshipWithNpcData;
import model.player_data.Skill;
import model.player_data.Trade;
import model.enums.SkillType;
import model.resources.Plant;
import model.tools.BackPack;
import model.tools.Tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Player {

    private final User user;
    private final Farm farm;
    private final Cabin cabin;
    private final GreenHouse greenHouse;

    private Gender gender;
    private Point location = null;
    private Map currentMap = null;

    private int energy;
    private int maxEnergy = 200;
    private int turnEnergy;
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

    private boolean newMessage;

    private ArrayList<Gift> newGifts = new ArrayList<>();
    private ArrayList<Gift> archiveGifts = new ArrayList<>();
    private ArrayList<Gift> givenGifts = new ArrayList<>();

    private FriendshipWithNpcData SebastianFriendship = new FriendshipWithNpcData();
    private FriendshipWithNpcData AbigailFriendship = new FriendshipWithNpcData();
    private FriendshipWithNpcData HarveyFriendship = new FriendshipWithNpcData();
    private FriendshipWithNpcData LiaFriendship = new FriendshipWithNpcData();
    private FriendshipWithNpcData RobinFriendship = new FriendshipWithNpcData();

    private HashMap<Player, GameObjectType> purposeList = new HashMap<>();
    private Player zeidy;

    private Tool currentTool;
    private double money;

    private ArrayList<Animal> animals = new ArrayList<>();

    private ArrayList<CraftingRecipeEnums> craftingRecipes = new ArrayList<>();

    private ArrayList<KitchenItems> cookingRecipes = new ArrayList<>(
            Arrays.asList(KitchenItems.FRIED_EGG,
                    KitchenItems.BAKED_FISH,
                    KitchenItems.SALAD));
    private ArrayList<EdibleThing> refrigerator = new ArrayList<>();

    public static ArrayList<String> appearences = new ArrayList<>(List.of("\uD83D\uDC31", "\uD83E\uDD8A", "\uD83D\uDC3C", "\uD83E\uDD81"));

    private boolean isInFarm = true;
    private boolean isInCity = false;
    private boolean isInGreenHouse = false;

    private final String apperance;

    public Player(User user, Farm farm, int number) {
        this.user = user;
        this.farm = farm;
        this.cabin = new Cabin();
        this.greenHouse = new GreenHouse();
        this.currentMap = this.farm;
        this.energy = 200;
        this.turnEnergy = 50;
        this.fainted = false;
        this.money = 0;

        this.zeidy = null;
        this.location = farm.getStartingPoint();
        this.newMessage = false;
        this.apperance = appearences.get(number);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void addFriendship(Player player, FriendshipData data)
    {
        this.friendships.put(player, data);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void increaseEnergy(int energy)
    {
        if (energy != -1)
        {
           this.energy += energy;
        }

        // TODO: add faint check mechanism
    }

    public int getTurnEnergy() {
        return turnEnergy;
    }

    public void setTurnEnergy(int turnEnergy) {
        this.turnEnergy = turnEnergy;
    }

    public void increaseTurnEnergy(int turnEnergy) {
        this.turnEnergy += turnEnergy;
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
        if (this.zeidy == null) {
            return money;
        }
        return money + zeidy.money;
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

    public boolean isNewMessage() {
        return newMessage;
    }

    public void setNewMessage(boolean newMessage) {
        this.newMessage = newMessage;
    }

    public ArrayList<Trade> getArchiveTrades() {
        return archiveTrades;
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

    public HashMap<Player, GameObjectType> getPurposeList() {
        return purposeList;
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

    public Map getCurrentMap()
    {
        return currentMap;
    }

    public void setLocation(Point location)
    {
        this.location = location;
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

    public void checkEnergy() {
        if (this.turnEnergy < 1) {
            this.setFainted(true);
        }
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

    public FriendshipWithNpcData getSebastianFriendship() {
        return SebastianFriendship;
    }

    public void setSebastianFriendship(FriendshipWithNpcData sebastianFriendship) {
        SebastianFriendship = sebastianFriendship;
    }

    public FriendshipWithNpcData getAbigailFriendship() {
        return AbigailFriendship;
    }

    public void setAbigailFriendship(FriendshipWithNpcData abigailFriendship) {
        AbigailFriendship = abigailFriendship;
    }

    public FriendshipWithNpcData getHarveyFriendship() {
        return HarveyFriendship;
    }

    public void setHarveyFriendship(FriendshipWithNpcData harveyFriendship) {
        HarveyFriendship = harveyFriendship;
    }

    public FriendshipWithNpcData getLiaFriendship() {
        return LiaFriendship;
    }

    public void setLiaFriendship(FriendshipWithNpcData liaFriendship) {
        LiaFriendship = liaFriendship;
    }

    public FriendshipWithNpcData getRobinFriendship() {
        return RobinFriendship;
    }

    public void setRobinFriendship(FriendshipWithNpcData robinFriendship) {
        RobinFriendship = robinFriendship;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
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

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Player player)) return false;
        return user.getUsername().equals(((Player) o).getUser().getUsername());
    }

    public Farm getFarm()
    {
        return farm;
    }

    public Cabin getCabin()
    {
        return cabin;
    }

    public GreenHouse getGreenHouse()
    {
        return greenHouse;
    }

    public void goToFarm()
    {
        if (isInCity)
        {
            City city = App.getCurrentGame().getCity();
            city.getPlayerPoints()[App.getCurrentGame().getPlayerIndex()] = this.location;
        }
        this.isInCity = false;
        this.isInGreenHouse = false;
        this.isInFarm = true;
        this.currentMap = this.farm;
        this.location = farm.getStartingPoint();
    }

    public void goToCabin()
    {
        this.isInFarm = false;
        this.currentMap = this.cabin;
        this.location = cabin.getStartingPoint();
    }

    public void goToGreenHouse()
    {
        this.isInFarm = false;
        this.isInGreenHouse = true;
        this.currentMap = this.greenHouse;
        this.location = greenHouse.getStartingPoint();
    }

    public void goToCity()
    {
        City city = App.getCurrentGame().getCity();
        this.isInFarm = false;
        this.isInCity = true;
        this.currentMap = this.user.getCurrentGame().getCity();
        this.location = this.user.getCurrentGame().getCity().findFreeStartingPoint();
        city.getPlayerPoints()[App.getCurrentGame().getPlayerIndex()] = this.location;
    }

    public void setEnergyToMax()
    {
        this.energy = maxEnergy;
    }

    public boolean canAffordGreenhouse()
    {
        return (money >= GreenHouse.getMoneyCost() && howManyInInventory(GameObjectType.WOOD) >= GreenHouse.getWoodCost());
    }

    public boolean hasEnoughEnergy(int required)
    {
        if (energy == -1)
        {
            return true;
        }

        return energy > required;
    }

    public ArrayList<Tile> getFarmPlants()
    {
        return farm.getAllPlantTiles();
    }

    public ArrayList<Tile> getGreenhousePlants()
    {
        return greenHouse.getAllPlantTiles();
    }

    public ArrayList<Tile> getAllPlantTiles()
    {
        ArrayList<Tile> allPlants = new ArrayList<>();

        allPlants.addAll(farm.getAllPlantTiles());
        allPlants.addAll(greenHouse.getAllPlantTiles());

        return allPlants;
    }

    public boolean isInFarm()
    {
        return isInFarm;
    }

    public boolean isInCity()
    {
        return isInCity;
    }

    public boolean isInGreenHouse()
    {
        return isInGreenHouse;
    }

    public String getApperance()
    {
        return apperance;
    }

    public ArrayList<Plant> getAllPlants()
    {
        ArrayList<Plant> allPlants = new ArrayList<>();

        for (Tile tile : farm.getAllPlantTiles())
        {
            if (tile.getObject() != null && tile.getObject() instanceof Plant)
            {
                allPlants.add((Plant) tile.getObject());
            }
        }

        for (Tile tile : greenHouse.getAllPlantTiles())
        {
            if (tile.getObject() != null && tile.getObject() instanceof Plant)
            {
                allPlants.add((Plant) tile.getObject());
            }
        }

        return allPlants;
    }
}
