package model;

import java.util.ArrayList;

public class Game
{
    private Time currentTime = new Time();

    private ArrayList<NPC> NPCs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;

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
    public void setNPCs(ArrayList<NPC> NPCs) {
        this.NPCs = NPCs;
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
            farms.add(player.getCurrentFarm());
        }
        return farms;
    }
    
    public Tile getTileFromDirection(String direction)
    {
        Farm farm = currentPlayer.getCurrentFarm();
        return switch (direction)
        {
            case "W" -> farm.getTile(currentPlayer.getLocation().getY(),
                    currentPlayer.getLocation().getX() - 1);
            case "E" -> farm.getTile(currentPlayer.getLocation().getY(),
                    currentPlayer.getLocation().getX() + 1);
            case "N" -> farm.getTile(currentPlayer.getLocation().getY() - 1,
                    currentPlayer.getLocation().getX());
            case "S" -> farm.getTile(currentPlayer.getLocation().getY() + 1,
                    currentPlayer.getLocation().getX());
            case "NW" -> farm.getTile(currentPlayer.getLocation().getY() - 1,
                    currentPlayer.getLocation().getX() - 1);
            case "NE" -> farm.getTile(currentPlayer.getLocation().getY() - 1,
                    currentPlayer.getLocation().getX() + 1);
            case "SW" -> farm.getTile(currentPlayer.getLocation().getY() + 1,
                    currentPlayer.getLocation().getX() - 1);
            case "SE" -> farm.getTile(currentPlayer.getLocation().getY() + 1,
                    currentPlayer.getLocation().getX() + 1);
            default -> null;
        };
    }

    public Player getPlayerByNickname(String nickname) {
        for (Player player : this.players) {
            if (player.getUser().getNickname().equals(nickname)) {
                return player;
            }
        }
        return null;
    }
}
