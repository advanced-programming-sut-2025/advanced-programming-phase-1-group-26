package control.game.activities;

import model.*;
import model.enums.Gender;
import model.player_data.FriendshipData;
import model.player_data.Trade;
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

    public static void upgradeFriendshipLevel (Player mainPlayer, Player player2) {
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

    public static boolean checkFriendship (Player player1, Player player2, String command) { //TODO: might change string to command

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
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (checkFriendship(currentPlayer, player, "gift")) {
            if (currentPlayer.getItemInInventory(item) == null ||
                    currentPlayer.getItemInInventory(item).getNumber() < amount) {
                System.out.println("you don't have enough number this item in your inventory!");
            } else {
                currentPlayer.getItemInInventory(item).addNumber(-amount);
                if (currentPlayer.getItemInInventory(item).getNumber() < 1) {
                    currentPlayer.getInventory().remove(item);
                }

                GameObject newObject = new GameObject(item, amount);
                Gift newGift = new Gift(newObject, currentPlayer, player);

                if (player.getItemInInventory(item) == null) {
                    player.getInventory().add(newObject);
                } else {
                    player.getItemInInventory(item).addNumber(amount);
                }

                player.getNewGifts().add(newGift);
                player.getArchiveGifts().add(newGift);
                currentPlayer.getGivenGifts().add(newGift);
            }
        } else {
            System.out.println("you can't give each other gifts at this level!");
        }
    }


    public void giftList () {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        System.out.println("your gifts: ");
        for (Gift object : currentPlayer.getArchiveGifts()) {
            System.out.println("id: " + object.getId() +
                    " item: " + object.getGameObject().getObjectType().name() +
                    " amount: " + object.getGameObject().getNumber());
            System.out.println("----");
        }
    }

    public void giftRate (int id, int rate) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Gift targetGift = currentPlayer.getGiftById(id);
        if (targetGift == null) {
            System.out.println("there's no gift with this id");
            return;
        } else if (rate < 1 || rate > 5) {
            System.out.println("your rate should be between 1 to 5");
            return;
        }

        targetGift.setRate(rate);
        Player giver = targetGift.getGiver();
        if (!currentPlayer.getFriendships().get(giver).isIntrcatedToday()) {
            int friendship = ((rate - 3) * 30) + 15;
            giver.getFriendships().get(currentPlayer).addXp(friendship);
            currentPlayer.getFriendships().get(giver).addXp(friendship);
            upgradeFriendshipLevel(currentPlayer, giver);
        }

    }

    public void giftHistory () {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        System.out.println("gits given: ");
        for (Gift object : currentPlayer.getGivenGifts())  {
            System.out.println("id: " + object.getId() +
                    " item: " + object.getGameObject().getObjectType().name() +
                    " amount: " + object.getGameObject().getNumber());
            System.out.println("----");
        }
        System.out.println("gifts taken: ");
        for (Gift object : currentPlayer.getArchiveGifts()) {
            System.out.println("id: " + object.getId() +
                    " item: " + object.getGameObject().getObjectType().name() +
                    " amount: " + object.getGameObject().getNumber());
            System.out.println("----");
        }
    }


    //hugging

    public void giveHug (Player player) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (checkFriendship(currentPlayer, player, "hug")) {
            if (!currentPlayer.getFriendships().get(player).isIntrcatedToday()) {
                player.getFriendships().get(currentPlayer).addXp(60);
                currentPlayer.getFriendships().get(player).addXp(60);
                upgradeFriendshipLevel(currentPlayer, player);
                currentPlayer.getFriendships().get(player).setIntrcatedToday(true);
            }
            System.out.println("you gave " + player.getUser().getNickname() + " a hug");
        }

    }

    //flowering

    public void giveFlower (Player player) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (checkFriendship(currentPlayer, player, "flower")) {
            if (currentPlayer.getItemInInventory(GameObjectType.FLOWER) != null) {
                currentPlayer.getItemInInventory(GameObjectType.FLOWER).addNumber(-1);
                if (currentPlayer.getItemInInventory(GameObjectType.FLOWER).getNumber() < 1) {
                    currentPlayer.getInventory().remove(currentPlayer.getItemInInventory(GameObjectType.FLOWER));
                }

                if (player.getItemInInventory(GameObjectType.FLOWER) != null) {
                    player.getItemInInventory(GameObjectType.FLOWER).addNumber(1);
                } else {
                    player.getInventory().add(new GameObject(GameObjectType.FLOWER, 1));
                }

                if (!currentPlayer.getFriendships().get(player).isIntrcatedToday()) {
                    currentPlayer.getFriendships().get(player).setBouquetBought(true);
                    player.getFriendships().get(currentPlayer).setBouquetBought(true);
                    upgradeFriendshipLevel(currentPlayer, player);
                }
            } else {
                System.out.println("you don't have flower in your inventory!");
            }
        } else {
            System.out.println("your friendship is not good enough to give each other flowers");
        }
    }

    //marriage

    public void purposeAsk (Player player, GameObject ring) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();


        if (currentPlayer.getUser().getGender().equals(Gender.FEMALE)) {
            System.out.println("you can't purpose ask doost pesaret");
        } else if (!checkFriendship(currentPlayer, player, "marriage")) {
            System.out.println("your friendship is not good enough to marry each other!");
        }

    }

    public void purposeRespond (Player player, boolean answer) {

    }


}
