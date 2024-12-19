package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.game.Card;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import java.util.Random;

public class Deck  {
    private final static int DECK_SIZE = 13;
    private static Card[] cards;
    private static int countDistributedCard;

    

    public Card[] getCards(){
        return cards;
    }

    public static void resetCountDistributedCard(){ 
        countDistributedCard = 0; 
    }

    public static Card[] giveCards() {
        Card[] hand = new Card[DECK_SIZE];
        int index = 0;
        for (int i = countDistributedCard; i < cards.length; i++) {
            hand[index++] = cards[i];
            if ((i+1) % DECK_SIZE == 0) {
                countDistributedCard = countDistributedCard + DECK_SIZE;
                break;
            }
        }
        return hand;
    }

    
}
