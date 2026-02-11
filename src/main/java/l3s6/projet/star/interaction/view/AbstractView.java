package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;

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

    public abstract void updateOnPlace(String player, String tile, int x, int y, String meeple);

    public abstract void updateOnOffer(String player, String tile, List<String> players);

    public abstract void updateOnEnter(String player);

    public abstract void updateOnLeave(String player);

    public abstract void updateOnClose(String player);

    public abstract void updateOnExpel(String player, String expelledPlayer);

    public abstract void updateOnGrant(String player, String grantedPlayer, List<String> keywords);

    public abstract void updateOnElect(String player, String role, List<String> ids);

    public abstract void updateOnAgree(String player, List<String> exp_or_var);

    public abstract void updateOnScore(String player, String other_id, int points);

    public abstract void updateOnStart(String player);

    public abstract void updateOnEnd(String player, List<String> ids);

}
