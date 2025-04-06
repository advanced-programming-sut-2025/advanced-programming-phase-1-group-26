package model.enums;

public enum Gender
{
    MALE("male"),
    FEMALE("female"),
    NON_Binary("non-binary");

    private final String name;

    Gender(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
