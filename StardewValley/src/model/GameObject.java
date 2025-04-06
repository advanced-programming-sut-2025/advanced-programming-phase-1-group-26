package model;

import model.enums.GameObjectType;

public class GameObject
{
    protected GameObjectType type;

    public GameObjectType getType()
    {
        return type;
    }

    public void setType(GameObjectType type)
    {
        this.type = type;
    }
}
