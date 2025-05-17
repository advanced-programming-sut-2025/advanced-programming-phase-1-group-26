package control.game.activities;

import model.*;
import model.enums.Gender;
import model.enums.shop_enums.PierresGeneralStoreYearRoundStock;
import model.player_data.FriendshipData;
import model.player_data.Trade;
import model.enums.GameObjectType;

import java.util.Map;
import java.util.regex.Matcher;

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

    public Result cheatUpgradeFriendship(Matcher matcher) {
        String name = matcher.group("name");
        Player player = App.getCurrentGame().getPlayerByNickname(name);
        Player mainPlayer = App.getCurrentGame().getCurrentPlayer();
        if (player == null || player.equals(mainPlayer)) {
            return new Result(false, "wrong name");
        }
        int level = Integer.parseInt(matcher.group("level"));

        FriendshipData currentLevel = mainPlayer.getFriendships().get(player);
        FriendshipData otherLevel = player.getFriendships().get(mainPlayer);
        currentLevel.setLevel(level);
        otherLevel.setLevel(level);

        return new Result(true, "level upgraded cheater");
    }

    public Result cheatUpgradeFriendshipLevel(Matcher matcher) {
        String name = matcher.group("name");
        Player player = App.getCurrentGame().getPlayerByNickname(name);
        int xp = Integer.parseInt(matcher.group("xp"));
        Player mainPlayer = App.getCurrentGame().getCurrentPlayer();
        FriendshipData currentLevel = mainPlayer.getFriendships().get(player);
        FriendshipData otherLevel = player.getFriendships().get(mainPlayer);
        currentLevel.setXp(xp);
        otherLevel.setXp(xp);

        return new Result(true, "xp upgraded cheater");
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
            if (command.equals("hug") || command.equals("flower")) {
                return true;
            }
        } if (level >= 3) {
            if (command.equals("marriage")) {
                return true;
            }
        }
        return false;
    }

    public Result talk (Matcher matcher) {
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.equals(player)) {
            return new Result(false, "you can't gift yourself");
        }
        String message = matcher.group("message");

        if (currentPlayer.isNear(player.getLocation())) {
            FriendshipData data1 = currentPlayer.getFriendships().get(player);
            FriendshipData data2 = player.getFriendships().get(currentPlayer);
            data1.getMessageHistory().add(message);
            data2.getMessageHistory().add(message);
            player.setNewMessage(true);

            if (!data1.isIntrcatedToday()) {
                data1.changeXp(20, currentPlayer, player);
                data2.changeXp(20, currentPlayer, player);
                if (player.getFriendships().get(currentPlayer).isNewLevel()) {
                    System.out.println("your friendship with " + player.getNickName() +
                            " changed to " + player.getFriendships().get(currentPlayer).getLevel());
                    player.getFriendships().get(currentPlayer).setNewLevel(false);
                    currentPlayer.getFriendships().get(player).setNewLevel(false);
                }
                data1.setIntrcatedToday(true);
                data2.setIntrcatedToday(true);
            }
            if (player.equals(currentPlayer.getZeidy())) {
                player.increaseTurnEnergy(50);
                currentPlayer.increaseTurnEnergy(50);
            }
            return new Result(true, "you talked to " + player.getUser().getNickname());
        }
        return new Result(false, "you can't talk to someone who's not near you!");
    }

    public void talkHistory(Matcher matcher) {
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        FriendshipData data1 = currentPlayer.getFriendships().get(player);
        for (String message : data1.getMessageHistory()) {
            System.out.println(message);
        }
    }

    //gifting methods

    public void gift (Matcher matcher) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        if (currentPlayer.equals(player)) {
            System.out.println("you can't gift yourself");
            return;
        }
        String itemName = matcher.group("item");
        int amount = Integer.parseInt(matcher.group("amount"));
        GameObjectType item = null;
        for (GameObjectType type : GameObjectType.values()) {
            if (type.name().equalsIgnoreCase(itemName)) {
                item = type;
            }
        }

        if (currentPlayer.isNear(player.getLocation())) {
            if (checkFriendship(currentPlayer, player, "gift")) {
                if (currentPlayer.getItemInInventory(item) == null ||
                        currentPlayer.getItemInInventory(item).getNumber() < amount) {
                    System.out.println("you don't have enough number this item in your inventory!");
                } else {
                    currentPlayer.removeAmountFromInventory(item, amount);

                    Gift newGift = new Gift(item, currentPlayer, player, amount);

                    if (player.getItemInInventory(item) == null) {
                        player.getCurrentBackPack().getInventory().add(new GameObject(item, amount));
                    } else {
                        player.getItemInInventory(item).addNumber(amount);
                    }
                    player.getNewGifts().add(newGift);
                    player.getArchiveGifts().add(newGift);
                    currentPlayer.getGivenGifts().add(newGift);

                    System.out.println("gifted successfully");
                }
            } else {
                System.out.println("you can't give each other gifts at this level!");
            }
        } else {
            System.out.println("you can't give gift to someone who's not near you!");
        }
    }


    public void giftList () {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        System.out.println("your gifts: ");
        for (Gift object : currentPlayer.getArchiveGifts()) {
            System.out.println("id: " + object.getId() +
                    " item: " + object.getGameObject().name() +
                    " amount: " + object.getAmount());
            System.out.println("----");
        }
    }

    public Result giftRate (Matcher matcher) {
        int id = Integer.parseInt(matcher.group("giftNumber"));
        int rate = Integer.parseInt(matcher.group("rate"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Gift targetGift = currentPlayer.getGiftById(id);
        if (targetGift == null) {
            return new Result(false, "there's no gift with this id");
        } else if (rate < 1 || rate > 5) {
            return new Result(false, "your rate should be between 1 to 5");
        }

        targetGift.setRate(rate);
        Player giver = targetGift.getGiver();
        if (!currentPlayer.getFriendships().get(giver).isIntrcatedToday()) {
            int friendship = ((rate - 3) * 30) + 15;
            giver.getFriendships().get(currentPlayer).changeXp(friendship, currentPlayer, giver);
            currentPlayer.getFriendships().get(giver).changeXp(friendship, currentPlayer, giver);
            if (giver.getFriendships().get(currentPlayer).isNewLevel()) {
                System.out.println("your friendship with " + giver.getNickName() +
                        " changed to " + giver.getFriendships().get(currentPlayer).getLevel());
                giver.getFriendships().get(currentPlayer).setNewLevel(false);
                currentPlayer.getFriendships().get(giver).setNewLevel(false);
            }
        }
        if (giver.equals(currentPlayer.getZeidy())) {
            giver.increaseTurnEnergy(50);
            currentPlayer.increaseTurnEnergy(50);
        }
        return new Result(true, "gift rated successfully");
    }

    public void giftHistory (Matcher matcher) {
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        System.out.println("gits given: ");
        for (Gift object : currentPlayer.getGivenGifts())  {
            if (object.getTaker().getNickName().equals(player.getNickName())) {
                System.out.println("id: " + object.getId() +
                        " item: " + object.getGameObject().name() +
                        " amount: " + object.getAmount());
                System.out.println("----");
            }
        }
        System.out.println("gifts taken: ");
        for (Gift object : currentPlayer.getArchiveGifts()) {
            if (object.getGiver().getNickName().equals(player.getNickName())) {
                System.out.println("id: " + object.getId() +
                        " item: " + object.getGameObject().name() +
                        " amount: " + object.getAmount());
                System.out.println("----");
            }
        }
    }


    //hugging

    public Result giveHug (Matcher matcher) {
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.isNear(player.getLocation())) {
            if (checkFriendship(currentPlayer, player, "hug")) {
                if (!currentPlayer.getFriendships().get(player).isIntrcatedToday()) {
                    player.getFriendships().get(currentPlayer).changeXp(60, currentPlayer, player);
                    currentPlayer.getFriendships().get(player).changeXp(60, currentPlayer, player);
                    if (player.getFriendships().get(currentPlayer).isNewLevel()) {
                        System.out.println("your friendship with " + player.getNickName() +
                                " changed to " + player.getFriendships().get(currentPlayer).getLevel());
                        player.getFriendships().get(currentPlayer).setNewLevel(false);
                        currentPlayer.getFriendships().get(player).setNewLevel(false);
                    }
                    currentPlayer.getFriendships().get(player).setIntrcatedToday(true);
                    player.getFriendships().get(currentPlayer).setIntrcatedToday(true);
                }
                if (player.equals(currentPlayer.getZeidy())) {
                    player.increaseTurnEnergy(50);
                    currentPlayer.increaseTurnEnergy(50);
                }
                return new Result(true, "you gave " + player.getUser().getNickname() + " a hug");
            } else {
                return new Result(false, "your friendship is not good enough to hug!");
            }
        } else {
            return new Result(false, "you can't hug someone who's not near you!");
        }
    }

    //flowering

    public Result giveFlower (Matcher matcher) {
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.isNear(player.getLocation())) {
            if (checkFriendship(currentPlayer, player, "flower")) {
                if (currentPlayer.getFriendships().get(player).getXp() >= 300) {
                    if (currentPlayer.getItemInInventory(GameObjectType.BOUQUET) != null) {
                        currentPlayer.getItemInInventory(GameObjectType.BOUQUET).addNumber(-1);
                        if (currentPlayer.getItemInInventory(GameObjectType.BOUQUET).getNumber() < 1) {
                            currentPlayer.getCurrentBackPack().getInventory().
                                    remove(currentPlayer.getItemInInventory(GameObjectType.BOUQUET));
                        }

                        if (player.getItemInInventory(GameObjectType.BOUQUET) != null) {
                            player.getItemInInventory(GameObjectType.BOUQUET).addNumber(1);
                        } else {
                            player.getCurrentBackPack().getInventory().add(new GameObject(GameObjectType.BOUQUET, 1));
                        }

                        if (!currentPlayer.getFriendships().get(player).isIntrcatedToday()) {
                            currentPlayer.getFriendships().get(player).setBouquetBought(true);
                            player.getFriendships().get(currentPlayer).setBouquetBought(true);
                            currentPlayer.getFriendships().get(player).changeXp(0, currentPlayer, player);
                            player.getFriendships().get(currentPlayer).changeXp(0, currentPlayer, player);

                            if (player.getFriendships().get(currentPlayer).isNewLevel()) {
                                System.out.println("your friendship with " + player.getNickName() +
                                        " changed to " + player.getFriendships().get(currentPlayer).getLevel());
                                player.getFriendships().get(currentPlayer).setNewLevel(false);
                                currentPlayer.getFriendships().get(player).setNewLevel(false);
                            }
                        }
                        if (player.equals(currentPlayer.getZeidy())) {
                            player.increaseTurnEnergy(50);
                            currentPlayer.increaseTurnEnergy(50);
                        }
                        return new Result(true, "you gave " + player.getNickName() + " a flower");

                    } else {
                        return new Result(false, "you don't have flower in your inventory!");
                    }
                } else {
                    return new Result(false, "you can't give flower upgrade your friendship xp");
                }
            } else {
                return new Result(false,
                        "your friendship is not good enough to give each other flowers");
            }
        } else {
            return new Result(false, "you can't give flower to someone who's not near you!");
        }
    }

    //marriage

    public Result purposeAsk (Matcher matcher) {
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        GameObjectType ring = GameObjectType.WEDDING_RING;
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.isNear(player.getLocation())) {
            if (checkFriendship(currentPlayer, player, "marriage")) {
                if (currentPlayer.getFriendships().get(player).getXp() >= 400) {
                    if (currentPlayer.getGender().equals(Gender.FEMALE)) {
                        return new Result(false, "you can't purpose ask doost pesaret");
                    } else if (player.getGender().equals(Gender.MALE)) {
                        return new Result(false,
                                "unfortunately this item is not supported in your country");
                    } else if (currentPlayer.getItemInInventory(ring) == null) {
                        return new Result(false, "you don't have a ring. buy one");
                    } else {
                        player.getPurposeList().put(currentPlayer, ring);
                        return new Result(true, "you purposed");
                    }
                } else {
                    return new Result(false, "you can't purpose. upgrade your friendship xp!");
                }
            } else {
                return new Result(false, "your friendship is not good enough to purpose!");
            }
        } else {
            return new Result(false, "you can't purpose to someone who's not near you!");
        }
    }

    public void purposeRespond (Matcher matcher) {
        Player player = App.getCurrentGame().getPlayerByNickname(matcher.group("username"));
        boolean answer = true;
        String respond = matcher.group("respond");
        if (respond.equals("cancel")) answer = false;
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        GameObject ring = player.getItemInInventory(currentPlayer.getPurposeList().get(player));

        if (answer) {
            player.getCurrentBackPack().getInventory().remove(ring);
            currentPlayer.getCurrentBackPack().getInventory().add(ring);
            player.setZeidy(currentPlayer);
            currentPlayer.setZeidy(player);
            player.getFriendships().get(currentPlayer).setMarried(true);
            currentPlayer.getFriendships().get(player).setMarried(true);
            currentPlayer.getFriendships().get(player).changeXp(0, currentPlayer, player);
            player.getFriendships().get(currentPlayer).changeXp(0, currentPlayer, player);
            if (currentPlayer.getFriendships().get(player).isNewLevel()) {
                System.out.println("your friendship with " + player.getNickName() +
                        " changed to " + player.getFriendships().get(currentPlayer).getLevel());
                player.getFriendships().get(currentPlayer).setNewLevel(false);
                currentPlayer.getFriendships().get(player).setNewLevel(false);
            }
            System.out.println("you are husband and wife now");
            //TODO: add energy things
        } else {
            FriendshipData data1 =currentPlayer.getFriendships().get(player);
            FriendshipData data2 =player.getFriendships().get(currentPlayer);
            data1.setLevel(0);
            data1.setXp(0);
            data1.setBouquetBought(false);
            data2.setLevel(0);
            data2.setXp(0);
            data2.setBouquetBought(false);
            System.out.println("go kill yourself");
        }
        currentPlayer.getPurposeList().remove(player);
    }
}
