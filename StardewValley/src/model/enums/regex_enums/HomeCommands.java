package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum HomeCommands implements Command
{
    CRAFTING_SHOW_RECIPES("crafting\\s+show\\s+recipes"),
    CRAFTING_CRAFT("crafting\\s+craft\\s+(?<itemName>.*)"),
    PLACE_ITEM("place\\s+item\\s+" +
            "-n\\s+(?<itemName>.*)\\s+" +
            "-d\\s+(?<direction>\\S+)"),
    CHEAT_ADD_ITEM("cheat\\s+add\\s+item\\s+" +
            "-n\\s+(?<itemName>.*)\\s+" +
            "-c\\s+(?<count>\\d+)"),
    COOKING_PUT("cooking\\s+refrigerator\\s+put\\s+(?<item>.*)"),
    COOKING_PICK("cooking\\s+refrigerator\\s+pick\\s+(?<item>.*)"),
    COOKING_SHOW_RECIPES("cooking\\s+show\\s+recipe"),
    COOKING_PREPARE("cooking\\s+prepare\\s+(?<recipeName>.*)"),
    EAT("eat\\s+(?<foodName>.*)"),
    CHEAT_ADD_CRAFTING_RECIPE("cheat\\s+add\\s+crafting\\s+recipe\\s+(?<recipeName>.*)"),
    CHEAT_ADD_COOKING_RECIPE("cheat\\s+add\\s+cooking\\s+recipe\\s+(?<recipeName>.*)"),
    GO_OUT("go\\s+out"),
    ;

    private final Pattern pattern;

    HomeCommands(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
