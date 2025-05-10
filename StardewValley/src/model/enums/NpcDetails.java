package model.enums;

import model.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NpcDetails {
    Sebastian("Sebastian",
            List.of(GameObjectType.WOOL, GameObjectType.PUMPKIN_PIE, GameObjectType.PIZZA),
            List.of(new GameObject(GameObjectType.IRON_BAR, 50),
                    new GameObject(GameObjectType.PUMPKIN_PIE, 1),
                    new GameObject(GameObjectType.STONE, 150)),
            List.of(new GameObject(GameObjectType.DIAMOND_MINERAL, 2),
                    new GameObject(GameObjectType.GOLD_COIN, 5000),
                    new GameObject(GameObjectType.QUARTZ_MINERAL, 50)),
            createDialogues(Map.of(
                    Season.Spring, Map.of(
                            TimeOfDay.MORNING, "Spring mornings are too bright for me...",
                            TimeOfDay.AFTERNOON, "Maybe I’ll ride my bike later.",
                            TimeOfDay.EVENING, "At least spring nights are quiet."
                    ),
                    Season.Summer, Map.of(
                            TimeOfDay.MORNING, "Mornings are for coding and coffee.",
                            TimeOfDay.AFTERNOON, "It’s too hot to go outside.",
                            TimeOfDay.EVENING, "Summer evenings are good for long walks."
                    ),
                    Season.Fall, Map.of(
                            TimeOfDay.MORNING, "The air feels crisp... I like that.",
                            TimeOfDay.AFTERNOON, "Leaves make the road look cool.",
                            TimeOfDay.EVENING, "Fall nights make me want to write."
                    ),
                    Season.Winter, Map.of(
                            TimeOfDay.MORNING, "Ugh, mornings in winter are freezing.",
                            TimeOfDay.AFTERNOON, "Time for a hot drink and some reading.",
                            TimeOfDay.EVENING, "I could code all night in this weather."
                    )
            ))
    ),

    Abigail("Abigail",
            List.of(GameObjectType.STONE, GameObjectType.IRON_MINERAL, GameObjectType.COFFEE),
            List.of(new GameObject(GameObjectType.GOLD_BAR, 1),
                    new GameObject(GameObjectType.PUMPKIN, 1),
                    new GameObject(GameObjectType.WHEAT, 50)),
            List.of(new GameObject(GameObjectType.GOLD_COIN, 500),
                    new GameObject(GameObjectType.Automating_Iridium_WateringCan, 1)),
            createDialogues(Map.of(
                    Season.Spring, Map.of(
                            TimeOfDay.MORNING, "Spring is great for adventuring!",
                            TimeOfDay.AFTERNOON, "Found a strange crystal earlier.",
                            TimeOfDay.EVENING, "The stars look amazing tonight."
                    ),
                    Season.Summer, Map.of(
                            TimeOfDay.MORNING, "Let’s do something fun today!",
                            TimeOfDay.AFTERNOON, "Want to explore the mines?",
                            TimeOfDay.EVENING, "Summer nights are perfect for ghost stories."
                    ),
                    Season.Fall, Map.of(
                            TimeOfDay.MORNING, "Fall has this mysterious vibe I love.",
                            TimeOfDay.AFTERNOON, "The wind is whispering secrets again.",
                            TimeOfDay.EVENING, "Want to try a spirit board with me?"
                    ),
                    Season.Winter, Map.of(
                            TimeOfDay.MORNING, "It’s so quiet... almost eerie.",
                            TimeOfDay.AFTERNOON, "Snow crunches in such a satisfying way.",
                            TimeOfDay.EVENING, "Let’s curl up and play some games."
                    )
            ))
    ),

    Harvey("Harvey",
            List.of(GameObjectType.COFFEE, GameObjectType.PICKLE, GameObjectType.WINE),
            List.of(new GameObject(GameObjectType.FLOWER, 12),
                    new GameObject(GameObjectType.SALMON, 1),
                    new GameObject(GameObjectType.WINE, 1)),
            List.of(new GameObject(GameObjectType.GOLD_COIN, 750),
                    new GameObject(GameObjectType.SALAD, 5)),
            createDialogues(Map.of(
                    Season.Spring, Map.of(
                            TimeOfDay.MORNING, "Spring is a good time for health checkups.",
                            TimeOfDay.AFTERNOON, "Make sure to stay hydrated today.",
                            TimeOfDay.EVENING, "Relaxation is part of a healthy routine."
                    ),
                    Season.Summer, Map.of(
                            TimeOfDay.MORNING, "Morning jogs are the best.",
                            TimeOfDay.AFTERNOON, "Don’t forget sunscreen!",
                            TimeOfDay.EVENING, "The breeze helps after a long day."
                    ),
                    Season.Fall, Map.of(
                            TimeOfDay.MORNING, "Cold season is coming. Stay warm.",
                            TimeOfDay.AFTERNOON, "I’ve been organizing the clinic supplies.",
                            TimeOfDay.EVENING, "Windy nights like this can mess with sinuses."
                    ),
                    Season.Winter, Map.of(
                            TimeOfDay.MORNING, "Flu season is in full swing.",
                            TimeOfDay.AFTERNOON, "The fireplace at the clinic is cozy.",
                            TimeOfDay.EVENING, "I hope everyone’s staying warm and healthy."
                    )
            ))
    ),

    Lia("Lia",
            List.of(GameObjectType.SALAD, GameObjectType.GRAPE, GameObjectType.WINE),
            List.of(new GameObject(GameObjectType.HARD_WOOD, 10),
                    new GameObject(GameObjectType.SALMON, 1),
                    new GameObject(GameObjectType.WOOD, 200)),
            List.of(new GameObject(GameObjectType.GOLD_COIN, 500),
                    new GameObject(GameObjectType.SALMON_DINNER_RECIPE, 1),
                    new GameObject(GameObjectType.DELUXE_SCARECROW, 3)),
            createDialogues(Map.of(
                    Season.Spring, Map.of(
                            TimeOfDay.MORNING, "The forest is waking up again!",
                            TimeOfDay.AFTERNOON, "I painted a new flower today.",
                            TimeOfDay.EVENING, "Spring skies are dreamy."
                    ),
                    Season.Summer, Map.of(
                            TimeOfDay.MORNING, "I love painting outside in summer.",
                            TimeOfDay.AFTERNOON, "I had fresh grapes for lunch!",
                            TimeOfDay.EVENING, "Wine, art, and a sunset... bliss."
                    ),
                    Season.Fall, Map.of(
                            TimeOfDay.MORNING, "The colors this season are inspiring.",
                            TimeOfDay.AFTERNOON, "A perfect time to gather wood.",
                            TimeOfDay.EVENING, "The falling leaves calm my spirit."
                    ),
                    Season.Winter, Map.of(
                            TimeOfDay.MORNING, "Snow changes the world’s color palette.",
                            TimeOfDay.AFTERNOON, "I made a sculpture from ice!",
                            TimeOfDay.EVENING, "Even winter has its charm."
                    )
            ))
    ),

    Robin("Robin",
            List.of(GameObjectType.SPAGHETTI, GameObjectType.WOOD, GameObjectType.IRON_BAR),
            List.of(new GameObject(GameObjectType.WOOD, 80),
                    new GameObject(GameObjectType.IRON_BAR, 10),
                    new GameObject(GameObjectType.WOOD, 1000)),
            List.of(new GameObject(GameObjectType.GOLD_COIN, 1000),
                    new GameObject(GameObjectType.BEE_HOUSE, 3),
                    new GameObject(GameObjectType.GOLD_COIN, 25000)),
            createDialogues(Map.of(
                    Season.Spring, Map.of(
                            TimeOfDay.MORNING, "Let’s build something fresh today!",
                            TimeOfDay.AFTERNOON, "Supplies are easier to gather now.",
                            TimeOfDay.EVENING, "Spring evenings are great for planning."
                    ),
                    Season.Summer, Map.of(
                            TimeOfDay.MORNING, "Hot weather? Perfect for drying lumber!",
                            TimeOfDay.AFTERNOON, "I’m making good progress on your project.",
                            TimeOfDay.EVENING, "Phew! Long day of construction!"
                    ),
                    Season.Fall, Map.of(
                            TimeOfDay.MORNING, "Time to reinforce those buildings.",
                            TimeOfDay.AFTERNOON, "Wood is more brittle this time of year.",
                            TimeOfDay.EVENING, "I could go for a bowl of spaghetti."
                    ),
                    Season.Winter, Map.of(
                            TimeOfDay.MORNING, "Even in winter, there's work to do!",
                            TimeOfDay.AFTERNOON, "Watch out for icy scaffolding.",
                            TimeOfDay.EVENING, "Cold days make hot meals extra nice."
                    )
            ))
    );

    private final String name;
    private final List<GameObjectType> favorites;
    private final List<GameObject> requests;
    private final List<GameObject> rewards;
    private final Map<Season, Map<TimeOfDay, String>> dialogues;

    NpcDetails(String name, List<GameObjectType> favorites, List<GameObject> requests,
               List<GameObject> rewards, Map<Season, Map<TimeOfDay, String>> dialogues) {
        this.name = name;
        this.favorites = favorites;
        this.requests = requests;
        this.rewards = rewards;
        this.dialogues = dialogues;
    }

    public String getDialogue(Season season, TimeOfDay time) {
        return dialogues.getOrDefault(season, Map.of())
                .getOrDefault(time, "Hi there!");
    }

    public String getName() {
        return name;
    }

    public List<GameObjectType> getFavorites() {
        return favorites;
    }

    public List<GameObject> getRequests() {
        return requests;
    }

    public List<GameObject> getRewards() {
        return rewards;
    }

    public Map<Season, Map<TimeOfDay, String>> getDialogues() {
        return dialogues;
    }

    private static Map<Season, Map<TimeOfDay, String>> createDialogues(Map<Season, Map<TimeOfDay, String>> map) {
        return map;
    }
}
