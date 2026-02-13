package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class ElectCommand<V extends AbstractView> extends AbstractCommand<V> {

    public ElectCommand(){
        this.keyword = "ELECTS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* Format : id ELECTS role id+ */
        if (params == null || params.length < 2) {
            throw new InvalidArgumentNumberException(
                    "Invalid number of arguments given (must be 2 or more for ELECTS command)"
            );
        }

        StringBuilder ids = new StringBuilder();
        for (int i = 1; i < params.length; i++) {
            ids.append(params[i]);
            if (i < params.length - 1) {
                ids.append(" ");
            }
        }

        return id + " " + this.keyword + " " + params[0] + " " + ids;
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 or more for " + this.keyword + " command)");
        }
        view.updateOnElect(id, parts.get(0), parts.subList(1, parts.size()));
    }
}