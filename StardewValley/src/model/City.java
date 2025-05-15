package model;

import model.enums.MapTypes;
import model.enums.ShopType;
import model.enums.TileTexture;

import java.util.ArrayList;

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

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                tile.setInCity();
            }
        }
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

    public Point[] getPlayerPoints()
    {
        return playerPoints;
    }

    public ArrayList<Point> getNpcLocations()
    {
        ArrayList<Point> npcLocations = new ArrayList<>();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                if (tile.getTexture().equals(TileTexture.NPC_BLACKSMITH))
                {
                    npcLocations.add(tile.getPoint());
                }
            }
        }
        return npcLocations;
    }

    public boolean isNearShop(ShopType type)
    {
        TileTexture texture = getShopTileTexture(type);

        if (texture == null)
        {
            return false;
        }

        Player player = App.getCurrentGame().getCurrentPlayer();
        Point location = player.getLocation();

        for (int dy = -1; dy <= 1; dy++)
        {
            for (int dx = -1; dx <= 1; dx++)
            {
                if (isInBounds(location.getX() + dx, location.getY() + dy))
                {
                    Tile tile = tiles[location.getY() + dy][location.getX() + dx];
                    if (tile.getTexture().equals(texture))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private TileTexture getShopTileTexture(ShopType type)
    {
        switch (type)
        {
            case ShopType.CARPENTER_SHOP ->
            {
                return TileTexture.SHOP_CARPENTER;
            }

            case ShopType.BLACK_SMITH ->
            {
                return TileTexture.SHOP_BLACKSMITH;
            }

            case ShopType.JOJA_MART ->
            {
                return TileTexture.SHOP_JOJAMART;
            }

            case ShopType.STARDROP_SALOON ->
            {
                return TileTexture.SHOP_SALOON;
            }

            case ShopType.PIERRE_GENERAL_STORE ->
            {
                return TileTexture.SHOP_PIERRE;
            }

            case ShopType.FISH_SHOP ->
            {
                return TileTexture.SHOP_FISH;
            }

            case ShopType.MARINE_RANCH ->
            {
                return TileTexture.SHOP_MARNIE;
            }

            default ->
            {
                return null;
            }
        }
    }
}
