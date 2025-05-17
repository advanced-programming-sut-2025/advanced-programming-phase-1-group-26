package model.resources;

import model.App;
import model.Map;
import model.Tile;
import model.enums.resources_enums.CropType;

import java.util.ArrayList;

public class GiantCrop extends Crop
{
    private final Tile rootTile;
    private final ArrayList<Tile> tiles;

    public GiantCrop(CropType cropType, Tile rootTile)
    {
        super(cropType, rootTile);
        super.setHasStarted();
        this.rootTile = rootTile;
        this.tiles = get2x2Tiles(rootTile);
    }

    @Override
    public boolean isGiant()
    {
        return true;
    }

    public static ArrayList<Tile> get2x2Tiles(Tile rootTile)
    {
        Map map = App.getCurrentGame().getCurrentPlayer().getCurrentMap();

        int x = rootTile.getX();
        int y = rootTile.getY();

        ArrayList<Tile> tiles = new ArrayList<>();

        for (int dy = 0; dy <= 1; dy++)
        {
            for (int dx = 0; dx <= 1; dx++)
            {
                int nx = x + dx;
                int ny = y + dy;

                if (!map.isInBounds(nx, ny))
                {
                    return null;
                }

                Tile t = map.getTile(nx, ny);
                if (t == null)
                {
                    return null;
                }

                tiles.add(t);
            }
        }

        return tiles;
    }

    public Tile getRootTile()
    {
        return rootTile;
    }
}
