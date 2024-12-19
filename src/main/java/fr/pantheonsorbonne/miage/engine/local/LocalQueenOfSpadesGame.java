package fr.pantheonsorbonne.miage.engine.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import fr.pantheonsorbonne.miage.engine.QueenOfSpadesGame;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.DeckTest;
import fr.pantheonsorbonne.miage.game.DumpPlayer;
import fr.pantheonsorbonne.miage.game.PlayerTest;

public class LocalQueenOfSpadesGame extends QueenOfSpadesGame {
    private Queue<PlayerTest> players;

    public LocalQueenOfSpadesGame (Queue<PlayerTest>players){
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
    public PlayerTest getWinnerTurn(Queue<PlayerTest> playersOrder, Queue<Card> roundDeck){
        PlayerTest winnerPlayer = null;
        Card winnerCard = getWinnerCard(roundDeck);
        Card currentCard = roundDeck.peek();
        PlayerTest currentPlayer = playersOrder.peek();
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
    public boolean firstPlayerHas100(Queue<PlayerTest> players){
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
    public PlayerTest getPlayerWithLowestPoints(){
        PlayerTest playerWithLowestPoints = players.peek();
        for(PlayerTest currentPlayer: players){
            if (currentPlayer.getPoints() < playerWithLowestPoints.getPoints() ){
                playerWithLowestPoints = currentPlayer;
            }
        }
        return playerWithLowestPoints; 
    }

    @Override
    public PlayerTest searchPlayerWithTwoOfClub(){
        PlayerTest firstPlayer = null;
        for(PlayerTest player : players){
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
    public Queue<PlayerTest> orderPlayer(PlayerTest first){
        Queue<PlayerTest> queue = players;
        PlayerTest playerPeeked = queue.peek();
        while(!first.equals(playerPeeked)){
            queue.poll();
            queue.offer(playerPeeked);
            playerPeeked = queue.peek();
        }
        return queue;
    }
    @Override
    public Queue<PlayerTest> getPlayers(){
        return this.players;
    }
        public static void main(String[] args){
            PlayerTest player1 = new DumpPlayer("player1");
            PlayerTest player2 = new DumpPlayer("player2");
            PlayerTest player3 = new DumpPlayer("player3");
            PlayerTest player4 = new DumpPlayer("player4");
            Queue<PlayerTest> players = new LinkedList<PlayerTest>();
            players.add(player1);
            players.add(player2);
            players.add(player3);
            players.add(player4);
            LocalQueenOfSpadesGame localQueenOfSpadesGame = new LocalQueenOfSpadesGame(players);
            localQueenOfSpadesGame.play();
            System.exit(0);
    }
    
}
