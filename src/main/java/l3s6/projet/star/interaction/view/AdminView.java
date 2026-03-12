package l3s6.projet.star.interaction.view;

import l3s6.projet.star.interaction.command.InvalidArgumentNumberException;
import l3s6.projet.star.interaction.network.AbstractClient;
import l3s6.projet.star.interaction.network.AdminClient;
import l3s6.projet.star.interaction.router.GameListener;
import l3s6.projet.star.interaction.router.AdminRouter;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class AdminView<T extends AdminClient> extends PlayerView<T> {

    public AdminView(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException{
        super(ipAddress, port, id);
    }

    protected GameListener createRouter(){
        return new AdminRouter<>(this);
    }

    protected AbstractClient createClient(String ipAddress, int port, String id) throws URISyntaxException, InterruptedException {
        return new AdminClient(ipAddress, port, id, this.dispatcher);
    }

    public void updateOnGrant(String id, String grantedPlayer, List<String> keywords) {
        System.out.println(String.format("[%s] Player %s was granted the keywords %s.", id, grantedPlayer, keywords.toString()));
    }

    public static void main(String[] args) throws InvalidArgumentNumberException {
        if (args.length != 3){
            throw new InvalidArgumentNumberException("Usage : <Host IP> <Port> <playerID>");
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String playerID = args[2];

        try {
            AdminView<?> view = new AdminView<>(host, port, playerID);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("quit")) {
                    running = false;
                } else {
                    try{
                        List<String> splitInput = List.of(input.split(" "));
                        view.send(splitInput.get(0), splitInput.subList(1, splitInput.size()));
                    } catch (InvalidArgumentNumberException e) {
                        System.out.println(e);
                    }
                    
                }
            }

            scanner.close();
            System.exit(0);

        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
