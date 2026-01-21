package carcassonneWS;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class CarcassonneClient {

    CarcassonneWebSocket cws;
    String id = null;
    CarcassonneGUI gui;

    public CarcassonneClient(String ip, int port, String id, CarcassonneGUI gui) throws URISyntaxException, InterruptedException{
        this.cws = new CarcassonneWebSocket(ip, port, gui);
        this.cws.connectBlocking();
        this.id = id;
        this.cws.send(id + " ENTERS");
    }

    public void leave(){
        this.cws.send(id + " LEAVES");
        this.cws.close();
    }

}
