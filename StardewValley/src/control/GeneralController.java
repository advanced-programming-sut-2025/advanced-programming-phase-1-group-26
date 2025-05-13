package control;

import model.*;
import model.enums.*;
import model.resources.*;
import model.tools.*;
import view.GameMenu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GeneralController
{
    /* player commands */
    public Result energyShow() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.getTurnEnergy() == -1)
            return new Result(true, "Your Energy is unlimited!");
        return new Result(true,
                "Your Energy: " + currentPlayer.getEnergy() +
                        "\nthis turn energy: " + currentPlayer.getTurnEnergy());
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
        String toolName = matcher.group("toolName");
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        for (GameObject object : currentPlayer.getCurrentBackPack().getInventory()) {
            if (object.getObjectType().name().equals(toolName)) {
                if (object instanceof Tool) {
                    currentPlayer.setCurrentTool((model.tools.Tool) object);
                    return new Result(true, "tool set successfully");
                } else {
                    return new Result(false, "this object is not a tool");
                }
            } else {
                return new Result(false, "this is not a proper tool type");
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
                currentPlayer.getCurrentTool().getObjectType().name());
    }

    public void toolsShowAvailable()
    {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        for (GameObject object : currentPlayer.getCurrentBackPack().getInventory()) {
            System.out.println(object.getObjectType().name());
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

    /* player cheat codes */
    public Result energySet(Matcher matcher) {
        int value = Integer.parseInt(matcher.group("value"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.getTurnEnergy() == -1) {
            return new Result(false, "your energy is unlimited yohahahahaha");
        } else if (value < 1) {
            return new Result(false,"you should set your energy to a positive number!");
        }
        currentPlayer.setTurnEnergy(value);
        return new Result(true,"your energy set to : " + currentPlayer.getTurnEnergy());
    }

    public Result energyUnlimited() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        currentPlayer.setTurnEnergy(-1); //might change later
        return new Result(true,"your energy is now unlimited eshghohal");

    }

    /* time and date commands */
    public Result showTime()
    {
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentTime().getHour()));
    }

    public Result showDate()
    {
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentTime().getDay()));
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

    /* time and date cheat codes */
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

        gameTime.updateDay(date, true);

        output.append("time: ").append(gameTime.getHour());
        output.append(", day: ").append(gameTime.getDay()).append("\n");
        output.append("Date advanced successfully using cheat code :)");

        return new Result(true, output.toString());
    }

    /* weather  commands*/
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

    /* weather cheat codes */
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

        Map map = App.getCurrentGame().getCurrentPlayer().getCurrentMap();
        Tile tile = map.getTile(x, y);

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

    /* basic map commands */
    public Result pwd()
    {
        StringBuilder output = new StringBuilder();

        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();

        if (map instanceof Farm)
        {
            output.append("myFarm/");
        } else if (map instanceof Cabin)
        {
            output.append("myCabin/");
        } else if (map instanceof GreenHouse)
        {
            output.append("myGreenhouse/");
        } else if (map instanceof City)
        {
            output.append("theCity/");
        }

        output.append("X=").append(player.getLocation().getX()).append("/Y=").append(player.getLocation().getY());
        return new Result(true, output.toString());
    }

    public Result showAround()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        return new Result(true, map.showAround(player.getLocation()));
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

    public Result printEntireMap()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();
        return new Result(true,
                map.getMapString(player.getLocation(), new Point(0,0), map.getHEIGHT(), map.getWIDTH()).trim());
    }

    /* walk commands */
    public Result canWalk(String inputX, String inputY)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);

        Point destination = new Point(x, y);

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

        if (player.hasEnoughEnergy(requiredEnergy))
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

        Point destination = new Point(x, y);

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

        if (!player.hasEnoughEnergy(requiredEnergy))
        {
            Point canGetTo = map.findFurthestAvailablePoint(player.getLocation(), destination, energy);

            GameMenu.println("You can't go all the way...");
            GameMenu.println("But we have a special offer for you: ");
            GameMenu.println("You can walk as much as you can, you would get closer to the destination.");
            GameMenu.println("Your new location will be (" + canGetTo.getY() + ", " + canGetTo.getX() + ").");
            GameMenu.println("Do you want to continue? [y/n]");

            String input = GameMenu.scan(scanner);
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))
            {
                player.increaseEnergy(-energy);
                player.setLocation(destination);
                return new Result(true, "You have successfully get to this place.");
            }
            return new Result(false, "OK");
        }

        if (player.getEnergy() == requiredEnergy)
        {
            GameMenu.println("You can get to this place, but you will faint right away.");
            GameMenu.println("Do you want to continue? [y/n]");
            String input = GameMenu.scan(scanner);
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))
            {
                player.increaseEnergy(-requiredEnergy);
                player.setLocation(destination);
                return new Result(true, "You have successfully get to the destination.");
            }
            return new Result(false, "OK");
        }

        player.setLocation(destination);
        player.increaseEnergy(-1 * requiredEnergy);
        return new Result(true, "You have successfully reached your destination.");
    }

    public Result showPath(String inputX, String inputY)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();

        if (!map.isInBounds(x, y))
        {
            return new Result(false, "Invalid destination coordinates.");
        }

        int energy = map.calculateEnergy(player.getLocation(), new Point(x, y));
        if (energy == -1)
        {
            return new Result(false, "There is no possible path to this point.");
        }

        return new Result(true, map.getMapWithPath(player.getLocation(), new Point(x, y)));
    }

    /* walk cheat code */
    public Result sudoCD(String inputX, String inputY)
    {
        int x = Integer.parseInt(inputX);
        int y = Integer.parseInt(inputY);
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map map = player.getCurrentMap();

        if (!map.isInBounds(x, y))
        {
            return new Result(false, "Invalid destination coordinates.");
        }

        Point destination = new Point(x, y);
        player.setLocation(destination);

        return new Result(true, "Teleported successfully.");
    }

    /* basic general commands */
    public Result whoAmI()
    {
        return new Result(true, App.getCurrentGame().getCurrentPlayer().getUser().getNickname());
    }

    public void nextTurn()
    {
        Game game = App.getCurrentGame();
        game.nextTurn();
    }

    public Result sudoNextTurn()
    {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        game.setCurrentPlayer(game.getNext(player));
        game.getCurrentPlayer().setEnergyToMax();
        return new Result(true, game.getCurrentPlayer().getUser().getNickname() + " is now playing.");
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

        if (answer.equalsIgnoreCase("n"))
        {
            return new Result(false, "Phew! You got me scared for a moment.");
        } else if (!answer.equalsIgnoreCase("y"))
        {
            return new Result(false, "Invalid input. Returning to game.");
        }

        App.setCurrentGame(null);
        App.setCurrentUser(player.getUser());
        App.setCurrentMenu(Menu.MainMenu);

        return new Result(true, """
                Soooo Loooong, gooood byeeeeeeeeeeeeeeee! (Do I really have to finish?)
                Redirecting to Main Menu...""");
    }

    public Result deleteGame(Scanner scanner)
    {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();

        int positive = 1;
        int negative = 0;

        for (Player player : App.getCurrentGame().getPlayers())
        {
            if (currentPlayer.equals(player))
            {
                GameMenu.println(player.getUser().getNickname() + " has voted positive.");
            } else
            {
                GameMenu.println("Hsssh! " + player.getUser().getNickname() + " is voting. ");
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
        }

        GameMenu.println("Election Results: (voting to end the game)");
        GameMenu.println("\tpositive: " + positive);
        GameMenu.println("\tnegative: " + negative);

        if (negative > 0)
        {
            GameMenu.println("You think you have democracy?");
            return new Result(false, "The game shall continue.");
        }

        for (Player player : App.getCurrentGame().getPlayers())
        {
            User user = player.getUser();
            user.setCurrentGame(null);
        }

        App.setCurrentUser(currentPlayer.getUser());
        App.setCurrentGame(null);
        App.setCurrentMenu(Menu.MainMenu);

        return new Result(true, """
                You broke my heart, good bye.
                Redirecting to Main Menu...""");
    }
}
