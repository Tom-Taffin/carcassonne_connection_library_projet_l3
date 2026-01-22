package l3s6.projet.star.interaction;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        TerminalPlayerInterface playerInterface = new TerminalPlayerInterface("127.0.0.1", 3000, "SAM");
        playerInterface.run();
    }
}
