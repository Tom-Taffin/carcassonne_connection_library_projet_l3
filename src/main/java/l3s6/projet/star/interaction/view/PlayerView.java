package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;

import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.interaction.network.PlayerClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.router.PlayerRouter;
    
public abstract class PlayerView {

    PlayerClient client;
    GameListener dispatcher;

    public PlayerView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.connect(ipAddress, port, id);
    }

    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.dispatcher = new PlayerRouter(this);
        this.client = new PlayerClient(ipAddress, port, id, dispatcher);
    }

    public abstract void updateOnPlace(String player, Tile tile, int x, int y, String meeple);

    public abstract void updateOnOffer(String player, Tile tile, List<String> players);

}
