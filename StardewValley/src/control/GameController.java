package control;

import model.*;
import model.animal.Animal;
import model.enums.*;
import model.enums.resources_enums.CropType;
import model.enums.resources_enums.ForagingSeedType;
import model.enums.resources_enums.ResourceItem;
import model.enums.resources_enums.TreeType;
import model.enums.resources_enums.*;
import model.enums.tool_enums.ToolType;
import model.resources.Crop;

import model.resources.ForagingMineral;
import model.resources.Plant;
import model.resources.Tree;
import model.resources.*;

import model.tools.Tool;

import model.tools.*;
import view.GameMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController
{
    public Result energyShow() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.getTurnEnergy() == -1)
            return new Result(true, "Your Energy is unlimited!");
        return new Result(true,
                "Your Energy: " + currentPlayer.getEnergy() +
                "\nthis turn energy: " + currentPlayer.getTurnEnergy());
    }

    public Result energySet(Matcher matcher) {
        int value = Integer.parseInt(matcher.group("value"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (value < 1) {
            return new Result(false,"you should set your energy to a positive number!");
        }
        currentPlayer.setEnergy(value);
        currentPlayer.setTurnEnergy(value);
        return new Result(true,"your energy set to : " + currentPlayer.getTurnEnergy());
    }

    public Result energyUnlimited() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        currentPlayer.setTurnEnergy(-1); //might change later
        currentPlayer.setEnergy(-1);
        return new Result(true,"your energy is now unlimited eshghohal");

    }

    public void inventoryShow() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        ArrayList<GameObject> inventory = new ArrayList<>(currentPlayer.getCurrentBackPack().getInventory());
        System.out.println("your items:");
        System.out.println("----");
        for (GameObject object : inventory) {
            System.out.println(object.getObjectType().name() + " x" + object.getNumber());
            System.out.println("----");
        }
    }

    public Result inventoryTrash(Matcher matcher) {
        String name = matcher.group("name");
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        GameObject object = null;
        for (GameObject gameObject : currentPlayer.getCurrentBackPack().getInventory()) {
            if (gameObject.getObjectType().name().equals(name)) {
                object = gameObject;
            }
        }

        if (object == null) {
            return new Result(false, "you don't have this item in your inventory!");
        }

        int number = object.getNumber();
        try {
            number = Integer.parseInt(matcher.group("number"));
        } catch (Exception ignored) {}

        object.addNumber(-number);
        if (object.getNumber() < 1) currentPlayer.getCurrentBackPack().getInventory().remove(object);
        return new Result(true, "item deleted successfully");
    }

    public Result toolsEquip(Matcher matcher) {
        String toolName = matcher.group("name");
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        for (GameObject object : currentPlayer.getCurrentBackPack().getInventory()) {
            if (object instanceof Tool) {
                if (((Tool)object).getName().equalsIgnoreCase(toolName)) {
                    currentPlayer.setCurrentTool((Tool) object);
                    return new Result(true, "tool set successfully");
                }
            }
        }

        return new Result(false, "you don't have this tool in your inventory");
    }

    public Result toolsShowCurrent() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.getCurrentTool() == null) {
            return new Result(false, "you don't have any tool right now");
        }

        return new Result(true, "your current tool: " +
                currentPlayer.getCurrentTool().getType().getName());
    }

    public void toolsShowAvailable() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        System.out.println("your tools: ");
        for (GameObject object : currentPlayer.getCurrentBackPack().getInventory()) {
            if (object instanceof Tool) {
                System.out.println("tool: " + ((Tool) object).getName());
            }
        }
    }

    public Result toolsUpgrade(Matcher matcher) { //TODO: add later
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        String toolName = matcher.group("toolName");
        //check if in blacksmith

        for (GameObject object : currentPlayer.getCurrentBackPack().getInventory()) {
            if (object.getObjectType().name().equals(toolName)) {
                if (object instanceof Tool) {
                    if (object instanceof Axe) {

                    }
                } else {
                    return new Result(false, "pick a valid tool name");
                }

            }
        }
        return new Result(true, "");
    }

    public Result toolsUse(Matcher matcher) { // might change to enum direction
        String direction = matcher.group("direction");
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Tile targetTile = App.getCurrentGame().getTileFromDirection(direction);

        if (targetTile == null) {
            return new Result(false, "you didn't choose a valid direction");
        }
        Tool tool = App.getCurrentGame().getCurrentPlayer().getCurrentTool();
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
                if (targetTile.getObject() instanceof Tree ||
                        targetTile.getObject() instanceof ForagingTree ||
                        targetTile.getObject().getObjectType().equals(ResourceItem.WOOD.getType())) {
                    currentPlayer.addToInventory(ResourceItem.WOOD.getType(), 3);
                    if (targetTile.getObject() instanceof Tree) {
                        if (((Tree) targetTile.getObject()).getTreeType().equals(TreeType.MAPLE_TREE)) {
                            currentPlayer.addToInventory(new GameObject(GameObjectType.MAPLE_SYRUP, 3));
                        } else if (((Tree) targetTile.getObject()).getTreeType().equals(TreeType.MYSTIC_TREE)) {
                            currentPlayer.addToInventory(new GameObject(GameObjectType.MYSTIC_SYRUP, 3));
                        }
                    }
                    currentPlayer.increaseEnergy(-((Axe) tool).getLevel().getBaseEnergyUsage());
                    return new Result(true, "axe used");
                } else {
                    currentPlayer.increaseEnergy(-((Axe) tool).getLevel().getFailedEnergyUsage());
                    return new Result(false, "you can't use axe on this tile");
                }
            } else if (tool instanceof Hoe) {
                if (targetTile.getObject() == null && !targetTile.isPloughed()) {
                    targetTile.plough();
                    currentPlayer.increaseTurnEnergy(-((Hoe) tool).getLevel().getBaseEnergyUsage());
                    return new Result(true, "hoe used");
                } else {
                    currentPlayer.increaseTurnEnergy(-((Hoe) tool).getLevel().getFailedEnergyUsage());
                    return new Result(false, "you can't use hoe on this tile");
                }
            } else if (tool instanceof MilkPail) {
                Animal animal = null;
                //TODO:

            } else if (tool instanceof Pickaxe) {
                if (targetTile.getObject() instanceof ForagingMineral) {
                    currentPlayer.addToInventory(targetTile.getObject());
                    currentPlayer.increaseEnergy(-((Pickaxe) tool).getLevel().getBaseEnergyUsage());
                    currentPlayer.getGashtogozarSkill().addUnit(5);
                    return new Result(true, "pickaxe used on mineral");
                } else if (targetTile.getTexture().equals(TileTexture.QUARRY)) {
                    if (currentPlayer.getMiningSkill().getLevel() > 1) {
                        currentPlayer.addToInventory(ResourceItem.STONE.getType(), 5);
                    } else {
                        currentPlayer.addToInventory(ResourceItem.STONE.getType(), 3);
                    }
                    currentPlayer.increaseEnergy(-((Pickaxe) tool).getLevel().getBaseEnergyUsage());
                    currentPlayer.getMiningSkill().addUnit(10);
                    return new Result(true, "pickaxe used on querry");
                } else if (targetTile.getTexture().equals(TileTexture.LAND)) {
                    if (targetTile.isPloughed()) {
                        targetTile.ploghInverse();
                        currentPlayer.increaseEnergy(-((Pickaxe) tool).getLevel().getBaseEnergyUsage());
                        return new Result(true, "tile is not ploughed anymore");
                    } else {
                        currentPlayer.increaseEnergy(-((Pickaxe) tool).getLevel().getFailedEnergyUsage());
                        return new Result(false, "tile is not ploughed");
                    }
                } else if (targetTile.getObject() instanceof  ForagingSeed) {
                    targetTile.setObject(null);
                    currentPlayer.increaseEnergy(-((Pickaxe) tool).getLevel().getBaseEnergyUsage());
                    return new Result(true, "seed is removed");
                }
                else {
                    currentPlayer.increaseEnergy(-((Pickaxe) tool).getLevel().getFailedEnergyUsage());
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
                    if (tree.getTreeType().equals(TreeType.MAPLE_TREE) ||
                    tree.getTreeType().equals(TreeType.MYSTIC_TREE)) {
                        return new Result(false, "you should use axe for this tree");
                    }
                    GameObject fruit = new GameObject(tree.getFruit().getType(), 1);
                    currentPlayer.addToInventory(fruit);
                    tree.harvest();
                    currentPlayer.getFarmingSkill().addUnit(5);
                    return new Result(true, "you harvested one " + fruit.getObjectType().name() + ".");
                } else if (plant instanceof Crop)
                {
                    Crop crop = (Crop) plant;
                    GameObject cropResult = new GameObject(crop.getCropType().getType(), 1);
                    currentPlayer.addToInventory(cropResult);
                    currentPlayer.getFarmingSkill().addUnit(5);
                    if (crop.harvest())
                    {
                        targetTile.unPlant();
                    }

                    return new Result(true, "you harvested one " + crop.getObjectType().name() + ".");
                }

            } else if (tool instanceof Shear) {
                //doesn't have level
                //TODO:

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
                    ((WateringCan) tool).addVolume(5);
                }
            } else if (tool instanceof FishingPole) {
                if (targetTile.getTexture().equals(TileTexture.LAKE)) {
                    //TODO: fishing
                }
                currentPlayer.increaseEnergy(-((FishingPole) tool).getLevel().getBaseEnergyUsage());
            }
            currentPlayer.checkEnergy();
        }

        return new Result(true, "WE SHOULD CHANGE THIS PART OF CODE!!!");
    }

    public Result showCraftInfo(String craftName)
    {
        CropType cropType = CropType.getCropType(craftName);
        TreeType treeType = TreeType.getTreeType(craftName);
        ForagingCropType foragingCropType = ForagingCropType.getForagingCropType(craftName);
        ForagingTreeType foragingTreeType = ForagingTreeType.getForagingTreeType(craftName);

        if (cropType == null && treeType == null && foragingCropType == null && foragingTreeType == null)
        {
            return new Result(false, "Item with name " + craftName + " does not exist.");
        }

        if (cropType != null)
        {
            return new Result(true, cropType.getCraftInfo());
        }

        if (treeType != null)
        {
            return new Result(true, treeType.getCraftInfo());
        }

        if (foragingCropType != null)
        {
            return new Result(true, foragingCropType.getCraftInfo());
        }

        if (foragingTreeType != null)
        {
            return new Result(true, foragingTreeType.getCraftInfo());
        }

        return new Result(false, "ERROR");
    }

    public Result plantSeed(String seedName, String direction)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObjectType seedType = GameObjectType.getGameObjectType(seedName);
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
            return new Result(false, "This tile is not ploughed.");
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

        CropType cropType = CropType.getCropBySeed(seedType);
        TreeType treeType = TreeType.getTreeType(seedName);

        if (cropType != null)
        {
            Crop crop = new Crop(cropType);
            tile.setObject(crop);
            if (player.isInGreenHouse())
            {
                crop.putInGreenhouse();
            }
            player.removeFromInventory(seed);
            return new Result(true, "Successfully planted " + cropType.getName() + ".");
        }

        if (treeType != null)
        {
            Tree tree = new Tree(treeType);
            tile.setObject(tree);
            if (player.isInGreenHouse())
            {
                tree.putInGreenhouse();
            }
            player.removeFromInventory(tree);
            return new Result(true, "Successfully planted " + treeType.getName() + ".");
        }

        return new Result(false, "invalid seed");
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

    public Result goToPlace(String placeName)
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (!player.isInFarm())
        {
            return new Result(false, "invalid command");
        }

        if (placeName.equalsIgnoreCase("cabin") || placeName.equalsIgnoreCase("home"))
        {
            Point cabinDoor = player.getCabin().getStartingPoint();
            int requiredEnergy = player.getCurrentMap().calculateEnergy(player.getLocation(), cabinDoor);

            if (!player.hasEnoughEnergy(requiredEnergy))
            {
                return new Result(false, "You don't have enough energy, you're stuck here.");
            }

            player.goToCabin();
            App.setCurrentMenu(Menu.HomeMenu);
            player.increaseEnergy(-1 * requiredEnergy);
            return new Result(true, "Going to cabin...");
        } else if (placeName.equalsIgnoreCase("greenhouse") ||
                placeName.equalsIgnoreCase("green house"))
        {
            GreenHouse greenHouse = player.getGreenHouse();
            if (!greenHouse.isBuilt())
            {
                return new Result(false, "You haven't built the greenhouse yet. You can't enter it.");
            }

            Point greenHouseDoor = player.getGreenHouse().getStartingPoint();
            int requiredEnergy = player.getCurrentMap().calculateEnergy(player.getLocation(), greenHouseDoor);

            if (!player.hasEnoughEnergy(requiredEnergy))
            {
                return new Result(false, "You don't have enough energy, you're stuck here.");
            }

            player.goToGreenHouse();
            player.increaseEnergy(-1 * requiredEnergy);
            return new Result(false, "Going to green house...");
        } else if (placeName.equalsIgnoreCase("city"))
        {
            Point cabinDoor = player.getCabin().getStartingPoint();
            int requiredEnergy = player.getCurrentMap().calculateEnergy(player.getLocation(), cabinDoor);

            if (!player.hasEnoughEnergy(requiredEnergy))
            {
                return new Result(false, "You don't have enough energy, you're stuck here.");
            }

            player.goToCity();
            App.setCurrentMenu(Menu.CityMenu);
            player.increaseEnergy(-1 * requiredEnergy);
            return new Result(true, "Going to city...");
        }

        return new Result(false, "invalid place name");
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

    public Result cheatAddMoney(String amount)
    {
        int money = Integer.parseInt(amount);
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.increaseMoney(money);

        return new Result(true, "Congrats Thief. You just stole $ " + money + " from bank.");
    }

    public Result showPlant(String inputX, String inputY)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);

        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();

        if (!map.isInBounds(x, y))
        {
            return new Result(false, "invalid bounds");
        }

        Tile tile = map.getTile(x, y);
        if (tile == null)
        {
            return new Result(false, "invalid tile");
        }

        if (!tile.hasPlants())
        {
            return new Result(false, "There is no plant on this tile.");
        }

        GameObject object = tile.getObject();
        if (object == null || !(object instanceof Plant))
        {
            return new Result(false, "There is no plant on this tile.");
        }

        Plant plant = (Plant) object;
        return new Result(true, plant.showDetails(plant, tile));
    }
}
