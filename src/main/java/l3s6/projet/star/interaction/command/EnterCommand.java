package l3s6.projet.star.interaction.command;


import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class EnterCommand extends AbstractCommand<T extends AbstractView> {

     public String build(String id, Object... params){
        return id + " ENTERS";
     }

     public void execute(List<String> parts, T view){
         view.updateOnEnter(parts.get(0));
     }
}
