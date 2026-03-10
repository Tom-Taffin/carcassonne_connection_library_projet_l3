package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

public class CollectCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    public CollectCommand(){
        this.keyword = "COLLECTS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format : id COLLECTS id' meeple_type (amount) */

        if (params == null || (params.length != 2 && params.length != 3)){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 or 3 for " + this.keyword + " command)");
        }
        if (params.length == 3){
            if (!(params[2] instanceof Integer)) {
                throw new InvalidArgumentNumberException("Invalid arguments: amount must be an integer for " + this.keyword + " command");
            }
            return String.format("%s %s %s %s %s", id, this.keyword, params[0], params[1], params[2]);
        }
        else {
            return String.format("%s %s %s %s", id, this.keyword, params[0], params[1]);
        }
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 2 && parts.size() != 3) {
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 or 3 for " + this.keyword + " command)");
        }
        try {
            if (parts.size() == 3) {
                String player = parts.get(0);
                String meeple_type = parts.get(1);
                int amount = Integer.parseInt(parts.get(2));
                view.updateOnCollectWithAmount(id, player, meeple_type, amount);
            } else if (parts.size() == 2) {
                String player = parts.get(0);
                String meeple_type = parts.get(1);
                view.updateOnCollect(id, player, meeple_type);
            }
        } catch (NumberFormatException e) {
            throw new InvalidArgumentNumberException("The third argument must be an integer.");
        }
    }
}
