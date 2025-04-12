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
    }

    public int getUnit() {
        return unit;
    }

    public void addUnit(int unit) {
        this.unit += unit;
    }


}
