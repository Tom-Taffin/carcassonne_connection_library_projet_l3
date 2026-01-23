package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;

import l3s6.projet.star.interaction.router.GameListener;

public abstract class AbstractClient {

    GameSocket cws;

    public AbstractClient(String ip, int port, GameListener updateListener) throws URISyntaxException, InterruptedException{
        this.cws = new GameSocket(ip, port, updateListener);
        this.cws.connectBlocking();
    }

    public void close(){
        this.cws.close();
    }

}
