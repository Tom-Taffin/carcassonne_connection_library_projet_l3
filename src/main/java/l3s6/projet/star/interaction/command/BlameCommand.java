package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing a blame action in the game's interaction system.
 * This command is identified by the keyword "BLAMES" and follows two formats:
 * id BLAMES id' reason, where id' is the blamed entity and reason is the cause,
 * or id BLAMES amount, where amount is a numeric blame value.
 * Upon reception, it triggers a view update via updateOnBlame or updateOnBlameWithReason method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class BlameCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs a BlameCommand and sets its keyword to "BLAMES".
     */
    public BlameCommand(){
        this.keyword = "BLAMES";
    }

    /**
     * Builds the string representation of the BLAMES command to be sent over the network.
     * @param id the identifier of the entity issuing the blame
     * @param params either a single numeric amount, or an entity identifier followed by a reason string, must be non-null and contain exactly one or two elements
     * @return the formatted BLAMES command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null, contains no elements, more than two elements, or if two params are provided but the first is an integer
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format : id BLAMES id' reason */
        /* format : id BLAMES amount */

        if (params == null || (params.length != 1 && params.length != 2)){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 1 or 2 for " + this.keyword + " command)");
        }
        if (params.length == 2){
            if (params[0] instanceof Integer) {
                throw new InvalidArgumentNumberException("Invalid arguments: 2 params requires id' and reason, not an integer for " + this.keyword + " command");
            }
            return String.format("%s %s %s %s", id, this.keyword, params[0], params[1]);
        }
        else {
            return String.format("%s %s %s", id, this.keyword, params[0]);
        }
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if the number of tokens in parts does not match what the command expects
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 1 && parts.size() != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 1 or 2 for " + this.keyword + " command)");
        }
        if (parts.size() == 1){
            int amount = Integer.parseInt(parts.get(0));
            view.updateOnBlame(id, amount);
        }
        if (parts.size() == 2) {
            String player = parts.get(0);
            String reason = parts.get(1);
            view.updateOnBlameWithReason(id, player, reason);
        }
    }
}