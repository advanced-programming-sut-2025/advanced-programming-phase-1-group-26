package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands implements Command {

    TRADE("trade -u (?<username>.*) -t (?<type>.*) -i (?<item>.*) -a (?<amount>\\d+) " +
                  "(-p (?<price>\\d+)|-ti (?<targetItem>.*?) -ta (?<targetAmount>\\d+))"),
    TRADE_LIST("trade list"),
    TRADE_RESPOND("trade respond -(?<respond>accept|reject) -i (?<id>\\d+)"),
    TRADE_HISTORY("trade history"),
    END_TRADE("end trade"),

    ;

    private final Pattern pattern;
    TradeCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
