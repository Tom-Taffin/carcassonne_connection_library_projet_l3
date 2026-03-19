package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing a score action in the game's interaction system.
 * This command is identified by the keyword "SCORES" and follows the format: id SCORES reason amount,
 * where reason is the cause of the score and amount is the number of points scored.
 * Upon reception, it triggers a view update via the updateOnScore method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class ScoreCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs a ScoreCommand and sets its keyword to "SCORES".
     */
    public ScoreCommand(){
        this.keyword = "SCORES";
    }

    /**
     * Builds the string representation of the SCORES command to be sent over the network.
     * @param id the identifier of the entity issuing the score
     * @param params the reason for the score followed by the number of points scored, must be non-null and contain exactly two elements
     * @return the formatted SCORES command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or does not contain exactly two elements
     */
     public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params == null || params.length != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 for SCORES command)");
        }
        return id + " " + this.keyword + " " + params[0] + " " + params[1];
     }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, where the first token is the reason for the score and the second token is the number of points scored as an integer
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if parts does not contain exactly two elements
     */
     public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
         if (parts.size() != 2){
             throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 for " + this.keyword + " command)");
         }
         view.updateOnScore(id, parts.get(0), Integer.parseInt(parts.get(1)));
     }
}
