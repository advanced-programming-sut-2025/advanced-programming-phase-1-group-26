package model.enums;

public enum BackPackLevel {
    base (12),
    Big (24),
    Deluxe (-1); /* Unlimited */

    private final int capacity;

    BackPackLevel(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
