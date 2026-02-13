package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class StartCommand<V extends AbstractView> extends AbstractCommand<V> {

    public StartCommand(){
        this.keyword = "STARTS";
    }

     public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params != null){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 0 for STARTS command)");
        }
        return id + " " + this.keyword;
     }

     public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
         view.updateOnStart(parts.get(0));
     }
}
