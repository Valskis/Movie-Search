Feature: Movie Search API

  Scenario: Searching for movies
    Given a user wants to search for movies
    When the user sends a POST request to "/api/movies/search" with the following request body:
    """
    {
      "movieTitle": "Harry Potter"
    }
    """
    Then the response status should be 200
