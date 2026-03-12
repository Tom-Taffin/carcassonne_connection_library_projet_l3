package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import l3s6.projet.star.interaction.command.AbstractCommand;
import l3s6.projet.star.interaction.command.InvalidArgumentNumberException;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent a client
 */
public abstract class AbstractClient {

    protected GameSocket cws;
    protected String id;
    protected List<AbstractCommand<?>> commands;

    /**
     * At creation, the client creates a socket {@link GameSocket} and connects to the specified in arguments
     * When a message is received, it updates a {@link GameListener}, also specified in arguments
     */
    public AbstractClient(String ip, int port, GameListener updateListener) throws URISyntaxException, InterruptedException{
        this.cws = new GameSocket(ip, port, updateListener);
        this.cws.connectBlocking();
        this.commands = new ArrayList<>();
    }

    /**
     * Closes the socket
     */
    public void close(){
        this.cws.close();
    }

    public void send(String commandName, Object... params) throws InvalidArgumentNumberException{
        for (AbstractCommand<?> command : this.commands){
            if (command.getKeyword().equals(commandName)){
                command.send(cws, id, params);
                return;
            }
        }
        throw new InvalidArgumentNumberException("command not found");
    }

}
