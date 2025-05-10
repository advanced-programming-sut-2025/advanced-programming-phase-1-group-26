package model;

import model.enums.FarmTypes;
import model.enums.TileTexture;
import model.enums.resources_enums.*;
import model.resources.*;

import java.util.*;
import java.util.Map;

public class Farm extends model.Map
{
    private final int height = 70;
    private final int width = 70;

    private final FarmTypes farmType;
    private final Map<String, List<Point>> mapData;
    private Tile[][] tiles;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

    private final Point startingPoint;

    public Farm(FarmTypes farmType)
    {
        this.farmType = farmType;
        this.tiles = new Tile[WIDTH][HEIGHT];

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                tiles[x][y] = new Tile(new Point(y, x));
                tiles[x][y].setType(TileTexture.LAND);
            }
        }

        this.mapData = MapLoader.loadMap(farmType.getMapPath());
        if (mapData == null)
        {
            throw new IllegalStateException("Failed to load farm map: " + farmType.getName());
        }

        applyMap();
        setRandomItems();
        this.startingPoint = getRandomFreeTile().getPoint();
    }

    private void applyMap()
    {
        for (String typeName : mapData.keySet())
        {
            TileTexture texture = mapTypeNameToTexture(typeName);
            if (texture == null) continue;

            List<Point> points = mapData.get(typeName);
            for (Point p : points)
            {
                if (isInBounds(p.getY(), p.getX()))
                {
                    tiles[p.getX()][p.getY()].setType(texture);
                }
            }
        }
    }

    public boolean isInBounds(int x, int y)
    {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private TileTexture mapTypeNameToTexture(String typeName)
    {
        return switch (typeName.toLowerCase())
        {
            case "lake" -> TileTexture.LAKE;
            case "grass" -> TileTexture.GRASS;
            case "cabin" -> TileTexture.CABIN;
            case "greenhouse" -> TileTexture.GREEN_HOUSE;
            case "quarry" -> TileTexture.QUARRY;
            default -> null;
        };
    }

    public Tile getTile(int x, int y)
    {
        if (isInBounds(x, y))
        {
            return tiles[x][y];
        }
        return null;
    }

    public String getMapString(Point location, int length, int width)
    {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < length; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (isInBounds(y, x))
                {
                    Tile tile = tiles[y][x];
                    output.append(tile.getAppearance());
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    private Tile getRandomFreeTile()
    {
        while (true)
        {
            int y = (int) (Math.random() * height);
            int x = (int) (Math.random() * width);
            Tile tile = tiles[y][x];
            if ((tile.getTexture() == TileTexture.LAND) || (tile.getTexture() == TileTexture.GRASS))
            {
                return tile;
            }
        }
    }

    private ArrayList<Tile> getFreeTiles()
    {
        ArrayList<Tile> freeTiles = new ArrayList<>();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
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

    private boolean isWalkable(Tile tile)
    {
        if (tile.getTexture() == TileTexture.LAKE ||
                tile.getTexture() == TileTexture.GREEN_HOUSE ||
                tile.getTexture() == TileTexture.CABIN)
        {
            return false;
        }

        if (tile.getObject() != null)
        {
            return switch (tile.getObject())
            {
                case Tree a -> false;
                case Resource b -> false;
                default -> true;
            };
        }

        return true;
    }

    public ArrayList<Point> getNeighbors(Point p)
    {
        ArrayList<Point> neighbors = new ArrayList<>();
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};

        for (int dir = 0; dir < 8; dir++)
        {
            int newX = p.getX() + dx[dir];
            int newY = p.getY() + dy[dir];

            if (isInBounds(newY, newX) && isWalkable(getTile(newY, newX)))
            {
                neighbors.add(new Point(newY, newX));
            }
        }

        return neighbors;
    }

    public ArrayList<Point> findShortestPath(Point from, Point to)
    {
        if (!isInBounds(from.getY(), from.getX()) || !isInBounds(to.getY(), to.getX()))
        {
            return null;
        }

        Tile startTile = getTile(from.getY(), from.getX());
        Tile endTile = getTile(to.getY(), to.getX());

        if (!isWalkable(startTile) || !isWalkable(endTile))
        {
            return null;
        }

        Queue<ArrayList<Point>> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();

        ArrayList<Point> startPath = new ArrayList<>();
        startPath.add(from);
        queue.add(startPath);
        visited.add(from);

        while (!queue.isEmpty())
        {
            ArrayList<Point> path = queue.poll();
            Point current = path.getLast();

            if (current.equals(to))
            {
                return path;
            }

            for (Point neighbor : getNeighbors(current))
            {
                if (!visited.contains(neighbor))
                {
                    visited.add(neighbor);
                    ArrayList<Point> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return null;
    }

    public int calculateEnergy(Point from, Point to)
    {
        List<Point> path = findShortestPath(from, to);
        if (path == null) return -1;

        int distance = path.size() - 1;
        int turns = countTurns(path);

        return (distance + (10 * turns)) / 20;
    }

    private int countTurns(List<Point> path)
    {
        if (path.size() < 3) return 0;

        int turns = 0;
        for (int i = 2; i < path.size(); i++)
        {
            int dx1 = path.get(i - 1).getX() - path.get(i - 2).getX();
            int dy1 = path.get(i - 1).getY() - path.get(i - 2).getY();
            int dx2 = path.get(i).getX() - path.get(i - 1).getX();
            int dy2 = path.get(i).getY() - path.get(i - 1).getY();

            if (dx1 != dx2 || dy1 != dy2)
            {
                turns += 1;
            }
        }
        return turns;
    }

    public Point findFurthestAvailablePoint(Point origin, Point destination, int availableEnergy)
    {
        ArrayList<Point> path = findShortestPath(origin, destination);

        if (path == null || path.isEmpty()) return null;

        Point lastReachable = path.getFirst();

        int usedEnergy = 0;

        for (int i = 1; i < path.size(); i++)
        {
            Point prev = path.get(i - 1);
            Point curr = path.get(i);

            int dx = curr.getX() - prev.getX();
            int dy = curr.getY() - prev.getY();

            usedEnergy += 1;

            if (i >= 2)
            {
                Point beforePrev = path.get(i - 2);
                int oldDx = prev.getX() - beforePrev.getX();
                int oldDy = prev.getY() - beforePrev.getY();

                if (dx != oldDx || dy != oldDy)
                {
                    usedEnergy += 10;
                }
            }

            int totalEnergyCost = usedEnergy / 20;

            if (totalEnergyCost > availableEnergy)
            {
                break;
            } else
            {
                lastReachable = curr;
            }
        }

        return lastReachable;
    }

    public Point getStartingPoint()
    {
        return startingPoint;
    }

    public FarmTypes getFarmType()
    {
        return farmType;
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

    public String showAround(Point location)
    {
        StringBuilder output = new StringBuilder();

        int x = location.getX();
        int y = location.getY();

        for (int i = 3; i >= -3; i--)
        {
            for (int j = -3; j <= 3; j++)
            {
                if (isInBounds(x + j, y + i))
                {
                    Tile tile = getTile(x + j, y + i);
                    output.append(tile.getAppearance());
                }
            }
            output.append("\n");
        }

        return output.toString();
    }
}
