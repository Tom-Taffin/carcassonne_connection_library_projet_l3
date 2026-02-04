package l3s6.projet.star.interaction.command;

import org.junit.jupiter.api.Test;

public abstract class AbstractCommandTest {

    protected AbstractCommand command;

    public abstract AbstractCommand getCommand();

    @Test
    public abstract void testCorrectBuild();

    @Test
    public abstract void testIncorrectBuild();

}