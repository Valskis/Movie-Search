package lt.viko.moviesearch;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://moviesdatabase.p.rapidapi.com/titles/episode/tt3060782"))
            .header("X-RapidAPI-Key", "e8a0cab474msh6f8a9cc15939910p170f76jsnb5332a8a4543")
            .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    public Main() throws IOException, InterruptedException {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Main main = new Main();
        System.out.println(main.response.body());
    }
}

