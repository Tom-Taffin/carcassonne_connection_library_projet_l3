package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BlameCommandTest extends AbstractCommandTest<SpectatorView<?>> {

    public AbstractCommand<SpectatorView<?>> getCommand(){
        return new BlameCommand<>();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        // format : id BLAMES id' reason
        assertEquals("Sam BLAMES Rem illegal-position", this.command.build("Sam", "Rem", "illegal-position"));
        // format : id BLAMES amount
        assertEquals("Sam BLAMES 3", this.command.build("Sam",  3));
    }

    @Test
    public void testIncorrectBuild() throws InvalidArgumentNumberException {
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", (Object[]) null));
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", "Rem", "illegal-position", "wrong argument"));
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", 3, "wrong argument"));
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        List<String> parts = List.of("3");
        this.command.execute(id, parts, mockView);
        verify(mockView).updateOnBlame("Sam", 3);
        verify(mockView, never()).updateOnBlameWithReason(anyString(), anyString(), anyString());

        List<String> partsWithReason = List.of("Rem", "illegal-position");
        this.command.execute(id, partsWithReason, mockView);
        verify(mockView).updateOnBlameWithReason("Sam", "Rem", "illegal-position");
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.execute(id, List.of(), mockView));
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.execute(id, List.of("Rem", "illegal-position", "wrong argument"), mockView));

        verify(mockView, never()).updateOnPlace(anyString(), anyString(), anyString(), anyInt(), anyInt());
        verify(mockView, never()).updateOnPlaceWithMeeple(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString(), anyString());
    }

}
