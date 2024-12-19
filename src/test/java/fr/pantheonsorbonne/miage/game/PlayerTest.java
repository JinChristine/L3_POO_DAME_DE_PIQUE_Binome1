package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public abstract class PlayerTest {
    private Player player1;
    private Player player2;
    
    @BeforeEach
    public void setUp() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
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
/*
    public String getName() {
        return this.name;
    }

    public Card[] getCards() {
        return this.cards;
    }

    public void swap3Cards(Player otherPlayer) {
        Card[] swapCardsHand1 = this.getSwap3Cards();
        Card[] swapCardsHand2 = otherPlayer.getSwap3Cards();

        swapCards(this, swapCardsHand1, swapCardsHand2);
        swapCards(otherPlayer, swapCardsHand2, swapCardsHand1);

    }

    private Card[] getSwap3Cards() {
        Card[] cardsToGive = new Card[3];
        Card maxCard = this.cards[0];
        int index = 0;
        while (index != 2) {
            for (Card card : this.cards) {
                if (card.getValue().getRank() > maxCard.getValue().getRank()) {
                    maxCard = card;
                }
            }
            cardsToGive[index++] = maxCard;
        }
        return cardsToGive;
    }

    private void swapCards(Player player, Card[] swapCardsHand1, Card[] swapCardsHand2) {
        Card[] handle = player.getCards();
        for (int i = 0; i < handle.length; i++) {
            for (int j = 0; j < swapCardsHand1.length; j++) {
                if (handle[i].equals(swapCardsHand1[j])) {
                    handle[i] = swapCardsHand2[j];
                }
            }
        }
        player.setCards(handle);
    }

    public abstract Card throwCard(Queue<Card> roundDeck, int turn);
  
    private boolean isNull(Card card){
        return card.equals(null);
    }

    protected List<Card> getCardListNotNull(Card[] cards){
        List<Card> list = new LinkedList<>();
        for(Card card: cards){
            if(!isNull(card)){
                list.add(card);
            }
        }
        return list;
    }
    
    protected boolean haveSameColorInDeck(Queue<Card> roundDeck) {
        Card firstPlayedCard = roundDeck.peek();
        for (Card card : this.cards) {
            if (card.getColor().equals(firstPlayedCard.getColor())) {
                return true;
            }
        }
        return false;
    }

    protected void replaceBestCardsInDeckByNull(Card bestCard){
        for(int i = 0; i<this.cards.length;i++){
            if(this.cards[i].getValue().getRank() == bestCard.getValue().getRank()){
                this.cards[i]=null;
            }
        }
    }*/

}
