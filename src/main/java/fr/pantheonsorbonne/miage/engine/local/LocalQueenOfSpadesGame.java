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
import fr.pantheonsorbonne.miage.game.SmartPlayer;

public class LocalQueenOfSpadesGame extends QueenOfSpadesGame {
    private Queue<Player> players;

    public LocalQueenOfSpadesGame (Queue<Player>players){
        this.players = players;
    }
    
    @Override
    public Card getWinnerCard(Queue<Card> roundDeck){
        Queue<Card> roundDeckCopy = new LinkedList<>();
        roundDeckCopy.addAll(roundDeck);
        Card highCardValue = roundDeckCopy.peek();
        Card currentCard = roundDeckCopy.peek();
        while(!roundDeckCopy.isEmpty()){
            if (currentCard.getValue().getRank() > highCardValue.getValue().getRank()){
                highCardValue = currentCard;
            }
            currentCard = roundDeckCopy.poll();
        }
        return highCardValue;
    }

    @Override
    public int givePointsToWinnerTurn(Queue<Card> roundDeck){
        int countPointsHeartCards = 0;
        Card currentCard = roundDeck.peek();
        while (!roundDeck.isEmpty()){
            if(currentCard.getColor().equals(CardColor.valueOf("SPADE"))){
                if(currentCard.getValue().getRank() == 12 ){
                    countPointsHeartCards += 13;
                }
            }
            else if (currentCard.getColor().equals(CardColor.valueOf("HEART"))){
                countPointsHeartCards++;
            }
            roundDeck.poll();
            currentCard = roundDeck.peek();
        }
        return countPointsHeartCards;
    }
    @Override
    public Player getWinnerTurn(Queue<Player> playersOrder, Queue<Card> roundDeck){
        Queue<Player> playersOrderCopy = new LinkedList<>();
        playersOrderCopy.addAll(playersOrder);
        Player winnerPlayer = null;
        Card winnerCard = getWinnerCard(roundDeck);
        Queue<Card> roundDeckCopy = new LinkedList<>();
        roundDeckCopy.addAll(roundDeck);
        Card currentCard = roundDeck.peek();
        Player currentPlayer = playersOrderCopy.peek();
        while(currentCard != winnerCard){
            roundDeckCopy.poll();
            playersOrderCopy.poll();
            currentCard = roundDeckCopy.peek();
            currentPlayer = playersOrderCopy.peek();
            
        }
        winnerPlayer = currentPlayer;
        winnerPlayer.setPoints(winnerPlayer.getPoints()+givePointsToWinnerTurn(roundDeck));
        return winnerPlayer;
    }

    @Override
    public boolean firstPlayerHas100(Queue<Player> players){
        Queue<Player> playersCopy = new LinkedList<>();
        playersCopy.addAll(players);
        while(!playersCopy.isEmpty()){
            if(playersCopy.peek().getPoints() >= 100){
                return true;
            }
            else{
                playersCopy.poll();
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
                if(card.getValue().getRank() == 2 && card.getColor().equals(CardColor.valueOf("CLUB"))){
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
            Player player1 = new SmartPlayer("player1");
            Player player2 = new SmartPlayer("player2");
            Player player3 = new SmartPlayer("player3");
            Player player4 = new SmartPlayer("player4");
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