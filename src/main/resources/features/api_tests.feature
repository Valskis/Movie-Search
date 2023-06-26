Feature: Movie search

  Scenario: Search by movie title
    Given Client sends GET request with a movie title
    When HTTP GET reqest sent to the server
    Then List of movies with the same title is returned

