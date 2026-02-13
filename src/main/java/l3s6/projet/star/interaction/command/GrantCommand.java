package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import l3s6.projet.star.interaction.view.AdminView;

import java.util.List;

public class GrantCommand<V extends AbstractView> extends AbstractCommand<V> {

    public GrantCommand(){
        this.keyword = "GRANTS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id GRANTS id’ mot-clé+ */
        if (params == null || params.length < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 or more for " + this.keyword + " command)");
        }
        StringBuilder stringBuilder = new StringBuilder(id).append(" ").append(this.keyword);
        for (Object o : params){
            stringBuilder.append(" ").append(o);
        }
        return stringBuilder.toString();
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 or more for " + this.keyword + " command)");
        }
        view.updateOnGrant(id, parts.get(0), parts.subList(1, parts.size()));
    }
}