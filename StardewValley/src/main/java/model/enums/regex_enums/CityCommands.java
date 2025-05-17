package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CityCommands implements Command
{
    CD_FARM("cd\\s+farm"),

    MEET_NPC("meet\\s+NPC\\s+(?<NPCname>\\S+)"),
    GIFT_NPC("gift\\s+NPC\\s+(?<NPCname>\\S+)\\s+-i\\s+(?<item>\\S+)"),
    FRIENDSHIP_NPC_LIST("friendship\\s+NPC\\s+list"),
    QUESTS_LIST("quests\\s+list"),
    QUESTS_FINISH("quests\\s+finish\\s+-i\\s+(?<index>\\d+)"),

    SHOW_GIFTS("show\\s+NPC\\s+gifts"),
    OPEN_GIFTS("open\\s+NPC\\s+gifts"),
    WHERE_NPC("where\\s+is\\s+(?<NPCname>\\S+)"),
    ;

    private final Pattern pattern;

    CityCommands(String regex)
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
