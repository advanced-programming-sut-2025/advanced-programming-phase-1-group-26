package model.animal;

import model.enums.animal_enums.FarmAnimals;

public class Animal {
    private String name;
    private FarmAnimals animalType;
    private int price;
    private int friendship;
    private boolean isFed;

    public Animal(String name, FarmAnimals animalType) {
        this.name = name;
        this.animalType = animalType;
        this.price = animalType.getPurchaseCost();
        this.friendship = 0;
    }

    public void feed() {}
    public void pet() {
        this.friendship += 15;
    }

    public void feedFromOutside() {
        this.friendship += 8;
    }
    public int getPrice()
    {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FarmAnimals getAnimalType() {
        return animalType;
    }

    public void setAnimalType(FarmAnimals animalType) {
        this.animalType = animalType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFriendship() {
        return friendship;
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }
}
