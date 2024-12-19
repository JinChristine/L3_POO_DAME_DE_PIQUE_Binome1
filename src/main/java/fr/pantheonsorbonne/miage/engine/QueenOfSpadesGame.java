package fr.pantheonsorbonne.miage.engine;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;
import fr.pantheonsorbonne.miage.enums.CardColor;
import fr.pantheonsorbonne.miage.enums.CardValue;

import fr.pantheonsorbonne.miage.game.Card;

import java.util.*;


public abstract class QueenOfSpadesGame {

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
        final Queue<Player> players = getPlayers();
        while (true){
            if(firstPlayerHas100(players)){
                System.out.println(getPlayerWithLowestPoints().getName() + "a gagné la partie avec "+getPlayerWithLowestPoints().getPoints() + " point(s)");
                break;
            }
            round++;
            Deck.newDeck();
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
                    firstPlayerToPlay = searchPlayerWithTwoOfClub();
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
                firstPlayerToPlay = winnerTurn;

                if (turn == 13){
                    System.out.println(getPlayerWithLowestPoints().getName() + " est en tête avec " + getPlayerWithLowestPoints().getPoints()+" point(s)");
                    break;
                }
                turn++;
            }
            turn = 1;
        }
    }


    public boolean has20PointsInRoundDeck(Queue<Card> roundeck){
        Queue<Card> playedCard = roundeck;
        int som = 0;
        while(playedCard.size() != 0){
            Card card = playedCard.peek();
            som += card.getValue().getRank();
            playedCard.poll();
        }
        if(som == 20){
            givePlayerTappedFasterOnTheTable(tapOntheTable(), tapOntheTable(), tapOntheTable(), tapOntheTable());
        }
        return true;
    }

    public float tapOntheTable(){
        Random rand = new Random();
        return rand.nextFloat(0,1);

    }
    public void givePlayerTappedFasterOnTheTable(float time1, float time2, float time3, float time4){
        float max = Math.max(time1, Math.max(time2, Math.max(time3, time4)));
        if (max == time1){
            player1.setPoints(player1.getPoints()+givePointsToWinnerTurn(roundDeck));
        }
        else if (max == time2){
            player2.setPoints(player2.getPoints()+givePointsToWinnerTurn(roundDeck));
        }
        else if (max == time3){
            player3.setPoints(player3.getPoints()+givePointsToWinnerTurn(roundDeck));
        }
        else {
            player4.setPoints(player4.getPoints()+givePointsToWinnerTurn(roundDeck));
        }
    }

    public boolean has21PointsInRoundDeck(Queue<Card> roundeck){
        Queue<Card> playedCard = roundeck;
        int som = 0;
        while(playedCard.size() != 0){
            Card card = playedCard.peek();
            som += card.getValue().getRank();
            playedCard.poll();
        }
        if(som == 21){
            changeDeckAndPointsWithRightPlayer();
        }
        return true;
    }

    public void changeDeckAndPointsWithRightPlayer(Player firstPlayer, Player secondPlayer, Player thirdPlayer, Player fourthPlayer){
        firstPlayer.swapAllCardsAndPoints(fourthPlayer);
        secondPlayer.swapAllCardsAndPoints(firstPlayer);
        thirdPlayer.swapAllCardsAndPoints(secondPlayer);
        fourthPlayer.swapAllCardsAndPoints(thirdPlayer);
    }

    public Card defineJoker(Card randomCard){
        Card joker = getRandomCard(getRandom(deck.size()));

    return joker;
    }
}