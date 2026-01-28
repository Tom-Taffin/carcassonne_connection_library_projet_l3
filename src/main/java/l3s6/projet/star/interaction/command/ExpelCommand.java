package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class ExpelCommand<V extends AbstractView> extends AbstractCommand<V> {

    public ExpelCommand(){
        this.keyword = "EXPELS";
    }

    public String build(String id, Object... params){
        return id + " " + this.keyword + " " + params[0];
    }

    public void execute(List<String> parts, V view){
        view.updateOnExpel(parts.get(0), parts.get(2));
    }
}