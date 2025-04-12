package model.player_data;

import model.Player;
import model.enums.GameObjectType;

public class Trade {
    private final int id;
    private int lastIdAssigned = 0;
    private final Player player;
    private final GameObjectType type;
    private final int amount;
    private final double price;
    private final GameObjectType otherObject;
    private final int otherObjectAmount;

    public Trade(Player player, GameObjectType type, int amount, double price, GameObjectType otherObject, int otherObjectAmount) {
        this.id = ++lastIdAssigned;
        this.player = player;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.otherObject = otherObject;
        this.otherObjectAmount = otherObjectAmount;
    }

    public Player getPlayer() {
        return player;
    }

    public GameObjectType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public GameObjectType getOtherObject() {
        return otherObject;
    }

    public int getOtherObjectAmount() {
        return otherObjectAmount;
    }

    public int getId() {
        return id;
    }
}
