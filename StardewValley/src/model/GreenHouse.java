package model;

import model.enums.GameObjectType;
import model.enums.MapTypes;
import model.enums.TileTexture;

public class GreenHouse extends Map
{
    private final String mapPath;
    private boolean isBuilt = false;

    private static final int woodCost = 500;
    private static final int moneyCost = 1000;

    public GreenHouse()
    {
        this.mapType = MapTypes.GREEN_HOUSE;
        this.mapPath = mapType.getMapPath();
        initialize();
    }

    private void initialize()
    {
        int[] dims = new int[2];
        this.mapData = MapLoader.loadMap(mapPath, dims);

        if (mapData == null)
        {
            throw new IllegalStateException("Failed to load green house map: " + mapPath);
        }

        this.WIDTH = dims[0];
        this.HEIGHT = dims[1];
        this.tiles = new Tile[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                tiles[y][x] = new Tile(new Point(x, y));
                tiles[y][x].setType(TileTexture.LAND);
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

    public void build()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.increaseMoney(-1 * moneyCost);
        player.addToInventory(GameObjectType.WOOD, woodCost);
        isBuilt = true;
    }

    public boolean isBuilt()
    {
        return isBuilt;
    }

    public static int getMoneyCost()
    {
        return moneyCost;
    }

    public static int getWoodCost()
    {
        return woodCost;
    }
}
