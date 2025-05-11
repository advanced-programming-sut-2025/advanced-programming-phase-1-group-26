package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainCommands implements Command
{
    USER_LOGOUT("user\\s+logout"),
    MENU_ENTER("menu\\s+enter\\s+(?<menuName>\\S+)"),
    MENU_EXIT("menu\\s+exit"),
    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    HELP("help"),

    ;
    private final Pattern pattern;

    MainCommands(String regex)
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
