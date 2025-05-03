package model;

import model.enums.GameObjectType;

public class GameObject
{
    protected GameObjectType ObjectType;
    protected int number;

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

    public void setNumber(int number) {
        this.number = number;
    }

    public Enum<?> getType()
    {
        return null;
    }
}
