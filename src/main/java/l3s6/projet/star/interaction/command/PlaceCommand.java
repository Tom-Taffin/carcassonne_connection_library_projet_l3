package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

/**
 * Command representing a place action in the game's interaction system.
 * This command is identified by the keyword "PLACES" and follows two formats:
 * id PLACES id' orientation x y, where id' is the placed tile, orientation is its rotation,
 * and x and y are its coordinates on the board,
 * or id PLACES id' orientation x y meeple_type meeple_position, which additionally specifies
 * the type and position of a meeple placed on the tile.
 * Upon reception, it triggers a view update via updateOnPlace or updateOnPlaceWithMeeple method.
 * @param <V> the type of spectator view this command operates on, must extend SpectatorView
 */
public class PlaceCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    /**
     * Constructs a PlaceCommand and sets its keyword to "PLACES".
     */
    public PlaceCommand(){
        this.keyword = "PLACES";
    }

    /**
     * Builds the string representation of the PLACES command to be sent over the network.
     * @param id the identifier of the entity issuing the place
     * @param params either a tile identifier, an orientation and two coordinates x and y, or those same four elements followed by a meeple type and a meeple position, must be non-null and contain exactly four or six elements
     * @return the formatted PLACES command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or does not contain exactly four or six elements
     */
    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id PLACES id' orientation x y */
        /* format: id PLACES id' orientation x y meeple_type meeple_position */
        if (params == null || (params.length != 4 && params.length != 6)){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 4 or 6 for " + this.keyword + " command)");
        }
        if (params.length == 4){
            return String.format("%s %s %s %s %s %s", id, this.keyword, params[0], params[1], params[2].toString(), params[3].toString());
        }
        else {
            return String.format("%s %s %s %s %s %s %s %s", id, this.keyword, params[0], params[1], params[2].toString(), params[3].toString(), params[4], params[5]);
        }
    }

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, containing the tile identifier, orientation, coordinates x and y, and optionally a meeple type and meeple position
     * @param view the spectator view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if parts does not contain exactly four or six elements
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 4 && parts.size() != 6){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 4 or 6 for " + this.keyword + " command)");
        }
        String other_id = parts.get(0);
        String orientation = parts.get(1);
        int x = Integer.parseInt(parts.get(2));
        int y = Integer.parseInt(parts.get(3));
        if (parts.size() == 4){
            view.updateOnPlace(id, other_id, orientation, x, y);
        }
        if (parts.size() == 6) {
            String meeple_type = parts.get(4);
            String meeple_position = parts.get(5);
            view.updateOnPlaceWithMeeple(id, other_id, orientation, x, y, meeple_type, meeple_position);
        }
    }
}
