package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

public class CloseCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    public CloseCommand(){
        this.keyword = "CLOSES";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params != null && params.length != 0){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 0 for CLOSES command)");
        }
        return id + " " + this.keyword;
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (!parts.isEmpty()){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 0 for " + this.keyword + " command)");
        }
        view.updateOnClose(id);
    }
}