package model;

import model.enums.MapTypes;
import model.enums.TileTexture;

public class Cabin extends Map
{
    private final String mapPath;

    public Cabin()
    {
        this.mapType = MapTypes.CABIN;
        this.mapPath = mapType.getMapPath();
        initialize();
    }

    private void initialize()
    {
        int[] dims = new int[2];
        this.mapData = MapLoader.loadMap(mapPath, dims);

        if (mapData == null)
        {
            throw new IllegalStateException("Failed to load cabin map: " + mapPath);
        }

        this.WIDTH = dims[0];
        this.HEIGHT = dims[1];
        this.tiles = new Tile[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                tiles[y][x] = new Tile(new Point(x, y));
                tiles[y][x].setType(TileTexture.CABIN_INTERIOR_FLOOR);
            }
        }

        applyMap();
        this.startingPoint = findDoor();
    }

    private Point findDoor()
    {
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                if (tile.getTexture().equals(TileTexture.SHOP_DOOR))
                {
                    return tile.getPoint();
                }
            }
        }
        return null;
    }
}
