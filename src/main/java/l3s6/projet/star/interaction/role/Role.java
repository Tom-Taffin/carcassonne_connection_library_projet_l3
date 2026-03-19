package l3s6.projet.star.interaction.role;

/**
 * Enum representing the possible roles of a client in the game.
 */
public enum Role {
    SPECTATOR, PLAYER, REFEREE, UTILITY;

    /**
     * Returns the Role corresponding to the given string, defaulting to SPECTATOR if unknown.
     * @param role the string representation of the role
     * @return the matching Role value
     */
    public static Role getRoleFromString(String role){
        switch (role) {
            case "spectator":
                return SPECTATOR;
            case "player":
                return PLAYER;
            case "referee":
                return REFEREE;
            case "utility":
                return UTILITY;
            default:
                return SPECTATOR;
        }
    }

}
