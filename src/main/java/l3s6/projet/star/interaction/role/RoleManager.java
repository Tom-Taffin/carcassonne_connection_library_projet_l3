package l3s6.projet.star.interaction.role;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the roles assigned to clients in the game, mapping their identifiers to their roles.
 */
public class RoleManager {

    protected Map<String, Role> roles;

    /**
     * Constructs a RoleManager with an empty role map.
     */
    public RoleManager(){
        this.roles = new HashMap<>();
    }

    /**
     * Assigns a role to the client with the given identifier.
     * @param id the identifier of the client
     * @param role the role to assign
     */
    public void addRole(String id, Role role){
        this.roles.put(id, role);
    }

    /**
     * Returns the role assigned to the client with the given identifier.
     * @param id the identifier of the client
     * @return the role of the client
     */
    public Role getRole(String id) {
        return this.roles.get(id);
    }

    /**
     * Checks whether the client with the given identifier has the specified role.
     * @param id the identifier of the client
     * @param role the role to check
     * @return true if the client has the given role, false otherwise
     */
    public boolean isRole(String id, Role role){
        return this.roles.get(id).equals(role);
    }

}