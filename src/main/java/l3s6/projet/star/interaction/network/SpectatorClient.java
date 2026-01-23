package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;

import l3s6.projet.star.interaction.router.GameListener;

public class SpectatorClient extends AbstractClient {

    public SpectatorClient(String ip, int port, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, updateListener);
    }

}
