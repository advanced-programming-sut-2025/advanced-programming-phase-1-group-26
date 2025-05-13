package model;

import model.enums.TileTexture;
import model.enums.resources_enums.ResourceItem;
import model.resources.*;

public class Tile
{
    private final Point point;

    private boolean hitByThunder = false;
    private TileTexture texture = null;

    private GameObject object = null;
    private boolean isPloughed = false;
    private boolean isFertilized = false;

    private boolean isImmuneFromCrows = false;

    public Tile(Point point)
    {
        this.point = point;
    }

    public int getY()
    {
        return point.getY();
    }

    public int getX()
    {
        return point.getX();
    }

    public boolean isHitByThunder()
    {
        return hitByThunder;
    }

    public void hitByThunder()
    {
        hitByThunder = true;
    }

    public TileTexture getTexture()
    {
        return texture;
    }

    public void setType(TileTexture texture)
    {
        this.texture = texture;
    }

    public GameObject getObject()
    {
        return object;
    }

    public void setObject(GameObject object)
    {
        this.object = object;
    }

    public Point getPoint()
    {
        return point;
    }

    public void plough()
    {
        isPloughed = true;
    }

    public void ploghInverse() {
        isPloughed = false;
    }

    public boolean isPloughed()
    {
        return isPloughed;
    }

    public boolean isFertilized()
    {
        return isFertilized;
    }

    public void fertilize()
    {
        isFertilized = true;
    }

    public boolean hasPlants()
    {
        if (object != null)
        {
            if (object instanceof Tree || object instanceof Crop)
            {
                return true;
            }
        }

        return false;
    }

    public void unPlant()
    {
        object = null;
        isPloughed = false;
        isFertilized = false;
    }

    public String getAppearance()
    {
        // TODO: uncomment this later
//        if (App.getCurrentGame().getCurrentPlayer().getLocation().equals(point))
//        {
//            return "\uD83E\uDD13"; // nerd face
//        }

        if (object == null)
        {
            if (texture.equals(TileTexture.LAND))
            {
                if (isPloughed)
                {
                    return "\uD83D\uDFEB";
                } else
                {
                    return "\uD83D\uDFE8";
                }
            } else if (texture.equals(TileTexture.LAKE))
            {
                if (Math.random() < 0.5)
                {
                    return "\uD83C\uDF0A"; // wave emoji
                } else
                {
                    return "\uD83D\uDFE6";
                }
            } else if (texture.equals(TileTexture.GRASS))
            {
                return "\uD83D\uDFE9";
            } else if (texture.equals(TileTexture.CABIN))
            {
                return "\uD83C\uDFE0"; // maybe (?)
            } else if (texture.equals(TileTexture.GREEN_HOUSE))
            {
                return "\uD83E\uDE9F"; // glass emoji, maybe change later
            } else if (texture.equals(TileTexture.QUARRY))
            {
                return "\uD83E\uDEA8"; // rock emoji
            } else if (texture.equals(TileTexture.CABIN_WALL) || texture.equals(TileTexture.GREEN_HOUSE_WALL) ||
            texture.equals(TileTexture.WALL))
            {
                return "\uD83D\uDFEB";
            } else if (texture.equals(TileTexture.CABIN_INTERIOR_FLOOR) || texture.equals(TileTexture.FLOOR))
            {
                return "\uD83D\uDFE9";
            } else if (texture.equals(TileTexture.GREEN_HOUSE_FLOOR))
            {
                return "\uD83D\uDFE9";
            } else if (texture.equals(TileTexture.GREEN_HOUSE_WOOD))
            {
                return "\uD83D\uDFE7";
            } else if (texture.equals(TileTexture.BED_TILE))
            {
                return "\uD83D\uDFE6";
            } else if (texture.equals(TileTexture.DECOR_TILE))
            {
                return "\uD83D\uDFEA";
            } else if (texture.equals(TileTexture.VILLAGE_GRASS))
            {
                return "\uD83D\uDFE9";
            } else if (texture.equals(TileTexture.ROAD))
            {
                return "\uD83D\uDD33";
            } else if (texture.equals(TileTexture.FENCE))
            {
                return "⬜";
            } else if (texture.equals(TileTexture.BUILDING))
            {
                return "\uD83E\uDE9F";
            } else if (texture.equals(TileTexture.SHOP_DOOR))
            {
                return "\uD83D\uDEAA";
            } else if (texture.equals(TileTexture.CITY_BOARD))
            {
                return "\uD83D\uDFE5";
            } else if (texture.equals(TileTexture.GARDEN))
            {
                return "\uD83D\uDFE9";
            } else if (texture.equals(TileTexture.TREE))
            {
                return "\uD83C\uDF34";
            } else if (texture.equals(TileTexture.BOOK))
            {
                return "\uD83D\uDCDA";
            } else if (texture.equals(TileTexture.LAMP))
            {
                return "\uD83D\uDCA1";
            } else if (texture.equals(TileTexture.TABLE))
            {
                return "\uD83E\uDEB4";
            }else if (texture.equals(TileTexture.COMPUTER))
            {
                return "\uD83D\uDCBB";
            }else if (texture.equals(TileTexture.SHOP_BLACKSMITH) ||
                    texture.equals(TileTexture.SHOP_JOJAMART) ||
                    texture.equals(TileTexture.SHOP_SALOON) ||
                    texture.equals(TileTexture.SHOP_MARNIE) ||
                    texture.equals(TileTexture.SHOP_FISH) ||
                    texture.equals(TileTexture.SHOP_PIERRE) ||
                    texture.equals(TileTexture.SHOP_CARPENTER))
            {
                return "\uD83D\uDFE7";
            }else if (texture.equals(TileTexture.NPC_BLACKSMITH) ||
                    texture.equals(TileTexture.NPC_JOJAMART) ||
                    texture.equals(TileTexture.NPC_SALOON) ||
                    texture.equals(TileTexture.NPC_MARNIE) ||
                    texture.equals(TileTexture.NPC_FISH) ||
                    texture.equals(TileTexture.NPC_PIERRE) ||
                    texture.equals(TileTexture.NPC_CARPENTER))
            {
                return "\uD83E\uDD13";
            }
            else
            {
                return "\uD83D\uDFE5"; // ERROR
            }
        } else
        {
            if (object instanceof Tree)
            {
                if (Math.random() < 0.5)
                {
                    return "\uD83C\uDF32"; // tree emoji type 1
                } else
                {
                    return "\uD83C\uDF33"; // tree emoji type 2
                }
            } else if (object instanceof Crop)
            {
                return "\uD83C\uDF31"; // seed emoji
            } else if (object instanceof ForagingCrop || object instanceof ForagingSeed || object instanceof ForagingTree)
            {
                return "\uD83C\uDF32";
            } else if (object instanceof Resource)
            {
                Resource r = (Resource) object;
                if (r.getResourceType().equals(ResourceItem.STONE))
                {
                    return "\uD83E\uDEA8"; // rock emoji
                } else if (r.getResourceType().equals(ResourceItem.WOOD))
                {
                    return "\uD83E\uDEB5"; // wood emoji
                }
            } else
            {
                return "\uD83D\uDFE5"; // ERROR
            }
        }

        return "\uD83D\uDFE5"; // ERROR
    }

    public boolean isImmuneFromCrows()
    {
        return isImmuneFromCrows;
    }

    public void makeImmuneFromCrows()
    {
        isImmuneFromCrows = true;
    }

    public void setImmunityFromCrows()
    {
        isImmuneFromCrows = false;
    }
}
