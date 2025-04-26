package model;

import model.enums.FarmTypes;
import model.enums.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{
    private Time currentTime = new Time();

    private ArrayList<NPC> NPCs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private GreenHouse greenHouse = null;

    public Game() //TODO: this is only for test, should be removed later
    {

    }

    public Game(ArrayList<Player> players, int height, int width)
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
//        return farm.getMap().get(y).get(x);
    return null;
    }

    public void endDay()
    {
        // TODO: add this methods later
        // resetPlayersEnergy();
        // growPlants();
        // respawnPlayers();
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
}
