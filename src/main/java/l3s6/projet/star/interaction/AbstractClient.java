package l3s6.projet.star.interaction;

import java.net.URISyntaxException;

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
