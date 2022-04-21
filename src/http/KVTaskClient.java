package http;


import exception.ManagerSaveException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {
    private final URI url;
    private final HttpClient httpClient;

    public KVTaskClient(URI url){
        this.url = url;
        this.httpClient = HttpClient.newHttpClient();
    }

    public void put(String json, int key, String apiKey) {
        URI requestURI = URI.create(url + "/save/" + key + "?API_KEY=" + apiKey);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(requestURI)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            HttpResponse<String> responseClient = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (responseClient.statusCode() == 200) {
                System.out.println("Сохранено");
            }
        } catch (IOException | InterruptedException e) {
            throw new ManagerSaveException(e.getMessage());

        }
    }

    public String load(int key, String apiKey) {
        String response = "";
        URI requestURI = URI.create(url + "/load/" + key + "?API_KEY=" + apiKey);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(requestURI)
                .GET()
                .build();
        try {
            HttpResponse<String> responseClient = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (responseClient.statusCode() == 200) {
                response = responseClient.body();
            }
        } catch (IOException | InterruptedException e) {
            throw new ManagerSaveException(e.getMessage());
        }
        return response;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}