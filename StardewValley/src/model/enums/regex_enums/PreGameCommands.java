package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PreGameCommands implements Command
{
    NEW_GAME("game\\s+new\\s+-u" +
            "(\\s+(?<user1>\\S*))?" +
            "(\\s+(?<user2>\\S*))?" +
            "(\\s+(?<user3>\\S*))?"),
    LOAD_GAME("load\\s+game"),
    ;

    private final Pattern pattern;

    PreGameCommands(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
