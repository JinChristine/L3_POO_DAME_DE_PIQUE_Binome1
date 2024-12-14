package fr.pantheonsorbonne.miage.game;

import java.util.Queue;
import java.util.Random;
import fr.pantheonsorbonne.miage.enums.CardColor;

public class DumpPlayer extends Player{
    
    public DumpPlayer (String name){
        super(name);
    }
    @Override
    public Card throwCard(Queue<Card> roundDeck, int turn){
        Card cardToPlay = getRandomCard();
        while(turn == 1 && cardToPlay.getColor().equals(CardColor.valueOf("HEART"))){
            cardToPlay = getRandomCard();
        }
        if(turn != 1){
            for(int i = 0; i < this.cards.length; i++){
                if(roundDeck.peek().getColor().equals(this.cards[i].getColor())){
                    Card temp = this.cards[i];
                    this.cards[i] = null;
                    return temp;
                }
            }
        }


        return ; 
    }

    public Card getRandomCard(){
        Random rand = new Random();
        int i = rand.nextInt(0, this.cards.length);
        return this.cards[i];
    }
}
