package model;

import model.enums.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{
    private Time currentTime = new Time();

    private ArrayList<NPC> NPCs = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Map map;
    private Player currentPlayer;
    private GreenHouse greenHouse = null;

    public Game() //TODO: this is only for test, should be removed later
    {
        this.map = new Map(20, 20);
    }

    public Game(ArrayList<Player> players, int height, int width)
    {
        this.players = players;
        this.map = new Map(height, width);
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

    public Tile findTile(int x, int y)
    {
        for (Tile tile : map.getTiles())
        {
            if (tile.getX() == x && tile.getY() == y)
            {
                return tile;
            }
        }

        return null;
    }
}
