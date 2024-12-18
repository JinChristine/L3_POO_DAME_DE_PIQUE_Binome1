package fr.pantheonsorbonne.miage.engine;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;
import fr.pantheonsorbonne.miage.game.SmartPlayer;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.DumpPlayer;

import java.util.*;


public abstract class QueenOfSpadesGame {
    protected Player player1;
    protected Player player2;
    protected Player player3;
    protected Player player4;
    protected Queue<Player> players;

    public QueenOfSpadesGame(Player player1, Player player2, Player player3, Player player4, Queue<Player>players){
        this.player1 = new DumpPlayer("player1");
        this.player2 = new DumpPlayer("player2");
        this.player3 = new DumpPlayer("player3");
        this.player4 = new DumpPlayer("player4");
        this.players = new LinkedList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.players.add(player4);
    }

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
            player1.setCards(Deck.giveCards());
            player2.setCards(Deck.giveCards());
            player3.setCards(Deck.giveCards());
            player4.setCards(Deck.giveCards());
            switch (round%4){
                case 1:
                    player1.swap3Cards(player2);
                    player2.swap3Cards(player3);
                    player3.swap3Cards(player4);
                    player4.swap3Cards(player1);
                    break;
                case 2:
                    player1.swap3Cards(player4);
                    player2.swap3Cards(player1);
                    player3.swap3Cards(player2);
                    player4.swap3Cards(player3);
                    break;
                case 3:
                    player1.swap3Cards(player3);
                    player2.swap3Cards(player4);
                    player3.swap3Cards(player1);
                    player4.swap3Cards(player2);
                    break; 
                default: 
                    break;
            }

            Player firstPlayer = null;
            Queue<Player> playersTurn;
            while(true){       
                if(turn == 1){
                    firstPlayer = searchPlayerWithTwoOfClub();
                }
                playersTurn = orderPlayer(firstPlayer);
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