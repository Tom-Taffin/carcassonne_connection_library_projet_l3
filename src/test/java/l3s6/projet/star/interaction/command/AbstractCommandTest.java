package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractCommandTest {

    protected AbstractCommand command;

    @BeforeEach
    public void init(){
        this.command = this.getCommand();
    }

    public abstract AbstractCommand getCommand();

    @Test
    public abstract void testCorrectBuild() throws InvalidArgumentNumberException;

    @Test
    public abstract void testIncorrectBuild() throws InvalidArgumentNumberException;

    @Test
    public abstract void testExecute() throws InvalidArgumentNumberException;

}