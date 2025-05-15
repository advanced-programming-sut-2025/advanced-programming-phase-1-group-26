package control;

import model.*;
import model.animal.Animal;
import model.enums.*;
import model.enums.resources_enums.CropType;
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

import java.util.regex.Matcher;

public class GameController
{
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
                currentPlayer.getCurrentTool().getToolType().getName());
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

    public Result toolsUse(Matcher matcher) {
        String direction = matcher.group("direction");
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Tile targetTile = App.getCurrentGame().getTileFromDirection(direction);

        if (targetTile == null) {
            return new Result(false, "you didn't choose a valid direction");
        }
        Tool tool = App.getCurrentGame().getCurrentPlayer().getCurrentTool();
        if (tool == null) {
            return new Result (false, "you don't have any tool equipped");
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
                    if (currentPlayer.getTurnEnergy() <= (int)(weatherModifier * ((Axe) tool).getLevel().getBaseEnergyUsage())) {
                        return new Result(false, "you don't have enough energy");
                    }
                    currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Axe) tool).getLevel().getBaseEnergyUsage()));
                    currentPlayer.addToInventory(ResourceItem.WOOD.getType(), 3);
                    GameMenu.println("3 units of wood added to inventory.");
                    if (targetTile.getObject() instanceof Tree) {
                        if (((Tree) targetTile.getObject()).getTreeType().equals(TreeType.MAPLE_TREE)) {
                            Tree tree = (Tree) targetTile.getObject();
                            tree.harvest();
                            currentPlayer.addToInventory(new GameObject(GameObjectType.MAPLE_SYRUP, 3));
                            GameMenu.println("3 units of wood added to inventory.");
                        } else if (((Tree) targetTile.getObject()).getTreeType().equals(TreeType.MYSTIC_TREE)) {
                            Tree tree = (Tree) targetTile.getObject();
                            tree.harvest();
                            currentPlayer.addToInventory(new GameObject(GameObjectType.MYSTIC_SYRUP, 3));
                        }
                    }
                    targetTile.setObject(null);
                    return new Result(true, "axe used successfully");
                } else {
                    currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Axe) tool).getLevel().getFailedEnergyUsage()));
                    return new Result(false, "you can't use axe on this tile");
                }
            } else if (tool instanceof Hoe) {
                if (!targetTile.getTexture().equals(TileTexture.LAND) && !targetTile.getTexture().equals(TileTexture.GRASS))
                {
                    currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Hoe) tool).getLevel().getFailedEnergyUsage()));
                    return new Result(false, "you can't use hoe on this tile");
                }
                if (targetTile.getObject() == null && !targetTile.isPloughed()) {
                    if (currentPlayer.getTurnEnergy() <= (int)(weatherModifier * -((Hoe) tool).getLevel().getFailedEnergyUsage())) {
                        return new Result(false, "you don't have enough energy");
                    }
                    targetTile.plough();
                    currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Hoe) tool).getLevel().getBaseEnergyUsage()));
                    return new Result(true, "hoe used");
                }
            } else if (tool instanceof MilkPail) {
                Animal animal = null;
                //TODO:

            } else if (tool instanceof Pickaxe) {
                if (targetTile.getObject() instanceof ForagingMineral) {
                    if (currentPlayer.getTurnEnergy() <= (int)(weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage())){
                        return new Result(false, "you don't have enough energy");
                    }
                    currentPlayer.addToInventory(targetTile.getObject());
                    currentPlayer.increaseTurnEnergy(-((Pickaxe) tool).getLevel().getBaseEnergyUsage());
                    currentPlayer.getGashtogozarSkill().changeUnit(5);
                    targetTile.setObject(null);
                    return new Result(true, "pickaxe used on mineral");
                } else if (targetTile.getTexture().equals(TileTexture.QUARRY) &&
                targetTile.getObject() == null) {
                    if (currentPlayer.getTurnEnergy() <= (int)(weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage())){
                        return new Result(false, "you don't have enough energy");
                    }
                    if (currentPlayer.getMiningSkill().getLevel() > 1) {
                        currentPlayer.addToInventory(ResourceItem.STONE.getType(), 5);
                    } else {
                        currentPlayer.addToInventory(ResourceItem.STONE.getType(), 3);
                    }
                    currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage()));
                    currentPlayer.getMiningSkill().changeUnit(10);
                    return new Result(true, "pickaxe used on query");
                } else if (targetTile.getTexture().equals(TileTexture.LAND)) {
                    if (currentPlayer.getTurnEnergy() <= (int)(weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage())){
                        return new Result(false, "you don't have enough energy");
                    }
                    if (targetTile.isPloughed()) {
                        if (targetTile.hasPlants()) {
                            targetTile.unPlant();
                        }
                        targetTile.ploghInverse();
                        currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage()));
                        return new Result(true, "tile is not ploughed anymore");
                    } else {
                        if (currentPlayer.getTurnEnergy() <= (int)(weatherModifier * -((Pickaxe) tool).getLevel().getFailedEnergyUsage())){
                            return new Result(false, "you don't have enough energy");
                        }
                        currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Pickaxe) tool).getLevel().getFailedEnergyUsage()));
                        return new Result(false, "tile is not ploughed");
                    }
                } else if (targetTile.getObject() instanceof  ForagingSeed) {
                    targetTile.setObject(null);
                    currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Pickaxe) tool).getLevel().getBaseEnergyUsage()));
                    return new Result(true, "seed is removed");
                } else if (!targetTile.hasPlants() && targetTile.getObject() != null)
                {
                    GameObject object = targetTile.getObject();
                    Player player = App.getCurrentGame().getCurrentPlayer();
                    if (!player.inventoryHasCapacity())
                    {
                        return new Result(false, "you don't have enough space in your inventory");
                    }
                    player.addToInventory(object);
                    targetTile.setObject(null);
                    return new Result(true, object.getObjectType().toString() + " added to your inventory");
                }
                else {
                    currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Pickaxe) tool).getLevel().getFailedEnergyUsage()));
                    return new Result(false, "you can't use pickaxe on this tile");
                }
            } else if (tool instanceof Seythe)
            {
                if (currentPlayer.getTurnEnergy() <= (int)(weatherModifier * -((Seythe) tool).getEnergyUsage())){
                    return new Result(false, "you don't have enough energy");
                }
                currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((Seythe) tool).getEnergyUsage()));

                if (targetTile.getTexture().equals(TileTexture.GRASS) &&
                targetTile.getObject() == null) {
                    targetTile.setTexture(TileTexture.LAND);
                    return new Result(true, "alaf is harzed successfully");
                }

                if (!targetTile.hasPlants())
                {
                    return new Result(false, "There are no plants in this tile :(");
                }

                Plant plant = (Plant) targetTile.getObject();
                if (!plant.canHarvest())
                {
                    return new Result(false, "You can't harvest this plant yet :(");
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
                    currentPlayer.getFarmingSkill().changeUnit(5);
                    return new Result(true, "you harvested one " + fruit.getObjectType().toString() + ".");
                } else if (plant instanceof Crop)
                {
                    boolean giant = false;

                    Crop crop = (Crop) plant;

                    if (plant instanceof GiantCrop)
                    {
                        giant = true;
                        GameObject cropResult = new GameObject(crop.getCropType().getType(), 10);
                        currentPlayer.addToInventory(cropResult);
                    } else
                    {
                        GameObject cropResult = new GameObject(crop.getCropType().getType(), 1);
                        currentPlayer.addToInventory(cropResult);
                    }

                    currentPlayer.getFarmingSkill().changeUnit(5);
                    if (crop.harvest())
                    {
                        targetTile.unPlant();
                    }

                    return new Result(true, "you harvested " + (giant ? "ten " : "one ") + crop.getObjectType().toString() + ".");
                }

            } else if (tool instanceof Shear) {

                //TODO:

            } else if (tool instanceof WateringCan) {
                if (currentPlayer.getTurnEnergy() < (int)(weatherModifier * -((WateringCan) tool).getLevel().getBaseEnergyUsage())){
                    return new Result(false, "you don't have enough energy");
                }
                currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((WateringCan) tool).getLevel().getBaseEnergyUsage()));
                if (targetTile.getTexture().equals(TileTexture.LAND) ||
                        targetTile.getTexture().equals(TileTexture.GRASS)) {
                    if (targetTile.hasPlants()) {
                        Plant plant = (Plant) targetTile.getObject();

                        if (plant.hasBeenWateredToday()) {
                            return new Result(true, "You have already watered this plant today.");
                        }

                        if (((WateringCan) tool).getCurrentVolume() == 0) {
                            return new Result(true, "You should refill your watering can.");
                        }

                        ((WateringCan) tool).decreaseVolume(1);
                        plant.water();
                        return new Result(true, "Plant has been watered.");
                    } else if (!targetTile.hasPlants()) {
                        return new Result(false, "There are no plants in this tile.");
                    }
                } else if (targetTile.getTexture().equals(TileTexture.LAKE))
                {
                    WateringCan can = (WateringCan) tool;
                    if (can.getCurrentVolume() == can.getLevel().getCapacity())
                    {
                        return new Result(true, "Watering can is already full.");
                    }
                    can.addVolume(5);
                    return new Result(true, "added water into watering can.");
                } else
                {
                    return new Result(false, "you can't use watering can on this type of tile.");
                }
            } else if (tool instanceof FishingPole) {
                if (targetTile.getTexture().equals(TileTexture.LAKE)) {
                    //TODO: fishing
                }
                currentPlayer.increaseTurnEnergy((int)(weatherModifier * -((FishingPole) tool).getLevel().getBaseEnergyUsage()));
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

        if (!tile.isFertilized())
        {
            return new Result(false, "This tile is not fertilized.");
        }

        if (!tile.getTexture().equals(TileTexture.LAND) && !tile.getTexture().equals(TileTexture.GRASS))
        {
            return new Result(false, "You can't plant in this type of tile.");
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
        TreeType treeType = TreeType.getTreeBySeed(seedType);

        if (cropType != null)
        {
            Crop crop = new Crop(cropType, tile);
            tile.setObject(crop);

            Tile rootTile;
            boolean giant = false;

            if ((rootTile = crop.canBecomeGiant(0)) != null)
            {
                giant = crop.becomeGiant(rootTile);
            } else if ((rootTile = crop.canBecomeGiant(1)) != null)
            {
                giant = crop.becomeGiant(rootTile);
            } else if ((rootTile = crop.canBecomeGiant(2)) != null)
            {
                giant = crop.becomeGiant(rootTile);
            } else if ((rootTile = crop.canBecomeGiant(3)) != null)
            {
                giant = crop.becomeGiant(rootTile);
            }

            if (!giant)
            {
                if (player.isInGreenHouse())
                {
                    crop.putInGreenhouse();
                }
            }

            player.removeAmountFromInventory(seed.getObjectType(), 1);

            if (giant)
            {
                GameMenu.println("Successfully planted " + cropType.getName() + ".");
                return new Result(true, "Agi magi, crops just became giant.");
            } else
            {
                return new Result(true, "Successfully planted " + cropType.getName() + ".");
            }
        }

        if (treeType != null)
        {
            Tree tree = new Tree(treeType, tile);
            tile.setObject(tree);
            if (player.isInGreenHouse())
            {
                tree.putInGreenhouse();
            }
            player.removeAmountFromInventory(tree.getObjectType(), 1);
            return new Result(true, "Successfully planted " + treeType.getName() + ".");
        }

        return new Result(false, "invalid seed");
    }

    public Result fertilize(String fertilizerName, String direction)
    {
        Player player = App.getCurrentGame().getCurrentPlayer();

        GameObjectType fertilizerType = GameObjectType.getGameObjectType(fertilizerName);
        if (fertilizerType == null)
        {
            return new Result(false, "There is no such kind of fertilizer.");
        }

        GameObject fertilizer = player.getItemInInventory(fertilizerType);
        if (fertilizer == null)
        {
            return new Result(false, "You don't have this kind of fertilizer in your inventory.");
        }

        Tile tile = App.getCurrentGame().getTileFromDirection(direction);
        if (tile == null)
        {
            return new Result(false, "Tile with this path does not exist.");
        }

        if (!tile.isPloughed())
        {
            return new Result(false, "You must first use the Hoe to plough this tile.");
        }

        if (tile.isFertilized())
        {
            return new Result(false, "This tile has already been fertilized.");
        }

        if (fertilizer.getObjectType() == GameObjectType.FERTILIZER)
        {
            tile.fertilize();
            tile.setWateringChance(0);
            return new Result(true, "Tile fertilized with fertilizer.");
        }

        if (fertilizer.getObjectType() == GameObjectType.SPECIAL_FERTILIZER)
        {
            tile.fertilize();
            tile.setWateringChance(0);
            tile.setGrowFaster();
            return new Result(true, "Tile fertilized with special fertilizer.");
        }

        if (fertilizer.getObjectType() == GameObjectType.BASIC_RETAINING_SOIL)
        {
            tile.fertilize();
            tile.setWateringChance(40);
            return new Result(true, "Tile fertilized with basic retaining soil.");
        }

        if (fertilizer.getObjectType() == GameObjectType.QUALITY_RETAINING_SOIL)
        {
            tile.fertilize();
            tile.setWateringChance(70);
            return new Result(true, "Tile fertilized with quality retaining soil.");
        }

        if (fertilizer.getObjectType() == GameObjectType.DELUXE_RETAINING_SOIL)
        {
            tile.fertilize();
            tile.setWateringChance(100);
            return new Result(true, "Tile fertilized with deluxe retaining soil.");
        }

        return new Result(false, "There is no such kind of fertilizer.");
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
            player.increaseTurnEnergy(-1 * requiredEnergy);
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
            player.increaseTurnEnergy(-1 * requiredEnergy);
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
            player.increaseTurnEnergy(-1 * requiredEnergy);
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

    public Result showMoney() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        return new Result(true, "your money: " + player.getMoney());
    }

    public Result cheatToolCheck(Matcher matcher) {
        String direction = matcher.group("direction");
        Tile targetTile = App.getCurrentGame().getTileFromDirection(direction);
        if (targetTile == null) {
            return new Result(false, "wrong tile");
        } else if (targetTile.getObject() == null) {
            return new Result(false, "no object " + "texture: "+ targetTile.getTexture());
        }
        return new Result(true, "object: " + targetTile.getObject().getObjectType().name() +
                "texture: " + targetTile.getTexture());
    }
}
