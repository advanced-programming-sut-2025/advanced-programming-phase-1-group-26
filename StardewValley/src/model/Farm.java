package model;

import model.enums.MapTypes;
import model.enums.TileTexture;
import model.enums.resources_enums.*;
import model.resources.*;

import java.util.*;

public class Farm extends model.Map
{
    public Farm(MapTypes farmType) {
        this.mapType = farmType;

        // Load map data and dimensions
        int[] dims = new int[2];
        this.mapData = MapLoader.loadMap(farmType.getMapPath(), dims);

        if (mapData == null) {
            throw new IllegalStateException("Failed to load farm map: " + farmType.getName());
        }

        this.WIDTH = dims[0];
        this.HEIGHT = dims[1];
        this.tiles = new Tile[HEIGHT][WIDTH];

        // Initialize all tiles as LAND
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                tiles[y][x] = new Tile(new Point(y, x));
                tiles[y][x].setType(TileTexture.LAND);
            }
        }

        applyMap();
        setRandomItems();
        this.startingPoint = getRandomFreeTile().getPoint();
    }

    public Tile getRandomFreeTile()
    {
        while (true)
        {
            int y = (int) (Math.random() * HEIGHT);
            int x = (int) (Math.random() * WIDTH);
            Tile tile = tiles[y][x];
            if ((tile.getTexture() == TileTexture.LAND) || (tile.getTexture() == TileTexture.GRASS))
            {
                return tile;
            }
        }
    }

    public ArrayList<Tile> getFreeTiles()
    {
        ArrayList<Tile> freeTiles = new ArrayList<>();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                if ((tile.getTexture() == TileTexture.LAND) || (tile.getTexture() == TileTexture.GRASS))
                {
                    freeTiles.add(tile);
                }
            }
        }
        return freeTiles;
    }

    private static <T extends Enum<T>> T randomItem(Class<T> clazz)
    {
        T[] constants = clazz.getEnumConstants();
        return constants[new java.util.Random().nextInt(constants.length)];
    }

    private void setRandomItems()
    {
        int randomItemsCount = getFreeTiles().size() / 10; //TODO: used some hard coded constants here

        for (int i = 0; i < randomItemsCount / 3; i++)
        {
            Tile random = getRandomFreeTile();
            TreeType type = randomItem(TreeType.class);
            random.setObject(new Tree(type));
        }

        for (int i = 0; i < randomItemsCount / 3; i++)
        {
            Tile random = getRandomFreeTile();
            ResourceItem type = randomItem(ResourceItem.class);
            random.setObject(new Resource(type));
        }

        for (int i = 0; i < randomItemsCount / 3; i++)
        {
            Tile random = getRandomFreeTile();
            ForagingTreeType type = randomItem(ForagingTreeType.class);
            random.setObject(new ForagingTree(type));
        }
    }

    public MapTypes getMapType()
    {
        return mapType;
    }

    public void setRandomForagingItems()
    {
        ArrayList<Tile> freeTiles = getFreeTiles();
        for (Tile tile : freeTiles)
        {
            if (Math.random() < 0.01)
            {
                if (Math.random() < 0.5)
                {
                    ForagingCropType type = randomItem(ForagingCropType.class);
                    tile.setObject(new ForagingCrop(type));
                } else if (tile.isPloughed())
                {
                    ForagingSeedType type = randomItem(ForagingSeedType.class);
                    tile.setObject(new ForagingSeed(type));
                }
            }
        }
    }

    public ArrayList<Plant> getPlants()
    {
        ArrayList<Plant> plants = new ArrayList<>();

        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                Tile tile = getTile(i, j);
                if (tile.getObject() != null)
                {
                    if (tile.getObject() instanceof Tree)
                    {
                        plants.add((Tree) tile.getObject());
                    } else if (tile.getObject() instanceof Crop)
                    {
                        plants.add((Crop) tile.getObject());
                    }
                }
            }
        }

        return plants;
    }
}
