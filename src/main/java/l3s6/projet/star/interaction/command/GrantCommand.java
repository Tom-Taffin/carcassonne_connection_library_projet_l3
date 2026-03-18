package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AdminView;

import java.util.List;

/**
 * Command representing a grant action in the game's interaction system.
 * This command is identified by the keyword "GRANTS" and follows the format: id GRANTS id' mot-clé+,
 * where id' is the identifier of the entity receiving the grant and mot-clé+ represents
 * one or more keywords describing what is being granted.
 * Upon reception, it triggers a view update via the updateOnGrant method.
 * @param <V> the type of admin view this command operates on, must extend AdminView
 */
public class GrantCommand<V extends AdminView<?>> extends AbstractCommand<V> {

    /**
     * Constructs a GrantCommand and sets its keyword to "GRANTS".
     */
    public GrantCommand(){
        this.keyword = "GRANTS";
    }

    /**
     * Builds the string representation of the GRANTS command to be sent over the network.
     * @param id the identifier of the entity issuing the grant
     * @param params the identifier of the entity receiving the grant followed by one or more keywords, must be non-null and contain at least two elements
     * @return the formatted GRANTS command string ready to be transmitted
     * @throws InvalidArgumentNumberException if params is null or contains fewer than two elements
     */
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

    /**
     * Executes this command on the receiving end, applying its effect on the given view.
     * @param id the identifier of the entity targeted by the command
     * @param parts the list of string tokens parsed from the received command message, where the first token is the identifier of the entity receiving the grant and the remaining tokens are the granted keywords
     * @param view the admin view to update or interact with as a result of the command
     * @throws InvalidArgumentNumberException if parts contains fewer than two elements
     */
    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 or more for " + this.keyword + " command)");
        }
        view.updateOnGrant(id, parts.get(0), parts.subList(1, parts.size()));
    }
}