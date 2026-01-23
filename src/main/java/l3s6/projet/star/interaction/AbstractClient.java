package l3s6.projet.star.interaction;

import java.net.URISyntaxException;

public class AbstractClient {

    GameSocket cws;
    String id = null;

    public AbstractClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        this.cws = new GameSocket(ip, port, updateListener);
        this.cws.connectBlocking();
        this.id = id;
        this.cws.send(id + " ENTERS");
    }

    public void leave(){
        this.cws.send(id + " LEAVES");
        this.cws.close();
    }

}
