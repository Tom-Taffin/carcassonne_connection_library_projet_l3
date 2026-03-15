package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

public class PlaceCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    public PlaceCommand(){
        this.keyword = "PLACES";
    }

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
