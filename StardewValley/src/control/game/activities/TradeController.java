package control.game.activities;

import model.*;
import model.enums.GameObjectType;
import model.enums.TradeType;
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

    public Result tradeWith(Player otherPlayer, TradeType type, GameObjectType object, int amount, double price) { //overload for price or item
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
            if (otherPlayer == null) {
                return new Result(false, "the selected player doesn't exist");
            } else if (object == null) {
                return new Result(false, "the selected item doesn't exist");
            } else if (amount == -1) {
                return new Result(false, "the amount of this item is not valid");
            } else if (currentPlayer.getMoney() < price) {
                return new Result(false, "you don't have enough money to trade");
            }

        Trade newTrade = new Trade(currentPlayer, otherPlayer, type, object, amount, price);
        currentPlayer.getSentTrades().add(newTrade);
        otherPlayer.getReceivedTrades().add(newTrade);
        return new Result(true, "trade has sent successfully");
    }

    public Result tradeWith(Player otherPlayer, TradeType type, GameObjectType object, int amount,
                          GameObjectType targetObject, int targetAmount) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        if (otherPlayer == null) {
            return new Result(false, "the selected player doesn't exist");
        } else if (object == null) {
            return new Result(false, "the selected item doesn't exist");
        } else if (amount == -1) {
            return new Result(false, "the amount of this item is not valid");
        } else if (currentPlayer.getItemInInventory(targetObject) == null ||
        currentPlayer.getItemInInventory(targetObject).getNumber() < targetAmount) {
            return new Result(false, "you don't have this item to offer");
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

    public void responseTrade (boolean answer, int id) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        Trade targetTrade = currentPlayer.getTradeById(id);
        if (targetTrade == null) {
            System.out.println("there's no trade with this Id");
            return;
        }

        Player otherPlayer = targetTrade.getSent();

        if (targetTrade.getType() == TradeType.REQUEST) {
            if (answer) {
                if (currentPlayer.getItemInInventory(targetTrade.getTargetItem()) != null ||
                        currentPlayer.getItemInInventory(targetTrade.getItem()).getNumber()
                                < targetTrade.getTargetAmount()) {
                    System.out.println("you don't have enough of this item to trade");
                } else {
                    if (targetTrade.getPrice() == -1) {
                        if (currentPlayer.getItemInInventory(targetTrade.getTargetItem()) != null) {
                            currentPlayer.getItemInInventory(targetTrade.getTargetItem()).
                                    addNumber(targetTrade.getTargetAmount());
                        } else {
                            currentPlayer.getCurrentBackPack().getInventory().add(
                                    new GameObject(targetTrade.getTargetItem(), targetTrade.getTargetAmount()));
                        }

                        otherPlayer.getItemInInventory(targetTrade.getTargetItem()).addNumber
                                (-targetTrade.getTargetAmount());
                        if (currentPlayer.getItemInInventory(targetTrade.getTargetItem()).getNumber() < 1) {
                            currentPlayer.getCurrentBackPack().getInventory().remove(
                                    currentPlayer.getItemInInventory(targetTrade.getTargetItem()));
                        }
                    } else {
                        currentPlayer.increaseMoney(targetTrade.getPrice());
                        otherPlayer.increaseMoney(-targetTrade.getPrice());
                    }

                    currentPlayer.getItemInInventory(targetTrade.getItem()).addNumber(-targetTrade.getAmount());
                    if (currentPlayer.getItemInInventory(targetTrade.getItem()).getNumber() < 1) {
                        currentPlayer.getCurrentBackPack().getInventory().remove(currentPlayer.getItemInInventory(targetTrade.getItem()));
                    }

                    if (otherPlayer.getItemInInventory(targetTrade.getItem()) == null) {
                        otherPlayer.getCurrentBackPack().getInventory().add(new GameObject(targetTrade.getItem(), targetTrade.getAmount()));
                    } else {
                        otherPlayer.getItemInInventory(targetTrade.getItem()).addNumber(targetTrade.getAmount());
                    }
                }
                System.out.println("trade was successful");
                currentPlayer.getFriendships().get(otherPlayer).addXp(50);
                otherPlayer.getFriendships().get(currentPlayer).addXp(50);
                CommunicateController.upgradeFriendshipLevel(currentPlayer, otherPlayer);
            } else {
                System.out.println(currentPlayer.getNickName() + " rejected " + otherPlayer.getNickName() + "'s trade");
                currentPlayer.getFriendships().get(otherPlayer).addXp(-30);
                otherPlayer.getFriendships().get(currentPlayer).addXp(-30);
            }
        } else {
            if (answer) {
                if (targetTrade.getPrice() == -1) {
                    if (currentPlayer.getItemInInventory(targetTrade.getTargetItem()) == null ||
                    currentPlayer.getItemInInventory(
                            targetTrade.getTargetItem()).getNumber() < targetTrade.getTargetAmount()) {
                        System.out.println("you don't have enough of this item to trade");
                        return;
                    }
                    //check if it's a new Item or not
                    if (currentPlayer.getItemInInventory(targetTrade.getTargetItem()) != null) {
                        currentPlayer.getItemInInventory(targetTrade.getTargetItem()).
                                addNumber(targetTrade.getTargetAmount());
                    } else {
                        currentPlayer.getCurrentBackPack().getInventory().add(
                                new GameObject(targetTrade.getTargetItem(), targetTrade.getTargetAmount()));
                    }
                    //remove from offer inventory
                    otherPlayer.getItemInInventory(targetTrade.getTargetItem()).addNumber(-targetTrade.getTargetAmount());
                    if (otherPlayer.getItemInInventory(targetTrade.getTargetItem()).getNumber() < 1) {
                        otherPlayer.getCurrentBackPack().getInventory().remove(
                                otherPlayer.getItemInInventory(targetTrade.getTargetItem()));
                    }

                } else { //price method
                    if (currentPlayer.getMoney() < targetTrade.getPrice()) {
                        System.out.println("you don't have enough money to trade");
                        return;
                    }
                    currentPlayer.increaseMoney(-targetTrade.getPrice());
                    otherPlayer.increaseMoney(targetTrade.getPrice());
                }


                otherPlayer.getItemInInventory(targetTrade.getItem()).addNumber(-targetTrade.getAmount());
                if (otherPlayer.getItemInInventory(targetTrade.getItem()).getNumber() < 1) {
                    otherPlayer.getCurrentBackPack().getInventory().remove(otherPlayer.getItemInInventory(targetTrade.getItem()));
                }

                if (currentPlayer.getItemInInventory(targetTrade.getItem()) != null) {
                    currentPlayer.getItemInInventory(targetTrade.getItem()).
                            addNumber(targetTrade.getAmount());
                } else {
                    currentPlayer.getCurrentBackPack().getInventory().add(
                            new GameObject(targetTrade.getItem(), targetTrade.getAmount()));
                }

                System.out.println("trade was successful");
            } else {
                System.out.println(currentPlayer.getNickName() + " rejected " + otherPlayer.getNickName() + "'s trade");
            }
        }
        currentPlayer.getArchiveTrades().add(targetTrade);
        otherPlayer.getArchiveTrades().add(targetTrade);
        currentPlayer.getReceivedTrades().remove(targetTrade);
        otherPlayer.getSentTrades().remove(targetTrade);
    }

    public void tradeHistory () {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        System.out.println("trade archive:");
        for (Trade trade : currentPlayer.getArchiveTrades()) {
            System.out.print("id: " + trade.getId() +
                    " player: " + trade.getSent().getNickName() +
                    " type: " + trade.getType().getName() +
                    " item: " + trade.getItem() +
                    " amount: " + trade.getAmount());
            if (trade.getPrice() == -1) {
                System.out.println(" target item: " + trade.getTargetItem() + " target amount: " + trade.getTargetAmount());
            } else {
                System.out.println(" price: " + trade.getPrice());
            }
            System.out.println("----");
        }
    }
}
