package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.interaction.network.PlayerClient;

public class TerminalPlayerView extends PlayerView<PlayerClient> {

    public TerminalPlayerView(String ipAddress, int port, String id) throws InterruptedException, URISyntaxException {
        super(ipAddress, p
                ort, id);
    }

    public void updateOnPlace(String player, Tile tile, int x, int y, String meeple){
        System.out.println("Player " + player + " place the tile " + tile + " at coordinates " + x + ":" + y + " with the meeple " + meeple);
    }

    public void updateOnOffer(String player, Tile tile, List<String> players){
        System.out.println("Players " + players + " receive the tile " + tile);
    }

    public void updateOnEnter(String player){
        System.out.println("Player " + player + " enters the game");
    }

    public void updateOnLeave(String player){
        System.out.println("Player " + player + " leaves the game");
    }

    public void updateOnClose(String player){
        System.out.println("Player " + player + " closed the game");
    }

    public void updateOnExpel(String player, String expelledPlayer){
        System.out.println("Player " + player + " expels " + expelledPlayer);
    }

    public void updateOnGrant(String player, String grantedPlayer, List<String> keywords){
        System.out.println("Player " + player + " grants " + grantedPlayer);
    }

    public static void main(String[] args) {
        if (args.length != 3){
            System.err.println("Invalid number of arguments.\n Usage : arg 1 = ip address, arg 2 = port, arg 3 = id.");
        }
        try {
            TerminalPlayerView terminalInterface = new TerminalPlayerView(args[0] , Integer.parseInt(args[1]), args[2]);
            Scanner s = new Scanner(System.in);
            s.nextLine();
            terminalInterface.client.place(null, 1, 2, "blue");
            s.nextLine();
            terminalInterface.client.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
