package l3s6.projet.star.interaction.command;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import l3s6.projet.star.interaction.view.SpectatorView;

public class OfferCommandTest extends AbstractCommandTest<SpectatorView<?>> {

    public AbstractCommand<SpectatorView<?>> getCommand(){
        return new OfferCommand<>();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam OFFERS Rem f-f-f-f" ,this.command.build("Sam", "Rem", "f-f-f-f"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem");});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem", "f-f-f-f", "wrong argument");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";

        List<String> parts1 = List.of("Rem", "f-f-f-f");
        this.command.execute(id, parts1, mockView);
        verify(mockView).updateOnOffer(id, parts1.get(0), parts1.get(1));
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        SpectatorView<?> mockView = mock(SpectatorView.class);
        String id = "Sam";
        List<String> invalidParts1 = List.of();
        List<String> invalidParts2 = List.of("f-f-f-f");
        List<String> invalidParts3 = List.of("Sam", "f-f-f-f", "wrong argument");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts1, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts2, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts3, mockView); });

        verify(mockView, never()).updateOnOffer(anyString(), anyString(), anyString());
    }

}
