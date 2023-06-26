function searchMovies() {
    const movieInput = document.getElementById('movieInput').value;
    const userInput = {
        movieTitle: movieInput,
        actorName: ''
    };

    fetch('/api/movies/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userInput)
    })
        .then(res => res.json())
        .then(data => {
            displayMovieResults(data);
        })
        .catch(err => console.log(err));
}

function searchActors() {
    const actorInput = document.getElementById('actorInput').value;
    const userInput = {
        movieTitle: '',
        actorName: actorInput
    };

    fetch('/api/actors/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userInput)
    })
        .then(res => res.json())
        .then(data => {
            displayActorResults(data);
        })
        .catch(err => console.log(err));
}

function displayMovieResults(data) {
    const resultContainer = document.getElementById('resultContainer');
    resultContainer.innerHTML = '';

    if (data.length === 0) {
        resultContainer.textContent = 'No movie results found.';
    } else {
        data.forEach(movie => {
            const movieContainer = document.createElement('div');
            movieContainer.classList.add('movie-container');

            const info = document.createElement('p');
            info.textContent = JSON.stringify(movie, null, 2);
            movieContainer.appendChild(info);

            resultContainer.appendChild(movieContainer);
        });
    }
}

function displayActorResults(data) {
    const resultContainer = document.getElementById('resultContainer');
    resultContainer.innerHTML = '';

    if (data.length === 0) {
        resultContainer.textContent = 'No actor results found.';
    } else {
        data.forEach(actor => {
            const actorContainer = document.createElement('div');
            actorContainer.classList.add('actor-container');

            const info = document.createElement('p');
            info.textContent = JSON.stringify(actor, null, 2);
            actorContainer.appendChild(info);

            resultContainer.appendChild(actorContainer);
        });
    }
}
