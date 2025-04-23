package control;
import model.App;
import model.GameObject;
import model.Player;
import model.Result;
import model.tools.*;

import java.util.ArrayList;

public class GameController {
    public Result createNewGame(String[] usernames) {
        return null;
    }

    public Result selectMap(String mapNumber) {
        return null;
    }

    public Result loadGame() {
        return null;
    }

    public Result exitGame() {
        return null;
    }

    public Result nextTurn() {
        return null;
    }

    public Result energyShow() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        return new Result(true, "" + currentPlayer.getEnergy());
    }

    public void energySet(int value) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        currentPlayer.setEnergy(value);
    }

    public void energyUnlimited() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        currentPlayer.setEnergy(2147483647); //might change later
    }

    public void inventoryShow() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        ArrayList<GameObject> inventory = new ArrayList<>();
        inventory.addAll(currentPlayer.getInventoryItems());
        for (GameObject object : inventory) {
            System.out.println(object.getObjectType().name() + " x" + object.getNumber());
        }
    }

    public void inventoryTrash(String name, int number) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        GameObject object = null;
        for (GameObject gameObject : currentPlayer.getInventoryItems()) {
            if (gameObject.getObjectType().name().equals(name)) {
                object = gameObject;
            }
        }

        if (object == null) {
            System.out.println("you don't have this item in your inventory!");
        } else {
            currentPlayer.getInventory().remove(object);
            System.out.println("item deleted successfully");
        }
    }

    public Result toolsEquip(String toolName) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        for (GameObject object : currentPlayer.getInventoryItems()) {
            if (object.getObjectType().name().equals(toolName)) {
                if (object instanceof Tool) {
                    currentPlayer.setCurrentTool((Tool) object);
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

    public void toolsShowAvailable() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        for (GameObject object : currentPlayer.getInventoryItems()) {
            System.out.println(object.getObjectType().name());
        }
    }

    public void toolsUpgrade(String toolName) { //TODO: add later
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        //check if in blacksmith

        for (GameObject object : currentPlayer.getInventoryItems()) {
            if (object.getObjectType().name().equals(toolName)) {
                if (object instanceof Tool) {
                    if (object instanceof Axe) {

                    }

                }

            }
        }
    }

    public void toolsUse(String direction) { //might change to enum direction
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Tool tool = App.getCurrentGame().getCurrentPlayer().getCurrentTool();
        if (tool == null) {
            System.out.println("you don't have any tool equipped");
        } else {
            if (tool instanceof Axe) {
                currentPlayer.increaseEnergy(-((Axe) tool).getLevel().getBaseEnergyUsage());
            } else if (tool instanceof Hoe) {

                currentPlayer.increaseEnergy(-((Hoe) tool).getLevel().getBaseEnergyUsage());
            } else if (tool instanceof MilkPail) {
                //doesn't have level

            } else if (tool instanceof Pickaxe) {

                currentPlayer.increaseEnergy(-((Pickaxe) tool).getLevel().getBaseEnergyUsage());
            } else if (tool instanceof Seythe) {
                //doesn't have level

            } else if (tool instanceof Shear) {
                //doesn't have level

            } else if (tool instanceof TrashCan) {
                currentPlayer.increaseEnergy(((TrashCan) tool).getLevel().getBaseEnergyUsage());

            } else if (tool instanceof WateringCan) {

                currentPlayer.increaseEnergy(-((WateringCan) tool).getLevel().getBaseEnergyUsage());
            } else if (tool instanceof FishingPole) {

                currentPlayer.increaseEnergy(-((FishingPole) tool).getLevel().getBaseEnergyUsage());
            } else if (tool instanceof BackPack) {
                //doesn't use energy
            }
        }
    }

    
}
