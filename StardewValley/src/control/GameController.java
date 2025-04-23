package control;

import model.App;
import model.GameObject;
import model.Player;
import model.Result;

import java.util.ArrayList;

public class GameController
{
    public Result createNewGame(String[] usernames)
    {
        return null;
    }

    public Result selectMap(String mapNumber)
    {
        return null;
    }

    public Result loadGame()
    {
        return null;
    }

    public Result exitGame()
    {
        return null;
    }

    public Result nextTurn()
    {
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
        for(GameObject object : inventory) {
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
}
