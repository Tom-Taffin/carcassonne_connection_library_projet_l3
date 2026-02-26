package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent a client
 */
public abstract class AbstractClient {

    GameSocket cws;

    /**
     * At creation, the client creates a socket {@link GameSocket} and connects to the specified in arguments
     * When a message is received, it updates a {@link GameListener}, also specified in arguments
     */
    public AbstractClient(String ip, int port, GameListener updateListener) throws URISyntaxException, InterruptedException{
        this.cws = new GameSocket(ip, port, updateListener);
        this.cws.connectBlocking();
    }

    /**
     * Closes the socket
     */
    public void close(){
        this.cws.close();
    }

}
