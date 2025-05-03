package model.player_data;

import model.Player;
import model.enums.GameObjectType;
import model.enums.TradeType;

public class Trade {
    private final int id;
    private int lastIdAssigned = 0;
    private final Player sent;
    private final Player received;
    private final TradeType type;
    private final GameObjectType item;
    private final int amount;
    private final double price;
    private final GameObjectType targetItem;
    private final int targetAmount;

    public Trade(Player request, Player offer, TradeType type, GameObjectType item, int amount, double price) {
        this.id = ++lastIdAssigned;
        this.sent = request;
        this.received = offer;
        this.type = type;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.targetItem = null;
        this.targetAmount = -1;
    }

    public Trade(Player request, Player offer, TradeType type, GameObjectType item, int amount,
                 GameObjectType targetItem, int targetAmount) {
        this.id = ++lastIdAssigned;
        this.sent = request;
        this.received = offer;
        this.type = type;
        this.item = item;
        this.amount = amount;
        this.price = -1;
        this.targetItem = targetItem;
        this.targetAmount = targetAmount;
    }

    public int getId() {
        return id;
    }

    public Player getSent() {
        return sent;
    }

    public Player getReceived() {
        return received;
    }

    public TradeType getType() {
        return type;
    }

    public GameObjectType getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public GameObjectType getTargetItem() {
        return targetItem;
    }

    public int getTargetAmount() {
        return targetAmount;
    }
}
