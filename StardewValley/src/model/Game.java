package model;

import model.enums.NpcDetails;

import java.util.ArrayList;

public class Game
{
    private Time currentTime = new Time();

    private ArrayList<NPC> NPCs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player oppenheimer; // I actually wanted to call this "opener", but thought it would be funnier this way

    public Game() //TODO: this is only for test, should be removed later
    {

    }

    public Game(ArrayList<Player> players)
    {
        this.players = players;
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
        // TODO: add this methods later
        // resetPlayersEnergy();
        // growPlants();
        // respawnPlayers();
        distributeForagingItems();
    }

    public void distributeForagingItems()
    {
        for (Farm farm : getFarms())
        {
            farm.setRandomForagingItems();
        }
    }

    public void nextTurn()
    {
        int currentIndex = players.indexOf(currentPlayer);
        int index = currentIndex;

        // loop over players until reaches one that hasn't fainted yet (has enough energy)

        do
        {
            if (index == players.size() - 1)
            {
                index = 0;
                currentTime.updateHour(1);
            } else
            {
                index += 1;
            }

            currentPlayer = players.get(index);

            if (currentPlayer.getEnergy() > 0)
            {
                break;
            }

        } while (index != currentIndex);

        // if it reaches the original player again, the day is ended, and things are reset
        if (index == currentIndex)
        {
            currentTime.updateHour(23 - currentTime.getHour());
        }

        if (currentTime.getHour() == 9)
        {
            endDay();
        }
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
                return map.getTile(y, x - 1);
            case "E":
                return map.getTile(y, x + 1);
            case "N":
                return map.getTile(y - 1, x);
            case "S":
                return map.getTile(y + 1, x);
            case "NW":
                return map.getTile(y - 1, x - 1);
            case "NE":
                return map.getTile(y - 1, x + 1);
            case "SW":
                return map.getTile(y + 1, x - 1);
            case "SE":
                return map.getTile(y + 1, x + 1);
            default:
                return null;
        }
    }

    public Player getPlayerByNickname(String nickname) {
        for (Player player : this.players) {
            if (player.getUser().getNickname().equals(nickname)) {
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
}
