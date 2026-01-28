package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;

import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.router.GameListener;
    
public abstract class AbstractView<T extends AbstractClient> {

    protected T client;
    protected GameListener dispatcher;

    public AbstractView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.connect(ipAddress, port, id);
    }

    @SuppressWarnings("unchecked")
    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.dispatcher = createRouter();
        this.client = (T) createClient(ipAddress, port, id);
    }

    protected abstract GameListener createRouter();

    protected abstract AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException;

    public abstract void updateOnPlace(String player, Tile tile, int x, int y, String meeple);

    public abstract void updateOnOffer(String player, Tile tile, List<String> players);

    public abstract void updateOnEnter(String player);

    public abstract void updateOnExpel(String player, String expelledPlayer);

}
