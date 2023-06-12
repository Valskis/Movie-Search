import lt.viko.moviesearch.api.MovieController;
import lt.viko.moviesearch.model.UserInput;
import lt.viko.moviesearch.service.MovieSearchService;
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
public class MovieControllerTest {

    @Mock
    private MovieSearchService movieSearchService;

    @InjectMocks
    private MovieController movieController;

    @Test
    public void testSearchMovies() {
        UserInput userInput = new UserInput("Harr Potter", "");
        ResponseEntity<Object> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

        ResponseEntity<String> actualResponse = movieController.searchMovies(userInput);

        verify(movieSearchService, times(1)).searchMovies(userInput);
        assertEquals(expectedResponse, actualResponse);
    }
}
