package l3s6.projet.star.interaction.role;

public enum Role {
    SPECTATOR, PLAYER, REFEREE, UTILITY;

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
