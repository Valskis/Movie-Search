package lt.viko.moviesearch.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lt.viko.moviesearch.model.Actors;
import lt.viko.moviesearch.model.UserInput;
import lt.viko.moviesearch.service.ActorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ActorController class represents ActorController object which is responsible
 * for GET and POST requests related to actors
 * @version 1.0
 */
@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorSearchService actorSearchService;

    @Autowired
    public ActorController(ActorSearchService actorSearchService) {
        this.actorSearchService = actorSearchService;
    }

    /**
     * GET request to get all actors
     * @return list of actors
     */
    @PostMapping("/search")
    public ResponseEntity<List<Object>> searchActors(@RequestBody UserInput userInput) {
        List<Object> results;
        try {
            results = actorSearchService.searchActors(userInput);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(results);
    }
}
