package model;

import model.enums.GameObjectType;

public class Gift {
    private final GameObjectType gameObject;
    private final Player giver;
    private final Player taker;
    private final int amount;
    private int rate;
    private final int id;
    private static int lastAssigned = 0;

    public Gift(GameObjectType gameObject, Player giver, Player taker, int amount) {
        this.gameObject = gameObject;
        this.giver = giver;
        this.taker = taker;
        this.amount = amount;
        this.id = ++lastAssigned;
    }

    public GameObjectType getGameObject() {
        return gameObject;
    }

    public Player getGiver() {
        return giver;
    }

    public Player getTaker() {
        return taker;
    }

    public int getAmount() {
        return amount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }
}
