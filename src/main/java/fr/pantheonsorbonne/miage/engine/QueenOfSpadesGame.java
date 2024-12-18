package fr.pantheonsorbonne.miage.engine;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;
import fr.pantheonsorbonne.miage.game.SmartPlayer;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.DumpPlayer;

import java.util.*;


public abstract class QueenOfSpadesGame {
    private Queue<Player> players;

    public QueenOfSpadesGame(){
        
    }

    protected abstract Queue<Player> getPlayers();
    protected abstract Card getWinnerCard(Queue<Card> roundDeck);
    protected abstract int givePointsToWinnerTurn(Queue<Card> roundDeck);
    protected abstract Player getWinnerTurn(Queue<Player> playersOrder, Queue<Card> roundDeck);
    protected abstract boolean firstPlayerHas100(Queue<Player> players);
    protected abstract Player getPlayerWithLowestPoints();
    protected abstract Player searchPlayerWithTwoOfClub();
    protected abstract  Queue<Player> orderPlayer(Player first);

    public void play(){
        int turn = 1;
        int round = 0;
        while (!firstPlayerHas100(players)){
            round++;
            Player firstPlayer = players.peek();
            firstPlayer.setCards(Deck.giveCards());
            players.poll();
            players.offer(firstPlayer);
            Player secondPlayer = players.peek();
            secondPlayer.setCards(Deck.giveCards());
            players.poll();
            players.offer(secondPlayer);
            Player thirdPlayer = players.peek();
            thirdPlayer.setCards(Deck.giveCards());
            players.poll();
            players.offer(thirdPlayer);
            Player fourthPlayer = players.peek();
            fourthPlayer.setCards(Deck.giveCards());
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

            Player firstPlayerToPlay = null;
            Queue<Player> playersTurn;
            while(true){       
                if(turn == 1){
                    firstPlayer = searchPlayerWithTwoOfClub();
                }
                playersTurn = orderPlayer(firstPlayerToPlay);
                Queue<Card> turnDeck = new LinkedList<>();
                Player firstPlayerInTurn = playersTurn.poll();
                playersTurn.offer(firstPlayerInTurn);
                turnDeck.offer(firstPlayerInTurn.throwCard(turnDeck, turn));
                Player secondPlayerInTurn = playersTurn.poll();
                playersTurn.offer(secondPlayerInTurn);
                turnDeck.offer(secondPlayerInTurn.throwCard(turnDeck, turn));
                Player thirdPlayerInTurn = playersTurn.poll();
                playersTurn.offer(thirdPlayerInTurn);
                turnDeck.offer(thirdPlayerInTurn.throwCard(turnDeck, turn));
                Player fourthPlayerInTurn = playersTurn.poll();
                playersTurn.offer(fourthPlayerInTurn);
                turnDeck.offer(fourthPlayerInTurn.throwCard(turnDeck, turn));

                Player winnerTurn = getWinnerTurn(players, turnDeck);
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