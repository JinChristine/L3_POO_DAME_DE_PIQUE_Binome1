import java.util.Queue;
import java.util.Set;
import java.util.Deque;
import java.util.LinkedList;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.engine.QueenOfSpadesGame;
import fr.pantheonsorbonne.miage.exception.NoMoreCardException;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class QueenOfSpadesGameNetworkEngine extends QueenOfSpadesGame{
    private static final int PLAYER_COUNT = 5;

    private final HostFacade hostFacade;
    private final Set<String> players;
    private final Game war;
    private final Queue<Player> playersGame;

    public QueenOfSpadesGameNetworkEngine(Deque<Player> playersGame, HostFacade hostFacade, Set<String> players, fr.pantheonsorbonne.miage.model.Game war) {
        this.hostFacade = hostFacade;
        this.players = players;
        this.war = war;
    }

    public static void main(String[] args) {
        //create the host facade
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        //set the name of the player
        hostFacade.createNewPlayer("Host");

        //create a new game of war
        fr.pantheonsorbonne.miage.model.Game war = hostFacade.createNewGame("WAR");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        QueenOfSpadesGameNetworkEngine host = new QueenOfSpadesGameNetworkEngine(hostFacade, war.getPlayers(), war);
        host.play();
        System.exit(0);
    }
    @Override
    protected Card getWinnerCard(Queue<Card> roundDeck){
        
    }

    @Override
    protected int givePointsToWinnerTurn(Queue<Card> roundDeck){

    }

    @Override
    protected Player getWinnerTurn(Queue<Player> playersOrder, Queue<Card> roundDeck){

    }

    @Override
    protected boolean firstPlayerHas100(Queue<Player> players){

    }

    @Override
    protected Player getPlayerWithLowestPoints(){

    }

    @Override
    protected Player searchPlayerWithTwoOfClub(){

    }

    @Override
    protected  Queue<Player> orderPlayer(Player first){

    }

}
