package l3s6.projet.star.interaction;

import java.net.URISyntaxException;

public class ViewerClient extends AbstractClient {

    public ViewerClient(String ip, int port, GameListener updateListener) throws URISyntaxException, InterruptedException{
        super(ip, port, updateListener);
    }

}
