package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GeneralCommands implements Command
{
    /* Player Commands */
    ENERGY_SHOW("energy show"),
    INVENTORY_SHOW("inventory show"),
    INVENTORY_TRASH_NUMBER("inventory trash -i (?<name>.*) -n (?<number>\\d+)"),
    INVENTORY_TRASH("inventory trash -i (?<name>.*)"),
    TOOLS_EQUIP("tools equip (?<name>.*)"),
    TOOLS_SHOW_CURRENT("tools show current"),
    TOOLS_SHOW_AVAILABLE("tools show available"),
    TOOLS_UPGRADE("tools upgrade (?<toolName>.*)"),
    CHEAT_TOOL_CHECK("tools check (?<direction>.*)"),

    /* player cheat codes */
    ENERGY_SET("energy set -v (?<value>\\d+)"),
    ENERGY_UNLIMITED("energy unlimited"),

    /* time and date commands */
    SHOW_TIME("time"),
    SHOW_DATE("date"),
    SHOW_DATE_TIME("datetime"),
    SHOW_DAY_OF_WEEK("day\\s+of\\s+the\\s+week"),
    SHOW_SEASON("season"),

    /* time and date cheat codes */
    CHEAT_CODE_ADVANCE_TIME("cheat\\s+advance\\s+time\\s+-h\\s+(?<time>\\d+)"),
    CHEAT_CODE_ADVANCE_DATE("cheat\\s+advance\\s+date\\s+-d\\s+(?<date>\\d+)"),

    /* weather  commands*/
    SHOW_WEATHER("weather"),
    SHOW_TOMORROW_WEATHER("weather\\s+forecast"),

    /* weather cheat codes */
    CHEAT_CODE_SET_TOMORROW_WEATHER("cheat\\s+weather\\s+set\\s+(?<type>\\S+)"),
    CHEAT_CODE_HIT_THUNDER("cheat\\s+Thor\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)"),

    /* basic map commands */
    PWD("pwd"),
    SHOW_AROUND("show\\s+around"),
    PRINT_MAP("print\\s+map\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)-s\\s+(?<size>\\d+)"),
    PRINT_ENTIRE_MAP("print\\s+entire\\s+map"),

    /* walk commands */
    CAN_WALK("can\\s+walk\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)"),
    WALK("walk\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)"),
    SHOW_PATH("show\\s+path\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)"),

    /* walk cheat code */
    SUDO_CD("sudo\\s+cd\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)"),

    /* basic general commands */
    WHOAMI("whoami"),
    NEXT_TURN("next\\s+turn"),
    SUDO_NEXT_TURN("sudo\\s+next\\s+turn"),
    EXIT_GAME("exit\\s+game"),
    DELETE_GAME("delete\\s+game"),
    SHOW_MONEY("show\\s+money"),
    START_TRADE("start\\s+trade"),
    ;

    private final Pattern pattern;

    GeneralCommands(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input)
    {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
