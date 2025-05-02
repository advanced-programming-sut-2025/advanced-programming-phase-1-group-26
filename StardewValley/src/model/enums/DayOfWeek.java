package model.enums;

public enum DayOfWeek
{
    Monday(0, "Monday"),
    Tuesday(1, "Tuesday"),
    Wednesday(2, "Wednesday"),
    Thursday(3, "Thursday"),
    Friday(4, "Friday"),
    Saturday(5, "Saturday"),
    Sunday(6, "Sunday");

    private final int id;
    private final String name;

    DayOfWeek(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public static String getDayOfWeek(int id)
    {
        return DayOfWeek.values()[id].name();
    }
}
