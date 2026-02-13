package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class AgreeCommand<V extends AbstractView> extends AbstractCommand<V> {

    public AgreeCommand(){
        this.keyword = "AGREES";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* Format : id AGREES exp_or_var+  */
        if (params == null || params.length < 1) {
            throw new InvalidArgumentNumberException(
                    "Invalid number of arguments given (must be 1 or more for AGREES command)"
            );
        }

        StringBuilder exp_or_var = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            exp_or_var.append(params[i]);
            if (i < params.length - 1) {
                exp_or_var.append(" ");
            }
        }

        return id + " " + this.keyword + " " + exp_or_var;
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        view.updateOnAgree(parts.get(0), parts.subList(2, parts.size()));
    }
}