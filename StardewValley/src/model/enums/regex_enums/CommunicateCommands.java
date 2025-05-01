package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommunicateCommands implements Command {

    FRIENDSHIP("friendship"),
    TALK("talk -u (?<username>.*) -m (?<message>.*)"),
    TALK_HISTORY("talk history -u (?<username>.*)"),
    GIFT("gift -u (?<username>.*) -i (?<item>.*) -a (?<amount>\\d+)"),
    GIFT_LIST("gift list"),
    GIFT_RATE("gift rate -i (?<giftNumber?\\d+) -r (?<rate>\\d+)"),
    GIFT_HISTORY("gift history -u (?<username>.*)"),
    HUG("hug (?<username>.*)"),
    FLOWER("flower (?<username>.*)"),
    ASK_MARRIAGE("ask marriage -u (?<username>.*) -r (?<ring>.*)"),
    RESPOND("respond -(?<respond>accept|reject) -u (?<username>.*)"),
    START_TRADE("start trade"),
    TRADE("trade -u (?<username>.*) -t (?<type>.*) -i (?<item>.*) -a (?<amount>\\d+) " +
            "(-p (?<price>\\d+)|-ti (?<targetItem>.*) -ta (?<targetAmount>)\\d+))"),
    TRADE_LIST("trade list"),
    TRADE_RESPOND("trade respond -(?<respond>accept|reject) -i (?<id>\\d+)"),
    TRADE_HISTORY("trade history"),

    ;

    private final Pattern pattern;
    CommunicateCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
}
