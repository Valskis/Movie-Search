package lt.viko.moviesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.moviesearch.model.UserInput;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MovieSearchService class represents MovieSearchService object which is responsible
 * for searching movies
 * @version 1.0
 */
@Service
public class MovieSearchService {
    private static final String DATABASE_FILE_PATH = "searches.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_URL = "https://moviesdatabase.p.rapidapi.com/titles/search/title/";

    /**
     * Saves the searched movies to the database
     * @param userInput the user input
     */
    public List<Object> searchMovies(UserInput userInput) throws JsonProcessingException {
        HttpResponse<String> response = null;
        try {
            String encodedTitle = userInput.getMovieTitle().replaceAll(" ", "%20");
            String apiUrl = API_URL + encodedTitle + "?exact=false&titleType=movie";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("X-RapidAPI-Key", "e8a0cab474msh6f8a9cc15939910p170f76jsnb5332a8a4543")
                    .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send the API request and get the response
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Output: " + response.body());

            // Extract the search results from the response body
            List<String> searchResults = extractSearchResults(response.body());

            // Save the searched movies to the database
            saveSearch(userInput, searchResults);

        } catch (IOException e) {
            System.out.println("Error sending API request: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("API request interrupted: " + e.getMessage());
        }
        return Collections.singletonList(response.body());
    }

    /**
     * Extracts the search results from the API response body
     * @param responseBody the API response body
     * @return the list of search results
     */
    private List<String> extractSearchResults(String responseBody) throws JsonProcessingException {
        List<String> searchResults = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(responseBody);

        if (responseJson.isArray()) {
            for (JsonNode movieNode : responseJson) {
                // Extract the relevant movie information from the JSON nodes
                String movieTitle = movieNode.path("titleText").path("text").asText();
                String releaseYear = movieNode.path("releaseYear").path("year").asText();
                String imdbId = movieNode.path("id").asText();

                // Create a formatted string with the extracted information
                String searchResult = movieTitle + " (" + releaseYear + ") - IMDB ID: " + imdbId;
                searchResults.add(searchResult);
            }
        }

        return searchResults;
    }


    /**
     * Sends a request to search upcoming movies
     * @return the search results
     */
    public List<Object> searchUpcomingMovies() throws JsonProcessingException {
        HttpResponse<String> response = null;
        try {
            String apiUrl = "https://moviesdatabase.p.rapidapi.com/titles/x/upcoming";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("X-RapidAPI-Key", "e8a0cab474msh6f8a9cc15939910p170f76jsnb5332a8a4543")
                    .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send the API request and get the response
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Output: " + response.body());

            // Extract the search results from the response body
            List<String> searchResults = extractSearchResults(response.body());

            // Convert the search results to a List<Object>
            List<Object> searchResultsAsObjects = new ArrayList<>(searchResults);

            return Collections.singletonList(response.body());
        } catch (IOException e) {
            System.out.println("Error sending API request: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("API request interrupted: " + e.getMessage());
        }
        return Collections.singletonList(response.body());
    }




    /**
     * Saves the search to the database
     * @param userInput the user input
     * @param searchResults the list of search results
     */
    private void saveSearch(UserInput userInput, List<String> searchResults) {
        try {
            // Create a Search object to store the user input
            Search search = new Search(userInput);

            // Convert the search object to JSON string
            String json = objectMapper.writeValueAsString(search);

            // Write the JSON string to the database file
            FileWriter writer = new FileWriter(DATABASE_FILE_PATH, true);
            writer.write(json + "\n"); // Add a new line after each search
            writer.close();

            System.out.println("Search saved successfully.");
        } catch (JsonProcessingException e) {
            System.out.println("Error converting search to JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to the database file: " + e.getMessage());
        }
    }


    /**
     * Search class represents a search object which is used to store the user input
     * @version 1.0
     */
    private static class Search {
        private UserInput userInput;

        public Search() {
            // Default constructor for JSON serialization
        }

        public Search(UserInput userInput) {
            this.userInput = userInput;
        }

        public UserInput getUserInput() {
            return userInput;
        }

        public void setUserInput(UserInput userInput) {
            this.userInput = userInput;
        }
    }

}
