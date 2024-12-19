package fr.pantheonsorbonne.miage.game;

import java.util.Queue;
import java.util.Random;
import fr.pantheonsorbonne.miage.enums.CardColor;
import java.util.List;

public class DumpPlayer extends PlayerTest {

    public DumpPlayer(String name) {
        super(name);
    }

    @Override
    public Card throwCard(Queue<Card> roundDeck, int turn) {
        List<Card> listCardNotNull = getCardListNotNull(cards);
        int index = getRandom(listCardNotNull.size());
        Card cardToPlay = getRandomCard(index, listCardNotNull);
        while (true) {
            if (haveSameColorInDeck(roundDeck)) {
                cardToPlay = getCardWithSameColorInDeck(roundDeck, listCardNotNull);
                break;
            } else if (turn == 1) {
                if (cardToPlay.getColor().equals(CardColor.valueOf("HEART"))) {
                    index = getRandom(listCardNotNull.size());
                    cardToPlay = getRandomCard(index, listCardNotNull);
                }
            }
            else {
                break;
            }

        }
        replaceBestCardsInDeckByNull(cardToPlay);
        return cardToPlay;
    }

    public int getRandom(int max) {
        Random rand = new Random();
        return rand.nextInt(0, max);
    }

    public Card getRandomCard(int i, List<Card> listCardNotNull) {
        return listCardNotNull.get(i);
    }

    public Card getCardWithSameColorInDeck(Queue<Card> roundDeck, List<Card> listCardNotNull) {
        while (true) {
            Card randomCard = getRandomCard(getRandom(this.cards.length), listCardNotNull);
            if (roundDeck.peek().getColor().equals(randomCard.getColor())) {
                return randomCard;
            }
        }
    }
}