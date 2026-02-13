package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class ExpelCommand<V extends AbstractView> extends AbstractCommand<V> {

    public ExpelCommand(){
        this.keyword = "EXPELS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params == null || params.length != 1){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 1 for EXPELS command)");
        }
        return id + " " + this.keyword + " " + params[0];
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        view.updateOnExpel(parts.get(0), parts.get(2));
    }
}