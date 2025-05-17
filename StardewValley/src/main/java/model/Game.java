package model;

import control.game.activities.MarketingController;
import model.animal.AnimalBuilding;
import model.enums.Menu;
import model.enums.*;
import model.animal.Animal;
import model.enums.NpcDetails;
import model.enums.Weather;
import model.enums.animal_enums.FarmBuildingType;
import model.enums.building_enums.CraftingRecipeEnums;
import model.enums.building_enums.KitchenRecipe;
import model.enums.resources_enums.*;
import model.player_data.FriendshipData;
import model.player_data.FriendshipWithNpcData;
import model.resources.Plant;
import model.shops.*;
import view.GameMenu;
import view.HomeMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game
{
    private Time currentTime = new Time();

    private ArrayList<NPC> NPCs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player oppenheimer; // I actually wanted to call this "opener", but thought it would be funnier this way
    private City city = new City();

    public Game(ArrayList<Player> players)
    {
        this.players = players;

        for (Player player : players)
        {
            for (Player other : players)
            {
                if (!player.equals(other))
                {
                    FriendshipData newFriendshipData = new FriendshipData(0, 0, false);
                    player.addFriendship(other, newFriendshipData);
                }
            }
        }

        setNPCs();

        for (NPC npc : NPCs)
        {
            for (Player player : players)
            {
                switch (npc.getName().toLowerCase())
                {
                    case "robin":
                        player.setRobinFriendship(new FriendshipWithNpcData(npc, player));
                        break;
                    case "abigail":
                        player.setAbigailFriendship(new FriendshipWithNpcData(npc, player));
                        break;
                    case "sebastian":
                        player.setSebastianFriendship(new FriendshipWithNpcData(npc, player));
                        break;
                    case "harvey":
                        player.setHarveyFriendship(new FriendshipWithNpcData(npc, player));
                        break;
                    case "lia":
                        player.setLiaFriendship(new FriendshipWithNpcData(npc, player));
                        break;
                }
            }
        }
    }

    public Time getCurrentTime()
    {
        return currentTime;
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    private void setNPCs()
    {
        ArrayList<Point> locations = city.getNpcLocations();
        for(int i = 0; i < (NpcDetails.values()).length; i++)
        {
            NpcDetails details = NpcDetails.values()[i];
            Point location = locations.get(i);
            NPCs.add(new NPC(details, location));
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Tile findTile(int y, int x)
    {
        //return farm.getMap().get(y).get(x);
    return null;
    }

    public void endDay()
    {
//        for(Player player : players) {
//            for(AnimalBuilding animalBuilding : player.getAnimalBuildings()) {
//                if(animalBuilding.getFarmBuilding().equals(FarmBuildingType.SHIPPING_BIN)) {
//                    for(GameObject gameObject : animalBuilding.getFaghatVaseShipingBin()) {
//                        player.increaseMoney
//                                (MarketingController.getPrice(gameObject.getObjectType()) * gameObject.getNumber());
//                    }
//                }
//            }
//
//        }

        // TODO: add this methods later
        // resetPlayersEnergy();
        // growPlants();
        // respawnPlayers();

        distributeForagingItems();
        unleashTheCrows();

        if (currentTime.getCurrentWeather().equals(Weather.Rain) || currentTime.getCurrentWeather().equals(Weather.Storm))
        {
            waterAllFarmPlants();
        }

        resetHitByThunders();
        if (currentTime.getCurrentWeather().equals(Weather.Storm))
        {
            hitTilesByThunder();
        }

        startPlants();
        growPlants();
        killPlants();

        takePlayerHome();
        resetNPCs();
        npcGiveGift();

        distributeFish();
        resetAnimals();
    }

    public void distributeForagingItems()
    {
        for (Farm farm : getFarms())
        {
            farm.setRandomForagingItems();
            farm.setRandomForagingMinerals();
        }
    }

    public void nextTurn()
    {
        int currentIndex = players.indexOf(currentPlayer);
        int index = currentIndex;

        // loop over players until reaches one that hasn't fainted yet (has enough energy)
        do
        {
           currentPlayer = getNext(currentPlayer);
           currentIndex = players.indexOf(currentPlayer);

            if (players.indexOf(currentPlayer) == 0)
            {
                currentTime.updateHour(1);

                if (currentTime.getHour() == 9)
                {
                    GameMenu.println("a day passed by, current day: " + currentTime.getDay());
                } else
                {
                    GameMenu.println("an hour passed by, current time: " + currentTime.getHour());
                }
            }


            if (currentPlayer.getEnergy() > 0 && !currentPlayer.shouldBeSkipped())
           {
               GameMenu.println(currentPlayer.getUser().getNickname() + " is now playing.");
               System.out.println(currentPlayer.newMessages());
               System.out.println(currentPlayer.newGifts());
               break;
           } else
           {
               GameMenu.println("skipping " + currentPlayer.getUser().getNickname() + "'s turn :(");
           }

        } while (index != currentIndex);

        // if it reaches the original player again, the day is ended, and things are reset
        if (index == currentIndex)
        {
            HomeMenu.println("starting a new day...");
            currentTime.updateHour(23 - currentTime.getHour());
        }

        updateMenu();
    }

    public Player getNext(Player player)
    {
        int currentIndex = players.indexOf(currentPlayer);
        int index = currentIndex;

        if (index == players.size() - 1)
        {
            index = 0;
        } else
        {
            index += 1;
        }

        return players.get(index);
    }

    public ArrayList<Farm> getFarms()
    {
        ArrayList<Farm> farms = new ArrayList<>();
        for (Player player : players)
        {
            farms.add(player.getFarm());
        }
        return farms;
    }

    public Tile getTileFromDirection(String direction)
    {
        Map map = currentPlayer.getCurrentMap();
        int x = currentPlayer.getLocation().getX();
        int y = currentPlayer.getLocation().getY();

        switch (direction)
        {
            case "W":
                return map.getTile(x - 1, y);
            case "E":
                return map.getTile(x + 1, y);
            case "N":
                return map.getTile(x, y - 1);
            case "S":
                return map.getTile(x, y + 1);
            case "NW":
                return map.getTile(x - 1, y - 1);
            case "NE":
                return map.getTile(x + 1, y - 1);
            case "SW":
                return map.getTile(x - 1, y + 1);
            case "SE":
                return map.getTile(x + 1, y + 1);
            default:
                return null;
        }
    }

    public Player getPlayerByNickname(String nickname) {
        for (Player player : this.players) {
            if (player.getUser().getNickname().equalsIgnoreCase(nickname)) {
                return player;
            }
        }
        return null;
    }

    public User getCreator()
    {
        return players.get(0).getUser();
    }

    public Player getOppenheimer()
    {
        if (oppenheimer == null)
        {
            return players.get(0);
        }
        return oppenheimer;
    }

    public void setOppenheimer(Player oppenheimer)
    {
        this.oppenheimer = oppenheimer;
    }

    public Player getPlayerFromUser(User user)
    {
        for (Player player : this.players)
        {
            if (player.getUser().equals(user))
            {
                return player;
            }
        }
        return null;
    }

    public void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    public City getCity()
    {
        return city;
    }

    public ArrayList<Tile> getAllFarmPlants()
    {
        ArrayList<Tile> allFarmPlants = new ArrayList<>();
        for (Player player : players)
        {
            allFarmPlants.addAll(player.getFarmPlants());
        }
        return allFarmPlants;
    }

    public ArrayList<Tile> getAllGreenhousePlants()
    {
        ArrayList<Tile> allGreenhousePlants = new ArrayList<>();
        for (Player player : players)
        {
            allGreenhousePlants.addAll(player.getGreenhousePlants());
        }
        return allGreenhousePlants;
    }

    public ArrayList<Tile> getAllPlants()
    {
        ArrayList<Tile> allPlants = new ArrayList<>();

        allPlants.addAll(getAllFarmPlants());
        allPlants.addAll(getAllGreenhousePlants());

        return allPlants;
    }

    public void waterAllFarmPlants()
    {
        ArrayList<Tile> allFarmPlants = getAllFarmPlants();
        for (Tile tile : allFarmPlants)
        {
            if (tile.getObject() != null && tile.getObject() instanceof Plant)
            {
                Plant plant = (Plant) tile.getObject();
                plant.water();
            }
        }
    }

    public void hitTilesByThunder()
    {
        for (Player player : players)
        {
            ArrayList<Tile> playerFarmPlants = player.getFarmPlants();
            ArrayList<Tile> randomFarmPlants = getThreeRandomElements(playerFarmPlants);
            for (Tile tile : randomFarmPlants)
            {
                tile.hitByThunder();
            }
        }
    }

    public ArrayList<Tile> getThreeRandomElements(ArrayList<Tile> originalList)
    {
        ArrayList<Tile> shuffled = new ArrayList<>(originalList);
        Collections.shuffle(shuffled);
        int count = Math.min(3, shuffled.size());
        return new ArrayList<>(shuffled.subList(0, count));
    }

    public void resetHitByThunders()
    {
        for (Player player : players)
        {
            Farm farm = player.getFarm();
            for (Tile tile : farm.getThunderedTiles())
            {
                tile.unHitByThunder();
            }
        }
    }

    public int getPlayerIndex()
    {
        return players.indexOf(currentPlayer);
    }

    public void growPlants()
    {
        for (Player player : players)
        {
            ArrayList<Plant> plants = player.getAllPlants();
            for (Plant plant : plants)
            {
                plant.update();
            }
        }
    }

    public void killPlants()
    {
        for (Player player : players)
        {
            ArrayList<Plant> plants = player.getAllPlants();
            {
                for (Plant plant : plants)
                {
                    if (plant.getLastWatered() > 2)
                    {
                        Tile tile = plant.getTile();
                        tile.unPlant();
                    }
                }
            }
        }
    }

    public void startPlants()
    {
        for (Player player : players)
        {
            ArrayList<Plant> plants = player.getAllPlants();
            for (Plant plant : plants)
            {
                if (!plant.hasStarted())
                {
                    plant.setHasStarted(true);
                }
            }
        }
    }

    private Shop currentShop;

    public Shop getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(Shop currentShop) {
        this.currentShop = currentShop;
    }

    public void takePlayerHome()
    {
        for (Player player : players)
        {
            int requiredEnergy = 5;
            if (player.isInFarm())
            {
                Farm farm = player.getFarm();
                requiredEnergy = farm.calculateEnergy(player.getLocation(), farm.getStartingPoint());
            }

            boolean done = false;
            if (!player.isFainted() && player.hasEnoughEnergy(requiredEnergy))
            {
                done = true;
                player.goHome();
            }

            player.resetEnergy();

            if (done)
            {
                player.increaseTurnEnergy(-1 * requiredEnergy);
            }

            player.unFaint();
        }
    }

    public void updateMenu()
    {
        if (currentPlayer.isInCity())
        {
            App.setCurrentMenu(Menu.CityMenu);
        } else if (currentPlayer.isInGreenHouse() || currentPlayer.isInFarm())
        {
            App.setCurrentMenu(Menu.GameMenu);
        } else if (currentPlayer.isInHome())
        {
            App.setCurrentMenu(Menu.HomeMenu);
        }
    }

    public void unleashTheCrows()
    {
        for (Player player : players)
        {
            player.getAttackedByCrows();
        }
    }

    public NPC getNpc(NpcDetails npcDetails)
    {
        for (NPC npc : NPCs)
        {
            if (npc.getNpcDetails().equals(npcDetails))
            {
                return npc;
            }
        }

        return null;
    }

    public void resetNPCs()
    {
        for (Player player : players)
        {
            player.setCurrentNPC(null);
            for (NPC npc : NPCs)
            {
                FriendshipWithNpcData data = player.getNpcFriendship(npc);
                data.reset();
            }
        }
    }

    public void npcGiveGift()
    {
        for (NPC npc : NPCs)
        {
            for (Player player : players)
            {
                FriendshipWithNpcData data = player.getNpcFriendship(npc);
                if (data.getLevel() >= 3)
                {
                    List<GameObjectType> giftTypes = npc.getNpcDetails().getGifts();
                    GameObject gift = new GameObject(giftTypes.get(new Random().nextInt(3)), 1);
                    player.addNpcGiftObject(gift);
                    player.addNpcGiftNPC(npc);
                }
            }
        }
    }

    public int getPrice(GameObjectType type)
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

    private void distributeFish()
    {
        for (Player player : players)
        {
            Farm farm = player.getFarm();
            Season season = currentTime.getSeason();

            farm.resetFish();
            farm.putFishInLake(season);
            farm.putLegendaryFishInLake(player, season);
        }
    }

    public void resetAnimals()
    {
        for (Player player : players)
        {
            for (Animal animal : player.getAnimals())
            {
                animal.checkAndReset();
            }
        }
    }
}
