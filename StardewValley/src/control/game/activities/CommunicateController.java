package control.game.activities;

import model.*;
import model.player_data.FriendshipData;
import model.player_data.Trade;
import model.enums.GameObjectType;

public class CommunicateController
{

    /* friendship methods */

    public void upgradeFriendshipLevel (Player player1, Player player2) {
        FriendshipData currentLevel = player1.getFriendships().get(player2);
        currentLevel.addLevel();
        player1.getFriendships().put(player2, currentLevel);
        currentLevel = player2.getFriendships().get(player1);
        currentLevel.addLevel();
        player2.getFriendships().put(player1, currentLevel);
    }

    public boolean checkFriendship (Player player1, Player player2, String command) { //TODO: might change string to command
        return true;
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

    //Trade methods

    public void showCurrentTrades () {

    }

    public void tradeWith(Player player, GameObjectType object, int amount, double price, GameObjectType other, int otherAmount) { //overload for price or item
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        boolean isTradeSuccessful = true;

        //check logics ....


        // add to trade lists
        Trade newTrade = new Trade(player, object, amount, price, other, otherAmount);
        currentPlayer.getOfferTrades().add(newTrade);
        player.getRequestTrades().add(newTrade);
    }

    public void responseTrade (boolean answer, int id) {

    }

    public void tradeHistory () {

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
