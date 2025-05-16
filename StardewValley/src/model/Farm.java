package model;

import model.animal.Animal;
import model.animal.AnimalBuilding;
import model.animal.Fish;
import model.enums.MapTypes;
import model.enums.Season;
import model.enums.TileTexture;
import model.enums.animal_enums.AnimalType;
import model.enums.animal_enums.FarmAnimalsType;
import model.enums.animal_enums.FishType;
import model.enums.resources_enums.*;
import model.resources.*;

import java.util.*;

public class Farm extends model.Map
{
    private final Point homePoint;
    private ArrayList<Tile> lakeTiles = new ArrayList<>();
    private ArrayList<AnimalBuilding> animalBuildings = new ArrayList<>();

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
                tiles[y][x] = new Tile(new Point(x, y));
                tiles[y][x].setType(TileTexture.LAND);
            }
        }

        applyMap();
        setRandomItems();

        ArrayList<Tile> cabinNeighbors = getHouseNeighborTiles();
        Random rand = new Random();

        this.startingPoint = cabinNeighbors.get(rand.nextInt(cabinNeighbors.size())).getPoint();
        this.homePoint = startingPoint;

        this.lakeTiles = getLakeTiles();
        putFishInLake(Season.Spring);
    }

    public Tile getRandomFreeTile()
    {
        while (true)
        {
            int y = (int) (Math.random() * HEIGHT);
            int x = (int) (Math.random() * WIDTH);
            Tile tile = tiles[y][x];
            if ((tile.getTexture() == TileTexture.LAND) || (tile.getTexture() == TileTexture.GRASS) && tile.getObject() == null)
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
                if ((tile.getTexture() == TileTexture.LAND) || (tile.getTexture() == TileTexture.GRASS) && tile.getObject() == null)
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
        int randomItemsCount = getFreeTiles().size() / 50;

        for (int i = 0; i < randomItemsCount / 3; i++)
        {
            Tile random = getRandomFreeTile();
            random.setObject(new Resource(ResourceItem.STONE));
        }

        for (int i = 0; i < randomItemsCount / 3; i++)
        {
            Tile random = getRandomFreeTile();
            random.setObject(new Resource(ResourceItem.WOOD));
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
        if (foragingCount() < 200)
        {
            ArrayList<Tile> freeTiles = getFreeTiles();
            for (Tile tile : freeTiles)
            {
                if (Math.random() < 0.01 && !tile.isHitByThunder())
                {
                    if (Math.random() < 0.5)
                    {
                        ForagingCropType type = ForagingCropType.getRandomSeasonCrop(App.getCurrentGame().getCurrentTime().getSeason());
                        tile.setObject(new ForagingCrop(type));
                        tile.setRandomForaging(true);
                    } else if (tile.isPloughed())
                    {
                        ForagingSeedType type = ForagingSeedType.getRandomSeasonSeed(App.getCurrentGame().getCurrentTime().getSeason());
                        tile.setObject(new ForagingSeed(type));
                        tile.setRandomForaging(true);
                    }
                }
            }
        }
    }

    public void setRandomForagingMinerals()
    {
        if (foragingCount() < 200)
        {
            ArrayList<Tile> freeTiles = getFreeQuarryTiles();
            for (Tile tile : freeTiles)
            {
                if (Math.random() < 0.01)
                {
                    ForagingMineralType type = randomItem(ForagingMineralType.class);
                    tile.setObject(new ForagingMineral(type));
                    tile.setRandomForaging(true);
                }
            }
        }
    }

    public ArrayList<Tile> getFreeQuarryTiles()
    {
        ArrayList<Tile> quarryTiles = new ArrayList<>();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                if (tile.getObject() == null && tile.getTexture() == TileTexture.QUARRY)
                {
                    quarryTiles.add(tile);
                }
            }
        }
        return quarryTiles;
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

    public ArrayList<Tile> getThunderedTiles()
    {
        ArrayList<Tile> thunderedTiles = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                Tile tile = getTile(i, j);
                if (tile.isHitByThunder())
                {
                    thunderedTiles.add(tile);
                }
            }
        }
        return thunderedTiles;
    }

    private ArrayList<Tile> getHouseTiles()
    {
        ArrayList<Tile> houseNeighbors = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                Tile tile = getTile(i, j);
                if (tile.getTexture() == TileTexture.CABIN)
                {
                    houseNeighbors.add(tile);
                }
            }
        }
        return houseNeighbors;
    }

    private ArrayList<Tile> getHouseNeighborTiles()
    {
        ArrayList<Tile> houseNeighbors = getHouseTiles();
        ArrayList<Point> points = new ArrayList<>();

        for (Tile tile : houseNeighbors)
        {
            for (Point p : getNeighbors(tile.getPoint()))
            {
                if (!points.contains(p))
                {
                    Tile t = getTile(p.getX(), p.getY());
                    if (t.getTexture() == TileTexture.LAND || t.getTexture() == TileTexture.GRASS)
                    {
                        points.add(p);
                    }
                }
            }
        }

        ArrayList<Tile> neighbors = new ArrayList<>();
        for (Point p : points)
        {
            neighbors.add(getTile(p.getX(), p.getY()));
        }
        return neighbors;
    }

    public Point getHomePoint()
    {
        return homePoint;
    }

    private int foragingCount()
    {
        int count = 0;
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = getTile(x, y);
                if (tile.isRandomForaging())
                {
                    count += 1;
                }
            }
        }
        return count;
    }

    private ArrayList<Tile> getLakeTiles()
    {
        ArrayList<Tile> lakeTiles = new ArrayList<>();

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = getTile(x, y);
                if (tile.getTexture() == TileTexture.LAKE)
                {
                    lakeTiles.add(tile);
                }
            }
        }

        return lakeTiles;
    }

    public void putFishInLake(Season season)
    {
        Random random = new Random();
        int fishCount = random.nextInt(10) + 10 + (int)(0.2 * lakeTiles.size());

        ArrayList<Tile> randomLakeTiles = getRandomLakeTiles(fishCount);

        ArrayList<FishType> fishTypes = FishType.getOrdinaryFishTypes(season);

        for (Tile tile : randomLakeTiles)
        {
            FishType randomType = fishTypes.get(new Random().nextInt(fishTypes.size()));
            Fish fish = new Fish(randomType);
            tile.setFish(fish);
        }
    }

    public void putLegendaryFishInLake(Player player, Season currentSeason)
    {
        if (player.getFishingSkill().getLevel() >= 4)
        {
            Random random = new Random();
            ArrayList<Tile> randomLakeTiles = getRandomLakeTiles(random.nextInt(20));

            FishType legendaryType = FishType.getLegendaryFishType(currentSeason);

            for (Tile tile : randomLakeTiles)
            {
                Fish legend = new Fish(legendaryType);
                tile.setFish(legend);
            }
        }
    }

    public void resetFish()
    {
        for (Tile tile : lakeTiles)
        {
            tile.setFish(null);
        }
    }

    private ArrayList<Tile> getRandomLakeTiles(int count)
    {
        ArrayList<Tile> shuffled = new ArrayList<>(lakeTiles);
        Collections.shuffle(shuffled);

        int actualCount = Math.min(count, shuffled.size());
        return new ArrayList<>(shuffled.subList(0, actualCount));
    }

    public ArrayList<Tile> getListOfNearbyFish()
    {
        ArrayList<Tile> nearbyFish = new ArrayList<>();

        Point point = App.getCurrentGame().getCurrentPlayer().getLocation();
        int baseX = point.getX();
        int baseY = point.getY();

        for (int y = -2; y <= 2; y++)
        {
            for (int x = -2; x <= 2; x++)
            {
                if (isInBounds(baseX + x, baseY + y))
                {
                    Tile tile = getTile(baseX + x, baseY + y);
                    if (tile.getFish() != null)
                    {
                        nearbyFish.add(tile);
                    }
                }
            }
        }

        return nearbyFish;
    }

    private boolean isTileGoodForAnimalBuilding(Tile tile)
    {
        return (tile.getTexture() == TileTexture.GRASS || tile.getTexture() == TileTexture.LAND)
                && tile.getObject() == null;
    }

    public boolean isGoodForAnimalBuilding(Tile tile, int width, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            for (int x = 0; x <= width; x++)
            {
                if (!isTileGoodForAnimalBuilding(tile))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Tile> getStartTileForAnimalBuilding(int width, int height)
    {
        ArrayList<Tile> startTiles = new ArrayList<>();
        for (int y = 0; y < HEIGHT - height; y++)
        {
            for (int x = 0; x < WIDTH - width; x++)
            {
                Tile tile = getTile(x, y);
                if (isGoodForAnimalBuilding(tile, width, height))
                {
                    startTiles.add(tile);
                }
            }
        }
        return startTiles;
    }

    public void buildAnimalBuilding(AnimalBuilding building)
    {
        animalBuildings.add(building);
        for (Tile tile : building.getTiles())
        {
            tile.setTexture(TileTexture.ANIMAL_BUILDING);
        }
    }

    public AnimalBuilding getBuildingForAnimal(FarmAnimalsType animalType)
    {
        for (AnimalBuilding animalBuilding : animalBuildings)
        {
            if (animalType.getBuilding().contains(animalBuilding.getFarmBuildingType()))
            {
                if (animalBuilding.hasCapacity())
                {
                    return animalBuilding;
                }
            }
        }
        return null;
    }

    public ArrayList<AnimalBuilding> getAnimalBuildings()
    {
        return animalBuildings;
    }

    public AnimalBuilding getAnimalBuilding(Animal animal)
    {
        for (AnimalBuilding animalBuilding : animalBuildings)
        {
            if (animalBuilding.getAnimals().contains(animal))
            {
                return animalBuilding;
            }
        }
        return null;
    }
}
