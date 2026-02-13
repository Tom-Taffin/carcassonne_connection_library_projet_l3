package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class PlaceCommand<V extends AbstractView> extends AbstractCommand<V> {

    public PlaceCommand(){
        this.keyword = "PLACES";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id PLACES tile x y meeple */
        if (params == null || params.length != 4){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 4 for PLACES command)");
        }
        return String.format("%s %s %s %d:%d %s", id, this.keyword, params[0], params[1], params[2], params[3]);
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        String tile = parts.get(2);
        String[] xy = parts.get(3).split(":");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        String meeple = parts.get(4);
        view.updateOnPlace(parts.get(0), tile, x, y, meeple);
    }
}
