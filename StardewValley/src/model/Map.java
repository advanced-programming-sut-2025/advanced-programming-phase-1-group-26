package model;

import java.util.ArrayList;

public class Map
{
    private int height;
    private int width;
    private ArrayList<Tile> tiles = new ArrayList<>();

    public Map(int width, int height)
    {
        this.height = height;
        this.width = width;

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                tiles.add(new Tile(i, j));
            }
        }
    }
}
