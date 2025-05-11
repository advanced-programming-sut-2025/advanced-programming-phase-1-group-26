package model.enums.regex_enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterCommands implements Command{
    REGISTER("register -u (?<username>.*) -p (random password|(?<password>.*) (?<passwordConfirm>.*))" +
            " -n (?<nickname>.*) -e (?<email>.*) -g (?<gender>.*)"),
    CHECK_USERNAME("^[a-zA-Z0-9-]+$"),
    CHECK_PASSWORD("^[a-zA-Z0-9!#$%^&*)(=+}{\\]\\[|\\/:;'\",><?]+$"),
    CHECK_EMAIL("^[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._-]*[a-zA-Z0-9]@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$"),
    PICK_QUESTION("pick question -q (?<questionNumber>\\d+) -a (?<answer>\\d+) -c (?<answerConfirm>\\d+)"),
    ;

    private final Pattern pattern;
    RegisterCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
