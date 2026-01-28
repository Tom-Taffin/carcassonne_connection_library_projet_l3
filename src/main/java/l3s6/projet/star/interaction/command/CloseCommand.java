package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class CloseCommand<V extends AbstractView> extends AbstractCommand<V> {

    public String build(String id, Object... params){
        return id + " CLOSES";
    }

    public void execute(List<String> parts, V view){
        view.updateOnClose(parts.get(0));
    }
}