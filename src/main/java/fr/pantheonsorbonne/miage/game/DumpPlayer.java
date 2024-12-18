package fr.pantheonsorbonne.miage.game;

import java.util.Queue;
import java.util.Random;
import fr.pantheonsorbonne.miage.enums.CardColor;

public class DumpPlayer extends Player {

    public DumpPlayer(String name) {
        super(name);
    }

    @Override
    public Card throwCard(Queue<Card> roundDeck, int turn) {
        int index = getRandom(this.cards.length);
        Card cardToPlay = getRandomCard(index);
        while (true) {
            if (haveSameColorInDeck(roundDeck)) {
                cardToPlay = getCardWithSameColorInDeck(roundDeck);
                break;
            } else if (turn == 1) {
                if (cardToPlay.getColor().equals(CardColor.valueOf("HEART"))) {
                    index = getRandom(this.cards.length);
                    cardToPlay = getRandomCard(index);
                } else {
                    break;
                }
            }
        }
        Card temp = cardToPlay;
        this.cards[index] = null;
        return temp;
    }

    public int getRandom(int max) {
        Random rand = new Random();
        return rand.nextInt(0, max);
    }

    public Card getRandomCard(int i) {
        return this.cards[i];
    }

    public Card getCardWithSameColorInDeck(Queue<Card> roundDeck) {
        while (true) {
            Card randomCard = getRandomCard(getRandom(this.cards.length));
            if (roundDeck.peek().getColor().equals(randomCard.getColor())) {
                return randomCard;
            }
        }
    }
}