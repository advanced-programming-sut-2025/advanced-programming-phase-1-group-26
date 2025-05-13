package control.game.activities;

import model.App;
import model.GameObject;
import model.Result;
import model.animal.Animal;
import model.animal.AnimalBuilding;
import model.enums.animal_enums.FarmBuilding;
import model.enums.regex_enums.GameCommands;

import java.util.ArrayList;

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
                    //TODO, build a building
                    return new Result(true, "building built successfully");
                }
            }
        }
        return new Result(false, "building not found");
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

}
