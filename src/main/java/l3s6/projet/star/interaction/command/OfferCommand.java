package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;

import java.util.List;

public class OfferCommand<V extends AbstractView> extends AbstractCommand<V> {

    public OfferCommand(){
        this.keyword = "OFFERS";
    }

    public String build(String id, Object... params){
        StringBuilder stringBuilder = new StringBuilder(id).append(" ").append(this.keyword);
        for (Object o : params){
            stringBuilder.append(" ").append(o);
        }
        return stringBuilder.toString();
    }

    public void execute(List<String> parts, V view){
        view.updateOnOffer(parts.get(0), null, parts.subList(3, parts.size()));
    }
}
