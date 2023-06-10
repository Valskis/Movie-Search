package lt.viko.moviesearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.moviesearch.model.Actors;
import lt.viko.moviesearch.model.UserInput;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActorSearchService {
    private static final String DATABASE_FILE_PATH = "searches.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_URL = "https://moviesdatabase.p.rapidapi.com/actors/";

    public void searchActors(UserInput userInput) {
        try {
            String encodedName = userInput.getActorName().replaceAll(" ", "%20");
            String apiUrl = API_URL + encodedName;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("X-RapidAPI-Key", "e8a0cab474msh6f8a9cc15939910p170f76jsnb5332a8a4543")
                    .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send the API request and get the response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Output: " + response.body());

            // Extract the search results from the response body
            List<Actors> searchResults = extractSearchResults(response.body());

            // Save the searched actors to the database
            saveSearch(userInput, searchResults);
        } catch (IOException e) {
            System.out.println("Error sending API request: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("API request interrupted: " + e.getMessage());
        }
    }

    private List<Actors> extractSearchResults(String responseBody) throws JsonProcessingException {
        List<Actors> searchResults = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(responseBody);

        if (responseJson.isArray()) {
            for (JsonNode actorNode : responseJson) {
                // Extract the relevant actor information from the JSON nodes
                String id = actorNode.path("nconst").asText();
                String primaryName = actorNode.path("primaryName").asText();
                int birthYear = actorNode.path("birthYear").asInt();
                Integer deathYear = actorNode.path("deathYear").asInt();
                String primaryProfession = actorNode.path("primaryProfession").asText();
                String knownForTitles = actorNode.path("knownForTitles").asText();

                // Create an Actor object with the extracted information
                Actors actor = new Actors(id, primaryName, birthYear, deathYear, primaryProfession, knownForTitles);
                searchResults.add(actor);
            }
        }

        return searchResults;
    }

    private void saveSearch(UserInput userInput, List<Actors> searchResults) {
        try {
            // Create a Search object to store the user input and search results
            Search search = new Search(userInput, searchResults);

            // Convert the search object to JSON string
            String json = objectMapper.writeValueAsString(search);

            // Write the JSON string to the database file
            FileWriter writer = new FileWriter(DATABASE_FILE_PATH, true);
            writer.write(json + "\n");
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
        private List<Actors> searchResults;

        public Search() {
            // Default constructor for JSON serialization
        }

        public Search(UserInput userInput, List<Actors> searchResults) {
            this.userInput = userInput;
            this.searchResults = searchResults;
        }

        public UserInput getUserInput() {
            return userInput;
        }

        public void setUserInput(UserInput userInput) {
            this.userInput = userInput;
        }

        public List<Actors> getSearchResults() {
            return searchResults;
        }

        public void setSearchResults(List<Actors> searchResults) {
            this.searchResults = searchResults;
        }
    }
}
