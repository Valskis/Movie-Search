package lt.viko.moviesearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents Actors object that is used to store data from JSON file
 * with the help of Jackson library
 * @version 1.0
 */
public class Actors {
    @JsonProperty("nconst")
    private String id;

    @JsonProperty("primaryName")
    private String primaryName;

    @JsonProperty("birthYear")
    private int birthYear;

    @JsonProperty("deathYear")
    private Integer deathYear;

    @JsonProperty("primaryProfession")
    private String primaryProfession;

    @JsonProperty("knownForTitles")
    private String knownForTitles;

    public Actors() {
    }

    public Actors(String id, String primaryName, int birthYear, Integer deathYear, String primaryProfession, String knownForTitles) {
        this.id = id;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.primaryProfession = primaryProfession;
        this.knownForTitles = knownForTitles;
    }

    @JsonProperty("nconst")
    public String getId() {
        return id;
    }

    @JsonProperty("nconst")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("primaryName")
    public String getPrimaryName() {
        return primaryName;
    }

    @JsonProperty("primaryName")
    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    @JsonProperty("birthYear")
    public int getBirthYear() {
        return birthYear;
    }

    @JsonProperty("birthYear")
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @JsonProperty("deathYear")
    public Integer getDeathYear() {
        return deathYear;
    }

    @JsonProperty("deathYear")
    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @JsonProperty("primaryProfession")
    public String getPrimaryProfession() {
        return primaryProfession;
    }

    @JsonProperty("primaryProfession")
    public void setPrimaryProfession(String primaryProfession) {
        this.primaryProfession = primaryProfession;
    }

    @JsonProperty("knownForTitles")
    public String getKnownForTitles() {
        return knownForTitles;
    }

    @JsonProperty("knownForTitles")
    public void setKnownForTitles(String knownForTitles) {
        this.knownForTitles = knownForTitles;
    }
}
