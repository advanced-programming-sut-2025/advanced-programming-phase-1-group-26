package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands implements Command{
    LOGIN("^login -u (?<username>\\S+) -p (?<password>\\S+)(?: (?<flag>-stay-logged-in))?$"),
    FORGET_PASSWORD("^forget password -u (?<username>\\S+)$"),
    MENU_ENTER("menu\\s+enter\\s+(?<menuName>\\S+)"),
    MENU_EXIT("menu\\s+exit"),
    MENU_BACK("menu\\s+back"),
    HELP("help"),
    SHOW_CURRENT_MENU("show current menu"),
    ;

    private final Pattern pattern;
    LoginCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
