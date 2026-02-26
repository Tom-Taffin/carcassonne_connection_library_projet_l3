package l3s6.projet.star.interaction.network;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import l3s6.projet.star.interaction.router.GameListener;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * A class to represent a socket for the game.
 * It uses org.java_websocket to connect to a server.
 * When a message is received, it updates a {@link l3s6.projet.star.interaction.router.GameListener}.
 */
public class GameSocket extends WebSocketClient {

    GameListener updateListener;
    String ipAddress;
    int port;

    /**
     * Creates a socket.
     * @param ipAddress ip of the server
     * @param port port of the server
     * @param updateListener the {@link l3s6.projet.star.interaction.router.GameListener} that will be updated when a message is received
     */
    public GameSocket(String ipAddress, int port, GameListener updateListener) throws URISyntaxException {
        super(new URI("ws://" + ipAddress + ":" + port));
        this.ipAddress = ipAddress;
        this.port = port;
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
