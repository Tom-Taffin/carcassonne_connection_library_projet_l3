package l3s6.projet.star.interaction.view;

import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.network.AdminClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.router.AdminRouter;

import java.net.URISyntaxException;
import java.util.List;

public abstract class AdminView extends PlayerView<AdminClient> {

    public AdminView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super(ipAddress, port, id);
    }

    protected GameListener createRouter(){
        return new AdminRouter(this);
    }

    protected AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException {
        return new AdminClient(ipAddress, port, id, this.dispatcher);
    }

    public abstract void updateOnGrant(String id, String grantedPlayer, List<String> keywords);

}
