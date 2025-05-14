package control.game.activities;

import model.*;
import model.animal.Animal;
import model.animal.AnimalBuilding;
import model.animal.Fish;
import model.enums.GameObjectType;
import model.enums.Weather;
import model.enums.animal_enums.FarmAnimals;
import model.enums.animal_enums.FarmBuilding;
import model.enums.animal_enums.FishType;
import model.enums.regex_enums.GameCommands;
import model.enums.tool_enums.FishingPoleLevel;
import model.enums.tool_enums.ToolType;
import model.tools.FishingPole;

import java.util.ArrayList;
import java.util.Random;

public class AnimalController {
    public Result buildAnimalBuilding(String input) {
        String buildingName = GameCommands.BUILD_ANIMAL_HOUSE.getMatcher(input).group("name");
        int x = Integer.parseInt(GameCommands.BUILD_ANIMAL_HOUSE.getMatcher(input).group("x"));
        int y = Integer.parseInt(GameCommands.BUILD_ANIMAL_HOUSE.getMatcher(input).group("y"));
        for(FarmBuilding building : FarmBuilding.values()) {
            if(building.getName().equals(buildingName)) {
                if(!isInBounds(x, y)) {return new Result(false, "invalid bounds");}
                if(!isBuildable(x, y, building)) {
                    return new Result(false, "we can't build in this place");
                } else if(!canWeBuild(building)) {
                    return new Result(false, "you can't afford to build in this place");
                } else {
                    App.getCurrentGame().getCurrentPlayer().decreaseMoney(building.getPrice());
                    App.getCurrentGame().getCurrentPlayer().addAnimalBuilding(new AnimalBuilding(building, x, y));
                    return new Result(true, "building built successfully");
                }
            }
        }
        return new Result(false, "building not found");
    }

    public Result buyAnimal(String input) {
        String animal = GameCommands.BUY_ANIMAL.getMatcher(input).group("animal");
        String name = GameCommands.BUY_ANIMAL.getMatcher(input).group("name");
        Animal targetAnimal = null;

        for(FarmAnimals animalType : FarmAnimals.values()) {
            if(animalType.getName().equals(animal)) {
                targetAnimal = new Animal(name, animalType);
            }
        }
        if(targetAnimal == null) {return new Result(false, "Animal not found");}
        for(AnimalBuilding building : App.getCurrentGame().getCurrentPlayer().getAnimalBuildings()) {
            if(targetAnimal.canGoInThere(building.getFarmBuilding())) {
                if(targetAnimal.getPrice() > App.getCurrentGame().getCurrentPlayer().getMoney()) {
                    return new Result(false, "you can't buy this animal");
                }
                App.getCurrentGame().getCurrentPlayer().decreaseMoney(targetAnimal.getPrice());
                App.getCurrentGame().getCurrentPlayer().addAnimal(targetAnimal);
                return new Result(true, "You just bought this animal");
            }
        }
        return new Result(false, "you can't buy this animal, You don't have space");
    }

    public Result pet(String input) {
        String name = GameCommands.PET_ANIMAL.getMatcher(input).group("name");
        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            if(animal.getName().equals(name)) {
                animal.pet();
                return new Result(true, "animal pet");
            }
        }
        return new Result(false, "animal not found");
    }


    public void cheatSetFriendship(String input) {
        String name = GameCommands.SET_FRIENDSHIP.getMatcher(input).group("name");
        int amount = Integer.parseInt(GameCommands.SET_FRIENDSHIP.getMatcher(input).group("amount"));

        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            if(animal.getName().equals(name)) {
                animal.setFriendship(amount);
            }
        }
    }

    public String showAnimalDetails() {
        ArrayList<Animal> animals = App.getCurrentGame().getCurrentPlayer().getAnimals();
        StringBuilder animalDetails = new StringBuilder();

        for(Animal animal : animals) {
            animalDetails.append(animal.getName()).append(" ").append(animal.getFriendship()).append(" ")
                    .append(animal.isPet()).append(" ").append(animal.isFed()).append("\n");
        }
        return animalDetails.toString();
    }

    public String showProducts() {
        ArrayList<Animal> animals = App.getCurrentGame().getCurrentPlayer().getAnimals();
        StringBuilder productDetails = new StringBuilder();

        for(Animal animal : animals) {
            animal.secondProductLogic(animal.getProduces().get(1));
            for(FarmAnimals.Product product : animal.getProduces()) {
                productDetails.append(product.type().name()).append(" ").append(product.price()).append("\n");
            }
        }

        return productDetails.toString();
    }

    public Result collectProducts(String input) {
        String name = GameCommands.COLLECT_PRODUCES.getMatcher(input).group("name");
        ArrayList<Animal> animals = App.getCurrentGame().getCurrentPlayer().getAnimals();

        for(Animal animal : animals) {
            if(animal.getName().equals(name)) {
                //TODO : check for having item in inventory
                if(animal.getProduces().isEmpty()) {
                    return new Result(false, "no more products");
                }
                for(FarmAnimals.Product product : animal.getProduces()) {
                    App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().addProduct(product);
                }
                return new Result(true, "product collected");
            }
        }
        return new Result(false, "animal not found");
    }

    public Result shepherdAnimal(String input) {
        String name = GameCommands.SHEPHERD_ANIMAL.getMatcher(input).group("name");
        int x = Integer.parseInt(GameCommands.SHEPHERD_ANIMAL.getMatcher(input).group("x"));
        int y = Integer.parseInt(GameCommands.SHEPHERD_ANIMAL.getMatcher(input).group("y"));
        Animal targetAnimal = null;
        Point location = new Point(x, y);

        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            if(animal.getName().equals(name)) {
                targetAnimal = animal;
            }
        }
        if(targetAnimal == null) {return new Result(false, "animal not found");}

        for(AnimalBuilding building : App.getCurrentGame().getCurrentPlayer().getAnimalBuildings()) {
            if(building.getLocation().equals(location)) {
                targetAnimal.shepherdAnimal();
                return new Result(true, "shepherd animal");
            }
        }
        return new Result(false, "animal building not found");
    }

    public Result feedHay(String input) {
        String name = GameCommands.FEED_HAY.getMatcher(input).group("name");
        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            if(animal.getName().equals(name)) {
                if(App.getCurrentGame().getCurrentPlayer().getItemInInventory(GameObjectType.HAY) != null) {
                    App.getCurrentGame().getCurrentPlayer().getItemInInventory(GameObjectType.HAY).addNumber(-1);
                    animal.feed();
                    return new Result(true, "animal fed");
                }
                return new Result(false, "You don't have any hay");
            }
        }
        return new Result(true, "animal not found");
    }

    public Result sellAnimal(String input) {
        String name = GameCommands.SELL_ANIMAL.getMatcher(input).group("name");
        for(Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            if(animal.getName().equals(name)) {
                int price = (int) (animal.getPrice() * ((double) animal.getFriendship() / 1000 + 0.3));
                App.getCurrentGame().getCurrentPlayer().getAnimals().remove(animal);
                App.getCurrentGame().getCurrentPlayer().increaseMoney(price);
                return new Result(true, "animal selled");
            }
        }
        return new Result(false, "animal not found");
    }

    public Result fishing(String input) {
        Random random = new Random();
        String fishingPoleName = GameCommands.FISHING.getMatcher(input).group("fishingPole");
        FishingPole fishingPole = null;
        FishingPoleLevel level = null;
        for(FishingPoleLevel pole : FishingPoleLevel.values()) {
            if(pole.getName().equals(fishingPoleName)) {
                level = pole;
                fishingPole = new FishingPole();
                fishingPole.setType(ToolType.FishingPole);
                fishingPole.setLevel(pole);
                fishingPole.setName(fishingPoleName);
            }
        }
        if(fishingPole == null) {
            return new Result(false, "fishing pole not valid");
        }

        if(!App.getCurrentGame().getCurrentPlayer().getCurrentBackPack().getInventory().contains(fishingPole)) {
            return new Result(false, "fishing pole is not in inventory");
        }

        ArrayList<FishType> availableTypes = new ArrayList<>();
        for(FishType type : FishType.values()) {
            availableTypes.add(type);
            if(type.isLegendary() && App.getCurrentGame().getCurrentPlayer().
                    getFishingSkill().getLevel() != 4) availableTypes.remove(type);
        }

        FishType targetType = availableTypes.get(random.nextInt(availableTypes.size()));

        int number = numberOfFishes();

        for(int i = 0; i < number; i++) {
            Fish fish = new Fish(targetType);
            fish.calculateQuality(level);
            App.getCurrentGame().getCurrentPlayer().addFish(fish);
        }
        return new Result(true, "You just went fishing and collect " +
                number + " " + targetType.getDisplayName());
    }

    public boolean isInBounds(int x, int y)
    {
        return x >= 0 && x < 70 && y >= 0 && y < 70;
    }

    public boolean isBuildable(int x, int y, FarmBuilding building) {
        //TODO
        return true;
    }

    public boolean canWeBuild(FarmBuilding building) {
        boolean affordable = App.getCurrentGame().getCurrentPlayer().getMoney() >= building.getPrice();
        boolean requirements =
                App.getCurrentGame().getCurrentPlayer().getInventory().contains(
                        building.getRequirements()
                );

        return affordable && requirements;
    }

    public int numberOfFishes() {
        Random random = new Random();
        int R = random.nextInt(2);
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
