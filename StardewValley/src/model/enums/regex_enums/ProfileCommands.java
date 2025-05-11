package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands implements Command
{
    CHANGE_USERNAME("change\\s+username\\s+" +
            "-u\\s+(?<newUsername>.+)"),
    CHANGE_PASSWORD("change\\s+password\\s+" +
            "-p\\s+(?<newPassword>\\S+)\\s+" +
            "-o\\s+(?<oldPassword>\\S+)"),
    SET_PASSWORD_TO_RANDOM("set\\s+random\\s+password\\s+" +
            "-o\\s+(?<oldPassword>\\S+)"),
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
