package fr.pantheonsorbonne.miage.engine;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.game.Card;

import java.util.*;


public class QueenOfSpadesGame {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Queue<Player> players;

    public QueenOfSpadesGame(Player player1, Player player2, Player player3, Player player4, Queue<Player>players){
        this.player1 = new Player("player1");
        this.player2 = new Player("player2");
        this.player3 = new Player("player3");
        this.player4 = new Player("player4");
        this.players = new LinkedList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.players.add(player4);
    }

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
    
    public static void main(String[] args){
        QueenOfSpadesGame queenOfSpadesGame = new QueenOfSpadesGame();
        queenOfSpadesGame.play();
    }
}