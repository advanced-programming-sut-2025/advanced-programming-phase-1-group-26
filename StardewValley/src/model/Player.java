package model;

import model.enums.LevelType;

public class Player {

    User user;

    private int energy;
    private final static int maxEnergy = 200;
    /*TEMP*/ private boolean fainted;

    private Skill farmingSkill = new Skill(LevelType.Farming);
    private Skill miningSkill = new Skill(LevelType.Mining);
    private Skill gashtogozarSkill = new Skill(LevelType.Gashtogozar);
    private Skill fishingSkill = new Skill(LevelType.Fishing);

    public Player(User user) {
        this.user = user;
        this.energy = 200;
        this.fainted = false;

    }
    

    public int getEnergy() {
        return energy;
    }

    public void increaseEnergy(int energy) {
        this.energy += energy;
    }


    public boolean isFainted() {
        return fainted;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    public Skill getFarmingSkill() {
        return farmingSkill;
    }

    public void setFarmingSkill(Skill farmingSkill) {
        this.farmingSkill = farmingSkill;
    }

    public Skill getMiningSkill() {
        return miningSkill;
    }

    public void setMiningSkill(Skill miningSkill) {
        this.miningSkill = miningSkill;
    }

    public Skill getGashtogozarSkill() {
        return gashtogozarSkill;
    }

    public void setGashtogozarSkill(Skill gashtogozarSkill) {
        this.gashtogozarSkill = gashtogozarSkill;
    }

    public Skill getFishingSkill() {
        return fishingSkill;
    }

    public void setFishingSkill(Skill fishingSkill) {
        this.fishingSkill = fishingSkill;
    }
}
