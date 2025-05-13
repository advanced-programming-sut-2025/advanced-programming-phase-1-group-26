package model;

import model.enums.MapTypes;
import model.enums.ShopType;
import model.enums.TileTexture;

public class City extends Map
{
    private final String mapPath;
    Point[] playerPoints = new Point[4];

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
        this.startingPoint = findFreeStartingPoint();
    }

    public Point findFreeStartingPoint()
    {
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                if (tile.getTexture().equals(TileTexture.ROAD))
                {
                    boolean found = true;

                    for (Point point : playerPoints)
                    {
                        if (point != null && point.getX() == x && point.getY() == y)
                        {
                            found = false;
                            break;
                        }
                    }

                    if (found)
                    {
                        return tile.getPoint();
                    }
                }
            }
        }
        return null;
    }

    public static boolean isNearShop(ShopType type)
    {
        return true; // TODO: add later
    }

    public Point[] getPlayerPoints()
    {
        return playerPoints;
    }
}
