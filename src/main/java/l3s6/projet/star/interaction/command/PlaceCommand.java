package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

public class PlaceCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    public PlaceCommand(){
        this.keyword = "PLACES";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id PLACES id' tile x y */
        /* format: id PLACES id' tile x y meeple_type meeple_position */
        if (params == null || (params.length != 4 && params.length != 6)){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 4 or 6 for " + this.keyword + " command)");
        }
        if (params.length == 4){
            return String.format("%s %s %s %s %d:%d", id, this.keyword, params[0], params[1], params[2], params[3]);
        }
        else {
            return String.format("%s %s %s %s %d:%d %s %s", id, this.keyword, params[0], params[1], params[2], params[3], params[4], params[5]);
        }
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 4 && parts.size() != 6){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 4 or 6 for " + this.keyword + " command)");
        }
        String tile = parts.get(0);
        String[] xy = parts.get(1).split(":");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        String meeple = parts.get(2);
        view.updateOnPlace(id, tile, x, y, meeple);
    }
}
