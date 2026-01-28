package l3s6.projet.star.interaction.router;

import java.util.Arrays;
import java.util.List;

import l3s6.projet.star.interaction.command.EnterCommand;
import l3s6.projet.star.interaction.command.LeaveCommand;
import l3s6.projet.star.interaction.command.CloseCommand;
import l3s6.projet.star.interaction.command.PlaceCommand;
import l3s6.projet.star.interaction.command.OfferCommand;
import l3s6.projet.star.interaction.view.SpectatorView;

public class SpectatorRouter extends AbstractRouter<SpectatorView<?>>{
    
    public SpectatorRouter(SpectatorView<?> view){
        super(view);
    }

    public void update(String message){
        List<String> messageArray = Arrays.asList(message.split(" "));
        String id = messageArray.get(0);
        String keyword = messageArray.get(1);
        if (keyword.equals("ENTERS")) {
            EnterCommand command = new EnterCommand();
            command.execute(messageArray, view);
        } else if (keyword.equals("LEAVES")) {
            LeaveCommand command = new LeaveCommand<>();
            command.execute(messageArray, view);
        } else if (keyword.equals("CLOSES")){
            CloseCommand command = new CloseCommand<>();
            command.execute(messageArray, view);
        } else if (keyword.equals("PLACES")){
            PlaceCommand command = new PlaceCommand<>();
            command.execute(messageArray, view);
        } else if (keyword.equals("OFFERS")){
            OfferCommand command = new OfferCommand<>();
            command.execute(messageArray, view);
        }
    }

}