package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.game.Card;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import java.util.Random;

public class Deck {
    private final static int DECK_SIZE = 13;
    private static Card[] cards;
    private static int countDistributedCard;

    public static void newDeck(){
        countDistributedCard = 0;
        cards = new Card[52];
        int index = 0;
        for (CardColor color : CardColor.values()) {
            for (CardValue value : CardValue.values()) {
                cards[index++] = new Card(value, color);
            }
        }
        Random rand = new Random();
        for (int j = 0; j < cards.length; j++) {
            Card temp = cards[j];
            int indexAleatoire = rand.nextInt(0, cards.length);
            cards[j] = cards[indexAleatoire];
            cards[indexAleatoire] = temp;
        }
    }

    public static Card[] giveCards() {
        Card[] hand = new Card[DECK_SIZE];
        int index = 0;
        for (int i = countDistributedCard; i < cards.length; i++) {
            hand[index++] = cards[i];
            if ((i + 1) % DECK_SIZE == 0) {
                countDistributedCard = countDistributedCard + DECK_SIZE;
                break;
            }
        }
        return hand;
    }

}
