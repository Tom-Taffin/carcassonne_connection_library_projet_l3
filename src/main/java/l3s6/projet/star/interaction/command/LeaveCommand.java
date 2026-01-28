package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class LeaveCommand<V extends AbstractView> extends AbstractCommand<V> {

     public String build(String id, Object... params){
        return id + " LEAVES";
     }

     public void execute(List<String> parts, V view){
         view.updateOnLeave(parts.get(0));
     }
}
