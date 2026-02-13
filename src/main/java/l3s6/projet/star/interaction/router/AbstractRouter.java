package l3s6.projet.star.interaction.router;

import l3s6.projet.star.interaction.command.AbstractCommand;
import l3s6.projet.star.interaction.command.InvalidArgumentNumberException;
import l3s6.projet.star.interaction.view.AbstractView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractRouter<V extends AbstractView<?>> implements GameListener{
    
    protected V view;
    protected List<AbstractCommand> commands;

    public AbstractRouter(V view){
        this.view = view;
        this.commands = new ArrayList<>();
    }

    public void update(String message){
        List<String> messageArray = Arrays.asList(message.split(" "));
        String id = messageArray.get(0);
        String keyword = messageArray.get(1);
        for (AbstractCommand command : this.commands){
            if (command.getKeyword().equals(keyword)){
                try {
                    command.execute(id, messageArray.subList(2, messageArray.size()), view);
                } catch (InvalidArgumentNumberException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}