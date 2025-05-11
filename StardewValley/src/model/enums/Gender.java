package model.enums;

public enum Gender
{
    MALE("male"),
    FEMALE("female"),
    ;

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

    public static Gender getGender(String name)
    {
        for (Gender gender : Gender.values())
        {
            if (gender.toString().equals(name))
            {
                return gender;
            }
        }
        return null;
    }
}
