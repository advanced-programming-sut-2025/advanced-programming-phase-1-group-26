package model.enums;

public enum SecurityQuestionType
{
    CAT_OR_DOG("Are you a cat person (1) or a dog person (2)?", 1),
    NERDFIGHTERIA_TEST("Which one is better? Puppy sized elephants (1) or elephant sized puppies (2)?", 2),
    ;

    private final String question;
    private final int id;

    SecurityQuestionType(String question, int id)
    {
        this.question = question;
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }

    public static String getQuestionById (int id) {
        for (SecurityQuestionType type : SecurityQuestionType.values()) {
            if (type.id == id) {
                return type.question;
            }
        }
        return null;
    }
}
