package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class CloseCommand<V extends AbstractView> extends AbstractCommand<V> {

    public CloseCommand(){
        this.keyword = "CLOSES";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params != null){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 0 for CLOSES command)");
        }
        return id + " " + this.keyword;
    }

    public void execute(List<String> parts, V view){
        view.updateOnClose(parts.get(0));
    }
}