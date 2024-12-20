package fr.pantheonsorbonne.miage.game;
import java.util.List;
import java.util.Queue;

import fr.pantheonsorbonne.miage.enums.CardColor;

public class SmartPlayer extends Player{

    public SmartPlayer(String name) {
        super(name);
    }

    @Override
    public Card throwCard(Queue<Card> roundDeck, int turn) {
        Card bestCard = this.cards[0];
        if(turn == 1 && roundDeck.size()==0){
            bestCard=this.cards[searchIndexOfTwoOfClub()];
            replaceBestCardsInDeckByNull(bestCard);
            return bestCard;
        }
        if (haveSameColorInDeck(roundDeck)) {
            bestCard = playTheBestCardWhenSameColor(roundDeck);
        } else {
            bestCard = playTheBestCardWhenNotSameColor(turn, roundDeck);
        }

        replaceBestCardsInDeckByNull(bestCard);
        return bestCard;
    }

    private Card playTheBestCardWhenNotSameColor(int turn, Queue<Card> roundDeck) {
        List<Card> cardListNotNull = getCardListNotNull(this.cards);
        Card bestCard = cardListNotNull.get(0);
        if (roundDeck.size() != 0) {
            for (Card card : cardListNotNull) {
                if (card.getColor().equals(CardColor.HEART)) {
                    bestCard = card;
                    break;
                }
            }

            for (Card card : cardListNotNull) {
                if (card.getColor().equals(CardColor.SPADE) && card.getValue().getRank() == 12
                        && turn != 1) {
                    bestCard = card;
                    break;
                }
                if (haveHeartOrQueenOfSpadeInDeck(this.cards) && turn != 1) {
                    if (card.getColor().equals(CardColor.HEART)
                            && card.getValue().getRank() > bestCard.getValue().getRank()) {
                        bestCard = card;
                    }
                } else if (card.getValue().getRank() > bestCard.getValue().getRank()
                        && !haveHeartOrQueenOfSpadeInDeck(this.cards)) {
                    bestCard = card;

                }

            }
        } else {
            for (Card card : cardListNotNull) {
                if (card.getValue().getRank() == 2 && card.getColor().equals(CardColor.HEART)) {
                    bestCard = card;
                    break;
                }
                if (card.getValue().getRank() < bestCard.getValue().getRank()) {
                    bestCard = card;
                }
            }
        }

        return bestCard;
    }

    private Card playTheBestCardWhenSameColor(Queue<Card> roundDeck) {
        List<Card> cardListNotNull = getCardListNotNull(this.cards);
        Card highestCardInRoundDeck = getTheHighestCardInRoundDeck(roundDeck);
        Card bestCard = getTheLowestCardInSameColor(roundDeck);
        for (Card card : cardListNotNull) {
            if (roundDeck.size() == 3 && haveHeartOrQueenOfSpadeInDeck(roundDeck)) {
                if (card.getValue().getRank() < highestCardInRoundDeck.getValue().getRank()
                        && card.getValue().getRank() > bestCard.getValue().getRank()
                        && card.getColor().equals(roundDeck.peek().getColor())) {
                    bestCard = card;
                }
            } else if (roundDeck.size() == 3 && !haveHeartOrQueenOfSpadeInDeck(roundDeck)) {
                if (card.getValue().getRank() > bestCard.getValue().getRank()
                        && card.getColor().equals(roundDeck.peek().getColor())) {
                    bestCard = card;
                }
            } else if (roundDeck.size() < 3 &&
                    card.getValue().getRank() < highestCardInRoundDeck.getValue().getRank()
                    && card.getValue().getRank() > bestCard.getValue().getRank()
                    && card.getColor().equals(roundDeck.peek().getColor())) {
                bestCard = card;
            }
        }
        return bestCard;

    }

    private Card getTheLowestCardInSameColor(Queue<Card> roundDeck) {
        List<Card> cardListNotNull = getCardListNotNull(this.cards);
        Card lowestCard = cardListNotNull.get(0);
        for (Card card : cardListNotNull) {
            if (lowestCard.getColor().equals(roundDeck.peek().getColor())) {
                lowestCard = card;
                break;
            }
        }

        for (Card card : cardListNotNull) {
            if (card.getValue().getRank() <= lowestCard.getValue().getRank()
                    && card.getColor().equals(roundDeck.peek().getColor())) {
                lowestCard = card;
            }
        }
        return lowestCard;
    }

    private boolean haveHeartOrQueenOfSpadeInDeck(Queue<Card> roundDeck) {
        for (Card cardInDeck : roundDeck) {
            if (cardInDeck.getColor().equals(CardColor.HEART) || (cardInDeck.getValue().getRank() == 12
                    && cardInDeck.getColor().equals(CardColor.SPADE))) {
                return true;
            }
        }
        return false;
    }

    private boolean haveHeartOrQueenOfSpadeInDeck(Card[] cards) {
        List<Card> cardListNotNull = getCardListNotNull(this.cards);
        for (Card cardInDeck : cardListNotNull) {
            if (cardInDeck.getColor().equals(CardColor.HEART) || (cardInDeck.getValue().getRank() == 12
                    && cardInDeck.getColor().equals(CardColor.SPADE))) {
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