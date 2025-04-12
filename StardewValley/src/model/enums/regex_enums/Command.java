package model.enums.regex_enums;

import java.util.regex.Matcher;

public interface Command {
    Matcher getMatcher(String input);

    default boolean matches(String input) {
        return getMatcher(input).matches();
    }

    default String getGroup(String input, String group) {
        return getMatcher(input).group(group);
    }
}
