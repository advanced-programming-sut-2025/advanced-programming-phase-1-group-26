package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands implements Command
{
    /* NPC Commands */
    MEET_NPC("meet NPC (?<NPCname>\\w+)"),
    GIFT_NPC("gift NPC (?<NPCname>\\w+) -i (?<item>.*)"),
    FRIENDSHIP_NPC_LIST("friendship NPC list"),
    QUESTS_LIST("quests list"),
    QUESTS_FINISH("quests finish -i (?<index>.*)"),
    /* Marketing Commands*/
    SHOW_ALL_PRODUCTS(""),
    SHOW_AVAILABLE_PRODUCTS(""),
    PURCHASE(""),
    SELL(""),
    /* Player Commands */
    ENERGY_SHOW("energy show"),
    INVENTORY_SHOW("inventory show"),
    INVENTORY_TRASH_NUMBER("inventory trash -i (?<name>.*) -n (?<number>\\d+)"),
    INVENTORY_TRASH("inventory trash -i (?<name>.*)"),
    TOOLS_EQUIP("tools equip (?<name>.*)"),
    TOOLS_SHOW_CURRENT("tools show current"),
    TOOLS_SHOW_AVAILABLE("tools show available"),
    TOOLS_UPGRADE("tools upgrade (?<toolName>.*)"),
    TOOLS_USE("tools use -d (?<direction>.*)"),


    /* cheat codes */
    ENERGY_SET("energy set -v (?<value>\\d+)"),
    ENERGY_UNLIMITED("energy unlimited"),
    SET_FRIENDSHIP("cheat set friendship -n (?<name>.*) -c (?<amount>\\d+)"),


    SHOW_TIME("time"),
    SHOW_DATE("date"),
    SHOW_DATE_TIME("datetime"),
    SHOW_DAY_OF_WEEK("day\\s+of\\s+the\\s+week"),
    SHOW_SEASON("season"),

    CHEAT_CODE_ADVANCE_TIME("cheat\\s+advance\\s+time\\s+(?<time>\\d+)h"),
    CHEAT_CODE_ADVANCE_DATE("cheat\\s+advance\\s+date\\s+(?<date>\\d+)d"),

    SHOW_WEATHER("weather"),
    SHOW_TOMORROW_WEATHER("weather\\s+forecast"),

    CHEAT_CODE_SET_TOMORROW_WEATHER("cheat\\s+weather\\s+set\\s+(?<type>\\S+)"),
    CHEAT_CODE_HIT_THUNDER("cheat\\s+Thor\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)"),

    HELP_READ_MAP("help\\s+reading\\s+map"),

    /*Animal Commands*/
    BUILD_ANIMAL_HOUSE("build -a (?<name>.*) -l (?<y>\\d+) (?<x>\\d+)"),
    BUY_ANIMAL("buy animal -a (?<animal>.*) -n (?<name>.*)"),
    PET_ANIMAL("pet -n (?<name>.*)"),
    ANIMAL_INFOS("animals"),
    SHEPHERD_ANIMAL("shepherd animals -n (?<name>.*) -l (?<y>\\d+) (?<x>\\d+)"),
    FEED_HAY("feed hay -n (?<name>.*)"),
    PRODUCES("produces"),
    COLLECT_PRODUCES("collect produces -n (?<name>.*)"),
    SELL_ANIMAL("sell animal -n (?<name>.*)"),

    EXIT_GAME("exit\\s+game"),
    DELETE_GAME("delete\\s+game"),
    NEXT_TURN("next\\s+turn"),

    SHOW_CRAFT_INFO("craftinfo\\s+-n\\s+(?<craftName>.*)"),
    PLANT_SEED("plant\\s+" +
            "-s\\s+(?<seed>.*)\\s+" +
            "-d\\s+(?<direction>.*)"),
    FERTILIZE("fertilize\\s+" +
            "-f\\s+(?<fertilizer>.*)\\s+" +
            "-d\\s+(?<direction>\\S+)"),
    HOW_MUCH_WATER("how\\s+much\\s+water"),

    GO_TO_CABIN("cd\\s+cabin"),

    HELP_READING_MAP("help\\s+reading\\s+map"),
    BUILD_GREENHOUSE("greenhouse\\s+build"),
    SHOW_AROUND("show\\s+around"),
    ;

    private final Pattern pattern;

    GameCommands(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input)
    {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
