package model;

import model.enums.FarmTypes;
import model.enums.TileTexture;

import java.util.ArrayList;

public class Farm
{
    private final int height = 70;
    private final int width = 70;

    private final int farmId;
    private final FarmTypes type;

    private ArrayList<ArrayList<Tile>> map = new ArrayList<>();
    private ArrayList<Point> exitPoints = new ArrayList<>();

    public Farm(int farmId, FarmTypes farmType)
    {
        this.farmId = farmId;
        this.type = farmType;
    }

    public void initializeFarm()
    {
        makeFarm();

        makeInMap(type.getCabinPoint(), type.getCabinLength(), type.getCabinWidth(), TileTexture.CABIN);
        makeInMap(type.getFirstLakePoint(), type.getFirstLakeLength(), type.getFirstLakeWidth(), TileTexture.LAKE);
        if (type.getSecondLakePoint() != null)
        {
            makeInMap(type.getSecondLakePoint(), type.getSecondLakeLength(), type.getSecondLakeWidth(), TileTexture.LAKE);
        }
        makeInMap(type.getQuarryPoint(), type.getQuarryLength(), type.getQuarryWidth(), TileTexture.QUARRY);
        makeInMap(type.getGreenHousePoint(), type.getGreenHouseLength(), type.getGreenHouseWidth(), TileTexture.GREEN_HOUSE);

        exitPoints = new ArrayList<>(type.getExitPoints());
        setBorder();
        setPlainTiles();
    }

    private void makeFarm()
    {
        for (int y = 0; y < height; y++)
        {
            ArrayList<Tile> row = new ArrayList<>();
            for (int x = 0; x < width; x++)
            {
                row.add(new Tile(new Point(x, y)));
            }
            map.add(row);
        }
    }

    private void makeInMap(Point location, int length, int width, TileTexture texture)
    {
        for (int y = location.getY(); y < location.getY() + width; y++)
        {
            for (int x = location.getX(); x < location.getX() + length; x++)
            {
                Tile tile = map.get(y).get(x);
                tile.setTexture(texture);
            }
        }
    }

    public void setPlainTiles()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile tile = map.get(y).get(x);
                if (tile.getTexture() == null)
                {
                    tile.setTexture(TileTexture.LAND);
                }
            }
        }
    }

    public void setBorder()
    {
        for (int y = 0; y < height; y++)
        {
            map.get(y).get(0).setTexture(TileTexture.GRASS);
            map.get(y).get(1).setTexture(TileTexture.GRASS);
            map.get(y).get(width - 2).setTexture(TileTexture.GRASS);
            map.get(y).get(width - 1).setTexture(TileTexture.GRASS);
        }

        for (int x = 0; x < width; x++)
        {
            map.get(0).get(x).setTexture(TileTexture.GRASS);
            map.get(1).get(x).setTexture(TileTexture.GRASS);
            map.get(height - 2).get(x).setTexture(TileTexture.GRASS);
            map.get(height - 1).get(x).setTexture(TileTexture.GRASS);
        }

        for (int y = 0; y < 2; y++)
        {
            map.get(y).get(exitPoints.get(0).getX()).setTexture(TileTexture.LAND);
            map.get(height - 1 - y).get(exitPoints.get(1).getX()).setTexture(TileTexture.LAND);
        }
    }

    public Tile getRandomFreeTile()
    {
        while (true)
        {
            int y = (int) (Math.random() * height);
            int x = (int) (Math.random() * width);
            Tile tile = map.get(y).get(x);
            if (tile.getTexture() == TileTexture.LAND)
            {
                return tile;
            }
        }
    }

    public String getMapString(Point location, int length, int width)
    {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile tile = map.get(y).get(x);
                if (tile.getObject() == null)
                {
                    switch (tile.getTexture())
                    {
                        case CABIN:
                            output.append(Color.YELLOW + "CC" + Color.RESET);
                            break;
                        case LAND:
                            output.append(Color.BROWN + "LL" + Color.RESET);
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
                            output.append(Color.GREEN + "GG" + Color.RESET);
                            break;
                        default:
                            output.append(Color.RED + "##" + Color.RESET);
                            break;
                    }
                } else
                {

                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    public ArrayList<ArrayList<Tile>> getMap()
    {
        return map;
    }
}
