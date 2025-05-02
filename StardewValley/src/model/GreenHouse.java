package model;

import java.util.ArrayList;

public class GreenHouse
{
    private final int woodCost = 500;
    private final int coinCost = 1_000;


    private boolean isBuilt = false;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<GameObject> resources = new ArrayList<>();

    public void build()
    {
        isBuilt = true;
    }
}
