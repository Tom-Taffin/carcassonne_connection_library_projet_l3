package l3s6.projet.star.interaction.role;

import java.util.HashMap;
import java.util.Map;

public class RoleManager {

    protected Map<String, Role> roles;

    public RoleManager(){
        this.roles = new HashMap<>();
    }

    public void addRole(String id, Role role){
        this.roles.put(id, role);
    }

    public Role getRole(String id) {
        return this.roles.get(id);
    }

    public boolean isRole(String id, Role role){
        return this.roles.get(id).equals(role);
    }

}
