package carcassonneWS;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class CarcassonneWebSocket extends WebSocketClient {

    CarcassonneGUI gui; 

    public CarcassonneWebSocket(String ip, int port, CarcassonneGUI gui) throws URISyntaxException {
        super(new URI("ws://" + ip + ":" + port));
        this.gui = gui;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("CarcassonneWebSocket opened");
    }

    @Override
    public void onMessage(String s) {
        System.out.println(s);
        this.gui.update();
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println(s);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
