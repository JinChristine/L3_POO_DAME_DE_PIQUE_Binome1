package fr.pantheonsorbonne.miage.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Player {
    private String name;
    protected Card[] cards;
    protected int points;

    public Player(String name) {
        this.name = name;
        this.cards = new Card[13];
        this.points = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

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
    }

}
