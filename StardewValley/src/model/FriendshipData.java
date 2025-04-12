package model;

import java.util.ArrayList;

public class FriendshipData {
    private static final int[] XP_REQUIREMENTS = {0, 100, 200, 300, 400};
    private int level;
    private int xp;
    private boolean isIntrcatedToday;
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

    public boolean isIntrcatedToday() {
        return isIntrcatedToday;
    }

    public void setIntrcatedToday(boolean intrcatedToday) {
        isIntrcatedToday = intrcatedToday;
    }

    public ArrayList<String> getMessageHistory() {
        return messageHistory;
    }
}
