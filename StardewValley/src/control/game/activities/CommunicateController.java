package control.game.activities;

import model.App;
import model.GameObject;
import model.Player;
import model.player_data.FriendshipData;
import model.enums.GameObjectType;

import java.util.Map;

public class CommunicateController
{

    /* friendship methods */

    public void friendships() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        for (Map.Entry<Player, FriendshipData> entry : currentPlayer.getFriendships().entrySet()) {
            Player otherPlayer = entry.getKey();
            int level = entry.getValue().getLevel();
            int xp = entry.getValue().getXp();
            System.out.printf("%s: Level: %d XP: %d\n", otherPlayer, level, xp);
        }

    }

    public void upgradeFriendshipLevel (Player mainPlayer, Player player2) {
        FriendshipData currentLevel = mainPlayer.getFriendships().get(player2);
        FriendshipData otherLevel = player2.getFriendships().get(mainPlayer);

        switch (mainPlayer.getFriendships().get(player2).getLevel()) {
            case 0:
                while (currentLevel.getXp() >= 100) {
                    currentLevel.addLevel();
                    currentLevel.setXp(currentLevel.getXp() - 100);
                    otherLevel.addLevel();
                    otherLevel.setXp(currentLevel.getXp() - 100);
                    System.out.println("your friendship with " + player2 +
                            " upgraded to level: " + currentLevel.getLevel());
                }
                break;
            case 1:
                while (currentLevel.getXp() >= 200) {
                    currentLevel.addLevel();
                    currentLevel.setXp(currentLevel.getXp() - 200);
                    otherLevel.addLevel();
                    otherLevel.setXp(currentLevel.getXp() - 200);
                    System.out.println("your friendship with " + player2 +
                            " upgraded to level: " + currentLevel.getLevel());
                }
                break;
            case 2:
                while (currentLevel.getXp() >= 300) {
                    if (currentLevel.isBouquetBought()) {
                        currentLevel.addLevel();
                        currentLevel.setXp(currentLevel.getXp() - 300);
                        otherLevel.addLevel();
                        otherLevel.setXp(currentLevel.getXp() - 300);
                        System.out.println("your friendship with " + player2 +
                                " upgraded to level: " + currentLevel.getLevel());
                    } else {
                        System.out.println("buy bouquet to upgrade your friendship with " + player2);
                        break;
                    }
                }
                break;
            case 3:
                if (currentLevel.getXp() >= 400) {
                    if (currentLevel.isMarried()) {
                        currentLevel.addLevel();
                        currentLevel.setXp(0);
                        otherLevel.addLevel();
                        otherLevel.setXp(0);
                        System.out.println("your friendship with " + player2 +
                                " upgraded to level: " + currentLevel.getLevel());
                    } else {
                        System.out.println("you should marry each other to upgrade your friendship");
                    }
                }
                break;
            default:
                break;
        }
    }

    public boolean checkFriendship (Player player1, Player player2, String command) { //TODO: might change string to command

        int level = player1.getFriendships().get(player2).getLevel();

        if (level >= 0) {
            if (command.equals("talk") || command.equals("trade")) {
                return true;
            }
        } if (level >= 1) {
            if (command.equals("gift")) {
                return true;
            }
        } if (level >= 2) {
            if (command.equals("hug")) {
                return true;
            }
        } if (level >= 3) {
            if (command.equals("marriage")) {
                return true;
            }
        }
        return false;
    }

    public void talk (Player player, String message) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();

        FriendshipData data1 = currentPlayer.getFriendships().get(player);
        FriendshipData data2 = player.getFriendships().get(currentPlayer);
        data1.getMessageHistory().add(message);
        data2.getMessageHistory().add(message);

        if (!data1.isIntrcatedToday()) {
            data1.addXp(20);
            data2.addXp(20);
            data1.setIntrcatedToday(true);
            data2.setIntrcatedToday(true);
        }
    }

    public void talkHistory(Player player) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        FriendshipData data1 = currentPlayer.getFriendships().get(player);
        for (String message : data1.getMessageHistory()) {
            System.out.println(message);
        }
    }

    //gifting methods

    public void gift (Player player, GameObjectType item, int amount) {
        
    }


    public void giftList () {

    }

    public void giftRate () {

    }

    public void giftHistory () {

    }


    //hugging

    public void giveHug (Player player) {

    }

    //flowering

    public void giveFlower (Player player) {

    }

    //marriage

    public void purposeAsk (Player player, GameObject ring) {

    }

    public void purposeRespond (Player player, boolean answer) {

    }


}
