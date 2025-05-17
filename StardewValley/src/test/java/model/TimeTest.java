package model;

import model.enums.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    @Test
    public void testAdvanceHourNormalHour() {
        Time time = new Time();
        int currentHour = time.getHour();
        time.updateHour(1);
        int advancedTime = time.getHour();
        assertEquals(currentHour + 1, advancedTime);
    }

    @Test
    public void testAdvanceHourEdgeHour() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        int currentHour = time.getHour();
        time.updateHour(14);
        int advancedTime = time.getHour();
        assertEquals(currentHour, advancedTime);
    }

    @Test
    public void testAdvanceHourDay() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        int currentDay = time.getDay();
        time.updateHour(14);
        int advancedDay = time.getDay();
        assertEquals(currentDay + 1, advancedDay);
    }

    @Test
    public void testAdvanceHourTimeOfDayAfternoon() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateHour(4);
        TimeOfDay expected = time.getTimeOfDay();
        assertEquals(expected, TimeOfDay.AFTERNOON);
    }

    @Test
    public void testAdvanceHourTimeOfDayEvening() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateHour(9);
        TimeOfDay expected = time.getTimeOfDay();
        assertEquals(expected, TimeOfDay.EVENING);
    }

    @Test
    public void testAdvanceHourTimeOfDayMorning() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateHour(14);
        TimeOfDay expected = time.getTimeOfDay();
        assertEquals(expected, TimeOfDay.MORNING);
    }

    @Test
    public void testAdvanceDayNormalDay() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        int currentDay = time.getDay();
        time.updateDay(1, false);
        int advancedDay = time.getDay();
        assertEquals(currentDay + 1, advancedDay);
    }

    @Test
    public void testAdvanceDayEdgeDay() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        int currentDay = time.getDay();
        time.updateDay(28, false);
        int advancedDay = time.getDay();
        assertEquals(currentDay, advancedDay);
    }

    @Test
    public void testAdvanceDaySeasonSpring() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateDay(27, false);
        Season currentSeason = time.getSeason();
        assertEquals(currentSeason, Season.Spring);
    }

    @Test
    public void testAdvanceDaySeasonSummer() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateDay(28, false);
        Season currentSeason = time.getSeason();
        assertEquals(currentSeason, Season.Summer);
    }

    @Test
    public void testAdvanceDaySeasonFall() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateDay(56, false);
        Season currentSeason = time.getSeason();
        assertEquals(currentSeason, Season.Fall);
    }

    @Test
    public void testAdvanceDaySeasonWinter() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateDay(84, false);
        Season currentSeason = time.getSeason();
        assertEquals(currentSeason, Season.Winter);
    }


    @Test
    public void testAdvanceDaySeasonSpringAgain() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateDay(112, false);
        Season currentSeason = time.getSeason();
        assertEquals(currentSeason, Season.Spring);
    }

    @Test
    public void testAdvanceHourNextTurn() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        int currentHour = time.getHour();
        mockGame.nextTurn();
        int second = time.getHour();
        assertEquals(currentHour, second);
    }

    @Test //TODO: check later
    public void testAdvanceHourNextTurnFour() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        int currentHour = time.getHour();
        mockGame.nextTurn();
        mockGame.nextTurn();
        mockGame.nextTurn();
        mockGame.nextTurn();
        int second = time.getHour();
        assertEquals(currentHour + 1, second);
    }

    @Test
    public void testAdvanceDayDayOfWeek() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateDay(1, false);
        int day = ((time.getDay() - 1) % 7);
        String currentDayStr = DayOfWeek.getDayOfWeek(day);
        assertEquals(currentDayStr, DayOfWeek.Tuesday.name());
    }

    @Test
    public void testAdvanceDayDayOfWeekNext() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        time.updateDay(7, false);
        int day = ((time.getDay() - 1) % 7);
        String currentDayStr = DayOfWeek.getDayOfWeek(day);
        assertEquals(currentDayStr, DayOfWeek.Monday.name());
    }

    @Test
    public void testAdvanceDayCheckWeather() {
        Player mockPlayer = Mockito.mock(Player.class);
        Game mockGame = Mockito.mock(Game.class);
        App.setCurrentGame(mockGame);
        mockGame.setCurrentPlayer(mockPlayer);
        Time time = new Time();
        int currentHour = time.getHour();
        time.updateHour(14);
        int advancedTime = time.getHour();
        assertEquals(currentHour, advancedTime);
    }
}