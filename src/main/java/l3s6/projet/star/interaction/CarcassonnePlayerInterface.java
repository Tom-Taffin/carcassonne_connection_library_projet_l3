package l3s6.projet.star.interaction;

import java.util.List;

import l3s6.projet.star.game.tile.Tile;
    
public interface CarcassonnePlayerInterface {

    public void updateOnPlace(String player, Tile tile, int x, int y, String meeple);

    public void updateOnOffer(String player, Tile tile, List<String> players);

}
