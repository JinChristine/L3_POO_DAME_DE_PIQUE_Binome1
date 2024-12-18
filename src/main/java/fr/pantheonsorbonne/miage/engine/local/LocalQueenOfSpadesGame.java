package fr.pantheonsorbonne.miage.engine.local;

import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Player;

public class LocalQueenOfSpadesGame {
    


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

    public Player getPlayerWithLowestPoints(){
        Player playerWithLowestPoints = this.players.peek();
        for(Player currentPlayer: this.players){
            if (currentPlayer.getPoints() < playerWithLowestPoints.getPoints() ){
                playerWithLowestPoints = currentPlayer;
            }
        }
        return playerWithLowestPoints; 
    }

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
    
}
