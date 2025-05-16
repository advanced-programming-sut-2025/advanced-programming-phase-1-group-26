package model;

import model.animal.Animal;
import model.animal.AnimalBuilding;
import model.animal.Fish;
import model.enums.*;
import model.enums.building_enums.ArtisanGoodsType;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenRecipe;
import model.enums.tool_enums.ToolType;
import model.player_data.FriendshipData;
import model.player_data.FriendshipWithNpcData;
import model.player_data.Skill;
import model.player_data.Trade;
import model.resources.Plant;
import model.tools.BackPack;
import model.tools.Tool;
import model.tools.*;
import view.CityMenu;

import java.util.*;

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
    private int turnEnergy = 50;
    private boolean fainted = false;

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

    private FriendshipWithNpcData SebastianFriendship;
    private FriendshipWithNpcData AbigailFriendship;
    private FriendshipWithNpcData HarveyFriendship;
    private FriendshipWithNpcData LiaFriendship;
    private FriendshipWithNpcData RobinFriendship;

    private HashMap<Player, GameObjectType> purposeList = new HashMap<>();
    private Player zeidy;

    private Tool currentTool;
    private double money;

    private ArrayList<AnimalBuilding> animalBuildings = new ArrayList<>();

    private ArrayList<CraftingRecipeEnums> craftingRecipes = new ArrayList<>();

    private ArrayList<KitchenRecipe> cookingRecipes = new ArrayList<>(
            Arrays.asList(KitchenRecipe.FRIED_EGG,
                    KitchenRecipe.BAKED_FISH,
                    KitchenRecipe.SALAD));
    private ArrayList<GameObject> refrigerator = new ArrayList<>();

    public static ArrayList<String> appearences = new ArrayList<>(List.of("\uD83D\uDC31", "\uD83E\uDD8A", "\uD83D\uDC3C", "\uD83E\uDD81"));

    private boolean isInFarm = true;
    private boolean isInCity = false;
    private boolean isInGreenHouse = false;
    private boolean isInHome = false;
    private boolean isInZeidiesFarm = false;
    private boolean isInZeidiesHome = false;

    private final String apperance;
    private boolean shouldBeSkipped = false;

    private NPC currentNPC = null;
    private ArrayList<GameObject> npcGiftsObject = new ArrayList<>();
    private ArrayList<NPC> npcGiftsNPC = new ArrayList<>();

    private ArrayList<ArtisanGood> artisanGoods = new ArrayList<>();

    private ShopType currentShop;

    private ArrayList<Fish> fishes = new ArrayList<>();

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
        this.addToInventory(new Axe());
        this.addToInventory(new Hoe());
        this.addToInventory(new Pickaxe());
        this.addToInventory(new WateringCan());
        this.addToInventory(new Seythe());
        this.addToInventory(new TrashCan());

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
        if (isInCity)
        {
            int index = App.getCurrentGame().getPlayerIndex();
            City city = App.getCurrentGame().getCity();
            city.getPlayerPoints()[index] = location;
        }
        if (isInCity)
        {
            int index = App.getCurrentGame().getPlayerIndex();
            City city = App.getCurrentGame().getCity();
            city.getPlayerPoints()[index] = location;
        }

    }

    public void setCurrentMap(Map currentMap)
    {
        this.currentMap = currentMap;
    }

    public GameObject findObjectType(Enum<?> type)
    {
        for (GameObject obj : currentBackPack.getInventory())
        {
            Enum<?> inventoryItemType = obj.getObjectType();

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
            this.currentBackPack.getInventory().remove(object);
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
                if (tool.getToolType().equals(type))
                {
                    return tool;
                }
            }
        }
        return null;
    }

    public void addToInventory(GameObject object)
    {
        GameObject o = getItemInInventory(object.getObjectType());
        if (o != null)
        {
            addToInventory(object.getObjectType(), object.getNumber());
        } else
        {
            currentBackPack.getInventory().add(object);
        }
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
        boolean added = false;
        GameObject object = getItemInInventory(objectType);
        if (object != null)
        {
            object.addNumber(amount);
            added = true;
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

    public ArrayList<GameObject> getRefrigerator()
    {
        return refrigerator;
    }

    public ArrayList<KitchenRecipe> getCookingRecipes()
    {
        return cookingRecipes;
    }

    public boolean isNear(Point otherLocation)
    {

        Point location = this.location;
        int dx = Math.abs(location.getX() - otherLocation.getX());
        int dy = Math.abs(location.getY() - otherLocation.getY());

        return (dx <= 1 && dy <= 1) && !(dx == 0 && dy == 0);
    }

    public GameObject getFromRefrigerator (GameObjectType type)
    {
        for (GameObject thing : refrigerator)
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
        for (GameObject thing : refrigerator)
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
        for (GameObject thing : refrigerator)
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
            city.getPlayerPoints()[App.getCurrentGame().getPlayerIndex()] = null;
        }
        this.isInCity = false;
        this.isInGreenHouse = false;
        this.isInFarm = true;
        this.isInHome = false;
        this.isInZeidiesFarm = false;
        this.isInZeidiesHome = false;
        this.currentMap = this.farm;
        this.location = farm.getStartingPoint();
    }

    public void goToCabin()
    {
        this.isInHome = true;
        this.isInZeidiesFarm = false;
        this.isInZeidiesHome = false;
        this.isInFarm = false;
        this.currentMap = this.cabin;
        this.location = cabin.getStartingPoint();
    }

    public void goToGreenHouse()
    {
        this.isInHome = false;
        this.isInZeidiesFarm = false;
        this.isInZeidiesHome = false;
        this.isInFarm = false;
        this.isInGreenHouse = true;
        this.currentMap = this.greenHouse;
        this.location = greenHouse.getStartingPoint();
    }

    public void goToCity()
    {
        this.isInHome = false;
        this.isInZeidiesFarm = false;
        this.isInZeidiesHome = false;
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

    public ArrayList<AnimalBuilding> getAnimalBuildings() {
        return animalBuildings;
    }

    public void addAnimalBuilding(AnimalBuilding animalBuilding) {
        animalBuildings.add(animalBuilding);
    }

    public void decreaseMoney(int amount) {
        money -= amount;
    }

    public boolean hasEnoughEnergy(int required)
    {
        if (turnEnergy == -1)
        {
            return true;
        }

        return turnEnergy > required;
    }

    public ArrayList<Tile> getFarmPlants()
    {
        return farm.getAllPlantTiles();
    }

    public ArrayList<Tile> getGreenhousePlants()
    {
        return greenHouse.getAllPlantTiles();
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

    public Result newMessages() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.isNewMessage()) {
            currentPlayer.setNewMessage(false);
            return new Result(true, "you have some new messages! check them");
        } else {
            return new Result(false, "you don't have any new message");
        }
    }

    public Result newGifts() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (!currentPlayer.getNewGifts().isEmpty()) {
            getNewGifts().clear();
            return new Result(true, "you have received new gifts");
        } else {
            return new Result(false, "you didn't receive gifts loser");
        }
    }

    public boolean shouldBeSkipped()
    {
        return shouldBeSkipped;
    }

    public void setShouldBeSkipped(boolean shouldBeSkipped)
    {
        this.shouldBeSkipped = shouldBeSkipped;
    }

    public void resetEnergy()
    {
        if (turnEnergy != -1) // not unlimited
        {
            turnEnergy = 50;
        }

        if (energy != -1) // not unlimited
        {
            energy = maxEnergy;
            if (fainted)
            {
                energy = (int) (0.75 * maxEnergy);
                fainted = false;
            }
        }
    }

    public void faint()
    {
        fainted = true;
        shouldBeSkipped = true;
    }

    public boolean isInZeidiesFarm()
    {
        return isInZeidiesFarm;
    }

    public boolean isInZeidiesHome()
    {
        return isInZeidiesHome;
    }

    public boolean isShouldBeSkipped()
    {
        return shouldBeSkipped;
    }

    public void goHome()
    {
        this.isInFarm = false;
        this.isInCity = false;
        this.isInGreenHouse = false;
        this.isInZeidiesFarm = false;
        this.isInZeidiesHome = false; // TODO: add feature to wake up at zeidy's home [?]

        this.isInHome = true;
        this.currentMap = cabin;
        this.location = cabin.getBedPoint();
        App.setCurrentMenu(Menu.HomeMenu);
    }

    public void unFaint()
    {
        this.fainted = false;
        shouldBeSkipped = false;
    }

    public boolean isInHome()
    {
        return isInHome;
    }

    public void getAttackedByCrows()
    {
        ArrayList<Tile> farmPlants = getFarmPlants();
        int size = farmPlants.size();

        for (int i = 0; i < (size / 16); i++)
        {
            if (Math.random() < 0.25)
            {
                Random rand = new Random();
                Tile tile = farmPlants.get(rand.nextInt(size));
                if (tile.hasPlants())
                {
                    Plant plant = (Plant) tile.getObject();
                    if (!tile.isImmuneFromCrows())
                    {
                        plant.getAttacked();
                    }
                }
            }
        }
    }

    public FriendshipWithNpcData getNpcFriendship(NPC npc)
    {
        return switch (npc.getNpcDetails())
        {
            case NpcDetails.Lia -> LiaFriendship;
            case NpcDetails.Abigail -> AbigailFriendship;
            case NpcDetails.Harvey -> HarveyFriendship;
            case NpcDetails.Robin -> RobinFriendship;
            case NpcDetails.Sebastian -> SebastianFriendship;
            default -> null;
        };
    }

    public ArrayList<FriendshipWithNpcData> getAllNpcFriendships()
    {
        ArrayList<FriendshipWithNpcData> friendships = new ArrayList<>();

        friendships.add(LiaFriendship);
        friendships.add(AbigailFriendship);
        friendships.add(HarveyFriendship);
        friendships.add(RobinFriendship);
        friendships.add(SebastianFriendship);

        return friendships;
    }

    public NPC getCurrentNPC()
    {
        return currentNPC;
    }

    public void setCurrentNPC(NPC currentNPC)
    {
        this.currentNPC = currentNPC;
    }

    public void addNpcGiftObject(GameObject object)
    {
        npcGiftsObject.add(object);
    }

    public void addNpcGiftNPC(NPC npc)
    {
        npcGiftsNPC.add(npc);
    }

    public ArrayList<GameObject> getNpcGiftsObject()
    {
        return npcGiftsObject;
    }

    public ArrayList<NPC> getNpcGiftsNPC()
    {
        return npcGiftsNPC;
    }

    public boolean recieveNpcGifts()
    {
        boolean recieved = false;
        while (inventoryHasCapacity() && npcGiftsObject.size() > 0)
        {
            GameObject gift = npcGiftsObject.get(0);
            NPC npc = npcGiftsNPC.get(0);
            addToInventory(gift);
            npcGiftsObject.remove(0);
            npcGiftsNPC.remove(0);
            CityMenu.println("You just recieved a " + gift.getObjectType().toString() + " from " + npc.getName() + ".");
            recieved = true;
        }
        return recieved;
    }

    public ArtisanGood getArtisan(ArtisanGoodsType type)
    {
        for (ArtisanGood good : artisanGoods)
        {
            if (good.getArtisanType() == type)
            {
                return good;
            }
        }
        return null;
    }

    public boolean hasFishingPole()
    {
        for (GameObject object : currentBackPack.getInventory())
        {
            if (object instanceof FishingPole)
            {
                return true;
            }
        }
        return false;
    }

    public ShopType getCurrentShop()
    {
        return currentShop;
    }

    public void setCurrentShop(ShopType currentShop)
    {
        this.currentShop = currentShop;
    }

    public ArrayList<Animal> getAnimals()
    {
        ArrayList<Animal> animals = new ArrayList<>();
        for (AnimalBuilding animalBuilding : farm.getAnimalBuildings())
        {
            for (Animal animal : animalBuilding.getAnimals())
            {
                animals.add(animal);
            }
        }
        return animals;
    }

    public Animal findAnimal(String name)
    {
        for (Animal animal : getAnimals())
        {
            if (animal.getName().equalsIgnoreCase(name))
            {
                return animal;
            }
        }
        return null;
    }

    public boolean validAnimalName(String name)
    {
        for (Animal animal : getAnimals())
        {
            if (animal.getName().equalsIgnoreCase(name))
            {
                return false;
            }
        }
        return true;
    }

    public boolean isNearAnimal(Animal animal)
    {
        for (Animal a : getAnimals())
        {
            if (a.getName().equalsIgnoreCase(animal.getName()))
            {
                Tile tile = a.getTile();

                if (tile == null) // TODO: technically should be changed later
                {
                    return false;
                }

                Point p = tile.getPoint();
                ArrayList<Point> neighbors = App.getCurrentGame().getCurrentPlayer().getFarm().getNeighbors(
                        App.getCurrentGame().getCurrentPlayer().getLocation());

                for (Point neighbor : neighbors)
                {
                    if (neighbor.equals(p))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ArrayList<Fish> getFishes()
    {
        return fishes;
    }
}
