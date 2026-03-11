package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import l3s6.projet.star.interaction.command.*;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent a player client
 * A player can send every commands except admin ones
 */
public class PlayerClient extends SpectatorClient {

    /**
     * A player connect the same way as {@link AbstractClient}
     * It also as a unique id, specified in arguments, that is send to the server as the first message using {@link #enter}
     */
    public PlayerClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, updateListener);
        this.id = id;
        try { this.send("ENTERS"); } catch(InvalidArgumentNumberException e){}
        this.commands.add(new EnterCommand<>());
        this.commands.add(new LeaveCommand<>());
        this.commands.add(new PlaceCommand<>());
        this.commands.add(new AgreeCommand<>());
    }

    /**
     * Before closing the socket (see {@link AbstractClient#close}), sends a leave message using {@link #leave}
     */
    @Override
    public void close(){
        try {
            this.send("LEAVES");
            super.close();
        } catch(InvalidArgumentNumberException e){
            
        }
    }
    
}
