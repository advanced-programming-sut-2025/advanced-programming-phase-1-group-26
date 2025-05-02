package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands implements Command{
    LOGIN(""),
    FORGET_PASSWORD(""),
    ANSWER(""),

    ;

    private final Pattern pattern;
    LoginCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
}
