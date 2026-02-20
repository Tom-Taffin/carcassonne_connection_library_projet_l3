package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import l3s6.projet.star.interaction.view.AbstractView;

public abstract class AbstractCommandTest<V extends AbstractView<?>> {

    protected AbstractCommand<V> command;

    @BeforeEach
    public void init(){
        this.command = this.getCommand();
    }

    public abstract AbstractCommand<V> getCommand();

    @Test
    public abstract void testCorrectBuild() throws InvalidArgumentNumberException;

    @Test
    public abstract void testIncorrectBuild() throws InvalidArgumentNumberException;

    @Test
    public abstract void testCorrectExecute() throws InvalidArgumentNumberException;

    @Test
    public abstract void testIncorrectExecute() throws InvalidArgumentNumberException;

}