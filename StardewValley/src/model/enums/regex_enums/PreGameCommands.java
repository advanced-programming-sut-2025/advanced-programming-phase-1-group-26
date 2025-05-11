package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PreGameCommands implements Command
{
    NEW_GAME("game\\s+new\\s+-u\\s+" +
            "(?<username1>\\S+)" +
            "(\\s+(?<username2>\\S+))?" +
            "(\\s+(?<username3>\\S+))?"),
    LOAD_GAME("load\\s+game"),
    ;

    private final Pattern pattern;

    PreGameCommands(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input)
    {
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
}
