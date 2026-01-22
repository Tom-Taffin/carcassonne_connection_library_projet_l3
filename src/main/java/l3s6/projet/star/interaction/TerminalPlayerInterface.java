package l3s6.projet.star.interaction;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import l3s6.projet.star.game.tile.Tile;

public class TerminalPlayerInterface implements CarcassonnePlayerInterface, Runnable {

    protected String ipAddress;
    protected int port;
    protected String id;


    public TerminalPlayerInterface(String ipAddress, int port, String id) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.id = id;
    }

    public void updateOnPlace(String player, Tile tile, int x, int y, String meeple){
        System.out.println("Player " + player + " place the tile " + tile + " at coordinates " + x + ":" + y + " with the meeple " + meeple);
    }

    public void updateOnOffer(String player, Tile tile, List<String> players){
        System.out.println("Players " + players + " recieve the tile " + tile);
    }

    @Override
    public void run() {
        CarcassonneClient client;
        CarcassonnePlayerDispatcher dispatcher = new CarcassonnePlayerDispatcher(this);
        try {
            client = new CarcassonneClient(this.ipAddress, this.port, this.id, (CarcassonneUpdateListener) dispatcher);
            Scanner s = new Scanner(System.in);
            s.nextLine();
            client.leave();
            s.close();
        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
