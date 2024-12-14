package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.*;
import java.util.Queue;

public class Player {
    private String name;
    private Card[] cards;
    private int points;

    public Player(String name) {
        this.name = name;
        this.cards = new Card[13];
        this.points = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public String getName() {
        return this.name;
    }

    public Card[] getCards() {
        return this.cards;
    }

    public void swap3Cards(Player otherPlayer) {
        Card[] swapCardsHand1 = this.getSwap3Cards();
        Card[] swapCardsHand2 = otherPlayer.getSwap3Cards();

        swapCards(this, swapCardsHand1, swapCardsHand2);
        swapCards(otherPlayer, swapCardsHand2, swapCardsHand1);

    }

    private Card[] getSwap3Cards() {
        Card[] cardsToGive = new Card[3];
        Card maxCard = this.cards[0];
        int index = 0;
        while (index != 2) {
            for (Card card : this.cards) {
                if (card.getValue().getRank() > maxCard.getValue().getRank()) {
                    maxCard = card;
                }
            }
            cardsToGive[index++] = maxCard;
        }
        return cardsToGive;
    }

    private void swapCards(Player player, Card[] swapCardsHand1, Card[] swapCardsHand2) {
        Card[] handle = player.getCards();
        for (int i = 0; i < handle.length; i++) {
            for (int j = 0; j < swapCardsHand1.length; j++) {
                if (handle[i].equals(swapCardsHand1[j])) {
                    handle[i] = swapCardsHand2[j];
                }
            }
        }
        player.setCards(handle);
    }

    public Card throwCard(Queue<Card> roundDeck, int turn) {
        Card bestCards = this.cards[0];
        if (haveSameColorInDeck(roundDeck)) {
            bestCards = playTheBestCardWhenSameColor(roundDeck);
        } else {
            bestCards = playTheBestCardWhenNotSameColor(turn);
        }
        return bestCards;
    }

    private Card playTheBestCardWhenNotSameColor(int turn) {
        Card bestCard = this.cards[0];
        for (Card card : this.cards) {
            if (card.getColor().equals(CardColor.valueOf("HEART"))) {
                bestCard = card;
                break;
            }
        }

        for (Card card : this.cards) {
            if (card.getColor().equals(CardColor.valueOf("SPADE")) && card.getValue().getRank() == 12 && turn != 1) {
                bestCard = card;
                break;
            }
            if (haveHeartOrQueenOfSpadeInDeck(this.cards) && turn != 1) {
                if (card.getColor().equals(CardColor.valueOf("HEART"))
                        && card.getValue().getRank() > bestCard.getValue().getRank()) {
                    bestCard = card;
                }
            } else if (card.getValue().getRank() > bestCard.getValue().getRank()
                    && !haveHeartOrQueenOfSpadeInDeck(this.cards)) {
                bestCard = card;

            }

        }
        return bestCard;
    }

    private Card playTheBestCardWhenSameColor(Queue<Card> roundDeck) {
        Card HighestCardInRoundDeck = getTheHighestCardInRoundDeck(roundDeck);
        Card bestCard = getTheLowestCardInSameColor(roundDeck);
        for (Card card : this.cards) {
            if (roundDeck.size() == 3 && haveHeartOrQueenOfSpadeInDeck(roundDeck)) {
                if (card.getValue().getRank() < HighestCardInRoundDeck.getValue().getRank()
                        && card.getValue().getRank() > bestCard.getValue().getRank()
                        && card.getColor().equals(roundDeck.peek().getColor())) {
                    bestCard = card;
                }
            } else if (roundDeck.size() == 3 && !haveHeartOrQueenOfSpadeInDeck(roundDeck)) {
                if (card.getValue().getRank() > bestCard.getValue().getRank()
                        && card.getColor().equals(roundDeck.peek().getColor())) {
                    bestCard = card;
                }
            } else if (roundDeck.size() < 3 && haveHeartOrQueenOfSpadeInDeck(roundDeck)) {
                if (card.getValue().getRank() < HighestCardInRoundDeck.getValue().getRank()
                        && card.getValue().getRank() > bestCard.getValue().getRank()
                        && card.getColor().equals(roundDeck.peek().getColor())) {
                    bestCard = card;
                }
            } else if (roundDeck.size() < 3 && !haveHeartOrQueenOfSpadeInDeck(roundDeck)) {
                if (card.getValue().getRank() < HighestCardInRoundDeck.getValue().getRank()
                        && card.getValue().getRank() > bestCard.getValue().getRank()
                        && card.getColor().equals(roundDeck.peek().getColor())) {
                    bestCard = card;
                }
            }
        }
        return bestCard;
    }

    private Card getTheLowestCardInSameColor(Queue<Card> roundDeck) {
        Card lowestCard = this.cards[0];
        for (Card card : this.cards) {
            if (!lowestCard.getColor().equals(roundDeck.peek().getColor())) {
                lowestCard = card;
            } else {
                break;
            }
        }

        for (Card card : this.cards) {
            if (card.getValue().getRank() <= lowestCard.getValue().getRank()
                    && card.getColor().equals(roundDeck.peek().getColor())) {
                lowestCard = card;
            }
        }
        return lowestCard;
    }

    private boolean haveHeartOrQueenOfSpadeInDeck(Queue<Card> roundDeck) {
        for (Card cardInDeck : roundDeck) {
            if (cardInDeck.getColor().equals(CardColor.valueOf("HEART")) || (cardInDeck.getValue().getRank() == 12
                    && cardInDeck.getColor().equals(CardColor.valueOf("SPADE")))) {
                return true;
            }
        }
        return false;
    }

    private boolean haveHeartOrQueenOfSpadeInDeck(Card[] roundDeck) {
        for (Card cardInDeck : roundDeck) {
            if (cardInDeck.getColor().equals(CardColor.valueOf("HEART")) || (cardInDeck.getValue().getRank() == 12
                    && cardInDeck.getColor().equals(CardColor.valueOf("SPADE")))) {
                return true;
            }
        }
        return false;
    }

    private boolean haveSameColorInDeck(Queue<Card> roundDeck) {
        Card firstPlayedCard = roundDeck.peek();
        for (Card card : this.cards) {
            if (card.getColor().equals(firstPlayedCard.getColor())) {
                return true;
            }
        }
        return false;
    }

    private Card getTheHighestCardInRoundDeck(Queue<Card> roundDeck) {
        Card HighestCardInRoundDeck = roundDeck.peek();
        Card firstPlayedCard = roundDeck.peek();
        for (Card cardInDeck : roundDeck) {
            if (cardInDeck.getValue().getRank() > HighestCardInRoundDeck.getValue().getRank()
                    && cardInDeck.getColor().equals(firstPlayedCard.getColor())) {
                HighestCardInRoundDeck = cardInDeck;
            }
        }
        return HighestCardInRoundDeck;
    }
}
