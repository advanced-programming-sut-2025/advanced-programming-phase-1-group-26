package model;

import model.enums.GameObjectType;

public class GameObject
{
    protected GameObjectType ObjectType;

    public GameObjectType getObjectType()
    {
        return ObjectType;
    }

    public void setObjectType(GameObjectType objectType)
    {
        this.ObjectType = objectType;
    }
}
