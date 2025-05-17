package model.enums.tool_enums;

import model.enums.GameObjectType;

public enum BackPackLevel {
    base (12, GameObjectType.BASE_PACK),
    Large (24, GameObjectType.LARGE_PACK),
    Deluxe (-1, GameObjectType.DELUXE_PACK); /* Unlimited */

    private final int capacity;
    private final GameObjectType gameObjectType;

    BackPackLevel(int capacity, GameObjectType gameObjectType) {
        this.capacity = capacity;
        this.gameObjectType = gameObjectType;
    }

    public int getCapacity() {
        return capacity;
    }
    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }
}
