package carcassonneWS;

import java.net.URISyntaxException;

public class CarcassonneClient {

    CarcassonneWebSocket cws;
    String id = null;

    public CarcassonneClient(String ip, int port, String id) throws URISyntaxException, InterruptedException{
        this.cws = new CarcassonneWebSocket(ip, port);
        this.cws.connectBlocking();
        this.id = id;
        this.cws.send(id + " ENTERS");
    }

    public void leave(){
        this.cws.send(id + " LEAVES");
        this.cws.close();
    }

}
