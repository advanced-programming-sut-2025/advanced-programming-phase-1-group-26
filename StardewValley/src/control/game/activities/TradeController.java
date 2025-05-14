package control.game.activities;

import model.*;
import model.enums.GameObjectType;
import model.enums.TradeType;
import model.player_data.FriendshipData;
import model.player_data.Trade;

public class TradeController {
    public void showCurrentTrades () {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (currentPlayer.getReceivedTrades().isEmpty()) {
            System.out.println("you didn't receive any trades");
            return;
        }

        this.tradeList();
    }

    public Result tradeWith(Player otherPlayer, TradeType type, GameObjectType object, int amount, double price) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (type == null) {
            return new Result(false, "the selected type doesn't exist");
        } else if (type.equals(TradeType.REQUEST)) {
            if (otherPlayer == null) {
                return new Result(false, "the selected player doesn't exist");
            } else if (object == null) {
                return new Result(false, "the selected item doesn't exist");
            } else if (amount == -1) {
                return new Result(false, "amount number is not valid!");
            } else if (currentPlayer.getMoney() < price) {
                return new Result(false, "you don't have enough money to trade");
            }
        } else if (type.equals(TradeType.OFFER)) {
            if (otherPlayer == null) {
                return new Result(false, "the selected player doesn't exist");
            } else if (object == null) {
                return new Result(false, "the selected item doesn't exist");
            }  else if (amount == -1) {
                return new Result(false, "amount number is not valid!");
            }  else if (currentPlayer.getItemInInventory(object) == null ||
                    currentPlayer.getItemInInventory(object).getNumber() < amount) {
                return new Result(false, "you don't have this item to offer");
            }
        }

        Trade newTrade = new Trade(currentPlayer, otherPlayer, type, object, amount, price);
        currentPlayer.getSentTrades().add(newTrade);
        otherPlayer.getReceivedTrades().add(newTrade);
        return new Result(true, "trade has sent successfully");
    }

    public Result tradeWith(Player otherPlayer, TradeType type, GameObjectType object, int amount,
                          GameObjectType targetObject, int targetAmount) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (type == null) {
            return new Result(false, "the selected type doesn't exist");
        } else if (type.equals(TradeType.REQUEST)) {
            if (otherPlayer == null) {
                return new Result(false, "the selected player doesn't exist");
            } else if (object == null) {
                return new Result(false, "the selected item doesn't exist");
            }  else if (amount == -1) {
                return new Result(false, "amount number is not valid!");
            } else if (targetAmount == -1) {
                return new Result(false, "target amount number is not valid!");
            } else if (targetObject == null) {
                return new Result(false, "the target item doesn't exist");
            }  else if (currentPlayer.getItemInInventory(targetObject) == null ||
                    currentPlayer.getItemInInventory(targetObject).getNumber() < targetAmount) {
                return new Result(false, "you don't have this item to offer");
            }
        } else if (type.equals(TradeType.OFFER)) {
            if (otherPlayer == null) {
                return new Result(false, "the selected player doesn't exist");
            } else if (object == null) {
                return new Result(false, "the selected item doesn't exist");
            } else if (amount == -1) {
                return new Result(false, "amount number is not valid!");
            } else if (targetAmount == -1) {
                return new Result(false, "target amount number is not valid!");
            } else if (targetObject == null) {
                return new Result(false, "the target item doesn't exist");
            } else if (currentPlayer.getItemInInventory(object) == null ||
                    currentPlayer.getItemInInventory(object).getNumber() < amount) {
                return new Result(false, "you don't have this item to offer");
            }
        }

        Trade newTrade = new Trade(currentPlayer, otherPlayer, type, object, amount, targetObject, targetAmount);
        currentPlayer.getSentTrades().add(newTrade);
        otherPlayer.getReceivedTrades().add(newTrade);
        return new Result(true, "trade has sent successfully");
    }

    public void tradeList() {
        System.out.println("trades: ");
        for (Trade trade : App.getCurrentGame().getCurrentPlayer().getReceivedTrades()) {
            System.out.print("id: " + trade.getId() +
                    " player: " + trade.getSent().getNickName() +
                    " type: " + trade.getType().getName() +
                    " item: " + trade.getItem() +
                    " amount: " + trade.getAmount());
            if (trade.getPrice() == -1) {
                System.out.println(" target item: " + trade.getTargetItem() +
                        " target amount: " + trade.getTargetAmount());
            } else {
                System.out.println(" price: " + trade.getPrice());
            }
            System.out.println("----");
        }
    }

    public Result responseTrade (boolean answer, int id) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Trade targetTrade = currentPlayer.getTradeById(id);

        if (targetTrade == null) {
            return new Result(false, "there's no trade with this Id");
        }

        Player otherPlayer = targetTrade.getSent();
        FriendshipData data1 = currentPlayer.getFriendships().get(otherPlayer);
        FriendshipData data2 = otherPlayer.getFriendships().get(currentPlayer);


        if (targetTrade.getType().equals(TradeType.REQUEST)) {
            //REQUEST
            if (targetTrade.getPrice() != -1) {
                // PRICE
                if (answer) {
                    if (currentPlayer.getItemInInventory(targetTrade.getItem()) == null ||
                            currentPlayer.getItemInInventory(targetTrade.getItem()).
                                    getNumber() < targetTrade.getAmount()) {
                        return new Result(false, "you don't have enough of this item to trade!");
                    }

                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    //delete from inventory
                    currentPlayer.getItemInInventory(targetTrade.getItem()).addNumber(-targetTrade.getAmount());
                    if (currentPlayer.getItemInInventory(targetTrade.getItem()).getNumber() < 1) {
                        currentPlayer.getInventory().remove(currentPlayer.getItemInInventory(targetTrade.getItem()));
                    }
                    //add to other player inventory
                    otherPlayer.addToInventory(targetTrade.getItem(), targetTrade.getAmount());

                    otherPlayer.increaseMoney(-targetTrade.getPrice());
                    currentPlayer.increaseMoney(targetTrade.getPrice());

                    data1.changeXp(50, currentPlayer, otherPlayer);
                    data2.changeXp(50, currentPlayer, otherPlayer);
                    targetTrade.setRespond(true);
                    return new Result(true, "you accepted the request");
                } else {
                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    data1.changeXp(-30, currentPlayer, otherPlayer);
                    data2.changeXp(-30, currentPlayer, otherPlayer);
                    targetTrade.setRespond(false);
                    return new Result(false, "you rejected the request");
                }
            } else {
                // TARGET OBJECT
                if (answer) {
                    if (currentPlayer.getItemInInventory(targetTrade.getItem()) == null ||
                            currentPlayer.getItemInInventory(targetTrade.getItem()).
                                    getNumber() < targetTrade.getAmount()) {
                        return new Result(false, "you don't have enough of this item to trade!");
                    }

                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    //delete from inventory
                    currentPlayer.getItemInInventory(targetTrade.getItem()).addNumber(-targetTrade.getAmount());
                    if (currentPlayer.getItemInInventory(targetTrade.getItem()).getNumber() < 1) {
                        currentPlayer.getInventory().remove(currentPlayer.getItemInInventory(targetTrade.getItem()));
                    }
                    //add to other player inventory
                    otherPlayer.addToInventory(targetTrade.getItem(), targetTrade.getAmount());

                    otherPlayer.getItemInInventory(targetTrade.getTargetItem()).addNumber(-targetTrade.getTargetAmount());
                    if (otherPlayer.getItemInInventory(targetTrade.getTargetItem()).getNumber() < 1) {
                        otherPlayer.getInventory().remove(currentPlayer.getItemInInventory(targetTrade.getTargetItem()));
                    }
                    currentPlayer.addToInventory(targetTrade.getTargetItem(), targetTrade.getTargetAmount());

                    data1.changeXp(50, currentPlayer, otherPlayer);
                    data2.changeXp(50, currentPlayer, otherPlayer);
                    targetTrade.setRespond(true);
                    return new Result(true, "you accepted the request");
                } else {

                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    data1.changeXp(-30, currentPlayer, otherPlayer);
                    data2.changeXp(-30, currentPlayer, otherPlayer);
                    targetTrade.setRespond(false);
                    return new Result(false, "you rejected the request");
                }
            }
        } else {
            //OFFER
            if (targetTrade.getPrice() != -1) {
                // PRICE
                if (answer) {
                    if (currentPlayer.getMoney() < targetTrade.getPrice()) {
                        return new Result(false, "you don't have enough money to accept this trade");
                    }

                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    if (otherPlayer.getItemInInventory(targetTrade.getItem()).getNumber() < 1) {
                        otherPlayer.getInventory().remove(otherPlayer.getItemInInventory(targetTrade.getItem()));
                    }
                    //add to current player inventory
                    currentPlayer.addToInventory(targetTrade.getItem(), targetTrade.getAmount());

                    currentPlayer.increaseMoney(-targetTrade.getPrice());
                    otherPlayer.increaseMoney(targetTrade.getPrice());

                    data1.changeXp(50, currentPlayer, otherPlayer);
                    data2.changeXp(50, currentPlayer, otherPlayer);
                    targetTrade.setRespond(true);
                    return new Result(true, "you accepted the offer");
                } else {
                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    data1.changeXp(-30, currentPlayer, otherPlayer);
                    data2.changeXp(-30, currentPlayer, otherPlayer);
                    targetTrade.setRespond(false);
                    return new Result(true, "you rejected the offer");
                }
            } else {
                // TARGET OBJECT
                if (answer) {
                    if (currentPlayer.getItemInInventory(targetTrade.getTargetItem()) == null ||
                            currentPlayer.getItemInInventory(targetTrade.getTargetItem()).
                                    getNumber() < targetTrade.getTargetAmount()) {
                        return new Result(false, "you don't have enough of this item to trade!");
                    }

                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    //delete from inventory
                    currentPlayer.getItemInInventory(targetTrade.getTargetItem()).addNumber(-targetTrade.getTargetAmount());
                    if (currentPlayer.getItemInInventory(targetTrade.getTargetItem()).getNumber() < 1) {
                        currentPlayer.getInventory().remove(currentPlayer.getItemInInventory(targetTrade.getTargetItem()));
                    }
                    //add to other player inventory
                    otherPlayer.addToInventory(targetTrade.getTargetItem(), targetTrade.getTargetAmount());

                    otherPlayer.getItemInInventory(targetTrade.getItem()).addNumber(-targetTrade.getAmount());
                    if (otherPlayer.getItemInInventory(targetTrade.getItem()).getNumber() < 1) {
                        otherPlayer.getInventory().remove(currentPlayer.getItemInInventory(targetTrade.getItem()));
                    }
                    currentPlayer.addToInventory(targetTrade.getItem(), targetTrade.getAmount());

                    data1.changeXp(50, currentPlayer, otherPlayer);
                    data2.changeXp(50, currentPlayer, otherPlayer);
                    targetTrade.setRespond(true);
                    return new Result(true, "you accepted the request");
                } else {
                    currentPlayer.getArchiveTrades().add(targetTrade);
                    otherPlayer.getArchiveTrades().add(targetTrade);
                    currentPlayer.getReceivedTrades().remove(targetTrade);
                    otherPlayer.getSentTrades().remove(targetTrade);

                    data1.changeXp(-30, currentPlayer, otherPlayer);
                    data2.changeXp(-30, currentPlayer, otherPlayer);
                    targetTrade.setRespond(false);
                    return new Result(false, "you rejected the request");
                }
            }
        }
    }

    public void tradeHistory () {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        System.out.println("trade archive:");
        for (Trade trade : currentPlayer.getArchiveTrades()) {
            String respond;
            if (trade.isRespond()) respond = "accepted";
            else respond = "rejected";

            System.out.print("id: " + trade.getId() +
                    " sender: " + trade.getSent().getNickName() +
                    " receiver: " + trade.getReceived().getNickName() +
                    " type: " + trade.getType().getName() +
                    " item: " + trade.getItem() +
                    " amount: " + trade.getAmount());
            if (trade.getPrice() == -1) {
                System.out.print(" target item: " + trade.getTargetItem() + " target amount: " + trade.getTargetAmount());
            } else {
                System.out.print(" price: " + trade.getPrice());
            }
            System.out.println(" status: " + respond);
            System.out.println("----");
        }
    }
}
