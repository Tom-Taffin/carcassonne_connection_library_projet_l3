package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import java.util.List;

import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.interaction.command.ExpelCommand;
import l3s6.projet.star.interaction.command.GrantCommand;
import l3s6.projet.star.interaction.router.GameListener;

public class AdminClient extends PlayerClient {

    public AdminClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, id, updateListener);

    }

    public void expel(String expelledPlayer){
        ExpelCommand expelCommand = new ExpelCommand();
        this.cws.send(expelCommand.build(id, expelledPlayer));
    }

    public void grant(String grantedPlayer, String... keywords){
        GrantCommand grantCommand = new GrantCommand();
        this.cws.send(grantCommand.build(id, keywords));
    }

}
