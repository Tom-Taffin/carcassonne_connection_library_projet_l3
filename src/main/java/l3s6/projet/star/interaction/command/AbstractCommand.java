package l3s6.projet.star.interaction.command;

import java.util.List;

import l3s6.projet.star.interaction.network.GameSocket;
import l3s6.projet.star.interaction.view.AbstractView;

/**
 * Abstract base class representing a command in the game's interaction system.
 * A command encapsulates a specific action that can be built into a string,
 * sent over the network, and executed upon reception. Subclasses must define
 * the keyword identifying the command, how to build its string representation,
 * and how to execute it on the client side.
 * @param <V> the type of view this command operates on, must extend AbstractView
 */
public abstract class AbstractCommand<V extends AbstractView<?>> {

    protected String keyword;

    /**
     * Returns the keyword associated with this command.
     * @return the command keyword string
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Builds the string representation of this command to be sent over the network.
     * @param id the identifier of the entity issuing the command
     * @param params parameters required to build the command string
     * @return the formatted command string ready to be transmitted
     * @throws InvalidArgumentNumberException if the number of provided parameters does not match what the command expects
     */
    public abstract String build(String id, Object... params) throws InvalidArgumentNumberException;

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message
     * @param view the view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if the number of tokens in parts does not match what the command expects
     */
    public abstract void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException;

    /**
     * Builds and sends this command through the given GameSocket.
     * @param gs the game socket used to transmit the command
     * @param id the identifier of the entity issuing the command
     * @param param optional parameters forwarded to build method
     * @throws InvalidArgumentNumberException if the number of provided parameters does not match what the command expects
     */
    public void send(GameSocket gs, String id, Object... param) throws InvalidArgumentNumberException {
        gs.send(this.build(id, param));
    }
}
