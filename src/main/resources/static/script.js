$(document).ready(function() {
    $("#searchMoviesBtn").click(function() {
        var movieTitle = $("#movieTitle").val();
        var actorName = $("#actorName").val();
        var userInput = { movieTitle: movieTitle, actorName: actorName };

        $.ajax({
            url: "/api/movies/search",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(userInput),
            success: function(response) {
                displayMovieResults(response);
            },
            error: function(xhr, status, error) {
                console.log("Error: " + error);
            }
        });
    });

    $("#searchActorsBtn").click(function() {
        var movieTitle = $("#movieTitle").val();
        var actorName = $("#actorName").val();
        var userInput = { movieTitle: movieTitle, actorName: actorName };

        $.ajax({
            url: "/api/actors/search",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(userInput),
            success: function(response) {
                displayActorResults(response);
            },
            error: function(xhr, status, error) {
                console.log("Error: " + error);
            }
        });
    });

    $("#searchUpcomingMoviesBtn").click(function() {
        $.ajax({
            url: "/api/movies/upcoming",
            type: "GET",
            success: function(response) {
                displayMovieResults(response);
            },
            error: function(xhr, status, error) {
                console.log("Error: " + error);
            }
        });
    });

    function displayMovieResults(data) {
        var resultsContainer = $("#resultsContainer");
        resultsContainer.empty();

        if (Array.isArray(data) && data.length > 0) {
            data.forEach(function(movie) {
                var movieContainer = $("<div>").addClass("movie-container");
                var info = $("<p>").text(JSON.stringify(movie, null, 2));

                movieContainer.append(info);
                resultsContainer.append(movieContainer);
            });
        } else {
            resultsContainer.html("<p>No movie results found.</p>");
        }
    }

    function displayActorResults(data) {
        var resultsContainer = $("#resultsContainer");
        resultsContainer.empty();

        if (Array.isArray(data) && data.length > 0) {
            data.forEach(function(actor) {
                var actorContainer = $("<div>").addClass("actor-container");
                var info = $("<p>").text(JSON.stringify(actor, null, 2));

                actorContainer.append(info);
                resultsContainer.append(actorContainer);
            });
        } else {
            resultsContainer.html("<p>No actor results found.</p>");
        }
    }
});
