package l3s6.projet.star.interaction;

import java.util.Arrays;
import java.util.List;

public class CarcassonnePlayerDispatcher implements CarcassonneUpdateListener{
    
    public CarcassonnePlayerInterface carcassonneInterface;
    
    public CarcassonnePlayerDispatcher (CarcassonnePlayerInterface carcassonneInterface){
        this.carcassonneInterface = carcassonneInterface;
    }

    public void update(String message){
        List<String> messageArray = Arrays.asList(message.split(" "));
        String id = messageArray.get(0);
        String keyword = messageArray.get(1);
        if (keyword.equals("PLACES")){
            int x = Integer.parseInt(messageArray.get(3));
            int y = Integer.parseInt(messageArray.get(4));
            String meeple = messageArray.get(5);
            this.carcassonneInterface.updateOnPlace(id, null, x, y, meeple);
        } else if (keyword.equals("OFFERS")){
            List<String> players = messageArray.subList(3, messageArray.size());
            this.carcassonneInterface.updateOnOffer(id, null, players);
        }
    }

}