package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.List;

import l3s6.projet.star.interaction.command.InvalidArgumentNumberException;
import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.network.SpectatorClient;
import l3s6.projet.star.interaction.role.Role;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.router.SpectatorRouter;
    
public class SpectatorView<T extends SpectatorClient> extends AbstractView<T> {

    public SpectatorView(String ipAddress, int port) throws URISyntaxException, InterruptedException {
        super(ipAddress, port, null);
    }

    public SpectatorView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super(ipAddress, port, id);
    }

    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super.connect(ipAddress, port, id);
    }

    protected GameListener createRouter(){
        return new SpectatorRouter<>(this);
    }

    protected AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException {
        return new SpectatorClient(ipAddress, port, this.dispatcher);
    }
    
    public void updateOnPlace(String id, String player, String orientation, int x, int y) {
        if (this.roleManager.isRole(id, Role.PLAYER)){
            System.out.println(String.format("[%s] Player %s wants to place the tile with the orientation %s on position %d:%d.", id, player, orientation, x, y));
        } else if (this.roleManager.isRole(id, Role.REFEREE)){
            System.out.println(String.format("[%s] Player %s places the tile with the orientation %s on position %d:%d.", id, player, orientation, x, y));
        }
    }

    public void updateOnPlaceWithMeeple(String id, String player, String orientation, int x, int y, String meeple_type, String meeple_position) {
        if (this.roleManager.isRole(id, Role.PLAYER)){
            System.out.println(String.format("[%s] Player %s wants to place the tile with the orientation %s on position %d:%d with meeple %s on position %s.", id, player, orientation, x, y, meeple_type, meeple_position));
        } else if (this.roleManager.isRole(id, Role.REFEREE)){
            System.out.println(String.format("[%s] Player %s places the tile with the orientation %s on position %d:%d with meeple %s on position %s.", id, player, orientation, x, y, meeple_type, meeple_position));
        }
    }

    public void updateOnBlame(String id, int amount) {
        System.out.println(String.format("[%s] %d blames are authorized for this game.", id, amount));
    }

    public void updateOnBlameWithReason(String id, String player, String reason) {
        System.out.println(String.format("[%s] Player %s was blamed for the reason %s.", id, player, reason));
    }

    public void updateOnCollect(String id, String player, String meeple_type, int x, int y) {
        System.out.println(String.format("[%s] Player %s collects the meeple %s at position %d:%d.", id, player, meeple_type, x, y));
    }

    public void updateOnCollectAtStart(String id, String player, String meeple_type, int amount) {
        System.out.println(String.format("[%s] Player %s collects %d meeples %s.", id, player, amount, meeple_type));
    }

    public void updateOnOffer(String id, String player, String tile) {
        System.out.println(String.format("[%s] Player %s gets the tile %s.", id, player, tile));
    }

    public void updateOnEnter(String id) {
        this.roleManager.addRole(id, Role.SPECTATOR);
        System.out.println(String.format("[%s] %s enters.", id, id));
    }

    public void updateOnLeave(String id) {
        System.out.println(String.format("[%s] %s leaves.", id, id));
    }

    public void updateOnClose(String id)  {
        System.out.println(String.format("[%s] %s closes.", id, id));
    }

    public void updateOnExpel(String id, String expelledPlayer) {
        System.out.println(String.format("[%s] Player %s was expelled.", id, expelledPlayer));
    }

    public void updateOnElect(String id, String role, List<String> ids) {
        for (String i : ids) {
            this.roleManager.addRole(i, Role.getRoleFromString(role));
        }
        System.out.println(String.format("[%s] Players %s gained the role %s.", id, ids, role));
    }

    public void updateOnAgree(String id, List<String> expOrVar) {
        if (this.roleManager.isRole(id, Role.PLAYER)){
            System.out.println(String.format("[%s] %s supports the following expansions and variations : %s", id, id, expOrVar.toString()));
        } else if (this.roleManager.isRole(id, Role.REFEREE)){
            System.out.println(String.format("[%s] The expansions and variations %s are chosen for this game.", id, expOrVar.toString()));
        }
    }

    public void updateOnScore(String id, String otherId, int points) {
        System.out.println(String.format("[%s] Player %s gains %d points.", id, otherId, points));
    }

    public void updateOnStart(String id) {
        System.out.println(String.format("[%s] The game starts.", id));
    }

    public void updateOnEnd(String id, List<String> ids) {
        System.out.println(String.format("[%s] The game ends. Winners : %s.", id, ids.toString()));
    }

    public void updateOnPlay(String id) {
        System.out.println(String.format("[%s] %s will play in this game.", id, id));
    }

    public static void main(String[] args) throws InvalidArgumentNumberException {
        if (args.length != 2){
            throw new InvalidArgumentNumberException("Usage : <Host IP> <Port>");
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            SpectatorView<?> view = new SpectatorView<>(host, port);
        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
