package control;

import model.*;
import model.animal.Animal;
import model.enums.*;
import model.enums.resources_enums.CropType;
import model.enums.resources_enums.ForagingSeedType;
import model.enums.resources_enums.ResourceItem;
import model.enums.resources_enums.TreeType;
import model.enums.tool_enums.ToolType;
import model.resources.*;

import model.tools.Tool;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import model.tools.*;
import view.GameMenu;

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
        } else {
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
                currentPlayer.increaseEnergy(-((Seythe) tool).getEnergyUsage());

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
                currentPlayer.increaseEnergy(-((WateringCan) tool).getLevel().getBaseEnergyUsage());
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

    public Result showTime()
    {
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentTime().getHour()));
    }

    public Result showDate()
    {
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentTime().getDay()));
    }

    public Result cheatAdvanceTime(String timeAdvance)
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();
        int time = Integer.parseInt(timeAdvance);
        StringBuilder output = new StringBuilder();

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");

        gameTime.updateHour(time);

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");
        output.append("Time advanced successfully using cheat code :)");

        return new Result(true, output.toString());
    }

    public Result cheatAdvanceDate(String dateAdvance)
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();
        int date = Integer.parseInt(dateAdvance);
        StringBuilder output = new StringBuilder();

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");

        gameTime.updateDay(date);

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");
        output.append("Date advanced successfully using cheat code :)");

        return new Result(true, output.toString());
    }

    public Result showDateAndTime()
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();

        String output = "time: " + gameTime.getHour() +
                ", day: " + gameTime.getDay();

        return new Result(true, output);
    }

    public Result showDayOfWeek()
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();
        String dayName = DayOfWeek.getDayOfWeek((gameTime.getDay() - 1) % 7);

        return new Result(true, "Today is " + dayName + ".");
    }

    public Result showSeason()
    {
        Season season = App.getCurrentGame().getCurrentTime().getSeason();
        return new Result(true, "You're currently in " + season.toString() + ".");
    }

    public Result showWeather()
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();

        return new Result(true, "Today's weather is " + gameTime.getCurrentWeather() + ".");
    }

    public Result showTomorrowWeather()
    {
        Time gameTime = App.getCurrentGame().getCurrentTime();

        return new Result(true, "Tomorrow's weather would be " + gameTime.getTomorrowWeather() + ".");
    }

    public Result cheatChangeTomorrowWeather(String weatherType)
    {
        Weather weather = Weather.getWeather(weatherType);

        if (weather == null)
        {
            return new Result(false, "Invalid weather. Try again!");
        }

        Time gameTime = App.getCurrentGame().getCurrentTime();
        gameTime.setTomorrowWeather(weather);

        return new Result(true, "You're a Wizard!\nTomorrow's weather changed to " + weather + ".");
    }

    public Result cheatHitThunder(String inputX, String inputY)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);

        Game game = App.getCurrentGame();
        Tile tile = game.findTile(y, x);

        if (tile == null)
        {
            return new Result(false, "Tile with x: " + x + " and y: " + y + " does not exist.");
        }

        if (tile.isHitByThunder())
        {
            return new Result(true, "This unlucky tile has already been hit by thunder.");
        }

        tile.hitByThunder();

        return new Result(true, "By the might of Thor, son of Odin, this tile has been struck by lightning!");
    }

    public Result canWalk(String inputX, String inputY)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);

        Point destination = new Point(y, x);

        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Map map = player.getCurrentMap();

        int requiredEnergy = map.calculateEnergy(player.getLocation(), destination);
        int energy = player.getEnergy();

        if (requiredEnergy == -1)
        {
            return new Result(false, "You shall not pass!\n" +
                    "Choose your destination wisely.");
        }

        if (requiredEnergy <= energy)
        {
            return new Result(true, "positive\n" +
                    "\tRequired energy: " + requiredEnergy + "\n\tEnergy: " + energy + ".");
        }

        return new Result(false, "negative\n" +
                "\tRequired energy: " + requiredEnergy + "\n\tEnergy: " + energy + ".");
    }

    public Result walk(String inputX, String inputY, Scanner scanner)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);

        Point destination = new Point(y, x);

        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Map map = player.getCurrentMap();

        int requiredEnergy = map.calculateEnergy(player.getLocation(), destination);
        int energy = player.getEnergy();

        if (requiredEnergy < energy)
        {
            return new Result(false, "You do not have enough energy to get to this place :(");
        }

        if (requiredEnergy == energy)
        {
            GameMenu.println("You can get to this place, but you will faint right away.");
            GameMenu.println("Do you want to continue? (Y/N)");
            String input = GameMenu.scan(scanner);
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))
            {
                player.increaseEnergy(-requiredEnergy);
                player.setLocation(destination);
                return new Result(true, "You have successfully get to the destination.");
            }
            return new Result(false, "OK");
        }

        Point canGetTo = map.findFurthestAvailablePoint(player.getLocation(), destination, energy);

        GameMenu.println("You can't go all the way...");
        GameMenu.println("But we have a special offer for you: ");
        GameMenu.println("You can walk as much as you can, you would get closer to the destination.");
        GameMenu.println("Your new location will be (" + canGetTo.getY() + ", " + canGetTo.getX() + ").");
        GameMenu.println("Do you want to continue? (Y/N)");

        String input = GameMenu.scan(scanner);
        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))
        {
            player.increaseEnergy(-energy);
            player.setLocation(destination);
            return new Result(true, "You have successfully get to this place.");
        }
        return new Result(false, "OK");
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

    public Result exitGame(Scanner scanner)
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (!game.getOppenheimer().equals(player))
        {
            return new Result(false, "You are not Oppenheimer. You can not end this game.");
        }

        GameMenu.println("Are you sure? [y/n]");
        String answer = GameMenu.scan(scanner);

        if (!answer.equalsIgnoreCase("y"))
        {
            return new Result(false, "Phew! You got me scared for a moment.");
        }

        App.setCurrentGame(null);
        App.setCurrentUser(player.getUser());
        App.setCurrentMenu(Menu.LoginMenu);

        return new Result(true, """
                Soooo Loooong, gooood byeeeeeeeeeeeeeeee! (Do I really have to finish?)
                Redirecting to Login Menu...
                """);
    }

    public Result deleteGame(Scanner scanner)
    {
        int positive = 1;
        int negative = 0;

        for (Player player : App.getCurrentGame().getPlayers())
        {
            GameMenu.println("Hsssh! " + player.getUser().getNickname() + " is voting: ");
            if (!player.equals(App.getCurrentGame().getCurrentPlayer()))
            {
                do
                {
                    GameMenu.println("Do you vote for this game to be deleted? [y/n]");
                    String answer = GameMenu.scan(scanner);
                    if (answer.equalsIgnoreCase("y"))
                    {
                        positive += 1;
                        break;
                    } else if (answer.equalsIgnoreCase("n"))
                    {
                        negative += 1;
                        break;
                    } else
                    {
                        GameMenu.println("Please don't be such a dalghak, we're doing sth serious here.");
                    }
                } while (true);
            }
        }

        GameMenu.println("Election Results: (voting to end the game)");
        GameMenu.println("\tpositive: " + positive);
        GameMenu.println("\tnegative: " + negative);

        if (negative > 0)
        {
            GameMenu.println("You think you have democracy?");
            return new Result(false, "The game shall continue.");
        }

        App.setCurrentGame(null);
        App.setCurrentUser(App.getCurrentGame().getCurrentPlayer().getUser());
        App.setCurrentMenu(Menu.LoginMenu);

        return new Result(true, """
                Soooo Loooong, gooood byeeeeeeeeeeeeeeee! (Do I really have to finish?)
                Redirecting to Login Menu...
                """);
    }

    public void nextTurn()
    {
        Game game = App.getCurrentGame();
        game.nextTurn();
    }

    public Result forceNextTurn()
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        game.setCurrentPlayer(game.getNext(player));
        game.getCurrentPlayer().setEnergyToMax();
        return new Result(true, game.getCurrentPlayer().getUser().getNickname() + " is now playing.");
    }

    public Result goToCabin()
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.goToCabin();
        App.setCurrentMenu(Menu.HomeMenu);
        return new Result(true, "Going to cabin...");
    }

    public Result whoAmI()
    {
        return new Result(true, App.getCurrentGame().getCurrentPlayer().getUser().getNickname());
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

    public Result printMap(String inputX, String inputY, String inputSize)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);
        int size = Integer.parseInt(inputSize);
        Player player = App.getCurrentGame().getCurrentPlayer();
        return new Result(true, player.getCurrentMap().
                getMapString(player.getLocation(), new Point(x, y), size, size).trim());
    }

    public Result showAround()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        return new Result(true, map.showAround(player.getLocation()).trim());
    }

    public Result printEntireMap()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        return new Result(true,
                map.getMapString(player.getLocation(), new Point(0,0), map.getHEIGHT(), map.getWIDTH()).trim());
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
}
