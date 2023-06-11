function searchMovies() {
    const movieInput = document.getElementById('movieInput').value;
    const userInput = {
        movieTitle: movieInput,
        actorName: ''
    };

    searchAPI('/api/movies/search', userInput);
}

function searchActors() {
    const actorInput = document.getElementById('actorInput').value;
    const userInput = {
        movieTitle: '',
        actorName: actorInput
    };

    searchAPI('/api/actors/search', userInput);
}

function searchAPI(url, userInput) {
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userInput)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error searching API');
            }
        })
        .then(data => {
            displayResults(data);
        })
        .catch(error => {
            console.error(error);
        });
}

function displayResults(data) {
    const resultContainer = document.getElementById('resultContainer');
    resultContainer.innerHTML = '';

    if (data.length === 0) {
        resultContainer.textContent = 'No results found.';
    } else {
        data.forEach(item => {
            const itemContainer = document.createElement('div');
            itemContainer.classList.add('item-container');

            if (item.hasOwnProperty('imdbId')) {
                const movieTitle = item.titleText ? item.titleText.text : 'Unknown Title';
                const releaseYear = item.releaseYear ? item.releaseYear.year : 'Unknown Year';

                const movieInfo = document.createElement('p');
                movieInfo.textContent = `Title: ${movieTitle}, Release Year: ${releaseYear}`;
                itemContainer.appendChild(movieInfo);
            } else if (item.hasOwnProperty('id')) {
                const actorName = item.primaryName ? item.primaryName : 'Unknown Name';
                const birthYear = item.birthYear ? item.birthYear : 'Unknown Year';

                const actorInfo = document.createElement('p');
                actorInfo.textContent = `Actor: ${actorName}, Birth Year: ${birthYear}`;
                itemContainer.appendChild(actorInfo);
            }

            resultContainer.appendChild(itemContainer);
        });
    }
}
