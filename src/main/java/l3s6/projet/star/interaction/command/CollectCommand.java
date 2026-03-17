package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

public class CollectCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    public CollectCommand(){
        this.keyword = "COLLECTS";
    }

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
