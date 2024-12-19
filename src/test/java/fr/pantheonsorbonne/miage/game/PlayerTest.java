package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player1;
    private Player player2;
    
    @BeforeEach
    public void setUp() {
        player1 = new Player("Player 1"){
            @Override
            public Card throwCard(Queue<Card> roundDeck, int turn) {
                return null;
            } 
        };
        player2 = new Player("Player 2") {
            @Override
                public Card throwCard(Queue<Card> roundDeck, int turn) {
                return null;
            }
        };
    }

    @Test
    public void testConstructor() {
        assertEquals("Player 1", player1.getName());
        assertEquals(0, player1.getPoints());
        assertNotNull(player1.getCards());
    }

    @Test
    public void testSetAndGetPoints() {
        player1.setPoints(10);
        assertEquals(10, player1.getPoints());
    }

   
    @Test
    public void testSetAndGetCards() {
        Card[] cards = new Card[13];
        cards[0] = new Card(CardValue.ACE, CardColor.HEART);
        player1.setCards(cards);
        assertEquals(cards, player1.getCards());
    }
    @Test
    public void testGetName() {
        assertEquals("Player 1", player1.getName());
    }

    @Test
    public void testSwap3Cards() {
        Card[] startCardsPlayer1 = new Card[13];
        startCardsPlayer1[0] = new Card(CardValue.FIVE, CardColor.CLUB);
        startCardsPlayer1[1] = new Card(CardValue.THREE, CardColor.HEART);
        startCardsPlayer1[2] = new Card(CardValue.KING, CardColor.SPADE);
        player1.setCards(startCardsPlayer1);

        Card[] startCardsPlayer2 = new Card[13];
        startCardsPlayer2[0] = new Card(CardValue.TWO, CardColor.DIAMOND);
        startCardsPlayer2[1] = new Card(CardValue.FOUR, CardColor.CLUB);
        startCardsPlayer2[2] = new Card(CardValue.JACK, CardColor.HEART);
        player2.setCards(startCardsPlayer2);

        player1.swap3Cards(player2);
        assertNotNull(player1.getCards()[0]);
        assertNotNull(player2.getCards()[0]);

    }

    @Test
    void testHaveSameColorInDeck() {
        Queue<Card> roundDeck = new LinkedList<>();
        roundDeck.add(new Card(CardValue.FIVE, CardColor.CLUB));
        Card[] cards = new Card[13];
        cards[0] = new Card(CardValue.SEVEN, CardColor.CLUB);
        cards[1] = new Card(CardValue.ACE, CardColor.HEART);
        player1.setCards(cards);
        assertTrue(player1.haveSameColorInDeck(roundDeck));
    }

    @Test
    void testReplaceBestCardsInDeckByNull() {
        Card bestCard = player1.getCards()[0];
        player1.replaceBestCardsInDeckByNull(bestCard);
        assertNull(player1.getCards()[0]);
    }

}
