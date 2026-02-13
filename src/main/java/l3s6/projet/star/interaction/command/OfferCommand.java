package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class OfferCommand<V extends AbstractView> extends AbstractCommand<V> {

    public OfferCommand(){
        this.keyword = "OFFERS";
    }

    public String build(String id, Object... params) throws InvalidArgumentNumberException {
        /* format: id OFFERS tile id+ */
        if (params == null || params.length < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments given (must be 2 or more for OFFERS command)");
        }
        StringBuilder stringBuilder = new StringBuilder(id).append(" ").append(this.keyword);
        for (Object o : params){
            stringBuilder.append(" ").append(o);
        }
        return stringBuilder.toString();
    }

    public void execute(String id, List<String> parts, V view) throws InvalidArgumentNumberException {
        if (parts.size() < 2){
            throw new InvalidArgumentNumberException("Invalid number of arguments received (must be 2 or more for " + this.keyword + " command)");
        }
        view.updateOnOffer(id, parts.get(0), parts.subList(1, parts.size()));
    }
}
