package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;

import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.interaction.command.EnterCommand;
import l3s6.projet.star.interaction.command.InvalidArgumentNumberException;
import l3s6.projet.star.interaction.command.LeaveCommand;
import l3s6.projet.star.interaction.command.PlaceCommand;
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
        try {
            EnterCommand command = new EnterCommand();
            this.cws.send(command.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void leave() {
        try {
            LeaveCommand command = new LeaveCommand();
            this.cws.send(command.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void place(Tile tile, int x, int y, String meeple){
        try {
            PlaceCommand command = new PlaceCommand();
            this.cws.send(command.build(id, tile, x, y, meeple));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

}
