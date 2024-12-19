package fr.pantheonsorbonne.miage.engine;

import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;

import fr.pantheonsorbonne.miage.game.Card;

import java.util.*;

public abstract class QueenOfSpadesGame {

    public QueenOfSpadesGame() {
    }

    protected abstract Queue<Player> getPlayers();

    protected abstract Card getWinnerCard(Queue<Card> roundDeck);

    protected abstract int givePointsToWinnerTurn(Queue<Card> roundDeck);

    protected abstract Player getWinnerTurn(Queue<Player> playersOrder, Queue<Card> roundDeck);

    protected abstract boolean firstPlayerHas100(Queue<Player> players);

    protected abstract Player getPlayerWithLowestPoints();

    protected abstract Player searchPlayerWithTwoOfClub();

    protected abstract Queue<Player> orderPlayer(Player first);

    public void play() {
        int turn = 1;
        int round = 0;
        final Queue<Player> players = getPlayers();
        while (true) {
            if (firstPlayerHas100(players)) {
                System.out.println(getPlayerWithLowestPoints().getName() + " a gagné la partie avec "
                        + getPlayerWithLowestPoints().getPoints() + " point(s)");
                System.out.println("Voici le résultat de la partie:");
                for(Player player : players){
                    System.out.println("- "+player.getName() +" a obtenu "+player.getPoints()+ " dans la partie");
                }
                break;
            }
            round++;
            System.out.println("Round " + round+ "\n");
            Deck.newDeck();
            System.out.println("Le joker de ce Round est "+Deck.getJoker());
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
            switch (round % 4) {
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
            while (true) {
                if (turn == 1) {
                    firstPlayerToPlay = searchPlayerWithTwoOfClub();
                }
                System.out.println("Tour " + turn+ "\n");
                playersTurn = orderPlayer(firstPlayerToPlay);
                Queue<Card> turnDeck = new LinkedList<>();
                Player firstPlayerInTurn = playersTurn.poll();
                playersTurn.offer(firstPlayerInTurn);
                Card val = firstPlayerInTurn.throwCard(turnDeck, turn);
                turnDeck.offer(val);
                System.out.println(firstPlayerInTurn.getName() + " played " + val);
                Player secondPlayerInTurn = playersTurn.poll();
                playersTurn.offer(secondPlayerInTurn);
                val = secondPlayerInTurn.throwCard(turnDeck, turn);
                turnDeck.offer(val);
                System.out.println(secondPlayerInTurn.getName() + " played " + val);
                Player thirdPlayerInTurn = playersTurn.poll();
                playersTurn.offer(thirdPlayerInTurn);
                val = thirdPlayerInTurn.throwCard(turnDeck, turn);
                turnDeck.offer(val);
                System.out.println(thirdPlayerInTurn.getName() + " played " + val);
                Player fourthPlayerInTurn = playersTurn.poll();
                playersTurn.offer(fourthPlayerInTurn);
                val = fourthPlayerInTurn.throwCard(turnDeck, turn);
                turnDeck.offer(val);
                System.out.println(fourthPlayerInTurn.getName() + " played " + val);

                Player winnerTurn = getWinnerTurn(players, turnDeck);

                System.out.println();
                if (has20PointsInRoundDeck(turnDeck)) {
                    System.out.println("La somme des points sur la table a atteint 20, jouons à un jeu un peu plus particulier !");
                    winnerTurn = givePlayerTappedFasterOnTheTable(players);
                } else if (has21PointsInRoundDeck(turnDeck)) {
                    System.out.println("La somme des points sur la table a atteint 21, tous les joueurs échangent leurs points et leur cartes avec le voisin à droite :)");
                    changeDeckAndPointsWithRightPlayer(playersTurn);
                }

                firstPlayerToPlay = winnerTurn;

                if (turn == 13) {
                    System.out.println(getPlayerWithLowestPoints().getName() + " est en tête avec "
                            + getPlayerWithLowestPoints().getPoints() + " point(s)");
                    break;
                }
                turn++;
            }
            turn = 1;
        }
    }

    public boolean has20PointsInRoundDeck(Queue<Card> roundeck) {
        Queue<Card> playedCard = new LinkedList<>();
        playedCard.addAll(roundeck);
        int som = 0;
        while (playedCard.size() != 0) {
            Card card = playedCard.peek();
            som += card.getValue().getRank();
            playedCard.poll();
        }
        if (som == 20) {
            return true;
        }
        return false;
    }

    public Player givePlayerTappedFasterOnTheTable(Queue<Player> players) {
        Queue<Player> copyPlayers = new LinkedList<>();
        copyPlayers.addAll(copyPlayers);
        Player player1 = copyPlayers.poll();
        double player1Time = player1.tapOntheTable();
        Player player2 = copyPlayers.poll();
        double player2Time = player2.tapOntheTable();
        Player player3 = copyPlayers.poll();
        double player3Time = player3.tapOntheTable();
        Player player4 = copyPlayers.poll();
        double player4Time = player4.tapOntheTable();

        double minTime = Math.min(player1Time, Math.min(player2Time, Math.min(player3Time, player4Time)));
        if (minTime == player1Time) {
            return player1;
        } else if (minTime == player2Time) {
            return player2;
        } else if (minTime == player3Time) {
            return player3;
        } else {
            return player4;
        }
    }

    public boolean has21PointsInRoundDeck(Queue<Card> roundeck) {
        Queue<Card> playedCard = roundeck;
        int som = 0;
        while (playedCard.size() != 0) {
            Card card = playedCard.peek();
            som += card.getValue().getRank();
            playedCard.poll();
        }
        if (som == 21) {
            return true;
        }
        return false;
    }

    public void changeDeckAndPointsWithRightPlayer(Queue<Player> players) {
        int size = players.size();

        Player previousPlayer = players.poll();
        for (int i = 1; i < size; i++) {
            Player currentPlayer = players.poll();
            Card[] tempCards = previousPlayer.getCards();
            int tempPoints = previousPlayer.getPoints();
            currentPlayer.setCards(tempCards);
            currentPlayer.setPoints(tempPoints);
            players.offer(previousPlayer);
            previousPlayer = currentPlayer;
        }

    }

}