package fr.pantheonsorbonne.miage.engine.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fr.pantheonsorbonne.miage.engine.QueenOfSpadesGame;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.DumpPlayer;
import fr.pantheonsorbonne.miage.game.Player;

public class LocalQueenOfSpadesGame extends QueenOfSpadesGame {
    private Queue<Player> players;

    public LocalQueenOfSpadesGame (Queue<Player>players){
        this.players = players;
    }
    
    @Override
    public Card getWinnerCard(Queue<Card> roundDeck){
        Card highCardValue = roundDeck.peek();
        Card currentCard = roundDeck.peek();
        while(!roundDeck.isEmpty()){
            if (currentCard.getValue().getRank() > highCardValue.getValue().getRank()){
                highCardValue = currentCard;
            }
            currentCard = roundDeck.peek();
        }
        return highCardValue;
    }

    @Override
    public int givePointsToWinnerTurn(Queue<Card> roundDeck){
        int countPointsHeartCards = 0;
        Card currentCard = roundDeck.peek();
        while (!roundDeck.isEmpty()){
            if(currentCard.getColor().toString().equals("Spade")){
                if(currentCard.getValue().toString().equals("Queen")){
                    countPointsHeartCards += 13;
                }
            }
            else if (currentCard.getColor().toString().equals("Heart")){
                countPointsHeartCards++;
            }
            roundDeck.poll();
            currentCard = roundDeck.peek();
        }
        return countPointsHeartCards;
    }
    @Override
    public Player getWinnerTurn(Queue<Player> playersOrder, Queue<Card> roundDeck){
        Player winnerPlayer = null;
        Card winnerCard = getWinnerCard(roundDeck);
        Card currentCard = roundDeck.peek();
        Player currentPlayer = playersOrder.peek();
        while(currentCard != winnerCard){
            roundDeck.poll();
            playersOrder.poll();
            currentCard = roundDeck.peek();
            currentPlayer = playersOrder.peek();
            
        }
        winnerPlayer = currentPlayer;
        winnerPlayer.setPoints(givePointsToWinnerTurn(roundDeck));
        return winnerPlayer;
    }

    @Override
    public boolean firstPlayerHas100(Queue<Player> players){
        while(!players.isEmpty()){
            if(players.peek().getPoints() >= 100){
                return true;
            }
            else{
                players.poll();
            }
        }
        return false;
    }

    @Override
    public Player getPlayerWithLowestPoints(){
        Player playerWithLowestPoints = players.peek();
        for(Player currentPlayer: players){
            if (currentPlayer.getPoints() < playerWithLowestPoints.getPoints() ){
                playerWithLowestPoints = currentPlayer;
            }
        }
        return playerWithLowestPoints; 
    }

    @Override
    public Player searchPlayerWithTwoOfClub(){
        Player firstPlayer = null;
        for(Player player : players){
            for(Card card: player.getCards()){
                System.out.println(card.getValue().getRank());
                System.out.println(card.getColor());
                System.out.println( CardColor.valueOf("CLUB"));
                System.out.println(card.getColor() == CardColor.valueOf("CLUB"));
                if(card.getValue().getRank() == 2 && card.getColor() == CardColor.valueOf("CLUB")){
                    firstPlayer = player;
                }
            }
        }
        return firstPlayer;
    }
    @Override
    public Queue<Player> orderPlayer(Player first){
        Queue<Player> queue = players;
        Player playerPeeked = queue.peek();
        while(!first.equals(playerPeeked)){
            queue.poll();
            queue.offer(playerPeeked);
            playerPeeked = queue.peek();
        }
        return queue;
    }
    @Override
    public Queue<Player> getPlayers(){
        return this.players;
    }
        public static void main(String[] args){
            Player player1 = new DumpPlayer("player1");
            Player player2 = new DumpPlayer("player2");
            Player player3 = new DumpPlayer("player3");
            Player player4 = new DumpPlayer("player4");
            Queue<Player> players = new LinkedList<Player>();
            players.add(player1);
            players.add(player2);
            players.add(player3);
            players.add(player4);
            LocalQueenOfSpadesGame localQueenOfSpadesGame = new LocalQueenOfSpadesGame(players);
            localQueenOfSpadesGame.play();
            System.exit(0);
    }
    
}
