package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    public void setup() {
        this.deck = new Deck();
        deck.newDeck();
    }
    @Test
    public void testConstructor(){
        Deck deck = new Deck();
        Card[] cards = deck.getCards();
        assertNotNull(cards.length);
        assertEquals(52, deck.getCards().length);

    }
    @Test
    public void testGiveCards(){
        Card[] hand = deck.giveCards();
        assertEquals(13, hand.length);
        assertNotNull(hand[0]);
    }
    @Test
    public void testCountDistributedCardIncreases() {
        Card[] hand1 = deck.giveCards();
        assertEquals(13, deck.getCountDistributedCard());

        Card[] hand2 = deck.giveCards();
        assertEquals(26, deck.getCountDistributedCard());

        assertNotSame(hand1, hand2);
    }

    @Test
    public void testCardsAreShuffled() {
        Deck deck1 = new Deck();
        deck1.cards = deck1.newDeck();
        Deck deck2 = new Deck();
        deck2.cards = deck2.newDeck();
        Card[] deckCards1 = deck1.getCards();
        Card[] deckCards2 = deck2.getCards();
        int count = 0;
        for(int i = 0; i < deckCards1.length; i++){
            if(deckCards1[i].getColor() == deckCards2[i].getColor() && deckCards1[i].getValue() == deckCards2[i].getValue()){
                count++;
            }
        }
        assertTrue(count != 52);
}
    @Test
    public void testGetCard() {
        Card[] cards = deck.getCards();
        Card newCard = null;
        int index = 0;
        do {
            assertNotEquals(cards, null);
            newCard = cards[index++];
        } while (index < 52);
    }


}