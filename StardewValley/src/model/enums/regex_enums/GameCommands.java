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

    HELP_READ_MAP("help\\s+reading\\s+map"),
    TOOLS_USE("tools use -d (?<direction>.*)"),

    SHOW_CRAFT_INFO("craftinfo\\s+-n\\s+(?<craftName>.*)"),
    PLANT_SEED("plant\\s+" +
            "-s\\s+(?<seed>.*)\\s+" +
            "-d\\s+(?<direction>.*)"),
    FERTILIZE("fertilize\\s+" +
            "-f\\s+(?<fertilizer>.*)\\s+" +
            "-d\\s+(?<direction>\\S+)"),
    HOW_MUCH_WATER("how\\s+much\\s+water"),

    CD_PLACE("cd\\s+(?<placeName>.+)"),

    HELP_READING_MAP("help\\s+reading\\s+map"),
    BUILD_GREENHOUSE("greenhouse\\s+build"),
    CHEAT_ADD_MONEY("cheat\\s+add\\s+money\\s+-a\\s+(?<amount>\\d+)"),
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
