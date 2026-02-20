package l3s6.projet.star.interaction.router;

import l3s6.projet.star.interaction.command.GrantCommand;
import l3s6.projet.star.interaction.view.AdminView;

public class AdminRouter<V extends AdminView<?>> extends PlayerRouter<V>{

    public AdminRouter(V view){
        super(view);
        this.commands.add(new GrantCommand<>());
    }

}