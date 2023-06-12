import lt.viko.moviesearch.api.ActorController;
import lt.viko.moviesearch.model.UserInput;
import lt.viko.moviesearch.service.ActorSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ActorControllerTest {

    @Mock
    private ActorSearchService actorSearchService;

    @InjectMocks
    private ActorController actorController;

    @Test
    public void testSearchActors() {
        UserInput userInput = new UserInput("", "nm0000206");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

        ResponseEntity<String> actualResponse = actorController.searchActors(userInput);

        verify(actorSearchService, times(1)).searchActors(userInput);
        assertEquals(expectedResponse, actualResponse);
    }
}
