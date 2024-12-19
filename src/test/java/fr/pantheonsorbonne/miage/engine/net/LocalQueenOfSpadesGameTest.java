package fr.pantheonsorbonne.miage.engine.net;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.DumpPlayer;
import fr.pantheonsorbonne.miage.game.Player;
import fr.pantheonsorbonne.miage.game.SmartPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.engine.local.LocalQueenOfSpadesGame;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;


public class LocalQueenOfSpadesGameTest {
    private Queue<Player> players;
    private LocalQueenOfSpadesGame game;

    @BeforeEach
    void setUp() {
        players = new LinkedList<>();
        players.add(new SmartPlayer("player1"));
        players.add(new SmartPlayer("player2"));
        players.add(new DumpPlayer("player3"));
        players.add(new DumpPlayer("player4"));
        game = new LocalQueenOfSpadesGame(players);
    }
    
    @Test
    void testGetWinnerCard() {
        Queue<Card> roundDeck = new LinkedList<>();
        roundDeck.add(new Card(CardValue.THREE, CardColor.HEART)); 
        roundDeck.add(new Card(CardValue.QUEEN, CardColor.HEART)); 
        roundDeck.add(new Card(CardValue.NINE, CardColor.HEART)); 
        roundDeck.add(new Card(CardValue.ACE, CardColor.SPADE));

        Card winnerCard = game.getWinnerCard(roundDeck);

        assertEquals(12, winnerCard.getValue().getRank());
        assertEquals(CardColor.HEART, winnerCard.getColor());
    }
    
}
