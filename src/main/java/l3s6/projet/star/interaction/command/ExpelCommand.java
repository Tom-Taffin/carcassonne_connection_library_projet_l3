package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class ExpelCommand<V extends AbstractView> extends AbstractCommand<V> {

    public String build(String id, Object... params){
        return id + " EXPELS " + params[0];
    }

    public void execute(List<String> parts, V view){
        view.updateOnExpel(parts.get(0), parts.get(2));
    }
}