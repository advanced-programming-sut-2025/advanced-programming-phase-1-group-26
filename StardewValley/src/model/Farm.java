package model;

import model.enums.FarmTypes;
import model.enums.TileTexture;
import model.enums.resources_enums.*;
import model.resources.ForagingCrop;
import model.resources.ForagingSeed;
import model.resources.ForagingTree;
import model.resources.Tree;
import model.tools.Tool;

import java.util.List;
import java.util.Map;

public class Farm {
    private final int height = 70;
    private final int width = 70;

    private final FarmTypes farmType;
    private Map<String, List<Point>> mapData;
    private Tile[][] tiles;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

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
                                    case Tree a : output.append(Color.DARK_GREEN + "TT" + Color.RESET); break;
                                    case ForagingCrop a : output.append(Color.BROWN + "FC" + Color.RESET); break;
                                    case ForagingSeed a : output.append(Color.DARK_GREEN + "FS" + Color.RESET); break;
                                    case ForagingTree a : output.append(Color.DARK_GREEN + "FT" + Color.RESET); break;
                                    case Resource a : output.append(Color.DARK_GREY + "RR" + Color.RESET); break;
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
                                    case Tree a : output.append(Color.DARK_GREEN + "TT" + Color.RESET); break;
                                    case ForagingCrop a : output.append(Color.BROWN + "FC" + Color.RESET); break;
                                    case ForagingSeed a : output.append(Color.DARK_GREEN + "FS" + Color.RESET); break;
                                    case ForagingTree a : output.append(Color.DARK_GREEN + "FT" + Color.RESET); break;
                                    case Resource a : output.append(Color.DARK_GREY + "RR" + Color.RESET); break;
                                    default: output.append(Color.GREEN + "GG" + Color.RESET); break;
                                }
                            }
                            break;
                        default:
                            output.append(Color.RED + "##" + Color.RESET);
                            break;
                    }
            }
            output.append("\n");
        }
        return output.toString();
    }

    public Tile getRandomFreeTile()
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

    public int getFreeTilesNum()
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

    public static <T extends Enum<T>> T randomItem(Class<T> clazz)
    {
        T[] constants = clazz.getEnumConstants();
        return constants[new java.util.Random().nextInt(constants.length)];
    }


    public void setRandomItems()
    {
        int randomItemsCount = getFreeTilesNum() / 8;

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
}
