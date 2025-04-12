package model.enums;

public enum SecurityQuestionType
{
    CAT_OR_DOG("Are you a cat person or a dog person?"),
    NERDFIGHTERIA_TEST("Which one is better? Puppy sized elephants or elephant sized puppies?"),
    ;

    private final String question;

    SecurityQuestionType(String question)
    {
        this.question = question;
    }
}
