package model;

import model.enums.GameObjectType;

import java.util.Objects;

public class GameObject
{
    protected GameObjectType ObjectType;
    protected int number = 1;
    protected String appearance;

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

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof GameObject object)) return false;
        return number == object.number && ObjectType == object.ObjectType;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ObjectType, number);
    }
}
