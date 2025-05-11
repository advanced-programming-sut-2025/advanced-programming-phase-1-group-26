package model;

import model.enums.MapTypes;
import model.enums.TileTexture;

public class City extends Map
{
    private final String mapPath;

    public City()
    {
        this.mapType = MapTypes.CITY;
        this.mapPath = mapType.getMapPath();
        initialize();
    }

    private void initialize()
    {
        int[] dims = new int[2];
        this.mapData = MapLoader.loadMap(mapPath, dims);

        if (mapData == null)
        {
            throw new IllegalStateException("Failed to load city map: " + mapPath);
        }

        this.WIDTH = dims[0];
        this.HEIGHT = dims[1];
        this.tiles = new Tile[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                tiles[y][x] = new Tile(new Point(x, y));
                tiles[y][x].setType(TileTexture.VILLAGE_GRASS);
            }
        }

        applyMap();
        this.startingPoint = findStartingPoint();
    }

    private Point findStartingPoint()
    {
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                if (tile.getTexture().equals(TileTexture.ROAD))
                {
                    return tile.getPoint();
                }
            }
        }
        return null;
    }
}
