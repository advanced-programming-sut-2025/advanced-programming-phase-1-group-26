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
    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_AVAILABLE_PRODUCTS("show available products"),
    PURCHASE("purchase (?<name>.*) ([-n (?<count>\\d+)]?)"),
    SELL("sell (?<name>.*) ([-n (?<count>\\d+)]?)"),
    /* Player Commands */
    ENERGY_SHOW("energy show"),
    INVENTORY_SHOW("inventory show"),
    INVENTORY_TRASH("inventory trash -i (?<name>.*) -n (?<number>\\d+)"),
    TOOLS_EQUIP ("tools equip (?<name>.*)"),
    TOOLS_SHOW_CURRENT("tools show current"),
    TOOLS_SHOW_AVAILABLE("tools show available"),
    TOOLS_UPGRADE("tools upgrade (?<tool_name>.*)"),
    TOOLS_USE("tools use -d (?<direction>.*)"),


    /* cheat codes */
    ENERGY_SET("energy set -v (?<value>\\d+)"),
    ENERGY_UNLIMITED("energy unlimited"),
    ADD_MONEY("cheat add (?<count>\\d+) dollars"),


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
