package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands implements Command
{
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

    SHOW_TIME("time"),
    SHOW_DATE("date"),
    SHOW_DATE_TIME("datetime"),
    SHOW_DAY_OF_WEEK("day\\s+of\\s+the\\s+week"),
    SHOW_SEASON("season"),

    CHEAT_CODE_ADVANCE_TIME("cheat\\s+advance\\s+time\\s+(?<time>\\d+)h"),
    CHEAT_CODE_ADVANCE_DATE("cheat\\s+advance\\s+date\\s+(?<date>\\d+)d"),

    ;

    private final String pattern;

    GameCommands(String regex)
    {
        this.pattern = regex;
    }

    @Override
    public Matcher getMatcher(String input)
    {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches())
        {
            return matcher;
        }

        return null;
    }
}
