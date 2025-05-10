package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum HomeCommands implements Command
{
    SHOW_RECIPES("crafting\\s+show\\s+recipes"),
    ;

    private final String pattern;

    HomeCommands(String regex)
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
