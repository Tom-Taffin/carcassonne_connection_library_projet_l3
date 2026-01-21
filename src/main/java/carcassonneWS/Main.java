package carcassonneWS;

import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        CarcassonneGUI gui = new TestGUI();
        CarcassonneClient client = new CarcassonneClient("127.0.0.1", 3000, "SAM", gui);
        Scanner s = new Scanner(System.in);
        s.nextLine();
        client.leave();
        s.close();
    }
}
