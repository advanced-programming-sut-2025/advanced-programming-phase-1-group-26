package model;

import model.enums.Gender;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Gender gender;

    private int numberOfGames = 0;
    private boolean hasCurrentGame = false;
    private ArrayList<Integer> gameMoney = new ArrayList<>();

    public User(String username, String password, String nickname, String email, Gender gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getNumberOfGames()
    {
        return numberOfGames;
    }

    public boolean isHasCurrentGame()
    {
        return hasCurrentGame;
    }

    public ArrayList<Integer> getGameMoney()
    {
        return gameMoney;
    }

    public void setCurrentGame(boolean hasCurrentGame)
    {
        this.hasCurrentGame = hasCurrentGame;
    }

    public void addToNumberOfGames()
    {
        this.numberOfGames += 1;
    }

    public void addMoneyGame(int amount)
    {
        this.gameMoney.add(amount);
    }
}
