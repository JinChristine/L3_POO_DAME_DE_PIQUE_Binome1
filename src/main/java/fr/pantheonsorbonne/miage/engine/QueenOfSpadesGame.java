package fr.pantheonsorbonne.miage.engine;
import fr.pantheonsorbonne.miage.game.DeckTest;
import fr.pantheonsorbonne.miage.game.PlayerTest;
import fr.pantheonsorbonne.miage.game.SmartPlayerTets;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.DumpPlayer;

import java.util.*;


public abstract class QueenOfSpadesGame {
    private Queue<PlayerTest> players;

    public QueenOfSpadesGame(){
        
    }

    protected abstract Queue<PlayerTest> getPlayers();
    protected abstract Card getWinnerCard(Queue<Card> roundDeck);
    protected abstract int givePointsToWinnerTurn(Queue<Card> roundDeck);
    protected abstract PlayerTest getWinnerTurn(Queue<PlayerTest> playersOrder, Queue<Card> roundDeck);
    protected abstract boolean firstPlayerHas100(Queue<PlayerTest> players);
    protected abstract PlayerTest getPlayerWithLowestPoints();
    protected abstract PlayerTest searchPlayerWithTwoOfClub();
    protected abstract  Queue<PlayerTest> orderPlayer(PlayerTest first);

    public void play(){
        int turn = 1;
        int round = 0;
        while (!firstPlayerHas100(players)){
            round++;
            PlayerTest firstPlayer = players.peek();
            firstPlayer.setCards(DeckTest.giveCards());
            players.poll();
            players.offer(firstPlayer);
            PlayerTest secondPlayer = players.peek();
            secondPlayer.setCards(DeckTest.giveCards());
            players.poll();
            players.offer(secondPlayer);
            PlayerTest thirdPlayer = players.peek();
            thirdPlayer.setCards(DeckTest.giveCards());
            players.poll();
            players.offer(thirdPlayer);
            PlayerTest fourthPlayer = players.peek();
            fourthPlayer.setCards(DeckTest.giveCards());
            players.poll();
            players.offer(fourthPlayer);
            switch (round%4){
                case 1:
                    firstPlayer.swap3Cards(secondPlayer);
                    secondPlayer.swap3Cards(thirdPlayer);
                    thirdPlayer.swap3Cards(fourthPlayer);
                    fourthPlayer.swap3Cards(firstPlayer);
                    break;
                case 2:
                    firstPlayer.swap3Cards(fourthPlayer);
                    secondPlayer.swap3Cards(firstPlayer);
                    thirdPlayer.swap3Cards(secondPlayer);
                    fourthPlayer.swap3Cards(thirdPlayer);
                    break;
                case 3:
                    firstPlayer.swap3Cards(thirdPlayer);
                    secondPlayer.swap3Cards(fourthPlayer);
                    thirdPlayer.swap3Cards(firstPlayer);
                    fourthPlayer.swap3Cards(secondPlayer);
                    break; 
                default: 
                    break;
            }

            PlayerTest firstPlayerToPlay = null;
            Queue<PlayerTest> playersTurn;
            while(true){       
                if(turn == 1){
                    firstPlayer = searchPlayerWithTwoOfClub();
                }
                playersTurn = orderPlayer(firstPlayerToPlay);
                Queue<Card> turnDeck = new LinkedList<>();
                PlayerTest firstPlayerInTurn = playersTurn.poll();
                playersTurn.offer(firstPlayerInTurn);
                turnDeck.offer(firstPlayerInTurn.throwCard(turnDeck, turn));
                PlayerTest secondPlayerInTurn = playersTurn.poll();
                playersTurn.offer(secondPlayerInTurn);
                turnDeck.offer(secondPlayerInTurn.throwCard(turnDeck, turn));
                PlayerTest thirdPlayerInTurn = playersTurn.poll();
                playersTurn.offer(thirdPlayerInTurn);
                turnDeck.offer(thirdPlayerInTurn.throwCard(turnDeck, turn));
                PlayerTest fourthPlayerInTurn = playersTurn.poll();
                playersTurn.offer(fourthPlayerInTurn);
                turnDeck.offer(fourthPlayerInTurn.throwCard(turnDeck, turn));

                PlayerTest winnerTurn = getWinnerTurn(players, turnDeck);
                firstPlayer = winnerTurn;
                if (turn == 13){
                    System.out.println(getPlayerWithLowestPoints().getName() + " est en tête avec " + getPlayerWithLowestPoints().getPoints());
                    break;
                }
                turn++;
            }
            turn = 1;
        }
    }

}