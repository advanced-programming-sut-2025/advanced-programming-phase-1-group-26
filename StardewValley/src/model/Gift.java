package model;

public class Gift {
    private final GameObject gameObject;
    private final Player giver;
    private final Player taker;
    private int rate;
    private final int id;
    private static int lastAssigned = 0;

    public Gift(GameObject gameObject, Player giver, Player taker) {
        this.gameObject = gameObject;
        this.giver = giver;
        this.taker = taker;
        this.id = ++lastAssigned;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public Player getGiver() {
        return giver;
    }

    public Player getTaker() {
        return taker;
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
