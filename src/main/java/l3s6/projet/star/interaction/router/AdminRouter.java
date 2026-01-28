package l3s6.projet.star.interaction.router;

import l3s6.projet.star.interaction.command.EnterCommand;
import l3s6.projet.star.interaction.command.PlaceCommand;
import l3s6.projet.star.interaction.view.AdminView;

import java.util.Arrays;
import java.util.List;

public class AdminRouter extends PlayerRouter{

    public AdminRouter(AdminView view){
        super(view);
    }

    public void update(String message){
        List<String> messageArray = Arrays.asList(message.split(" "));
        String id = messageArray.get(0);
        String keyword = messageArray.get(1);
        if (keyword.equals("ENTERS")) {
            EnterCommand command = new EnterCommand();
            command.execute(messageArray, view);
        } else if (keyword.equals("PLACES")){
            PlaceCommand command = new PlaceCommand<>();
            command.execute(messageArray, view);
        } else {
            super.update(message);
        }
    }

}