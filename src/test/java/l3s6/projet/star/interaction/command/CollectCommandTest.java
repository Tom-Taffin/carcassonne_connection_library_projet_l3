package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.SpectatorView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CollectCommandTest extends AbstractCommandTest<SpectatorView<?>> {

    public AbstractCommand<SpectatorView<?>> getCommand(){
        return new CollectCommand<>();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        /* format : id COLLECTS id' meeple_type (amount) */
        assertEquals("Sam COLLECTS Rem regular 5", this.command.build("Sam", "Rem", "regular", 5));
        /* format : id COLLECTS id' meeple_type */
        assertEquals("Sam COLLECTS Rem regular 1 2", this.command.build("Sam", "Rem", "regular", 1, 2));
    }

    @Test
    public void testIncorrectBuild() throws InvalidArgumentNumberException {
        // pas assez d'arguments
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", (Object[]) null));
        // trop d'arguments
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", "Rem", "regular", 5, "wrong argument"));
        // argument incorrect (String à la place d'un int)
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", "Rem", "regular", "5"));
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        // exécution correcte sans amount
        List<String> parts = List.of("Rem", "regular", "1", "2");
        this.command.execute(id, parts, mockView);
        verify(mockView).updateOnCollect(id, parts.get(0), parts.get(1), Integer.parseInt(parts.get(2)), Integer.parseInt(parts.get(3)));

        // exécution correcte avec amount
        List<String> partsWithAmount = List.of("Rem", "regular", "5");
        this.command.execute(id, partsWithAmount, mockView);
        verify(mockView).updateOnCollectAtStart(id, partsWithAmount.get(0), partsWithAmount.get(1), Integer.parseInt(partsWithAmount.get(2)));
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        // pas assez d'arguments
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.execute(id, List.of(), mockView));
        // trop d'arguments
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.execute(id, List.of("Rem", "regular", "5", "1", "wrong argument"), mockView));
        // argument incorrect (String à la place d'un int)
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.execute(id, List.of("Rem", "regular", "wrong argument"), mockView));

        verify(mockView, never()).updateOnCollect(anyString(), anyString(), anyString(), anyInt(), anyInt());
        verify(mockView, never()).updateOnCollectAtStart(anyString(), anyString(), anyString(), anyInt());
    }

}
