package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent a spectator client
 * A spectator cannot send any message to the server its connected to
 */
public class SpectatorClient extends AbstractClient {

    /**
     * A spectator connect the same way as {@link AbstractClient}
     * @param ip the IP address of the server
     * @param port the port of the server
     * @param updateListener the {@link l3s6.projet.star.interaction.router.GameListener} that will be updated when a message is received
     * @throws URISyntaxException if the server URI has incorrect syntax
     * @throws InterruptedException if the connection is interrupted
     */
    public SpectatorClient(String ip, int port, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, updateListener);
    }

}
