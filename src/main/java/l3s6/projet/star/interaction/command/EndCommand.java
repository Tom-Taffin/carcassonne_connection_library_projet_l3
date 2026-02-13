package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class EndCommand<V extends AbstractView> extends AbstractCommand<V> {

    public EndCommand(){
        this.keyword = "ENDS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* Format : id ENDS id+  */
        if (params == null || params.length < 1) {
            throw new InvalidArgumentNumberException(
                    "Invalid number of arguments given (must be 1 or more for ENDS command)"
            );
        }

        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            ids.append(params[i]);
            if (i < params.length - 1) {
                ids.append(" ");
            }
        }

        return id + " " + this.keyword + " " + ids;
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        view.updateOnEnd(id, parts);
    }
}