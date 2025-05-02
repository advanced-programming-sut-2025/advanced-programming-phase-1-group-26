package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands implements Command{
    CHANGE_USERNAME(""),
    CHANGE_PASSWORD(""),
    CHANGE_NICKNAME(""),
    CHANGE_EMAIL(""),
    USER_INFO(""),

    ;

    private final Pattern pattern;
    ProfileCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
}
