package model.player_data;
import model.Player;
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

    public void changeUnit(int delta) {
        this.unit += delta;

        if (delta >= 0) handleLevelUp();
        else handleLevelDown();

    }

    private void handleLevelUp() {
        while (true) {
            switch (this.level) {
                case 0:
                    if (unit >= 50) levelUp(50);
                    else return;
                    break;
                case 1:
                    if (unit >= 150) levelUp(150);
                    else return;
                    break;
                case 2:
                    if (unit >= 250) levelUp(250);
                    else return;
                    break;
                case 3:
                    if (unit >= 350) levelUp(350);
                    else return;
                    break;
                default:
                    return;
            }
        }
    }

    private void handleLevelDown() {
        while (this.level > 0) {
            int threshold = getThresholdForLevel(this.level - 1);
            if (this.unit < 0) {
                this.level--;
                this.unit += threshold;
            } else {
                break;
            }
        }
    }

    private void levelUp(int threshold) {
        this.level++;
        this.unit -= threshold;
    }

    private int getThresholdForLevel(int level) {
        return switch (level) {
            case 0 -> 50;
            case 1 -> 150;
            case 2 -> 250;
            case 3 -> 350;
            default -> Integer.MAX_VALUE;
        };
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
