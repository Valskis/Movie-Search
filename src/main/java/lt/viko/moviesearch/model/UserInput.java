package lt.viko.moviesearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInput {

    @JsonProperty("movieTitle")
    private String movieTitle;

    @JsonProperty("actorName")
    private String actorName;

    //Default constructor required by Jackson
    public UserInput() {
    }

    //Constructor
    public UserInput(String movieTitle, String actorName) {
        this.movieTitle = movieTitle;
        this.actorName = actorName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}
