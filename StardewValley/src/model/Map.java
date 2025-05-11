package model;

import model.enums.MapTypes;
import model.enums.TileTexture;
import model.resources.Tree;

import java.util.*;

public abstract class Map
{
    protected MapTypes mapType;
    protected Tile[][] tiles;
    protected int WIDTH, HEIGHT;
    protected Point startingPoint;
    protected java.util.Map<String, List<Point>> mapData;

    public Tile getTile(int x, int y)
    {
        if (isInBounds(x, y))
        {
            return tiles[x][y];
        }
        return null;
    }

    public boolean isInBounds(int x, int y)
    {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    public Point getStartingPoint()
    {
        return startingPoint;
    }

    protected void applyMap()
    {
        for (String typeName : mapData.keySet())
        {
            TileTexture texture = TileTexture.mapTypeNameToTexture(typeName);
            if (texture == null) continue;

            List<Point> points = mapData.get(typeName);
            for (Point p : points)
            {
                if (isInBounds(p.getX(), p.getY()))
                {
                    tiles[p.getY()][p.getX()].setType(texture);
                }
            }
        }
    }

    public String getMapString(Point location, int HEIGHT, int WIDTH)
    {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < this.HEIGHT; y++)
        {
            for (int x = 0; x < this.WIDTH; x++)
            {
                if (isInBounds(x, y))
                {
                    Tile tile = tiles[y][x];
                    output.append(tile.getAppearance());
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    protected boolean isWalkable(Tile tile)
    {
        if (tile.getTexture() == TileTexture.LAKE || // TODO: add some more types later
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

    protected int countTurns(List<Point> path)
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

    public int getWIDTH()
    {
        return WIDTH;
    }

    public int getHEIGHT()
    {
        return HEIGHT;
    }
}

