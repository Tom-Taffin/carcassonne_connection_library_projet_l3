package l3s6.projet.star.interaction.router;

import l3s6.projet.star.interaction.view.AbstractView;

public abstract class AbstractRouter<V extends AbstractView<?>> implements GameListener{
    
    protected V view;

    public AbstractRouter(V view){
        this.view = view;
    }

}