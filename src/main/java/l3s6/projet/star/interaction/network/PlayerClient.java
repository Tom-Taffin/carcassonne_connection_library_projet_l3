package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;

import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.interaction.router.GameListener;

public class PlayerClient extends SpectatorClient {

    String id;

    public PlayerClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, updateListener);
        this.id = id;
        this.enter();
    }

    @Override
    public void close(){
        this.leave();
        super.close();
    }

    public void enter(){
        this.cws.send(id + " ENTERS");
    }

    public void leave(){
        this.cws.send(id + " LEAVES");
    }

    public void place(Tile tile, int x, int y, String meeple){
        this.cws.send(id + " PLACES " + tile + " " + x + ":" + y + " " + meeple);
    }

}
