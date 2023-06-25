package lt.viko.moviesearch.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lt.viko.moviesearch.model.Movies;
import lt.viko.moviesearch.model.UserInput;
import lt.viko.moviesearch.service.MovieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieSearchService movieSearchService;

    @Autowired
    public MovieController(MovieSearchService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<Object>> searchMovies(@RequestBody UserInput userInput) {
        List<Object> results;
        try {
            results = movieSearchService.searchMovies(userInput);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(results);
    }
}