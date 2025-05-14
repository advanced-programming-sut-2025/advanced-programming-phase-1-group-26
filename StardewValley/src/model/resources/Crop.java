package model.resources;

import model.App;
import model.Map;
import model.Tile;
import model.enums.resources_enums.CropType;
import view.GameMenu;

import java.util.ArrayList;

public class Crop extends Plant
{
    private boolean oneTime;
    private int growthTime;
    private boolean canBecomeGiant;

    public Crop(CropType cropType, Tile tile)
    {
        this.type = cropType;
        this.name = cropType.getName();
        this.source = cropType.getSeedType();
        this.stages = cropType.getStages();
        this.totalHarvestTime = cropType.getTotalHarvestTime();
        this.oneTime = cropType.isOneTime();
        this.growthTime = cropType.getGrowthTime();
        this.baseSellPrice = cropType.getBaseSellPrice();
        this.isEdible = cropType.isEdible();
        this.energy = cropType.getEnergy();
        this.seasons = cropType.getSeasons();
        this.canBecomeGiant = cropType.isCanBecomeGiant();
        this.ObjectType = cropType.getType();

        this.harvestWaitTime = totalHarvestTime;
        this.tile = tile;
    }

    public String getName()
    {
        return this.name;
    }

    public CropType getCropType()
    {
        return (CropType) type;
    }

    public boolean harvest()
    {
        if (oneTime)
        {
            return true;
        }

        hasHarvested = true;
        lastHarvested = 0;
        harvestWaitTime = growthTime;

        return false;
    }

    public boolean isGiant()
    {
        return false;
    }

    public Tile canBecomeGiant(int pos)
    {
        Map map = App.getCurrentGame().getCurrentPlayer().getCurrentMap();

        int x = tile.getX();
        int y = tile.getY();

        int[][] offsets = switch (pos)
        {
            case 0 -> new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}};
            case 1 -> new int[][]{{-1, 0}, {0, 0}, {-1, 1}, {0, 1}};
            case 2 -> new int[][]{{0, -1}, {1, -1}, {0, 0}, {1, 0}};
            case 3 -> new int[][]{{-1, -1}, {0, -1}, {-1, 0}, {0, 0}};
            default -> null;
        };

        if (offsets == null)
        {
            return null;
        }

        Tile[] tiles = new Tile[4];
        for (int i = 0; i < 4; i++)
        {
            int tx = x + offsets[i][0];
            int ty = y + offsets[i][1];

            if (!map.isInBounds(tx, ty))
            {
                return null;
            }

            tiles[i] = map.getTile(tx, ty);

            if (tiles[i].getObject() == null)
            {
                return null;
            }

            if (!(tiles[i].getObject() instanceof Crop crop))
            {
                return null;
            }

            if (!crop.getCropType().isCanBecomeGiant())
            {
                return null;
            }

            if (!crop.getCropType().equals(((Crop) tile.getObject()).getCropType()))
            {
                return null;
            }
        }

        return map.getTile(x + offsets[0][0], y + offsets[0][1]); // root tile
    }

    public int getCurrentStage()
    {
        return super.currentStage;
    }

    public boolean becomeGiant(Tile rootTile)
    {
        ArrayList<Tile> tiles = GiantCrop.get2x2Tiles(rootTile);

        int maxGrowth = 0;
        int lastWatered = 1;

        for (Tile tile : tiles)
        {
            if (tile.getObject() instanceof Crop crop)
            {
                Crop cropCrop = (Crop) tile.getObject();

                if (cropCrop.getCurrentStage() > maxGrowth)
                {
                    maxGrowth = cropCrop.getCurrentStage();
                }

                if (cropCrop.getLastWatered() < lastWatered)
                {
                    lastWatered = cropCrop.getLastWatered();
                }
            }
        }

        GiantCrop giantCrop = new GiantCrop(getCropType(), rootTile);
        for (Tile tile : tiles)
        {
            tile.setObject(giantCrop);
        }

        return true;
    }
}
