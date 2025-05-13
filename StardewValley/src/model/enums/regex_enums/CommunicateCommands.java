package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommunicateCommands implements Command {

    FRIENDSHIP("^friendship$"),
    TALK("talk -u (?<username>.*) -m (?<message>.*)"),
    TALK_HISTORY("talk history -u (?<username>.*)"),
    GIFT("gift -u (?<username>.*) -i (?<item>.*) -a (?<amount>\\d+)"),
    GIFT_LIST("gift list"),
    GIFT_RATE("gift rate -i (?<giftNumber>\\d+) -r (?<rate>\\d+)"),
    GIFT_HISTORY("gift history -u (?<username>.*)"),
    HUG("hug (?<username>.*)"),
    FLOWER("flower (?<username>.*)"),
    ASK_MARRIAGE("ask marriage -u (?<username>.*) -r (?<ring>.*)"),
    RESPOND("respond -(?<respond>I do|cancel) -u (?<username>.*)"),

    CHEAT_UPGRADE_FRIENDSHIP("cheat upgrade friendship -u (?<name>\\S+) -l (?<level>\\d+)"),
    ;

    private final Pattern pattern;
    CommunicateCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
