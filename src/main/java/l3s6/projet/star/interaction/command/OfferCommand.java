package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing an offer action in the game's interaction system.
 * This command is identified by the keyword "OFFERS" and follows the format: id OFFERS id' tile,
 * where id' is the identifier of the entity receiving the offer and tile is the offered tile.
 * Upon reception, it triggers a view update via the updateOnOffer method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class OfferCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs an OfferCommand and sets its keyword to "OFFERS".
     */
    public OfferCommand(){
        this.keyword = "OFFERS";
    }

    /**
     * Builds the string representation of the OFFERS command to be sent over the network.
     * @param id the identifier of the entity issuing the offer
     * @param params the identifier of the entity receiving the offer followed by the offered tile, must be non-null and contain exactly two elements
     * @return the formatted OFFERS command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or does not contain exactly two elements
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id OFFERS id' tile */
        if (params == null || params.length != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 for OFFERS command)");
        }
        return id + " " + this.keyword + " " + params[0] + " " + params[1];
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, where the first token is the identifier of the entity receiving the offer and the second token is the offered tile
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if parts does not contain exactly two elements
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 for " + this.keyword + " command)");
        }
        view.updateOnOffer(id, parts.get(0), parts.get(1));
    }
}
