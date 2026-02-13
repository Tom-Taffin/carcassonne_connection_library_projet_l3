package l3s6.projet.star.interaction.command;

import l3s6.projet.star.interaction.view.AbstractView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class OfferCommandTest extends AbstractCommandTest {

    public AbstractCommand getCommand(){
        return new OfferCommand();
    }

    @Test
    public void testCorrectBuild() throws InvalidArgumentNumberException {
        assertEquals("Sam OFFERS Rem f-f-f-f" ,this.command.build("Sam", "Rem", "f-f-f-f"));
    }

    @Test
    public void testIncorrectBuild() {
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", null);});
        assertThrows(InvalidArgumentNumberException.class , () -> {this.command.build("Sam", "Rem");});
    }

    @Test
    public void testCorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";

        List<String> parts1 = List.of("f-f-f-f", "Rem");
        this.command.execute(id, parts1, mockView);
        verify(mockView).updateOnOffer(id, parts1.get(0), parts1.subList(1, parts1.size()));

        List<String> parts2 = List.of("f-f-f-f", "Rem", "Sam", "Tom");
        this.command.execute(id, parts2, mockView);
        verify(mockView).updateOnOffer(id, parts1.get(0), parts2.subList(1, parts2.size()));
    }

    @Test
    public void testIncorrectExecute() throws InvalidArgumentNumberException {
        AbstractView mockView = mock(AbstractView.class);
        String id = "Sam";
        List<String> invalidParts1 = List.of();
        List<String> invalidParts2 = List.of("f-f-f-f");

        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts1, mockView); });
        assertThrows(InvalidArgumentNumberException.class, () -> { this.command.execute(id, invalidParts2, mockView); });

        verify(mockView, never()).updateOnOffer(anyString(), anyString(), anyList());
    }

}
