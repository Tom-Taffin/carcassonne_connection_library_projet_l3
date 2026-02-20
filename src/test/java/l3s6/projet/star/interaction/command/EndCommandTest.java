package l3s6.projet.star.interaction.command;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import l3s6.projet.star.interaction.view.SpectatorView;

public class EndCommandTest extends AbstractCommandTest<SpectatorView<?>> {

    public AbstractCommand<SpectatorView<?>> getCommand(){
        return new EndCommand<>();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam ENDS Rem Tom" ,this.command.build("Sam", "Rem", "Tom"));
        assertEquals("Sam ENDS Andrei" ,this.command.build("Sam", "Andrei"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";

        List<String> parts1 = List.of("Rem", "Tom");
        this.command.execute(id, parts1, mockView);
        verify(mockView).updateOnEnd(id, parts1);

        List<String> parts2 = List.of();
        this.command.execute(id, parts2, mockView);
        verify(mockView).updateOnEnd(id, parts2);
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {

    }

}
