package fr.pantheonsorbonne.miage.game;

import java.util.Queue;
import java.util.Random;
import fr.pantheonsorbonne.miage.enums.CardColor;
import java.util.List;

public class DumpPlayer extends Player {

    public DumpPlayer(String name) {
        super(name);
    }

    @Override
    public Card throwCard(Queue<Card> roundDeck, int turn) {
        List<Card> listCardNotNull = getCardListNotNull(this.cards);
        int index = getRandom(listCardNotNull.size());
        Card cardToPlay = getRandomCard(index, listCardNotNull);
        while (true) {
            if (haveSameColorInDeck(roundDeck)) {
                cardToPlay = getCardWithSameColorInDeck(roundDeck, listCardNotNull);
                break;
            } else if (turn == 1 && roundDeck.isEmpty()) {
                cardToPlay = this.cards[searchIndexOfTwoOfClub()];
                break;
            } else if (turn == 1) {
                if (cardToPlay.getColor().equals(CardColor.HEART)) {
                    index = getRandom(listCardNotNull.size());
                    cardToPlay = getRandomCard(index, listCardNotNull);
                }
                else{break;}
            } else {
                break;
            }

        }
        replaceBestCardsInDeckByNull(cardToPlay);
        return cardToPlay;
    }

    public int getRandom(int max) {
        if(max <=1){
            return 0;
        }
        Random rand = new Random();
        return rand.nextInt(0, max);
    }

    public Card getRandomCard(int i, List<Card> listCardNotNull) {
        return listCardNotNull.get(i);
    }

    public Card getCardWithSameColorInDeck(Queue<Card> roundDeck, List<Card> listCardNotNull) {
        while (true) {
            Card randomCard = getRandomCard(getRandom(listCardNotNull.size()), listCardNotNull);
            if (roundDeck.peek().getColor().equals(randomCard.getColor())) {
                return randomCard;
            }
        }
    }
}