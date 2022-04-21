package http;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import exception.ManagerSaveException;
import manager.FileBackedTasksManager;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

public class HttpTaskManager extends FileBackedTasksManager {

    private final KVTaskClient client;

    public HttpTaskManager(String filename, URI url) {
        super(filename);
        this.client = new KVTaskClient(url);
    }

    public void saveManager(int key, String apiKey) throws IOException {
        String json;
        Gson gson = new Gson();
        FileReader reader = new FileReader(getFileName());
        BufferedReader br = new BufferedReader(reader);
        ArrayList<String> values = new ArrayList<>();
        try {
            while (br.ready()) {
                String line = br.readLine();
                values.add(line);
            }
            br.close();
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка");
        }
        json = gson.toJson(values);
        client.put(json, key, apiKey);
    }

    public void loadManager(int key, String apiKey) {
        String manager1 = client.load(key, apiKey);
        String manager = String.valueOf(JsonParser.parseString(manager1));
        String[] values = manager.split("/n");
        ArrayList<String> valuesInArr = new ArrayList<>(List.of(values));
        deleteAllTasks();
        deleteAllSubtasks();
        deleteAllEpics();
        fromString(valuesInArr.toString());
    }

    public HttpClient getClient() {
        return client.getHttpClient();
    }

    @Override
    public String getFileName() {
        return super.getFileName();
    }
}