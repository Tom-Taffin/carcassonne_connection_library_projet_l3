package l3s6.projet.star.interaction.command;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import l3s6.projet.star.interaction.view.SpectatorView;

public class PlayCommandTest extends AbstractCommandTest<SpectatorView<?>> {

    public AbstractCommand<SpectatorView<?>> getCommand(){
        return new PlayCommand<>();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Tom PLAYS" ,this.command.build("Tom", null));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Tom", "wrong argument");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Tom";
        List<String> parts = List.of();

        this.command.execute(id, parts, mockView);

        verify(mockView).updateOnPlay(id);
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Tom";
        List<String> invalidParts = List.of("invalid_part");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts, mockView); });

        verify(mockView, never()).updateOnPlay(anyString());
    }

}
