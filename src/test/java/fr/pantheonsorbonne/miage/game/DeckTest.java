package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {

    @BeforeEach
    public void setup() {
        Deck.newDeck();
    }

    @Test
    public void testConstructor() {
        Card[] cards = Deck.getCards();
        assertNotNull(cards.length);
        assertEquals(52, Deck.getCards().length);

    }

    @Test
    public void testGiveCards() {
        Card[] hand = Deck.giveCards();
        assertEquals(13, hand.length);
        assertNotNull(hand[0]);
    }

    @Test
    public void testCountDistributedCardIncreases() {
        Card[] hand1 = Deck.giveCards();
        assertEquals(13, Deck.getCountDistributedCard());

        Card[] hand2 = Deck.giveCards();
        assertEquals(26, Deck.getCountDistributedCard());

        assertNotSame(hand1, hand2);
    }

    @Test
    public static void testCardsAreShuffled() {
        Card[] deckCards = Deck.getCards().clone();
        Deck.shuffleDeck(deckCards);
        boolean isShuffled = false;
        for (int i = 0; i < deckCards.length; i++) {
            if (!deckCards[i].equals(Deck.getCards()[i])) {
                isShuffled = true;
                break;
            }
        }
        assertTrue(isShuffled, "Les cartes doivent être mélangées");
    }

    @Test
    public void testGetCards() {
        Card[] newCard = Deck.getCards();
        assertNotNull(newCard);
        assertEquals(52, newCard.length);
    }

}