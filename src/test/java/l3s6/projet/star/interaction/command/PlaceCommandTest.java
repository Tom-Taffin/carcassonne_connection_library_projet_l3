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
        assertEquals("Sam PLACES f-f-f-f 2:3 bleu" ,this.command.build("Sam", "f-f-f-f", 2, 3, "bleu"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "f-f-f-f", 2, 3);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "f-f-f-f", 2, 3, "bleu", "wrong argument");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        List<String> parts = List.of("f-f-f-f", "2:3", "meep");

        this.command.execute(id, parts, mockView);

        verify(mockView).updateOnPlace(id, parts.get(0), 2, 3, parts.get(2));
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        List<String> invalidParts1 = List.of("f-f-f-f", "2:3");
        List<String> invalidParts2 = List.of("f-f-f-f", "2:3", "meep", "invalid_part");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts1, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts2, mockView); });

        verify(mockView, never()).updateOnPlace(anyString(), anyString(), anyInt(), anyInt(), anyString());
    }

}
