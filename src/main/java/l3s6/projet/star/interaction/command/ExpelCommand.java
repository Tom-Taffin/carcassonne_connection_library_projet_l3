package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing an expel action in the game's interaction system.
 * This command is identified by the keyword "EXPELS" and follows the format: id EXPELS id',
 * where id' is the identifier of the entity being expelled.
 * Upon reception, it triggers a view update via the updateOnExpel method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class ExpelCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs an ExpelCommand and sets its keyword to "EXPELS".
     */
    public ExpelCommand(){
        this.keyword = "EXPELS";
    }

    /**
     * Builds the string representation of the EXPELS command to be sent over the network.
     * @param id the identifier of the entity issuing the expulsion
     * @param params the identifier of the entity being expelled, must be non-null and contain exactly one element
     * @return the formatted EXPELS command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or does not contain exactly one element
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params == null || params.length != 1){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 1 for EXPELS command)");
        }
        return id + " " + this.keyword + " " + params[0];
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, where the single token is the identifier of the expelled entity
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if parts does not contain exactly one element
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 1){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 1 for " + this.keyword + " command)");
        }
        view.updateOnExpel(id, parts.get(0));
    }
}