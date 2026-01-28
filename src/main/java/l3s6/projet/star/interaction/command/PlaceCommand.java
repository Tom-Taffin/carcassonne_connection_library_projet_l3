package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class PlaceCommand<V extends AbstractView> extends AbstractCommand<V> {

    public PlaceCommand(){
        this.keyword = "PLACES";
    }

    public String build(String id, Object... params){
        return String.format("%s %s %s %d:%d %s", id, this.keyword, params[0], params[1], params[2], params[3]);
    }

    public void execute(List<String> parts, V view){
        String[] xy = parts.get(3).split(":");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        String meeple = parts.get(4);
        view.updateOnPlace(parts.get(0), null /*TODO à faire quand les tiles sont dispo*/, x, y, meeple);
    }
}
