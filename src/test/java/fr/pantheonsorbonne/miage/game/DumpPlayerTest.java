package fr.pantheonsorbonne.miage.game;

import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class DumpPlayerTest{
    private DumpPlayer player;
    private Card[] initialHand;

    @BeforeEach
    public void setUp(){
        this.player = new DumpPlayer("Toto");
        initialHand = new Card[] {
            new Card(CardValue.ACE, CardColor.HEART),
            new Card(CardValue.TWO, CardColor.CLUB),
            new Card(CardValue.THREE, CardColor.DIAMOND),
            new Card(CardValue.FOUR, CardColor.SPADE),
            new Card(CardValue.ACE, CardColor.DIAMOND),
            new Card(CardValue.TWO, CardColor.SPADE),
            new Card(CardValue.THREE, CardColor.CLUB),
            new Card(CardValue.SIX, CardColor.SPADE),
            new Card(CardValue.JACK, CardColor.HEART),
            new Card(CardValue.QUEEN, CardColor.HEART),
            new Card(CardValue.SEVEN, CardColor.SPADE),
            new Card(CardValue.THREE, CardColor.CLUB),
            new Card(CardValue.TEN, CardColor.SPADE)
        };
        player.setCards(initialHand);
    }

    @Test
    public void testConstructor(){
        assertSame("Toto", player.getName());
    }

    @Test
    public void testThrowCard() {
        Queue<Card> roundDeck = new LinkedList<>();
        roundDeck.add(new Card(CardValue.KING, CardColor.HEART));

        Card cardPlayed = player.throwCard(roundDeck, 2);
        assertEquals(CardColor.HEART, cardPlayed.getColor());
    }
    @Test
    public void testThrowCardOnFirstTurn(){
        Queue<Card> roundDeck = new LinkedList<>();
        roundDeck.add(new Card(CardValue.KING, CardColor.SPADE));

        Card cardPlayed = player.throwCard(roundDeck, 1);
        assertNotEquals(CardColor.HEART, cardPlayed.getColor());
    }

    @Test 
    public void testThrowCardSelectsBecomesNull(){
        Queue<Card> roundDeck = new LinkedList<>();
        roundDeck.add(new Card(CardValue.KING, CardColor.DIAMOND));
        Card cardPlayed = player.throwCard(roundDeck, 2);
        boolean verification = false;
        for(Card card: player.cards){
            if(card == null){
                verification = true;
                break;
            }
        }
        assertTrue(verification);
    }

    @Test
    public void testGetRandom() {
        int max = 13;
        for(int i = 0; i < 100; i++){
            int result = player.getRandom(max);
            assertTrue(result >= 0 && result < max);
        }
    }
    @Test
    public void testGetRandomCard() {
        List<Card> listCard = player.getCardListNotNull(player.cards);
        int randomIndex = player.getRandom(listCard.size());
        Card card = player.getRandomCard(randomIndex, listCard);

        assertNotNull(card);
        assertEquals(listCard.get(randomIndex), card);
    }
    @Test 
    public void testGetCardWithSameColorInDeck(){
     Queue<Card> roundDeck = new ArrayDeque<>();
        roundDeck.add(new Card(CardValue.KING, CardColor.HEART));

        List<Card> listCardNotNull = player.getCardListNotNull(player.cards);
        Card cardWithSameColor = player.getCardWithSameColorInDeck(roundDeck, listCardNotNull);

        assertNotNull(cardWithSameColor);
        assertEquals(CardColor.HEART, cardWithSameColor.getColor());
    }
    @Test
    public void testReplaceBestCardsInDeckByNull(){
        Card cardToRemove = player.cards[0];
        player.replaceBestCardsInDeckByNull(cardToRemove);

        boolean removeVerification = true;
        for (Card card : player.cards) {
            if (card != null && card.getValue() == cardToRemove.getValue() && card.getColor() == cardToRemove.getColor()) {
                removeVerification = false;
                break;
            }
        }
        assertTrue(removeVerification);
    }
}