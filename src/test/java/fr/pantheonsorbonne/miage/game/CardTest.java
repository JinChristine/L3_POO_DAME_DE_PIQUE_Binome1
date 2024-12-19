package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class CardTest {

    private Card card;

    @BeforeEach
    public void setUp (){
        this.card = new Card(CardValue.ACE, CardColor.DIAMOND);
    }

    @Test
    public void testConstructor(){
        card = new Card(CardValue.ACE, CardColor.DIAMOND);
        assertEquals(CardValue.ACE, card.getValue());
        assertEquals(CardColor.DIAMOND, card.getColor());
    }

    @Test
    public void TestGetValue() {
        assertEquals(CardValue.ACE, card.getValue());
    }

    @Test
    public void TestGetColor() {
        assertEquals(CardColor.DIAMOND, card.getColor());
    }

    @Test
    public void TestToString(){
        Card newCard = new Card(CardValue.NINE, CardColor.HEART);
        String str = "NINE of HEART";
        assertEquals(str, newCard.toString());
    }
}