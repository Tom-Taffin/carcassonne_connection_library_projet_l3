package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class EnterCommand<V extends AbstractView> extends AbstractCommand<V> {

    public EnterCommand(){
        this.keyword = "ENTERS";
    }

     public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params != null){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 0 for " + this.keyword + " command)");
        }
        return id + " " + this.keyword;
     }

     public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (!parts.isEmpty()){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 0 for " + this.keyword + " command)");
        }
        view.updateOnEnter(id);
     }
}
