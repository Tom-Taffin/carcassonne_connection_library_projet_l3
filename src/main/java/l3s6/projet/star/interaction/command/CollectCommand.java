package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing a collect action in the game's interaction system.
 * This command is identified by the keyword "COLLECTS" and follows two formats:
 * id COLLECTS id' meeple_type amount, where amount is the number of meeples collected at start,
 * or id COLLECTS id' meeple_type x y, where x and y are the coordinates of the collection.
 * Upon reception, it triggers a view update via updateOnCollectAtStart or updateOnCollect method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class CollectCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs a CollectCommand and sets its keyword to "COLLECTS".
     */
    public CollectCommand(){
        this.keyword = "COLLECTS";
    }

    /**
     * Builds the string representation of the COLLECTS command to be sent over the network.
     * @param id the identifier of the entity issuing the collect
     * @param params either a target entity identifier, a meeple type and an amount, or a target entity identifier, a meeple type and two coordinates x and y, must be non-null and contain exactly three or four elements
     * @return the formatted COLLECTS command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or does not contain exactly three or four elements
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format : id COLLECTS id' meeple_type amount */
        /*   or   : id COLLECTS id' meeple_type x y */
        if (params == null || (params.length != 3 && params.length != 4)){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 3 or 4 for " + this.keyword + " command)");
        }
        if (params.length == 3){
            return String.format("%s %s %s %s %s", id, this.keyword, params[0], params[1], params[2].toString());
        }
        else {
            return String.format("%s %s %s %s %s %s", id, this.keyword, params[0], params[1], params[2].toString(), params[3].toString());
        }
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if the number of tokens in parts does not match what the command expects, or if the numeric arguments cannot be parsed as integers
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 3 && parts.size() != 4) {
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 or 3 for " + this.keyword + " command)");
        }
        try {
            if (parts.size() == 3) {
                String player = parts.get(0);
                String meeple_type = parts.get(1);
                int amount = Integer.parseInt(parts.get(2));
                view.updateOnCollectAtStart(id, player, meeple_type, amount);
            } else if (parts.size() == 4) {
                String player = parts.get(0);
                String meeple_type = parts.get(1);
                int x = Integer.parseInt(parts.get(2));
                int y = Integer.parseInt(parts.get(3));
                view.updateOnCollect(id, player, meeple_type, x, y);
            }
        } catch (NumberFormatException e) {
            throw new InvalidArgumentNumberException("argument must be an integer.");
        }
    }
}