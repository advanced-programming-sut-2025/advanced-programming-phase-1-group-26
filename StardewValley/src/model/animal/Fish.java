package model.animal;

import model.App;
import model.enums.Weather;
import model.enums.animal_enums.FishType;
import model.enums.tool_enums.FishingPoleLevel;

import java.util.Random;

public class Fish {
    private FishType type;
    private double quality;

    public double getQuality() {
        return quality;
    }

    public void calculateQuality(FishingPoleLevel level) {
        Random random = new Random();
        int R = random.nextInt(2);
        int skill = App.getCurrentGame().getCurrentPlayer().getFishingSkill().getLevel();
        double M;
        double pole = 0;
        switch (App.getCurrentGame().getCurrentTime().getCurrentWeather()) {
            case Weather.Sunny -> M = 1.5;
            case Weather.Rain -> M = 1.2;
            case Weather.Storm -> M = 0.5;
            default -> M = 1;
        }
        switch (level) {
            case FishingPoleLevel.Training -> pole = 0.1;
            case FishingPoleLevel.Bamboo -> pole = 0.5;
            case FishingPoleLevel.FiberGlass -> pole = 0.9;
            case FishingPoleLevel.Iridium -> pole = 1.2;
        }

        this.quality = (R * (skill + 2) * pole) / (7 - M);
    }

    public Fish(FishType type) {
        this.type = type;
    }
}
