package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;

import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.network.SpectatorClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.router.SpectatorRouter;
    
public abstract class SpectatorView<T extends SpectatorClient> extends AbstractView<T> {

    public SpectatorView(String ipAddress, int port) throws URISyntaxException, InterruptedException {
        super(ipAddress, port, null);
    }

    public SpectatorView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super(ipAddress, port, id);
    }

    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super.connect(ipAddress, port, id);
    }

    protected GameListener createRouter(){
        return new SpectatorRouter(this);
    }

    protected AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException {
        return new SpectatorClient(ipAddress, port, this.dispatcher);
    }

    public abstract void updateOnPlace(String player, Tile tile, int x, int y, String meeple);

    public abstract void updateOnOffer(String player, Tile tile, List<String> players);

}
