package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands implements Command{
    /* NPC Commands */
    MEET_NPC(""),
    GIFT_NPC(""),
    FRIENDSHIP_NPC_LIST(""),
    QUESTS_LIST(""),
    QUESTS_FINISH(""),
    /* Marketing Commands*/
    SHOW_ALL_PRODUCTS(""),
    SHOW_AVAILABLE_PRODUCTS(""),
    PURCHASE(""),
    SELL(""),
    /* Player Commands */
    ENERGY_SHOW("energy show"),
    INVENTORY_SHOW("inventory show"),
    INVENTORY_TRASH("inventory trash -i (?<name>.*) -n (?<number>\\d+)"),


    /* cheat codes */
    ENERGY_SET("energy set -v (?<value>\\d+)"),
    ENERGY_UNLIMITED("energy unlimited"),


    ;

    private final Pattern pattern;
    GameCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
}
