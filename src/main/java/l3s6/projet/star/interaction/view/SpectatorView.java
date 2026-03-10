package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;

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
        return new SpectatorRouter<>(this);
    }

    protected AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException {
        return new SpectatorClient(ipAddress, port, this.dispatcher);
    }
    
    public abstract void updateOnPlace(String id, String player, String tile, int x, int y);

    public abstract void updateOnPlaceWithMeeple(String id, String player, String tile, int x, int y, String meeple_type, String meeple_position);

    public abstract void updateOnBlame(String id, int amount);

    public abstract void updateOnBlameWithReason(String id, String player, String reason);

    public abstract void updateOnCollect(String id, String player, String meeple_type);

    public abstract void updateOnCollectWithAmount(String id, String player, String meeple_type, int amount);

    public abstract void updateOnOffer(String id, String player, String tile);

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
