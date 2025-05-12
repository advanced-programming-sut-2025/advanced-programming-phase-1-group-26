package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegexCommands implements Command
{
    USERNAME_FORMAT("[a-zA-Z0-9-]+"),
    ;

    private final Pattern pattern;

    RegexCommands(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}