package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command
{
    default boolean matches(String input) {
        return getMatcher(input).matches();
    }
    Matcher getMatcher(String input);
}
