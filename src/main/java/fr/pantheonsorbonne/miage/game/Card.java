package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

public class Card {
    private CardValue value;
    private CardColor color;
    private boolean isJoker;

    public Card(CardValue value, CardColor color){
        this.value = value;
        this.color = color;
        this.isJoker = false;
    }

    public Card(CardValue value, CardColor color, boolean isJoker){
        this.value = value;
        this.color = color;
        this.isJoker = isJoker;
    }

    public CardValue getValue(){
        return this.value;
    }

    public CardColor getColor(){
        return this.color;
    }

    public boolean isJoker(){
        return isJoker;
    }

    public void setIsJoker(){
        this.isJoker = true;
    }

    public void imitateCard(CardColor newColor, CardValue newValue){
        if(isJoker){
            this.value = newValue;
            this.color = newColor;
        }
    }

    @Override
    public String toString(){
        CardValue value = getValue();
        CardColor color = getColor();
        
        return value + " of " + color;
    }

}
