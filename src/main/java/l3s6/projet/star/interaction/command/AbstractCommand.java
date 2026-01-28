package l3s6.projet.star.interaction.command;

import java.util.List;

public abstract class AbstractCommand<V> {

    public abstract String build(String id, Object... params);

    public abstract void execute(List<String> parts, V view);

}
