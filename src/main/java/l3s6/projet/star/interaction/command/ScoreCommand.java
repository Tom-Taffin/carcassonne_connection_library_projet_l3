package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class ScoreCommand<V extends AbstractView> extends AbstractCommand<V> {

    public ScoreCommand(){
        this.keyword = "SCORES";
    }

     public String build(String id, Object... params) throws InvalidArgumentNumberException {
        if (params == null || params.length != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 for SCORES command)");
        }
        return id + " " + this.keyword + " " + params[0] + " " + params[1];
     }

     public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
         view.updateOnScore(parts.get(0), parts.get(2), Integer.parseInt(parts.get(3)));
     }
}
