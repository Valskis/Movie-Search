import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lt.viko.moviesearch.api.MovieController;
import lt.viko.moviesearch.model.UserInput;
import lt.viko.moviesearch.service.MovieSearchService;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Cucumber step definitions for movie search feature
 */
public class SearchStepDefinition {

    private MovieController movieController;
    private ResponseEntity<List<Object>> responseEntity;

    @Given("Client sends GET request with a movie title")
    public void Client_sends_GET_request_with_a_movie_title() {
        movieController = new MovieController(new MovieSearchService());
    }

    @When("HTTP GET reqest sent to the server")
    public void HTTP_GET_reqest_sent_to_the_server() {
        UserInput userInput = new UserInput("Harry Potter", null);
        responseEntity = movieController.searchMovies(userInput);
    }

    @Then("List of movies with the same title is returned")
    public void List_Of_Movies_With_The_Same_Title_Is_Returned() {
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        List<Object> results = responseEntity.getBody();
        Assert.assertNotNull(results);

        boolean containsHarryPotter = false;
        for (Object result : results) {
            if (result.toString().contains("Harry Potter")) {
                containsHarryPotter = true;
                break;
            }
        }

        Assert.assertTrue("Response does not contain 'Harry Potter'", containsHarryPotter);
    }

}
