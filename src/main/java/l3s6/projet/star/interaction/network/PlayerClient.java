package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import l3s6.projet.star.interaction.command.*;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent a player client
 * A player can send every commands except admin ones
 */
public class PlayerClient extends SpectatorClient {

    String id;

    /**
     * A player connect the same way as {@link AbstractClient}
     * It also as a unique id, specified in arguments, that is send to the server as the first message using {@link #enter}
     */
    public PlayerClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, updateListener);
        this.id = id;
        this.enter();
    }

    /**
     * Before closing the socket (see {@link AbstractClient#close}), sends a leave message using {@link #leave}
     */
    @Override
    public void close(){
        this.leave();
        super.close();
    }

    /**
     * Sends an enter command
     */
    public void enter(){
        try {
            EnterCommand<?> enterCommand = new EnterCommand<>();
            this.cws.send(enterCommand.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a leave command
     */
    public void leave() {
        try {
            LeaveCommand<?> leaveCommand = new LeaveCommand<>();
            this.cws.send(leaveCommand.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a place command
     * #TODO change this method when PlaceCommand complete
     * @param tile
     * @param x
     * @param y
     * @param meeple
     */
    public void place(String tile, int x, int y, String meeple){
        try {
            PlaceCommand<?> placeCommand = new PlaceCommand<>();
            this.cws.send(placeCommand.build(id, tile, x, y, meeple));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends an agree command
     * @param exp_or_var All expansions and variations the player is supporting
     */
    public void agree(String... exp_or_var){
        try {
            AgreeCommand<?> agreeCommand = new AgreeCommand<>();
            this.cws.send(agreeCommand.build(id, exp_or_var));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

}
