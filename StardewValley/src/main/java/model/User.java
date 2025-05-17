package model;

import model.enums.Gender;

import java.util.ArrayList;
import java.util.Collections;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Gender gender;
    private String question;
    private int answer;
    private boolean stay;

    private int numberOfGames = 0;
    private Game currentGame = null;
    private ArrayList<Integer> gameMoney = new ArrayList<>();

    public User(String name)
    {
        this.username = name;
        this.nickname = name;

        if (Math.random() < 0.5)
        {
            this.gender = Gender.MALE;
        } else
        {
            this.gender = Gender.FEMALE;
        }
    }

    public User(String username, String password, String nickname, String email, Gender gender) {
        this.username = username;
        this.password = SHA256Hasher.hash(password);
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

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getNumberOfGames()
    {
        return numberOfGames;
    }

    public boolean hasCurrentGame()
    {
        return (currentGame != null);
    }

    public ArrayList<Integer> getGameMoney()
    {
        return gameMoney;
    }

    public void setCurrentGame(Game game)
    {
        this.currentGame = game;
    }

    public void addToNumberOfGames()
    {
        this.numberOfGames += 1;
    }

    public void addMoneyGame(int amount)
    {
        this.gameMoney.add(amount);
    }

    public int getMaxMoney()
    {
        if (gameMoney.isEmpty())
        {
            return 0;
        }

        return Collections.max(gameMoney);
    }

    public Game getCurrentGame()
    {
        return currentGame;
    }
}


