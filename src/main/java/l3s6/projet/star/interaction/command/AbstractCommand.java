package l3s6.projet.star.interaction.command;

import java.util.List;

import l3s6.projet.star.interaction.network.GameSocket;
import l3s6.projet.star.interaction.view.AbstractView;

public abstract class AbstractCommand<V extends AbstractView<?>> {

    protected String keyword;

    public String getKeyword(){
        return this.keyword;
    }

    public abstract String build(String id, Object... params) throws InvalidArgumentNumberException;

    public abstract void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException;

    public void send(GameSocket gs, String id, Object... param) throws InvalidArgumentNumberException{
        gs.send(this.build(id, param));
    }

}
