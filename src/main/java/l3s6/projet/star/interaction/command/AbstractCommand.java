package l3s6.projet.star.interaction.command;

import java.util.List;

public abstract class AbstractCommand<V> {

    protected String keyword;

    public abstract String build(String id, Object... params);

    public abstract void execute(List<String> parts, V view);

    public String getKeyword(){
        return this.keyword;
    }

}
