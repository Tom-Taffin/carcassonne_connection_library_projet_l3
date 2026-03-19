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

    /**
     * Called when the connection to the server is established.
     * @param serverHandshake the handshake data sent by the server
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("CarcassonneWebSocket opened");
    }

    /**
     * Called when a message is received from the server, forwarded to the update listener.
     * @param message the message received from the server
     */
    @Override
    public void onMessage(String message) {
        this.updateListener.update(message);
    }

    /**
     * Called when the connection to the server is closed.
     * @param i the close code
     * @param s the close reason
     * @param b whether the closing was initiated by the remote host
     */
    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println(s);
    }

    /**
     * Called when an error occurs on the connection.
     * @param e the exception that was raised
     */
    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
