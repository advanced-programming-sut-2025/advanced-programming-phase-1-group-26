package model;

import model.enums.AnimalType;
import model.enums.GameObjectType;

import java.util.ArrayList;
import java.util.HashMap;

public class Animal {
    private AnimalType animalType;
    private HashMap<GameObjectType, Integer> products = new HashMap<>();
    private int friendship;
    private int price;
    private boolean isFeeded;

    public void feed() {}

}
