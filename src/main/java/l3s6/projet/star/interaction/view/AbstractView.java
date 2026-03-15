package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;

import l3s6.projet.star.interaction.command.InvalidArgumentNumberException;
import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.role.RoleManager;
    
public abstract class AbstractView<T extends AbstractClient> {

    protected T client;
    protected GameListener dispatcher;
    protected RoleManager roleManager;
    protected String id;

    public AbstractView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.roleManager = new RoleManager();
        this.id = id;
        this.connect(ipAddress, port, id);
    }

    @SuppressWarnings("unchecked")
    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        this.dispatcher = createRouter();
        this.client = (T) createClient(ipAddress, port, id);
    }

    protected abstract GameListener createRouter();

    protected abstract AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException;

    public void send(String commandName, Object... params) throws InvalidArgumentNumberException{
        this.client.send(commandName, params);
    }
    
    public void send(String commandName, List<String> params) throws InvalidArgumentNumberException{
        this.client.send(commandName, params.toArray());
    }

}
