package l3s6.projet.star.interaction.router;

import java.util.Arrays;
import java.util.List;

import l3s6.projet.star.interaction.view.PlayerView;

public class PlayerRouter implements GameListener{
    
    public PlayerView carcassonneInterface;
    
    public PlayerRouter (PlayerView carcassonneInterface){
        this.carcassonneInterface = carcassonneInterface;
    }

    public void update(String message){
        List<String> messageArray = Arrays.asList(message.split(" "));
        String id = messageArray.get(0);
        String keyword = messageArray.get(1);
        if (keyword.equals("PLACES")){
            String[] xy = messageArray.get(3).split(":");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            String meeple = messageArray.get(4);
            this.carcassonneInterface.updateOnPlace(id, null, x, y, meeple);
        } else if (keyword.equals("OFFERS")){
            List<String> players = messageArray.subList(3, messageArray.size());
            this.carcassonneInterface.updateOnOffer(id, null, players);
        }
    }

}