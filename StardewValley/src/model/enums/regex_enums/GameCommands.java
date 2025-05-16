package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands implements Command
{
    /* Marketing Commands*/
    SHOW_ALL_PRODUCTS("show\\s+all\\s+products"),
    SHOW_AVAILABLE_PRODUCTS("show\\s+all\\s+available\\s+products"),
    PURCHASE("purchase\\s+(?<productName>.+)"),
    PURCHASE_N("purchase\\s+(?<productName>.+)\\s+-n\\s+(?<count>\\d+)"),
    SELL("sell\\s+(?<productName>.+)"),
    SELL_N("sell\\s+(?<productName>.+)\\s+-n\\s+(?<count>\\d+)"),

    HELP_READ_MAP("help\\s+reading\\s+map"),
    TOOLS_USE("tools use -d (?<direction>.*)"),

    SHOW_CRAFT_INFO("craftinfo\\s+-n\\s+(?<craftName>.*)"),
    PLANT_SEED("plant\\s+" +
            "-s\\s+(?<seed>.*)\\s+" +
            "-d\\s+(?<direction>.*)"),
    FERTILIZE("fertilize\\s+" +
            "-f\\s+(?<fertilizer>.*)\\s+" +
            "-d\\s+(?<direction>\\S+)"),
    HOW_MUCH_WATER("how\\s+much\\s+water"),
    SHOW_PLANT("show\\s+plant\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)"),

    CD_PLACE("cd\\s+(?<placeName>.+)"),

    HELP_READING_MAP("help\\s+reading\\s+map"),
    BUILD_GREENHOUSE("greenhouse\\s+build"),
    CHEAT_ADD_MONEY("cheat\\s+add\\s+money\\s+-a\\s+(?<amount>\\d+)"),

    /*Animal Commands*/
    BUILD_ANIMAL_HOUSE("build\\s+-a\\s+(?<name>\\S+)\\s+-l\\s+(?<x>\\d+)\\s+(?<y>\\d+)"),
    BUY_ANIMAL("buy\\s+animal\\s+-a\\s+(?<animal>\\S+)\\s+-n (?<name>.*)"),
    PET_ANIMAL("pet\\s+-n\\s+(?<name>\\S+)"),
    ANIMAL_INFOS("animals"),
    SHEPHERD_ANIMAL("shepherd\\s+animals\\s+-n\\s+(?<name>\\S+)\\s+-l\\s+(?<x>\\d+)\\s+(?<y>\\d+)"),
    FEED_HAY("feed\\s+hay\\s+-n\\s+(?<name>\\S+)"),
    PRODUCES("produces"),
    COLLECT_PRODUCES("collect\\s+produces\\s+-n\\s+(?<name>\\S+)"),
    SELL_ANIMAL("sell\\s+animal\\s+-n\\s+(?<name>\\S+)"),
    FISHING("fishing\\s+-p\\s+(?<fishingPole>\\S+)"),

    EXIT_GAME("exit\\s+game"),
    DELETE_GAME("delete\\s+game"),
    NEXT_TURN("next\\s+turn"),

    GO_TO_CABIN("go\\s+to\\s+cabin"),
    WHOAMI("whoami"),
    SUDO_NEXT_TURN("sudo\\s+next\\s+turn"),
    SHOW_AROUND("show\\s+around"),
    SET_FRIENDSHIP("cheat\\s+set\\s+friendship\\s+-n\\s+(?<name>.*)\\s+-c\\s+(?<amount>\\d+)"),

    ARTISAN_USE("artisan\\s+use\\s+(?<artisanName>\\S+)(\\s+(?<ingredientOne>\\S+))?(\\s+(?<ingredientTwo>\\S+))?"),
    ARTISAN_GET("artisan\\s+get\\s+(?<artisanName>\\S+)"),
    ;

    private final Pattern pattern;

    GameCommands(String regex)
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
