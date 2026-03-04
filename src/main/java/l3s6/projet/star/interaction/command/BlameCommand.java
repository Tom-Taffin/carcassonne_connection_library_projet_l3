package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

public class BlameCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    public BlameCommand(){
        this.keyword = "BLAMES";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format : id BLAMES id' reason */
        /* format : id BLAMES amount */

        if (params == null || (params.length != 1 && params.length != 2)){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 1 or 2 for " + this.keyword + " command)");
        }
        if (params.length == 2){
            if (params[0] instanceof Integer) {
                throw new InvalidArgumentNumberException("Invalid arguments: 2 params requires id' and reason, not an integer for " + this.keyword + " command");
            }
            return String.format("%s %s %s %s", id, this.keyword, params[0], params[1]);
        }
        else {
            return String.format("%s %s %s", id, this.keyword, params[0]);
        }
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 1 && parts.size() != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 1 or 2 for " + this.keyword + " command)");
        }
        if (parts.size() == 1){
            int amount = Integer.parseInt(parts.get(0));
            view.updateOnBlame(id, amount);
        }
        if (parts.size() == 2) {
            String player = parts.get(0);
            String reason = parts.get(1);
            view.updateOnBlameWithReason(id, player, reason);
        }
    }
}
