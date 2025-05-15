package model;

import model.enums.GameObjectType;

public class GameObject
{
    protected GameObjectType ObjectType;
    protected int number = 1;
    protected String appearance;
    protected int price = 0;

    public GameObject(GameObjectType objectType, int number) {
        ObjectType = objectType;
        this.number = number;
    }

    public GameObject() {
    }

    public GameObjectType getObjectType()
    {
        return ObjectType;
    }

    public void setObjectType(GameObjectType objectType)
    {
        this.ObjectType = objectType;
    }

    public int getNumber() {
        return number;
    }

    public void addNumber(int number) {
        this.number += number;
    }

    public Enum<?> getToolType()
    {
        return null;
    }

    public int getPrice() {
        return price;
    }
}
