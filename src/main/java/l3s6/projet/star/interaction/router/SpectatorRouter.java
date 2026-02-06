package l3s6.projet.star.interaction.router;

import l3s6.projet.star.interaction.command.*;
import l3s6.projet.star.interaction.view.SpectatorView;

public class SpectatorRouter extends AbstractRouter<SpectatorView<?>>{
    
    public SpectatorRouter(SpectatorView<?> view){
        super(view);
        this.commands.add(new EnterCommand<>());
        this.commands.add(new LeaveCommand<>());
        this.commands.add(new CloseCommand<>());
        this.commands.add(new PlaceCommand<>());
        this.commands.add(new OfferCommand<>());
    }

}