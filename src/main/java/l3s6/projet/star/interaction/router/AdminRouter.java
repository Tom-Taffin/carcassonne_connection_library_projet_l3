package l3s6.projet.star.interaction.router;

import l3s6.projet.star.interaction.command.ExpelCommand;
import l3s6.projet.star.interaction.command.GrantCommand;
import l3s6.projet.star.interaction.view.AdminView;


public class AdminRouter extends PlayerRouter{

    public AdminRouter(AdminView view){
        super(view);
        this.commands.add(new GrantCommand());
        this.commands.add(new ExpelCommand());
    }

}