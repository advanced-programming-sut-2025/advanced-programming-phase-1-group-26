package model.player_data;

import java.util.ArrayList;

public class FriendshipData {
    private int level;
    private int xp;
    private boolean isIntrcatedToday;
    private boolean bouquetBought = false;
    private boolean isMarried = false;
    private ArrayList<String> messageHistory = new ArrayList<>();

    public FriendshipData(int level, int xp, boolean isIntrcatedToday) {
        this.level = level;
        this.xp = xp;
        this.isIntrcatedToday = isIntrcatedToday;
    }

    public int getLevel() {
        return level;
    }

    public void addLevel() {
        this.level ++;
    }

    public int getXp() {
        return xp;
    }

    public void addXp(int xp) {
        this.xp += xp;
        //check if xp changes level
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public boolean isIntrcatedToday() {
        return isIntrcatedToday;
    }

    public void setIntrcatedToday(boolean intrcatedToday) {
        isIntrcatedToday = intrcatedToday;
    }

    public boolean isBouquetBought() {
        return bouquetBought;
    }

    public void setBouquetBought(boolean bouquetBought) {
        this.bouquetBought = bouquetBought;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public ArrayList<String> getMessageHistory() {
        return messageHistory;
    }
}
