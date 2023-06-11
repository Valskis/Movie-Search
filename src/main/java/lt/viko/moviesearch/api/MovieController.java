package lt.viko.moviesearch.api;

import lt.viko.moviesearch.model.Movies;
import lt.viko.moviesearch.model.UserInput;
import lt.viko.moviesearch.service.MovieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieSearchService movieSearchService;

    @Autowired
    public MovieController(MovieSearchService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @PostMapping("/search")
    public ResponseEntity<String> searchMovies(@RequestBody UserInput userInput) {
        movieSearchService.searchMovies(userInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
