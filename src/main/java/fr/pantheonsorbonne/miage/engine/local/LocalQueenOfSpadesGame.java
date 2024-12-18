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
    

    public LocalQueenOfSpadesGame (Player player1, Player player2, Player player3, Player player4, Queue<Player>players){
        super(player1, player2, player3, player4, players);
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
        Player playerWithLowestPoints = this.players.peek();
        for(Player currentPlayer: this.players){
            if (currentPlayer.getPoints() < playerWithLowestPoints.getPoints() ){
                playerWithLowestPoints = currentPlayer;
            }
        }
        return playerWithLowestPoints; 
    }

    @Override
    public Player searchPlayerWithTwoOfClub(){
        Player firstPlayer = null;
        for(Player player : this.players){
            for(Card card: player.getCards()){
                if(card.getValue().getRank() == 2 && card.getColor() == CardColor.valueOf("CLUB")){
                    firstPlayer = player;
                }
            }
        }
        return firstPlayer;
    }
    @Override
    public Queue<Player> orderPlayer(Player first){
        Queue<Player> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(player1, player2, player3, player4));
        Player playerPeeked = queue.peek();
        while(!first.equals(playerPeeked)){
            queue.poll();
            queue.offer(playerPeeked);
            playerPeeked = queue.peek();
        }
        return queue;
    }
        public static void main(String[] args){
            LocalQueenOfSpadesGame localQueenOfSpadesGame = new LocalQueenOfSpadesGame(new DumpPlayer("player1"), new DumpPlayer("player2"), new DumpPlayer("player3"), new DumpPlayer("player4"), new LinkedList<Player>());
            localQueenOfSpadesGame.play();
            System.exit(0);
    }
    
}
