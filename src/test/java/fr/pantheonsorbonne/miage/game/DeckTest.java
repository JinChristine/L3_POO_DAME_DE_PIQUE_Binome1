package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

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
    }
    @Test
    public void testConstructor(){
        Card[] cards = deck.newDeck();
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
        Card[] deckCards = deck.newDeck().clone();
        deck.shuffleDeck(deckCards);
        assertNotEquals(java.util.Arrays.toString(deckCards), java.util.Arrays.toString(deck.getCards()));
    }

    @Test
    public void testGetCards() {
        deck.newDeck();
        Card[] newCard = deck.getCards();
        assertNotNull(newCard);
        assertEquals(52, newCard.length);
    }


}