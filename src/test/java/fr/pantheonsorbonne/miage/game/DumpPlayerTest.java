package fr.pantheonsorbonne.miage.game;

import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

public class DumpPlayerTest{
    private DumpPlayer player;

    @BeforeEach
    public void setUp(){
        this.player = new DumpPlayer("Toto");
        player.cards = new Card[] {
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
    }

    @Test
    public void testConstructor(){
        assertSame("Toto", player.getName());
    }

    @Test
    public void testThrowCard(Queue<Card> roundDeck, int turn) {
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

    }
    @Test
    public void getRandom(int max) {
        Random rand = new Random();
        return rand.nextInt(0, max);
    }
    @Test
    public void getRandomCard(int i, List<Card> listCardNotNull) {
        return listCardNotNull.get(i);
    }
    @Test
    public void getCardWithSameColorInDeck(Queue<Card> roundDeck, List<Card> listCardNotNull) {
        while (true) {
            Card randomCard = getRandomCard(getRandom(this.cards.length), listCardNotNull);
            if (roundDeck.peek().getColor().equals(randomCard.getColor())) {
                return randomCard;
            }
        }
    }
}