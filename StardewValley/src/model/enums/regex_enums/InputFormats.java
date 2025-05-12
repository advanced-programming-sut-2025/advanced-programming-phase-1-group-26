package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputFormats implements Command{
    USERNAME(""),
    NICKNAME(""),
    PASSWORD(""),
    EMAIL("")

    ;
    private final Pattern pattern;
    InputFormats(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input)
    {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
