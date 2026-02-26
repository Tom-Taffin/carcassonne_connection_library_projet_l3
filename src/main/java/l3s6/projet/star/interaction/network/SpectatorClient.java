package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent a spectator client
 * A spectator cannot send any message to the server its connected to
 */
public class SpectatorClient extends AbstractClient {

    public SpectatorClient(String ip, int port, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, updateListener);
    }

}
