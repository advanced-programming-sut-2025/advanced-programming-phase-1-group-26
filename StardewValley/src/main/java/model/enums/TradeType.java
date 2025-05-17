package model.enums;

public enum TradeType {
    REQUEST("request"),
    OFFER("offer");

    private final String name;

    TradeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
