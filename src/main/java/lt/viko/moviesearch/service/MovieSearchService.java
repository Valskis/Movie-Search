package lt.viko.moviesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.moviesearch.model.UserInput;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class MovieSearchService {
    private static final String DATABASE_FILE_PATH = "searches.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_URL = "https://moviesdatabase.p.rapidapi.com/titles/search/title/";

    public void searchMovies(UserInput userInput) {
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
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Extract the search results from the response body
            List<String> searchResults = extractSearchResults(response.body());

            // Save the searched movies to the database
            saveSearch(userInput, searchResults);
        } catch (IOException e) {
            System.out.println("Error sending API request: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("API request interrupted: " + e.getMessage());
        }
    }

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


    private void saveSearch(UserInput userInput, List<String> searchResults) {
        try {
            // Create a Search object to store the user input and search results
            Search search = new Search(userInput, searchResults);

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

    private static class Search {
        private UserInput userInput;
        private List<String> searchResults;

        public Search() {
            // Default constructor for JSON serialization
        }

        public Search(UserInput userInput, List<String> searchResults) {
            this.userInput = userInput;
            this.searchResults = searchResults;
        }

        public UserInput getUserInput() {
            return userInput;
        }

        public void setUserInput(UserInput userInput) {
            this.userInput = userInput;
        }

        public List<String> getSearchResults() {
            return searchResults;
        }

        public void setSearchResults(List<String> searchResults) {
            this.searchResults = searchResults;
        }
    }
}
