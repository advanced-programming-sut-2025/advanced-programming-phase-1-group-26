package model;

import java.util.ArrayList;

public class GreenHouse
{
    private boolean isBuilt = false;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<GameObject> resources = new ArrayList<>();

    public void build()
    {
        isBuilt = true;
    }
}
