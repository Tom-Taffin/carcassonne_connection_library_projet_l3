package l3s6.projet.star.interaction.command;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import l3s6.projet.star.interaction.view.SpectatorView;

public class PlaceCommandTest extends AbstractCommandTest<SpectatorView<?>> {

    public AbstractCommand<SpectatorView<?>> getCommand(){
        return new PlaceCommand<>();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        // format : id PLACES id' tile x:y  (4 params)
        assertEquals("Sam PLACES Rem W 2 3", this.command.build("Sam", "Rem", "W", 2, 3));
        // format : id PLACES id' tile x:y meeple_type meeple_position  (6 params)
        assertEquals("Sam PLACES Rem W 2 3 FARMER TOP", this.command.build("Sam", "Rem", "W", 2, 3, "FARMER", "TOP"));
    }

    @Test
    public void testIncorrectBuild() throws InvalidArgumentNumberException {
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", (Object[]) null));
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", "Rem", "W", 2));
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.build("Sam", "Rem", "W", 2, 3, "FARMER"));
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        List<String> parts = List.of("Rem", "f-f-f-f", "2", "3");
        this.command.execute(id, parts, mockView);
        verify(mockView).updateOnPlace("Sam", "Rem", "f-f-f-f", 2, 3);
        verify(mockView, never()).updateOnPlaceWithMeeple(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString(), anyString());

        List<String> partsWithMeeple = List.of("Rem", "f-f-f-f", "2", "3", "FARMER", "TOP");
        this.command.execute(id, partsWithMeeple, mockView);
        verify(mockView).updateOnPlaceWithMeeple("Sam", "Rem", "f-f-f-f", 2, 3, "FARMER", "TOP");
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.execute(id, List.of("Rem", "f-f-f-f", "2"), mockView));
        assertThrows(InvalidArgumentNumberException.class, () -> this.command.execute(id, List.of("Rem", "f-f-f-f", "2", "3", "FARMER"), mockView));

        verify(mockView, never()).updateOnPlace(anyString(), anyString(), anyString(), anyInt(), anyInt());
        verify(mockView, never()).updateOnPlaceWithMeeple(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyString(), anyString());
    }

}
