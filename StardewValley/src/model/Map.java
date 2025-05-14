package model;

import model.enums.MapTypes;
import model.enums.TileTexture;
import model.resources.Plant;
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
            return tiles[y][x];
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

    public String getMapString(Point heroLocation, Point location, int HEIGHT, int WIDTH)
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
            if (tile.getObject() instanceof Tree)
            {
                return false;
            } else if (tile.getObject() instanceof Resource)
            {
                return false;
            } else
            {
                return true;
            }
        }

        return true;
    }

    public ArrayList<Point> getNeighbors(Point p)
    {
        ArrayList<Point> neighbors = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int dir = 0; dir < 4; dir++)
        {
            int newX = p.getX() + dx[dir];
            int newY = p.getY() + dy[dir];

            if (isInBounds(newX, newY) && isWalkable(getTile(newX, newY)))
            {
                neighbors.add(new Point(newX, newY));
            }
        }

        return neighbors;
    }

    public ArrayList<Point> findShortestPath(Point from, Point to)
    {
        if (!isInBounds(from.getX(), from.getY()) || !isInBounds(to.getX(), to.getY()))
            return null;

        Tile startTile = getTile(from.getX(), from.getY());
        Tile endTile = getTile(to.getX(), to.getY());

        if (!isWalkable(startTile) || !isWalkable(endTile))
            return null;

        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        java.util.Map<Point, Point> parentMap = new java.util.HashMap<>();

        queue.add(from);
        visited.add(from);

        while (!queue.isEmpty())
        {
            Point current = queue.poll();

            if (current.equals(to))
                break;

            for (Point neighbor : getNeighbors(current))
            {
                if (!visited.contains(neighbor))
                {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Reconstruct path
        if (!parentMap.containsKey(to))
            return null;

        ArrayList<Point> path = new ArrayList<>();
        for (Point at = to; at != null; at = parentMap.get(at))
        {
            path.add(at);
        }

        Collections.reverse(path);
        return path;
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

        output.append("\n");

        int x = location.getX();
        int y = location.getY();

        // Column headers
        output.append(" "); // 2 spaces for top-left corner
        for (int j = -2; j <= 2; j++)
        {
            int col = x + j;
            if (isInBounds(col, y))
            {
                output.append(String.format("%02d", col));
            }
            else
            {
                output.append("  "); // 2 spaces for missing column label
            }
            output.append(" "); // space between columns (always)
        }
        output.append("\n");

        // Rows
        for (int i = -2; i <= 2; i++)
        {
            int row = y + i;

            if (isInBounds(x, row))
            {
                output.append(String.format("%02d", row)).append(" ");
            }
            else
            {
                output.append("   "); // 2 for missing label, 1 for space
            }

            for (int j = -2; j <= 2; j++)
            {
                int col = x + j;

                if (isInBounds(col, row))
                {
                    Tile tile = tiles[row][col];
                        output.append(tile.getAppearance());
                }
                else
                {
                    output.append("⬛");
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

    public String getMapWithPath(Point from, Point to)
    {
        ArrayList<Point> path = findShortestPath(from, to);

        StringBuilder output = new StringBuilder();

        for (int y = 0; y < this.HEIGHT; y++)
        {
            for (int x = 0; x < this.WIDTH; x++)
            {
                Point current = new Point(x, y);
                Tile tile = tiles[y][x];

                if (path != null && isInPath(path, current) && !current.equals(from))
                {
                    output.append("\uD83D\uDFE7"); // Path
                }
                else
                {
                    output.append(tile.getAppearance());
                }
            }
            output.append("\n");
        }

        return output.toString().trim();
    }

    public boolean isInPath(ArrayList<Point> path, Point point)
    {
        for (Point at : path)
        {
            if (at.equals(point))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Tile> getAllPlantTiles()
    {
        ArrayList<Tile> allPlantTiles = new ArrayList<>();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Tile tile = tiles[y][x];
                if (tile.getTexture().equals(TileTexture.LAND) || tile.getTexture().equals(TileTexture.GRASS))
                {
                    if (tile.getObject() != null && tile.getObject() instanceof Plant)
                    {
                        allPlantTiles.add(tile);
                    }
                }
            }
        }
        return allPlantTiles;
    }
}

