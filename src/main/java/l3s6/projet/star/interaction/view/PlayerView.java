package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;

import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.network.PlayerClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.router.PlayerRouter;
    
public abstract class PlayerView<T extends PlayerClient> extends SpectatorView<T> {

    public PlayerView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super(ipAddress, port, id);
    }

    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super.connect(ipAddress, port, id);
    }

    protected GameListener createRouter(){
        return new PlayerRouter<>(this);
    }

    protected AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException {
        return new PlayerClient(ipAddress, port, id, this.dispatcher);
    }

}
