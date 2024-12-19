package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class CardTest {

    private Card normalCard;
    private Card jokerCard;

    @BeforeEach
    public void setUp (){
        normalCard = new Card(CardValue.ACE, CardColor.DIAMOND);
        jokerCard = new Card(CardValue.TWO, CardColor.HEART, true);
    }

    @Test
    public void testConstructor(){
        normalCard = new Card(CardValue.ACE, CardColor.DIAMOND);
        assertEquals(CardValue.ACE, normalCard.getValue());
        assertEquals(CardColor.DIAMOND, normalCard.getColor());
    }

    @Test 
    public void testIsJoker(){
        assertTrue(jokerCard.isJoker());
        assertFalse(normalCard.isJoker()); 
    }
    @Test
    public void TestGetValue() {
        assertEquals(CardValue.ACE, normalCard.getValue());
        assertEquals(CardValue.TWO, jokerCard.getValue());
    }

    @Test
    public void TestGetColor() {
        assertEquals(CardColor.DIAMOND, normalCard.getColor());
        assertEquals(CardColor.HEART, jokerCard.getColor());
    }

    @Test
    public void testSetIsJoker() {
        normalCard.setIsJoker();
        assertTrue(normalCard.isJoker());
    }

    @Test
    public void testImitateCard() {
        jokerCard.imitateCard(CardColor.DIAMOND, CardValue.KING);
        assertEquals(CardValue.KING, jokerCard.getValue());
        assertEquals(CardColor.DIAMOND, jokerCard.getColor());
    }

    @Test
    public void testImitateCardWithNormalCard() {
        normalCard.imitateCard(CardColor.CLUB, CardValue.QUEEN);
        assertEquals(CardValue.ACE, normalCard.getValue());
        assertEquals(CardColor.DIAMOND, normalCard.getColor());
    }
    @Test
    public void TestToString(){
        Card newCard = new Card(CardValue.NINE, CardColor.HEART);
        String str = "NINE of HEART";
        assertEquals(str, newCard.toString());
    }
}