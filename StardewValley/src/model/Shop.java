package model;

import model.enums.GameObjectType;

public abstract class Shop {
    private String shopName;
    private String salesManName;
    private Time startWork;
    private Time endWork;
    public void workabality(GameObjectType gameObjectType){}
}
