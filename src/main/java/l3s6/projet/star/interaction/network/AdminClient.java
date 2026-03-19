package l3s6.projet.star.interaction.network;

import java.net.URISyntaxException;
import l3s6.projet.star.interaction.command.*;
import l3s6.projet.star.interaction.router.GameListener;

/**
 * A class to represent an admin client
 * An admin can send every commands
 */
public class AdminClient extends PlayerClient {

    /**
     * Constructs an AdminClient and registers all commands available for an admin.
     * @param ip the IP address of the server
     * @param port the port of the server
     * @param id the identifier of the admin
     * @param updateListener the game listener to notify upon receiving commands
     * @throws URISyntaxException if the server URI has incorrect syntax
     * @throws InterruptedException if the connection is interrupted
     */
    public AdminClient(String ip, int port, String id, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, id, updateListener);
        this.commands.add(new ExpelCommand<>());
        this.commands.add(new GrantCommand<>());
        this.commands.add(new CloseCommand<>());
        this.commands.add(new ElectCommand<>());
        this.commands.add(new ScoreCommand<>());
        this.commands.add(new StartCommand<>());
        this.commands.add(new EndCommand<>());
        this.commands.add(new OfferCommand<>());
        this.commands.add(new BlameCommand<>());
        this.commands.add(new CollectCommand<>());
    }

}
