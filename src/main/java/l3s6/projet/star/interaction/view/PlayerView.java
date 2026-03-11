package l3s6.projet.star.interaction.view;

import java.net.URISyntaxException;
import java.util.Scanner;

import l3s6.projet.star.interaction.command.InvalidArgumentNumberException;
import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.network.PlayerClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.router.PlayerRouter;
    
public class PlayerView<T extends PlayerClient> extends SpectatorView<T> {

    public PlayerView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super(ipAddress, port, id);
    }

    public void connect(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super.connect(ipAddress, port, id);
    }

    protected GameListener createRouter(){
        return new PlayerRouter<>(this);
    }

    protected AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException {
        return new PlayerClient(ipAddress, port, id, this.dispatcher);
    }

    public static void main(String[] args) throws InvalidArgumentNumberException {
        if (args.length != 3){
            throw new InvalidArgumentNumberException("Usage : <Host IP> <Port> <playerID>");
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String playerID = args[2];

        try {
            PlayerView<?> view = new PlayerView<>(host, port, playerID);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("quit")) {
                    running = false;
                } else {
                    view.client.place(playerID, "Wf-f-f-f", 1, 2);
                }
            }

            scanner.close();
            System.exit(0);

        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
