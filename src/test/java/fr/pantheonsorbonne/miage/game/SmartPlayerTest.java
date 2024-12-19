package fr.pantheonsorbonne.miage.game;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

public class SmartPlayerTest {
    private SmartPlayer smartPlayer;

    @BeforeEach
    void setUp(){
        smartPlayer = new SmartPlayer("Player1");
    }

    @Test
    void testThrowCards(){
        Card startCard = new Card(CardValue.TWO,CardColor.SPADE);
        smartPlayer.setCards(new Card[]{startCard, new Card(CardValue.NINE,CardColor.HEART), new Card(CardValue.EIGHT, CardColor.SPADE), new Card(CardValue.FOUR,CardColor.CLUB)});
        Queue<Card> roundDeck = new LinkedList<>();
        Card throwCard = smartPlayer.throwCard(roundDeck, 1);

        assertEquals(startCard.getValue(),throwCard.getValue());
        assertEquals(startCard.getColor(),throwCard.getColor());
    }

    @Test
    void testThrowBestCardWithSameColor(){
        Card startCard =new Card(CardValue.NINE,CardColor.HEART);
        smartPlayer.setCards(new Card[]{startCard, new Card(CardValue.TEN,CardColor.HEART), new Card(CardValue.EIGHT, CardColor.SPADE), new Card(CardValue.FOUR,CardColor.HEART)});
        Queue<Card> roundDeck = new LinkedList<>();
        roundDeck.add(startCard);
        Card throwCard = smartPlayer.throwCard(roundDeck, 2);
        assertEquals(throwCard.getValue(),CardValue.FOUR);
        assertEquals(startCard.getColor(),throwCard.getColor());
    }

    @Test
    void testThrowBestCardWhenNotSameColor(){
        Card startCard =new Card(CardValue.NINE,CardColor.HEART);
        smartPlayer.setCards(new Card[]{startCard, new Card(CardValue.TEN,CardColor.SPADE), new Card(CardValue.EIGHT, CardColor.SPADE), new Card(CardValue.FOUR,CardColor.SPADE)});
        Queue<Card> roundDeck = new LinkedList<>();
        roundDeck.add(startCard);
        Card throwCard = smartPlayer.throwCard(roundDeck, 2);
        assertEquals(throwCard.getValue(),CardValue.TEN);
        assertNotEquals(startCard.getColor(),throwCard.getColor());
    }
}
