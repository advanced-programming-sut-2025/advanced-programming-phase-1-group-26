package control;

import model.*;
import model.enums.*;
import model.enums.resources_enums.CropType;
import model.enums.resources_enums.ForagingSeedType;
import model.enums.resources_enums.TreeType;
import model.enums.tool_enums.ToolType;
import model.resources.Crop;

import model.resources.ForagingMineral;
import model.resources.Plant;
import model.resources.Tree;
import model.tools.Tool;

import model.tools.*;
import view.GameMenu;

import java.util.regex.Matcher;

public class GameController
{
    public Result toolsUse(Matcher matcher) { // might change to enum direction
        String direction = matcher.group("direction");
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Tile targetTile = App.getCurrentGame().getTileFromDirection(direction);

        if (targetTile == null) {
            return new Result(false, "you didn't choose a valid direction");
        }
        Tool tool = (Tool) App.getCurrentGame().getCurrentPlayer().getCurrentTool();
        if (tool == null) {
            GameMenu.println("you don't have any tool equipped");
        } else
        {
            Double weatherModifier = 1.0;
            Weather weather = App.getCurrentGame().getCurrentTime().getCurrentWeather();
            if (weather.equals(Weather.Rain) || weather.equals(Weather.Storm))
            {
                weatherModifier = 1.5;
            } else if (weather.equals(Weather.Snow))
            {
                weatherModifier = 2.0;
            }

            if (tool instanceof Axe) {
                if (targetTile.getObject() instanceof Tree) {
                    currentPlayer.increaseEnergy((int)(weatherModifier * -((Axe) tool).getLevel().getBaseEnergyUsage()));
                } else {
                    return new Result(false, "you can't use axe on this tile");
                }
            } else if (tool instanceof Hoe) {
                if (targetTile.getObject() == null && !targetTile.isPloughed()) {
                    targetTile.plough();
                    currentPlayer.increaseTurnEnergy(-((Hoe) tool).getLevel().getBaseEnergyUsage());
                } else {
                    return new Result(false, "you can't use hoe on this tile");
                }
            } else if (tool instanceof MilkPail) {
                //TODO: use near animal
                //doesn't have level

            } else if (tool instanceof Pickaxe) {
                if (targetTile.getObject() instanceof ForagingMineral) {
                    currentPlayer.increaseEnergy((int)(weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage()));
                } else if (targetTile.getObject() == null) {
                    currentPlayer.increaseEnergy((int) (weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage()));
                } //items on the tile
                else {
                    return new Result(false, "you can't use pickaxe on this tile");
                }
            } else if (tool instanceof Seythe)
            {
                currentPlayer.increaseEnergy((int)(weatherModifier * -((Seythe) tool).getEnergyUsage()));

                if (targetTile.getTexture().equals(TileTexture.GRASS) && targetTile.getObject() == null)
                {
                    targetTile.setType(TileTexture.LAND);
                    return new Result(true, "You can now plant seeds in this tile.");
                }

                if (!targetTile.hasPlants())
                {
                    return new Result(false, "There are no plants in this tile :(");
                }

                Plant plant = (Plant) targetTile.getObject();
                if (!plant.canHarvest())
                {
                    return new Result(false, "You can't harvest this tile yet :(");
                }

                if (plant instanceof Tree)
                {
                    Tree tree = (Tree) plant;
                    GameObject fruit = new GameObject(tree.getFruit().getType(), 1);
                    currentPlayer.addToInventory(fruit);
                    tree.harvest();
                    return new Result(true, "you harvested one " + fruit.getObjectType().name() + ".");
                } else if (plant instanceof Crop)
                {
                    Crop crop = (Crop) plant;
                    GameObject cropResult = new GameObject(crop.getCropType().getType(), 1);
                    currentPlayer.addToInventory(cropResult);

                    if (crop.harvest())
                    {
                        targetTile.unPlant();
                    }

                    return new Result(true, "you harvested one " + crop.getObjectType().name() + ".");
                }

            } else if (tool instanceof Shear) {
                //doesn't have level

            } else if (tool instanceof TrashCan) {
                currentPlayer.increaseEnergy((int)(weatherModifier * -((TrashCan) tool).getLevel().getBaseEnergyUsage()));

            } else if (tool instanceof WateringCan) {
                currentPlayer.increaseEnergy((int)(weatherModifier * -((WateringCan) tool).getLevel().getBaseEnergyUsage()));
                if (targetTile.hasPlants())
                {
                    Plant plant = (Plant) targetTile.getObject();
                    if (((WateringCan) tool).getCurrentVolume() == 0)
                    {
                        return new Result(true, "You should refill your watering can.");
                    }
                    ((WateringCan) tool).decreaseVolume(1);
                    plant.water();
                } else if (targetTile.getTexture().equals(TileTexture.LAKE))
                {
                    ((WateringCan) tool).addVolume(5); // TODO: HARD-CODED here, should change later
                }
            } else if (tool instanceof FishingPole) {

                currentPlayer.increaseEnergy((int)(weatherModifier * -((FishingPole) tool).getLevel().getBaseEnergyUsage()));
            } else if (tool instanceof BackPack) {
                //doesn't use energy
            }

            currentPlayer.checkEnergy();
        }

        return new Result(true, "WE SHOULD CHANGE THIS PART OF CODE!!!");
    }

    public Result showCraftInfo(String craftName)
    {
        CropType cropType = CropType.getCropType(craftName);
        TreeType treeType = TreeType.getTreeType(craftName);

        if (cropType == null && treeType == null)
        {
            return new Result(false, "Craft with name " + craftName + " does not exist.");
        }

        if (cropType != null)
        {
            return new Result(true, cropType.getCraftInfo());
        }

        if (treeType != null)
        {
            return new Result(true, treeType.getCraftInfo());
        }

        return new Result(false, "ERROR");
    }

    public Result plantSeed(String seedName, String direction)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        ForagingSeedType seedType = ForagingSeedType.getSeedType(seedName);
        if (seedType == null)
        {
            return new Result(false, "There's no such kind of seed.");
        }

        Tile tile = App.getCurrentGame().getTileFromDirection(direction);
        if (tile == null)
        {
            return new Result(false, "Tile with this path does not exist.");
        }

        if (!tile.isPloughed())
        {
            return new Result(false, "This tile is not ploughed :(");
        }

        if (tile.getObject() != null)
        {
            return new Result(false, "This tile is not empty.");
        }

        GameObject seed = player.findObjectType(seedType);
        if (seed == null)
        {
            return new Result(false, "You don't have this type of seed.");
        }

        CropType cropType = CropType.getCropFromSeed((ForagingSeedType) seed.getType());
        Crop crop = new Crop(cropType);

        player.removeFromInventory(seed);

        tile.setObject(crop);
        return new Result(true, "You have successfully planted " + cropType.getCraftInfo() + ".");
    }

    public Result fertilize(String fertilizerName, String direction)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObject fertilizer = player.getItemInInventory(GameObjectType.FERTILIZER);
        if (fertilizer == null)
        {
            return new Result(false, "You don't have any fertilizer :(");
        }

        Tile tile = App.getCurrentGame().getTileFromDirection(direction);
        if (tile == null)
        {
            return new Result(false, "Tile with this path does not exist.");
        }

        if (tile.isFertilized())
        {
            return new Result(false, "This tile has alreaady been fertilized.");
        }

        if (tile.getObject() == null || !tile.hasPlants())
        {
            return new Result(false, "There are no plants in this tile :(");
        }

        tile.fertilize();
        return new Result(true, "You have successfully fertilized " +
                "tile ( " + tile.getX() + ", " + tile.getY() + ".");
    }

    public Result howMuchWater()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        Tool tool = player.getTool(ToolType.WateringCan);
        if (tool == null)
        {
            return new Result(false, "What kind of gardener are you? You don't even have a watering can.");
        }

        WateringCan wateringCan = (WateringCan) tool;
        return new Result(true, "Your watering can currently has " + wateringCan.getCurrentVolume() + "" +
                " units of water.\n" +
                "Tip: You can refill it near tiles that contain water.");
    }

    public Result goToCabin()
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.goToCabin();
        App.setCurrentMenu(Menu.HomeMenu);
        return new Result(true, "Going to cabin...");
    }

    public Result helpReadMap()
    {
        StringBuilder help = new StringBuilder();

        help.append("📖 Reading the Map:\n");

        help.append("\n== Basic Tile Textures ==\n");
        help.append(Color.YELLOW).append("🟨 Unploughed Land").append(Color.RESET).append("\n");
        help.append(Color.BROWN).append("🟫 Ploughed Land").append(Color.RESET).append("\n");
        help.append(Color.BLUE).append("🌊 / 🟦 Lake / Water").append(Color.RESET).append("\n");
        help.append(Color.GREEN).append("🟩 Grass, Village Grass, or Floor").append(Color.RESET).append("\n");
        help.append(Color.LIGHT_GREY).append("🏠 Cabin").append(Color.RESET).append("\n");
        help.append(Color.CYAN).append("🪟 Greenhouse / Building").append(Color.RESET).append("\n");
        help.append(Color.DARK_GREY).append("🪨 Quarry (Rock)").append(Color.RESET).append("\n");
        help.append(Color.LIGHT_GREY).append("⬜ Fence").append(Color.RESET).append("\n");
        help.append(Color.BLUE).append("🔷 Road").append(Color.RESET).append("\n");
        help.append(Color.RED).append("🚪 Shop Door").append(Color.RESET).append("\n");
        help.append(Color.RED).append("🟥 City Board").append(Color.RESET).append("\n");
        help.append(Color.YELLOW).append("📚 Book").append(Color.RESET).append("\n");
        help.append(Color.YELLOW).append("💡 Lamp").append(Color.RESET).append("\n");
        help.append(Color.LIGHT_GREY).append("🛋️ Table").append(Color.RESET).append("\n");
        help.append(Color.CYAN).append("💻 Computer").append(Color.RESET).append("\n");
        help.append(Color.LIGHT_GREY).append("🛏️ Bed Tile").append(Color.RESET).append("\n");
        help.append(Color.LIGHT_GREY).append("🏬 Shop Floor").append(Color.RESET).append("\n");
        help.append(Color.YELLOW).append("🧠 NPC in Shop").append(Color.RESET).append("\n");
        help.append(Color.DARK_GREY).append("🧱 Cabin Wall / Wall").append(Color.RESET).append("\n");

        help.append("\n== Planted Objects ==\n");
        help.append(Color.GREEN).append("🌳 / 🌴 Tree").append(Color.RESET).append("\n");
        help.append(Color.LIME_GREEN).append("🌱 Crop").append(Color.RESET).append("\n");
        help.append(Color.OLIVE_GREEN).append("🌳 Foraging Crop / Tree / Seed").append(Color.RESET).append("\n");
        help.append(Color.DARK_GREY).append("🪨 Stone Resource").append(Color.RESET).append("\n");
        help.append(Color.BROWN).append("🪵 Wood Resource").append(Color.RESET).append("\n");

        help.append("\n== Other ==\n");
        help.append(Color.RESET).append("🤓 Current Player Location").append(Color.RESET).append("\n");
        help.append(Color.RED).append("🟥 Unknown/Error Tile").append(Color.RESET).append("\n");

        return new Result(true, help.toString().trim());
    }

    public Result buildGreenhouse()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GreenHouse greenhouse = player.getGreenHouse();
        if (greenhouse.isBuilt())
        {
            return new Result(false, """
                    You have already paid for the greenhouse.
                    Although I could've not told you this and get your money. (Is the grammar of this sentence correct?)
                   
                    """);
        }

        if (!player.canAffordGreenhouse())
        {
            return new Result(false, "You can't afford the greenhouse.\n" +
                    "You are poor :(");
        }

        greenhouse.build();
        return new Result(true, "Yippee! You successfully built a greenhouse.");
    }

    public Result showPlant()
    {
        return null; // TODO: add later
    }
}
