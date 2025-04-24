package model.enums;

import model.Point;

import java.util.List;

public enum FarmTypes
{
    STANDARD("standard", new Point (10, 45), new Point(50, 25), 15, 10,
            null, -1, -1, new Point(7, 5), 10,
            7, new Point(20, 25), List.of(new Point(0, 35), new Point(69, 35)));
    ;

    private final String name;

    private final Point cabinPoint;
    private final int cabinLength = 4;
    private final int cabinWidth = 4;

    private final Point firstLakePoint;
    private final int firstLakeLength;
    private final int firstLakeWidth;

    private final Point secondLakePoint;
    private final int secondLakeLength;
    private final int secondLakeWidth;

    private final Point QuarryPoint;
    private final int QuarryLength;
    private final int QuarryWidth;

    private final Point greenHousePoint;
    private final int greenHouseLength = 6;
    private final int greenHouseWidth = 5;

    private final List<Point> exitPoints;

    FarmTypes(String name, Point cabinPoint, Point firatLakePoint, int firstLakeLength, int firstLakeWidth,
              Point secondLakePoint, int secondLakeLength, int secondLakeWidth, Point quarryPoint, int quarryLength,
              int quarryWidth, Point greenHousePoint, List<Point> exitPoints)
    {
        this.name = name;
        this.cabinPoint = cabinPoint;
        this.firstLakePoint = firatLakePoint;
        this.firstLakeLength = firstLakeLength;
        this.firstLakeWidth = firstLakeWidth;
        this.secondLakePoint = secondLakePoint;
        this.secondLakeLength = secondLakeLength;
        this.secondLakeWidth = secondLakeWidth;
        QuarryPoint = quarryPoint;
        QuarryLength = quarryLength;
        QuarryWidth = quarryWidth;
        this.greenHousePoint = greenHousePoint;
        this.exitPoints = exitPoints;
    }

    public Point getCabinPoint()
    {
        return cabinPoint;
    }

    public int getCabinLength()
    {
        return cabinLength;
    }

    public int getCabinWidth()
    {
        return cabinWidth;
    }

    public Point getFirstLakePoint()
    {
        return firstLakePoint;
    }

    public int getFirstLakeLength()
    {
        return firstLakeLength;
    }

    public int getFirstLakeWidth()
    {
        return firstLakeWidth;
    }

    public Point getSecondLakePoint()
    {
        return secondLakePoint;
    }

    public int getSecondLakeLength()
    {
        return secondLakeLength;
    }

    public int getSecondLakeWidth()
    {
        return secondLakeWidth;
    }

    public Point getQuarryPoint()
    {
        return QuarryPoint;
    }

    public int getQuarryLength()
    {
        return QuarryLength;
    }

    public int getQuarryWidth()
    {
        return QuarryWidth;
    }

    public Point getGreenHousePoint()
    {
        return greenHousePoint;
    }

    public int getGreenHouseLength()
    {
        return greenHouseLength;
    }

    public int getGreenHouseWidth()
    {
        return greenHouseWidth;
    }

    public List<Point> getExitPoints()
    {
        return exitPoints;
    }
}
