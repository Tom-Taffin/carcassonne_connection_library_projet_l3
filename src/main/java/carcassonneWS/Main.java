package carcassonneWS;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        CarcassonneWebSocket cws = new CarcassonneWebSocket("127.0.0.1", 3000);
        cws.connectBlocking();
        cws.send("1 ENTERS");
        cws.close();
    }
}
