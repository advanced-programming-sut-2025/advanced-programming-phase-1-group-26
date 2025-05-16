package model.animal;

import model.Farm;
import model.GameObject;
import model.Tile;
import model.enums.animal_enums.FarmAnimalsType;
import model.enums.animal_enums.FarmBuildingType;
import view.GameMenu;

import java.util.ArrayList;
import java.util.Random;

public class Animal extends GameObject
{
    private String name;
    private FarmAnimalsType animalType;
    private int friendship = 0;
    private boolean isFed = false;
    private boolean isIn = true;
    private boolean isPet = false;
    private final ArrayList<GameObject> products;
    private Tile tile = null;

    private boolean hasProduct = false;
    private boolean secondProduct = false;

    private int quality = 0;

    public Animal(String name, FarmAnimalsType animalType)
    {
        this.name = name;
        this.animalType = animalType;
        this.price = animalType.getPurchaseCost();
        this.isFed = false;
        this.isIn = false;
        this.isPet = false;
        this.products = animalType.getProducts();
    }

    public void feed()
    {
        if (!isFed)
        {
            GameMenu.println("You fed " + name + " for the first time today!\n" +
                    "Your friendship level was increased 8 units!");
            increaseFriendship(8);
            isFed = true;
        }
    }

    public void pet()
    {
        if (!isPet)
        {
            GameMenu.println("You pet " + name + " for the first time today!\n" +
                    "Your friendship level was increased 15 units!");
            increaseFriendship(15);
            isPet = true;
        }
    }

    public String getName() {
        return name;
    }

    public FarmAnimalsType getAnimalType() {
        return animalType;
    }

    public int getFriendship() {
        return friendship;
    }

    public void setFriendship(int friendship)
    {
        this.friendship = Math.max(1000, friendship);
    }

    public void increaseFriendship(int amount)
    {
        this.friendship += amount;
        if(friendship >= 1000) friendship = 1000;
    }

    public void decreaseFriendship(int amount) {
        this.friendship -= amount;
    }

    public boolean isFed() {
        return isFed;
    }

    public boolean isIn() {
        return isIn;
    }

    public boolean isPet() {
        return isPet;
    }

    public void checkAndReset()
    {
        if(!isFed)
        {
            decreaseFriendship(20);
        }

        if(!isIn)
        {
            decreaseFriendship(20);
        }

        if(!isPet)
        {
            decreaseFriendship(10 - getFriendship() / 200);
        }

        hasProduct = isFed;

        isFed = false;
        isPet = false;
        isIn = true;

        secondProduct = false;

        if (friendship > 100)
        {
            Random rand = new Random();
            if ((friendship + (rand.nextInt(3) / 3.0 + 1)) / 1500 >= 1)
            {
                secondProduct = true;
            }
        }

        Random rand = new Random();
        quality = (int) (((double) friendship / 1000) * (0.5 + (0.5 * rand.nextDouble())));
    }


    public Tile getTile()
    {
        return tile;
    }

    public void setTile(Tile tile)
    {
        this.tile = tile;
    }

    public String getInfo()
    {
        StringBuilder output = new StringBuilder();

        output.append(name).append("\n");
        output.append("\t").append("kind: ").append(animalType.getName()).append("\n");
        output.append("\t").append("friendship: ").append(friendship).append("xp \n");
        output.append("\t").append("is fed today: ").append(isFed ? "positive" : "negative").append("\n");
        output.append("\t").append("is pet today: ").append(isPet ? "positive" : "negative").append("\n");
        output.append("--------------------------------");
        return output.toString();
    }

    public void goOut()
    {
        isIn = false;
        isFed = true;
        increaseFriendship(8);
    }

    public void goIn()
    {
        isIn = true;
    }

    public boolean hasProduct()
    {
        return hasProduct;
    }

    public GameObject getProduct()
    {
        GameObject product = products.get(0);
        if (secondProduct && products.size() > 1)
        {
            product = products.get(1);
        }

        return product;
    }

    public int getQuality()
    {
        return quality;
    }

    public int getPrice()
    {
        return animalType.getPurchaseCost() * (int) (((double) friendship / 1000) + 0.3);
    }
}
