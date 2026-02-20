package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;

import java.util.List;

public class OfferCommand<V extends SpectatorView<?>> extends AbstractCommand<V> {

    public OfferCommand(){
        this.keyword = "OFFERS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id OFFERS id' tile */
        if (params == null || params.length != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 for OFFERS command)");
        }
        return id + " " + this.keyword + " " + params[0] + " " + params[1];
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() != 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 for " + this.keyword + " command)");
        }
        view.updateOnOffer(id, parts.get(0), parts.get(1));
    }
}
