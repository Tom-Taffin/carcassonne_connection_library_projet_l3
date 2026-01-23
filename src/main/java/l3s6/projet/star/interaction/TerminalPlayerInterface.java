package l3s6.projet.star.interaction;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import l3s6.projet.star.game.tile.Tile;

public class TerminalPlayerInterface extends CarcassonnePlayerInterface {

    public TerminalPlayerInterface(String ipAddress, int port, String id) throws InterruptedException, URISyntaxException {
        super(ipAddress, port, id);
    }

    public void updateOnPlace(String player, Tile tile, int x, int y, String meeple){
        System.out.println("Player " + player + " place the tile " + tile + " at coordinates " + x + ":" + y + " with the meeple " + meeple);
    }

    public void updateOnOffer(String player, Tile tile, List<String> players){
        System.out.println("Players " + players + " recieve the tile " + tile);
    }

    public static void main(String[] args) {
        if (args.length != 3){
            System.err.println("Invalid number of arguments.\n Usage : arg 1 = ip address, arg 2 = port, arg 3 = id.");
        }
        try {
            TerminalPlayerInterface terminalInterface = new TerminalPlayerInterface(args[0] , Integer.parseInt(args[1]), args[2]);
            Scanner s = new Scanner(System.in);
            s.nextLine();
            terminalInterface.client.leave();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
