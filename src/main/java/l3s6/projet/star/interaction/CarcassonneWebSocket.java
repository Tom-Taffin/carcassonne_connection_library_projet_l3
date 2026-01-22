package l3s6.projet.star.interaction;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class CarcassonneWebSocket extends WebSocketClient {

    CarcassonneUpdateListener updateListener; 

    public CarcassonneWebSocket(String ip, int port, CarcassonneUpdateListener updateListener) throws URISyntaxException {
        super(new URI("ws://" + ip + ":" + port));
        this.updateListener = updateListener;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("CarcassonneWebSocket opened");
    }

    @Override
    public void onMessage(String message) {
        this.updateListener.update(message);
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
