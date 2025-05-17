package control.game.activities;

import model.*;
import model.animal.Animal;
import model.animal.AnimalBuilding;
import model.animal.Fish;
import model.enums.*;
import model.enums.animal_enums.FarmAnimalsType;
import model.enums.animal_enums.FarmBuildingType;
import model.enums.animal_enums.FishType;
import model.enums.regex_enums.GameCommands;
import model.enums.tool_enums.FishingPoleLevel;
import model.tools.FishingPole;
import model.tools.Tool;
import view.CityMenu;
import view.GameMenu;
import view.HomeMenu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AnimalController
{
    public Result buildAnimalBuilding(String input)
    {
        String buildingName = GameCommands.BUILD_ANIMAL_HOUSE.getMatcher(input).group("name").trim();
        int x = Integer.parseInt(GameCommands.BUILD_ANIMAL_HOUSE.getMatcher(input).group("x").trim());
        int y = Integer.parseInt(GameCommands.BUILD_ANIMAL_HOUSE.getMatcher(input).group("y").trim());

        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = player.getFarm();
        City city = App.getCurrentGame().getCity();

        Tile targetTile = farm.getTile(x, y);
        FarmBuildingType farmBuilding = FarmBuildingType.getFarmBuilding(buildingName);


        if (!city.isNearShop(ShopType.CARPENTER_SHOP))
        {
            return new Result(false, "you should be in Carpenter's Shop for this command");
        }

        if (farmBuilding == null)
        {
            return new Result(false, "invalid Building Name");
        }

        if (!farm.isInBounds(x, y))
        {
            return new Result(false, "this tile doesn't even exist");
        }

        int width = farmBuilding.getWidth();
        int height = farmBuilding.getHeight();

//        if (!farm.isGoodForAnimalBuilding(targetTile, width, height))
//        {
//            return new Result(false, "you can't build this building here");
//        }

        int price = farmBuilding.getPrice();
        int wood = farmBuilding.getWoodNumber();
        int stone = farmBuilding.getStoneNumber();

        boolean canAfford = true;

        if (player.getMoney() < price)
        {
            CityMenu.println("You don't have enough of money for this building.");
            CityMenu.println("\tyou have: " + player.getMoney());
            CityMenu.println("\trequired: " + price);
            canAfford = false;
        }

        if (player.howManyInInventory(GameObjectType.WOOD) < wood)
        {
            CityMenu.println("You don't have enough of wood in your inventory.");
            CityMenu.println("\tyou have: " + player.howManyInInventory(GameObjectType.WOOD));
            CityMenu.println("\trequired: " + wood);
            canAfford = false;
        }

        if (player.howManyInInventory(GameObjectType.STONE) < stone)
        {
            CityMenu.println("You don't have enough of stone in your inventory.");
            CityMenu.println("\tyou have: " + player.howManyInInventory(GameObjectType.STONE));
            CityMenu.println("\trequired: " + stone);
            canAfford = false;
        }

        if (!canAfford)
        {
            return new Result(false, "sorry, you are poor");
        }

        player.increaseMoney(-1 * price);
        player.removeAmountFromInventory(GameObjectType.WOOD, wood);
        player.removeAmountFromInventory(GameObjectType.STONE, stone);

        farm.buildAnimalBuilding(new AnimalBuilding(targetTile, farmBuilding));
        return new Result(true, "Animal Building built successfully! You can go to your farm to visit it.");
    }

    public Result buyAnimal(String input)
    {
        String animal = GameCommands.BUY_ANIMAL.getMatcher(input).group("animal").trim();
        String name = GameCommands.BUY_ANIMAL.getMatcher(input).group("name").trim();

        Player player = App.getCurrentGame().getCurrentPlayer();
        Farm farm = player.getFarm();
        City city = App.getCurrentGame().getCity();

        if (!city.isNearShop(ShopType.MARINE_RANCH))
        {
            return new Result(false, "you must be in Marine's Ranch to get a cute animal");
        }

        FarmAnimalsType animalType = FarmAnimalsType.getFarmAnimalsType(animal);
        if (animalType == null)
        {
            return new Result(false, "invalid animal type");
        }

        if (!player.validAnimalName(name))
        {
            return new Result(false, "you already have a " + name + " in your farm");
        }

        AnimalBuilding animalBuilding = farm.getBuildingForAnimal(animalType);
        if (animalBuilding == null)
        {
            return new Result(false, "you don't have a good home for " + name + " in your farm");
        }

        int price = animalType.getPurchaseCost();
        if (player.getMoney() < price)
        {
            return new Result(false, "capitalism says that you don''t have enough 'money' to 'buy' "
                    + name + " :(");
        }

        player.increaseMoney(-1 * price);
        animalBuilding.putAnimalInBuilding(new Animal(name, animalType));

        return new Result(true, "Ahhhhhh! " + name + " is now in a new home. Isn't that lovely?!");
    }

    public Result pet(String input)
    {
        String name = GameCommands.PET_ANIMAL.getMatcher(input).group("name").trim();

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.findAnimal(name);

        if (animal == null)
        {
            return new Result(false, "you have no animal named " + name + " in your farm");
        }

        if (!player.isNear(animal.getTile().getPoint()))
        {
            return new Result(false, "you are not near " + name);
        }

        animal.pet();

        return new Result(false, "OMG!!! you just pet " + name + ". It's so CUTEEEE.");
    }


    public Result cheatSetFriendship(String input)
    {
        String name = GameCommands.SET_FRIENDSHIP.getMatcher(input).group("name").trim();
        int amount = Integer.parseInt(GameCommands.SET_FRIENDSHIP.getMatcher(input).group("amount").trim());

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.findAnimal(name);

        if (animal == null)
        {
            return new Result(false, "you have no " + name + " in your farm");
        }

        animal.setFriendship(amount);

        return new Result(true, "friendship with " + name + " set to " + animal.getFriendship());
    }

    public void showAnimalDetails()
    {
        Player player = App.getCurrentGame().getCurrentPlayer();
        ArrayList<Animal> animals = player.getAnimals();

        if (animals.isEmpty())
        {
            GameMenu.println("you have no animals =(");
        }

        GameMenu.println("Your Friends (Animals): ");
        GameMenu.println("--------------------------------");

        for (Animal animal : animals)
        {
            GameMenu.println(animal.getInfo());
        }
    }

    public Result shepherdAnimal(String input)
    {
        String name = GameCommands.SHEPHERD_ANIMAL.getMatcher(input).group("name").trim();
        int x = Integer.parseInt(GameCommands.SHEPHERD_ANIMAL.getMatcher(input).group("x").trim());
        int y = Integer.parseInt(GameCommands.SHEPHERD_ANIMAL.getMatcher(input).group("y").trim());

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.findAnimal(name);

        if (animal == null)
        {
            return new Result(false, "you have no " + name + " in your farm");
        }

        Farm farm = player.getFarm();

        if (!animal.isIn())
        {
            if (!farm.isInBounds(x, y))
            {
                return new Result(false, "invalid location");
            }

            AnimalBuilding animalBuilding = player.getFarm().getAnimalBuilding(animal);
            Tile tile = farm.getTile(x, y);

            if (!animalBuilding.getTiles().contains(tile))
            {
                return new Result(false, "the given location is not in " + name + "'s house");
            }

            animal.goIn();
            tile.setObject(null);

            return new Result(true, name + " just went home");
        }

        Weather weather = App.getCurrentGame().getCurrentTime().getCurrentWeather();

        if (weather == Weather.Snow)
        {
            return new Result(false, "you want to take " + name + " out?? In this weather?\n" +
                    "It's snowing out there! Poor " + name + " will freeze if you let them out now.");
        }

        if (weather == Weather.Rain)
        {
            return new Result(false, "you want to take " + name + " out?? In this weather?\n" +
                    "It's pouring rain! " + name + " might catch a cold. Let's wait for better weather.");
        }

        if (weather == Weather.Storm)
        {
            return new Result(false, "you want to take " + name + " out?? In this weather?\n" +
                    "A storm is raging outside! " + name + " could get hurt. It's much safer to stay inside today.");
        }

        if (!farm.isInBounds(x, y))
        {
            return new Result(false, "invalid location");
        }

        Tile tile = farm.getTile(x, y);

        if (tile.getObject() != null)
        {
            return new Result(false, "this tile is not empty");
        }

        if (tile.getTexture() != TileTexture.LAND && tile.getTexture() != TileTexture.GRASS)
        {
            return new Result(false, "you can't put " + name + " on this tile =(");
        }

        animal.goOut();
        tile.setObject(animal);

        return new Result(true, name + " just went out! Yippee!");
    }

    public void showProducts()
    {
        ArrayList<Animal> animals = App.getCurrentGame().getCurrentPlayer().getAnimals();
        ArrayList<Animal> animalsWithProducts = new ArrayList<>();

        for (Animal animal : animals)
        {
            if (animal.hasProduct())
            {
                animalsWithProducts.add(animal);
            }
        }

        if (animalsWithProducts.isEmpty())
        {
            GameMenu.println("no products to show");
            return;
        }

        GameMenu.println("Animal Products: (shame on you)");
        GameMenu.println("--------------------------------");

        for (Animal animal : animalsWithProducts)
        {
            GameObject product = animal.getProduct();
            animal.calculateProductPrice(product);
            GameMenu.println(animal.getName());
            GameMenu.println("\tproduct: " + product.getObjectType().toString() + " x" + product.getNumber());
            GameMenu.println("\tquality: " + String.format("%.2f", animal.getQuality()));
            GameMenu.println("\tprice: " + animal.getPrice());
            GameMenu.println("--------------------------------");
        }
    }

    public Result collectProducts(String input)
    {
        String name = GameCommands.COLLECT_PRODUCES.getMatcher(input).group("name").trim();

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.findAnimal(name);

        if (animal == null)
        {
            return new Result(false, "you have no " + name + " in your farm");
        }

        if (!animal.hasProduct())
        {
            return new Result(false, name + " has no products for today");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "you have no capacity in your inventory");
        }

        GameObject product = animal.getProduct();
        player.addToInventory(product.getObjectType(), 1);

        return new Result(true, product.getNumber() + " " + product.getObjectType().toString()
                + " was just added to your inventory");
    }

    public Result feedHay(String input)
    {
        String name = GameCommands.FEED_HAY.getMatcher(input).group("name").trim();

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.findAnimal(name);

        if (animal == null)
        {
            return new Result(false, "you have no animal named " + name + " in your farm");
        }

        if (!player.hasEnoughInInventory(GameObjectType.HAY, 1))
        {
            return new Result(false, "you have no hay in your inventory");
        }

        player.removeAmountFromInventory(GameObjectType.HAY, 1);
        animal.feed();

        return new Result(false, "you just fed " + name + "!");
    }

    public Result sellAnimal(String input, Scanner scanner) {
        String name = GameCommands.SELL_ANIMAL.getMatcher(input).group("name").trim();

        Player player = App.getCurrentGame().getCurrentPlayer();
        Animal animal = player.findAnimal(name);

        if (animal == null)
        {
            return new Result(false, "you have no animal named " + name + " in your farm");
        }

        GameMenu.print("Noooo! You really want to sell " + name + "? [y/n] ");
        String answer = GameMenu.scan(scanner);

        if (answer.equalsIgnoreCase("n"))
        {
            return new Result(false, "OK");
        }

        if (!answer.equalsIgnoreCase("y"))
        {
            return new Result(false, "dalghak");
        }

        int price = animal.getPrice();

        Farm farm = App.getCurrentGame().getCurrentPlayer().getFarm();
        AnimalBuilding animalBuilding = farm.getAnimalBuilding(animal);
        animalBuilding.sellAnimal(animal);

        player.increaseMoney(price); // TODO: add shipping bin mechanism later

        return new Result(true, price + " $ was added to your bank account, dork");
    }

    public Result fishing(String input)
    {
        Random random = new Random();
        String fishingPoleName = GameCommands.FISHING.getMatcher(input).group("fishingPole").trim();

        Player player = App.getCurrentGame().getCurrentPlayer();
        Tool tool = player.getCurrentTool();

        if (tool == null)
        {
            return new Result(false, "you're not holding any tool");
        }

        if (!(tool instanceof FishingPole))
        {
            return new Result(false, "you should hold a fishing pole");
        }

        if (!player.isInFarm())
        {
            return new Result(false, "you can only fish in your farm");
        }

        if (!player.hasFishingPole())
        {
            return new Result(false, "you don't have any fishing pole\n" +
                    "you can buy one one from Fish Shop in the city");
        }

        Farm farm = player.getFarm();

        ArrayList<Point> neighborPoints = new ArrayList<>();
        int baseX = player.getLocation().getX();
        int baseY = player.getLocation().getY();
        for (int y = -1; y <= 1; y++)
        {
            for (int x = -1; x <= 1; x++)
            {
                if (farm.isInBounds(baseX + x, baseY + y))
                {
                    neighborPoints.add(new Point(baseX + x, baseY + y));
                }
            }
        }

        boolean isNearSee = false;

        for (Point neighborPoint : neighborPoints)
        {
            Tile tile = farm.getTile(neighborPoint.getX(), neighborPoint.getY());
            if (tile.getTexture() == TileTexture.LAKE)
            {
                isNearSee = true;
            }
        }

        if (!isNearSee)
        {
            return new Result(false, "you should be around at least one lake tile to do fishing");
        }

        int numberOfFishes = numberOfFishes();

        FishingPole pole = (FishingPole) player.getCurrentTool();
        ArrayList<Fish> fishes = new ArrayList<>();
        for (int i = 0; i < numberOfFishes; i++)
        {
            FishType type = FishType.getRandomFish(pole.getLevel());
            if (type != null)
            {
                fishes.add(new Fish(type));
            }
        }

        if (fishes.isEmpty())
        {
            return new Result(false, "you didn't catch any fish =(");
        }

        if (!player.inventoryHasCapacity())
        {
            return new Result(false, "you don't have enough capacity in your inventory");
        }

        for (Fish fish : fishes)
        {
            fish.calculateQuality(pole.getLevel());

            player.getFishingSkill().changeUnit(5);
            player.addToInventory(fish);
            GameMenu.println("\tYou caught a " + fish.getType().getType().toString() + " with a quality of " + String.format("%.2f", fish.getQuality()) + ".");
        }

        return new Result(true, "That's all!");
    }

    public Result showShop(String shop_name)
    {
        ShopType type = ShopType.getShop(shop_name);
        if (type == null)
        {
            return new Result(false, "invalidd");
        }

        City city = App.getCurrentGame().getCity();
        ArrayList<Tile> tiles = city.getShopTiles(type);

        for (Tile tile : tiles)
        {
            System.out.println("x: " + tile.getX() + " - y: " + tile.getY());
        }

        return new Result(true, "done");
    }

    private int numberOfFishes()
    {
        Random random = new Random();
        double R = 0.5 + 0.5 * random.nextDouble();
        double M;
        int skill = App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel();

        switch (App.getCurrentGame().getCurrentTime().getCurrentWeather()) {
            case Weather.Sunny -> M = 1.5;
            case Weather.Rain -> M = 1.2;
            case Weather.Storm -> M = 0.5;
            default -> M = 1;
        }

        int result = (int) (R * M * (skill + 2));
        return result;
    }

}
