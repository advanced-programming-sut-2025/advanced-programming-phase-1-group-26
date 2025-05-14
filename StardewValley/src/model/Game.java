package model;

import model.enums.Menu;
import model.animal.Animal;
import model.enums.NpcDetails;
import model.enums.Weather;
import model.player_data.FriendshipData;
import model.resources.Plant;
import view.GameMenu;
import view.HomeMenu;

import java.util.ArrayList;
import java.util.Collections;

public class Game
{
    private Time currentTime = new Time();

    private ArrayList<NPC> NPCs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player oppenheimer; // I actually wanted to call this "opener", but thought it would be funnier this way
    private City city = new City();

    public Game() //TODO: this is only for test, should be removed later
    {

    }

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
    }


    public Time getCurrentTime()
    {
        return currentTime;
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }
    public void setNPCs() { //TODO, should add where the game starts
        for(NpcDetails npcDetails : NpcDetails.values()) {
            NPCs.add(new NPC(npcDetails));
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
        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            animal.checkAndReset();
        }
        for(NPC npc : NPCs) {
            npc.reset();
        }
        // TODO: add this methods later
        // resetPlayersEnergy();
        // growPlants();
        // respawnPlayers();

        distributeForagingItems();

        if (currentTime.getCurrentWeather().equals(Weather.Rain) || currentTime.getCurrentWeather().equals(Weather.Storm))
        {
            waterAllFarmPlants();
        }

        resetHitByThunders();
        if (currentTime.getCurrentWeather().equals(Weather.Storm))
        {
            hitTilesByThunder();
        }
        growPlants();
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

           if (currentPlayer.getEnergy() > 0)
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

        if (currentPlayer.isInCity())
        {
            App.setCurrentMenu(Menu.CityMenu);
        } else if (currentPlayer.isInGreenHouse() || currentPlayer.isInFarm())
        {
            App.setCurrentMenu(Menu.GameMenu);
        } else
        {
            App.setCurrentMenu(Menu.HomeMenu);
        }
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
}
