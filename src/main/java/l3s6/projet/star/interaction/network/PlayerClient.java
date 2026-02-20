package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;

import l3s6.projet.star.interaction.command.*;
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
            EnterCommand<?> command = new EnterCommand<>();
            this.cws.send(command.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void leave() {
        try {
            LeaveCommand<?> command = new LeaveCommand<>();
            this.cws.send(command.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void place(String tile, int x, int y, String meeple){
        try {
            PlaceCommand<?> command = new PlaceCommand<>();
            this.cws.send(command.build(id, tile, x, y, meeple));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    public void agree(String... exp_or_var){
        try {
            AgreeCommand<?> agreeCommand = new AgreeCommand<>();
            this.cws.send(agreeCommand.build(id, exp_or_var));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

}
