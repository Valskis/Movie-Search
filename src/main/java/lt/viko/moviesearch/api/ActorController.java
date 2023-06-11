package lt.viko.moviesearch.api;

import lt.viko.moviesearch.model.Actors;
import lt.viko.moviesearch.model.UserInput;
import lt.viko.moviesearch.service.ActorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorSearchService actorSearchService;

    @Autowired
    public ActorController(ActorSearchService actorSearchService) {
        this.actorSearchService = actorSearchService;
    }

    @PostMapping("/search")
    public ResponseEntity<String> searchActors(@RequestBody UserInput userInput) {
        actorSearchService.searchActors(userInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
