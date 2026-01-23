package l3s6.projet.star.interaction;

import java.net.URISyntaxException;
import java.util.List;

import l3s6.projet.star.game.tile.Tile;
    
public abstract class PlayerView {

    AbstractClient client;
    GameListener dispatcher;

    public PlayerView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.connect(ipAddress, port, id);
    }

    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.dispatcher = new PlayerRouter(this);
        this.client = new AbstractClient(ipAddress, port, id, dispatcher);
    }

    public abstract void updateOnPlace(String player, Tile tile, int x, int y, String meeple);

    public abstract void updateOnOffer(String player, Tile tile, List<String> players);

}
