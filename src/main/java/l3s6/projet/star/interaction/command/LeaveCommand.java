package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class LeaveCommand<V extends AbstractView> extends AbstractCommand<V> {

    public LeaveCommand(){
        this.keyword = "LEAVES";
    }

     public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params != null){
             throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 0 for LEAVES command)");
        }
        return id + " " + this.keyword;
     }

     public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
         view.updateOnLeave(parts.get(0));
     }
}
