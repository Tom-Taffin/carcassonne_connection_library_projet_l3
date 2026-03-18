package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing a leave action in the game's interaction system.
 * This command is identified by the keyword "LEAVES" and follows the format: id LEAVES.
 * Upon reception, it triggers a view update via the updateOnLeave method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class LeaveCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs a LeaveCommand and sets its keyword to "LEAVES".
     */
    public LeaveCommand(){
        this.keyword = "LEAVES";
    }

    /**
     * Builds the string representation of the LEAVES command to be sent over the network.
     * @param id the identifier of the entity issuing the leave
     * @param params must be null or empty, as the LEAVES command takes no parameters
     * @return the formatted LEAVES command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is non-null and contains at least one element
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params != null && params.length != 0){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 0 for " + this.keyword + " command)");
        }
        return id + " " + this.keyword;
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, must be empty
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if parts is not empty
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (!parts.isEmpty()){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 0 for " + this.keyword + " command)");
        }
        view.updateOnLeave(id);
    }
}
