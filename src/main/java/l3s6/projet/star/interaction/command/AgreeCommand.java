package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing an agreement action in the game's interaction system.
 * This command is identified by the keyword "AGREES" and follows the format: id AGREES exp_or_var+
 * where exp_or_var+ represents one or more expressions or variables
 * that the entity agrees with. Upon reception, it triggers a view update
 * via updateOnAgree method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class AgreeCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs an AgreeCommand and sets its keyword to "AGREES".
     */
    public AgreeCommand() {
        this.keyword = "AGREES";
    }

    /**
     * Builds the string representation of the AGREES command to be sent over the network.
     * @param id the identifier of the entity issuing the agreement
     * @param params the expressions or variables being agreed upon must be non-null and contain at least one element
     * @return the formatted AGREES command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or contains no elements
     */
    @Override
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* Format : id AGREES exp_or_var+  */
        if (params == null || params.length < 1) {
            throw new InvalidArgumentNumberException(
                    "Invalid number of arguments given (must be 1 or more for AGREES command)"
            );
        }

        StringBuilder exp_or_var = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            exp_or_var.append(params[i]);
            if (i < params.length - 1) {
                exp_or_var.append(" ");
            }
        }

        return id + " " + this.keyword + " " + exp_or_var;
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if the number of tokens in parts does not match what the command expects
     */
    @Override
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        view.updateOnAgree(id, parts);
    }
}