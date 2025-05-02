package model;

import model.enums.FarmTypes;
import model.enums.TileTexture;
import model.enums.resources_enums.*;
import model.resources.ForagingCrop;
import model.resources.ForagingSeed;
import model.resources.ForagingTree;
import model.resources.Tree;

import java.util.*;
import java.util.Map;

public class Farm {
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
                tiles[y][x] = new Tile(new Point(y, x));
                tiles[y][x].setType(TileTexture.LAND);
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
                if (isInBounds(p.getY(), p.getX())) {
                    tiles[p.getY()][p.getX()].setType(texture);
                }
            }
        }
    }

    private boolean isInBounds(int y, int x)
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

    public Tile getTile(int y, int x)
    {
        if (isInBounds(y, x))
        {
            return tiles[y][x];
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
                    switch (tile.getTexture())
                    {
                        case CABIN:
                            output.append(Color.RED + "CC" + Color.RESET);
                            break;
                        case LAND:
                            if (tile.getObject() == null)
                            {
                                output.append(Color.YELLOW + "LL" + Color.RESET);
                            } else
                            {
                                switch (tile.getObject())
                                {
                                    case Tree _: output.append(Color.DARK_GREEN + "TT" + Color.RESET); break;
                                    case ForagingCrop _ : output.append(Color.BROWN + "FC" + Color.RESET); break;
                                    case ForagingSeed _ : output.append(Color.DARK_GREEN + "FS" + Color.RESET); break;
                                    case ForagingTree _ : output.append(Color.DARK_GREEN + "FT" + Color.RESET); break;
                                    case Resource _ : output.append(Color.DARK_GREY + "RR" + Color.RESET); break;
                                    default: output.append(Color.YELLOW + "LL" + Color.RESET); break;
                                }
                            }
                            break;
                        case LAKE:
                            output.append(Color.BLUE + "OO" + Color.RESET);
                            break;
                        case QUARRY:
                            output.append(Color.DARK_GREY + "QQ" + Color.RESET);
                            break;
                        case GREEN_HOUSE:
                            output.append(Color.DARK_GREEN + "GG" + Color.RESET);
                            break;
                        case GRASS:
                            if (tile.getObject() == null)
                            {
                                output.append(Color.GREEN + "GG" + Color.RESET);
                            } else
                            {
                                switch (tile.getObject())
                                {
                                    case Tree _ : output.append(Color.DARK_GREEN + "TT" + Color.RESET); break;
                                    case ForagingCrop _ : output.append(Color.BROWN + "FC" + Color.RESET); break;
                                    case ForagingSeed _ : output.append(Color.DARK_GREEN + "FS" + Color.RESET); break;
                                    case ForagingTree _ : output.append(Color.DARK_GREEN + "FT" + Color.RESET); break;
                                    case Resource _ : output.append(Color.DARK_GREY + "RR" + Color.RESET); break;
                                    default: output.append(Color.GREEN + "GG" + Color.RESET); break;
                                }
                            }
                            break;
                        default:
                            output.append(Color.RED + "##" + Color.RESET);
                            break;
                    }
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

    private int getFreeTilesNum()
    {
        int count = 0;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile tile = tiles[y][x];
                if ((tile.getTexture() == TileTexture.LAND) || (tile.getTexture() == TileTexture.GRASS))
                {
                    count += 1;
                }
            }
        }
        return count;
    }

    private static <T extends Enum<T>> T randomItem(Class<T> clazz)
    {
        T[] constants = clazz.getEnumConstants();
        return constants[new java.util.Random().nextInt(constants.length)];
    }

    private void setRandomItems()
    {
        int randomItemsCount = getFreeTilesNum() / 8; //TODO: used some hard coded constants here

        for (int i = 0; i < randomItemsCount / 5; i++)
        {
            Tile random = getRandomFreeTile();
            TreeType type = randomItem(TreeType.class);
            random.setObject(new Tree(type));
        }

        for (int i = 0; i < randomItemsCount / 5; i++)
        {
            Tile random = getRandomFreeTile();
            ResourceItem type = randomItem(ResourceItem.class);
            random.setObject(new Resource(type));
        }

        for (int i = 0; i < randomItemsCount / 5; i++)
        {
            Tile random = getRandomFreeTile();
            ForagingCropType type = randomItem(ForagingCropType.class);
            random.setObject(new ForagingCrop(type));
        }

        for (int i = 0; i < randomItemsCount / 5; i++)
        {
            Tile random = getRandomFreeTile();
            ForagingSeedType type = randomItem(ForagingSeedType.class);
            random.setObject(new ForagingSeed(type));
        }

        for (int i = 0; i < randomItemsCount / 5; i++)
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
                case Tree _, Resource _ -> false;
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
            int dx1 = path.get(i-1).getX() - path.get(i-2).getX();
            int dy1 = path.get(i-1).getY() - path.get(i-2).getY();
            int dx2 = path.get(i).getX() - path.get(i-1).getX();
            int dy2 = path.get(i).getY() - path.get(i-1).getY();

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
}
