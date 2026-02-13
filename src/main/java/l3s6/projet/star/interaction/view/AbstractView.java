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

    public abstract void updateOnPlace(String id, String tile, int x, int y, String meeple);

    public abstract void updateOnOffer(String id, String tile, List<String> ids);

    public abstract void updateOnEnter(String id);

    public abstract void updateOnLeave(String id);

    public abstract void updateOnClose(String id);

    public abstract void updateOnExpel(String id, String expelledPlayer);

    public abstract void updateOnElect(String id, String role, List<String> ids);

    public abstract void updateOnAgree(String id, List<String> expOrVar);

    public abstract void updateOnScore(String id, String otherId, int points);

    public abstract void updateOnStart(String id);

    public abstract void updateOnEnd(String id, List<String> ids);

}
