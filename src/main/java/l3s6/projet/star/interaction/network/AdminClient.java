package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import l3s6.projet.star.interaction.command.*;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent an admin client
 * An admin can send every commands
 */
public class AdminClient extends PlayerClient {

    public AdminClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, id, updateListener);

    }

    /**
     * Sends an expel command
     * @param expelledPlayer the player expelled
     */
    public void expel(String expelledPlayer){
        try {
            ExpelCommand<?> expelCommand = new ExpelCommand<>();
            this.cws.send(expelCommand.build(id, expelledPlayer));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a grant command
     * @param grantedPlayer the player granted of the keywords
     * @param keywords the command keywords the player can send
     */
    public void grant(String grantedPlayer, String... keywords){
        try {
            GrantCommand<?> grantCommand = new GrantCommand<>();
            this.cws.send(grantCommand.build(id, grantedPlayer, keywords));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a close command, then closes the socket like {@link AbstractClient#close}
     */
    @Override
    public void close(){
        try {
            CloseCommand<?> closeCommand = new CloseCommand<>();
            this.cws.send(closeCommand.build(id));
            this.cws.close();
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends an elect command
     * @param role the role attributed
     * @param ids the ids that receive the role
     */
    public void elect(String role, String... ids){
        try {
            ElectCommand<?> electCommand = new ElectCommand<>();
            this.cws.send(electCommand.build(id, role, ids));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a score command
     * @param player the player that receive the points
     * @param points the amount of points
     */
    public void score(String player, int points){
        try {
            ScoreCommand<?> scoreCommand = new ScoreCommand<>();
            this.cws.send(scoreCommand.build(id, player, points));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a start command
     */
    public void start(){
        try {
            StartCommand<?> startCommand = new StartCommand<>();
            this.cws.send(startCommand.build(id, null));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends an end command
     * @param ids the players who won
     */
    public void end(String... ids){
        try {
            EndCommand<?> endCommand = new EndCommand<>();
            this.cws.send(endCommand.build(id, ids));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends an offer command
     * @param player the player that receive the tile
     * @param tile a tile
     */
    public void offer(String player, String tile){
        try {
            OfferCommand<?> offerCommand = new OfferCommand<>();
            this.cws.send(offerCommand.build(id, player, tile));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a blame command
     * @param player the player that receive the tile
     * @param reason the reason of the blame
     */
    public void blame(String player, String reason){
        try {
            BlameCommand<?> blameCommand = new BlameCommand<>();
            this.cws.send(blameCommand.build(id, player, reason));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a blame command
     * @param amount the amount of blames authorized
     */
    public void blame(int amount){
        try {
            BlameCommand<?> blameCommand = new BlameCommand<>();
            this.cws.send(blameCommand.build(id, amount));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a collect command
     */
    public void collect(String player, String meeple_type){
        try {
            CollectCommand<?> collectCommand = new CollectCommand<>();
            this.cws.send(collectCommand.build(id, player, meeple_type));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a collect command
     */
    public void collect(String player, String meeple_type, int amount){
        try {
            CollectCommand<?> collectCommand = new CollectCommand<>();
            this.cws.send(collectCommand.build(id, player, meeple_type, amount));
        } catch (InvalidArgumentNumberException e) {
            e.printStackTrace();
        }
    }

}
