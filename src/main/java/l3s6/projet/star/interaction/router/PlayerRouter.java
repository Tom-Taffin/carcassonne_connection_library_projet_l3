package l3s6.projet.star.interaction.router;

import l3s6.projet.star.interaction.view.PlayerView;

public class PlayerRouter<V extends PlayerView<?>> extends SpectatorRouter<V>{
    
    public PlayerRouter (V view){
        super(view);
    }

}