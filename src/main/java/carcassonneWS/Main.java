package carcassonneWS;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        CarcassonneClient client = new CarcassonneClient("127.0.0.1", 3000, "SAM");
        client.leave();
    }
}
