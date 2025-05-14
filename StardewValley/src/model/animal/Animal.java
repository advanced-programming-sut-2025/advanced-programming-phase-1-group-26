package model.animal;

import model.enums.animal_enums.FarmAnimals;
import model.enums.animal_enums.FarmBuilding;

public class Animal {
    private String name;
    private FarmAnimals animalType;
    private int price;
    private int friendship;
    private boolean isFed;
    private boolean isIn;
    private boolean isPet;

    public Animal(String name, FarmAnimals animalType) {
        this.name = name;
        this.animalType = animalType;
        this.price = animalType.getPurchaseCost();
        this.friendship = 0;
        this.isFed = false;
        this.isIn = false;
        this.isPet = false;
    }

    public void feed() {
        isFed = true;
    }
    public void pet() {
        increaseFriendship(15);
    }

    public void feedFromOutside() {
        isFed = true;
        increaseFriendship(8);
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
    public void increaseFriendship(int amount) {
        this.friendship += amount;
        if(friendship >= 1000) friendship = 1000;
    }
    public void decreaseFriendship(int amount) {
        this.friendship -= amount;
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    public boolean isPet() {
        return isPet;
    }

    public void setPet(boolean pet) {
        isPet = pet;
    }

    public void checkAndReset() {
        if(!isFed) {
            decreaseFriendship(20);
        }
        if(!isIn) {
            decreaseFriendship(20);
        }
        if(!isPet) {
            decreaseFriendship(10 - friendship / 200);
        }

        isFed = false;
        isPet = false;
    }

    public boolean canGoInThere(FarmBuilding building) {
        switch(this.animalType.getBuilding()) {
            case BIG_BARN : if(building.equals(FarmBuilding.BARN)) return false; break;
            case DELUXE_BARN : if(building.equals(FarmBuilding.BARN) ||
                    building.equals(FarmBuilding.BIG_BARN)) return false; break;
            case BIG_COOP : if(building.equals(FarmBuilding.COOP)) return false; break;
            case DELUXE_COOP : if(building.equals(FarmBuilding.COOP) ||
                    building.equals(FarmBuilding.BIG_COOP)) return false; break;
        }
        return true;
    }

    public void shepherdAnimal() {
        isIn = !isIn;
        if(!isIn) feedFromOutside();
    }
}
