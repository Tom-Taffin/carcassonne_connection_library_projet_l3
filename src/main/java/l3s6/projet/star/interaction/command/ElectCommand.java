package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing an election action in the game's interaction system.
 * This command is identified by the keyword "ELECTS" and follows the format: id ELECTS role id+,
 * where role is the elected role and id+ represents one or more identifiers of the elected entities.
 * Upon reception, it triggers a view update via the updateOnElect method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class ElectCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs an ElectCommand and sets its keyword to "ELECTS".
     */
    public ElectCommand(){
        this.keyword = "ELECTS";
    }

    /**
     * Builds the string representation of the ELECTS command to be sent over the network.
     * @param id the identifier of the entity issuing the election
     * @param params the role followed by one or more entity identifiers being elected, must be non-null and contain at least two elements
     * @return the formatted ELECTS command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or contains fewer than two elements
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* Format : id ELECTS role id+ */
        if (params == null || params.length < 2) {
            throw new InvalidArgumentNumberException(
                    "Invalid number of arguments given (must be 2 or more for ELECTS command)"
            );
        }

        StringBuilder ids = new StringBuilder();
        for (int i = 1; i < params.length; i++) {
            ids.append(params[i]);
            if (i < params.length - 1) {
                ids.append(" ");
            }
        }

        return id + " " + this.keyword + " " + params[0] + " " + ids;
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, where the first token is the role and the remaining tokens are the elected entity identifiers
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if parts contains fewer than two elements
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 or more for " + this.keyword + " command)");
        }
        view.updateOnElect(id, parts.get(0), parts.subList(1, parts.size()));
    }
}