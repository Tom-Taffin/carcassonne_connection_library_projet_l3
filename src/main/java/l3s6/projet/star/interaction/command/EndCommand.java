package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing an end action in the game's interaction system.
 * This command is identified by the keyword "ENDS" and follows the format: id ENDS id+,
 * where id+ represents one or more identifiers of the entities concerned by the end.
 * Upon reception, it triggers a view update via the updateOnEnd method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class EndCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs an EndCommand and sets its keyword to "ENDS".
     */
    public EndCommand(){
        this.keyword = "ENDS";
    }

    /**
     * Builds the string representation of the ENDS command to be sent over the network.
     * @param id the identifier of the entity issuing the end
     * @param params one or more entity identifiers concerned by the end, must be non-null and contain at least one element
     * @return the formatted ENDS command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or contains no elements
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* Format : id ENDS id+  */
        if (params == null || params.length < 1) {
            throw new InvalidArgumentNumberException(
                    "Invalid number of arguments given (must be 1 or more for ENDS command)"
            );
        }

        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            ids.append(params[i]);
            if (i < params.length - 1) {
                ids.append(" ");
            }
        }

        return id + " " + this.keyword + " " + ids;
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, representing the identifiers of the entities concerned by the end
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if the number of tokens in parts does not match what the command expects
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        view.updateOnEnd(id, parts);
    }
}