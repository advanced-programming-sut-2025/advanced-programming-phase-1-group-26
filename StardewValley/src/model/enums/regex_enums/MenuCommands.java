package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MenuCommands implements Command{ //+MainMenuCommand
    LOGOUT(""),
    MENU_ENTER(""),
    MENU_EXIT(""),
    SHOW_CURRENT_MENU(""),

    ;
    private final Pattern pattern;
    MenuCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }

}
