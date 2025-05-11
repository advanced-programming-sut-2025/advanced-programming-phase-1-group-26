package model.player_data;

public class FriendshipWithNpcData {
    private int xp;
    private int level;

    public int getXp() {
        return xp;
    }
    public void increaseXp(int amount) {
        this.xp += amount;
        if(this.xp > 200) {
            increaseLevel();
        }
    }

    public int getLevel() {
        return level;
    }
    public void increaseLevel() {
        if(level == 799) return;
        this.level++;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
