import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lt.viko.moviesearch.api.MovieController;
import lt.viko.moviesearch.model.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class SearchStepDefinition {

    private final MovieController movieController;
    private ResponseEntity<List<Object>> response;

    @Autowired
    public SearchStepDefinition(MovieController movieController) {
        this.movieController = movieController;
    }

    @Given("a user wants to search for movies")
    public void givenUserWantsToSearchMovies() {
        // No additional action needed for this step
    }

    @When("the user sends a POST request to \"/api/movies/search\" with the following request body:")
    public void whenUserSendsPostRequestWithRequestBody(String requestBody) throws JsonProcessingException {
        UserInput userInput = new UserInput("Avengers", "");
        response = movieController.searchMovies(userInput);
    }

    @Then("the response status should be {int}")
    public void thenResponseStatusShouldBe(int expectedStatus) {
        HttpStatus expectedHttpStatus = HttpStatus.valueOf(expectedStatus);
        assertEquals(expectedHttpStatus, response.getStatusCode());
    }
}

