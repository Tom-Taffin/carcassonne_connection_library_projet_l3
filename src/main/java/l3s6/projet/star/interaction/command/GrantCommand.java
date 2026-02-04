package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AdminView;

import java.util.List;

public class GrantCommand extends AbstractCommand<AdminView> {

    public GrantCommand(){
        this.keyword = "GRANTS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id GRANTS id’ mot-clé+ */
        if (params.length < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 or more for GRANTS command)");
        }
        StringBuilder stringBuilder = new StringBuilder(id).append(" ").append(this.keyword);
        for (Object o : params){
            stringBuilder.append(" ").append(o);
        }
        return stringBuilder.toString();
    }

    public void execute(List<String> parts, AdminView view){
        view.updateOnGrant(parts.get(0), parts.get(2), parts.subList(3, parts.size()));
    }
}