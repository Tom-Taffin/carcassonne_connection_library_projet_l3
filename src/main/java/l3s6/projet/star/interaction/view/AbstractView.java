package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;

import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.role.RoleManager;
    
public abstract class AbstractView<T extends AbstractClient> {

    protected T client;
    protected GameListener dispatcher;
    protected RoleManager roleManager;

    public AbstractView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.connect(ipAddress, port, id);
        this.roleManager = new RoleManager();
    }

    @SuppressWarnings("unchecked")
    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.dispatcher = createRouter();
        this.client = (T) createClient(ipAddress, port, id);
    }

    protected abstract GameListener createRouter();

    protected abstract AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException;

}
