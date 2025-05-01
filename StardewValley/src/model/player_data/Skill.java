package model.player_data;
import model.enums.SkillType;

public class Skill {

    private final SkillType type;
    private int level;
    private int unit;
    private final static int maxLevel = 4;

    public Skill(SkillType type) {
        this.type = type;
        this.level = 0;
        this.unit = 0;

    }

    public SkillType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void addLevel() {
        this.level ++;
        setUnit(0);
    }

    public boolean checkLevel() {
        switch (level) {
            case 0:
                if (unit >= 50) {
                    return true;
                }
                break;
            case 1:
                if (unit >= 150) {
                    return true;
                }
                break;
            case 2:
                if (unit >= 250) {
                    return true;
                }
                break;
            case 3:
                if (unit >= 350) {
                    return true;
                }
                break;
        }
        return false;
    }

    public int getUnit() {
        return unit;
    }

    public void addUnit(int unit) {
        this.unit += unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
