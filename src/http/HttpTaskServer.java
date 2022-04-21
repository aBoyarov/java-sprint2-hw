package http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import handle.CustomHandler;
import manager.FileBackedTasksManager;
import manager.TaskManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HttpTaskServer {
    private TaskManager taskManager;
    private Gson gson;
    private HttpServer httpServer;

    public HttpTaskServer(TaskManager manager) throws IOException {
        this.taskManager = manager;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext("/tasks", new CustomHandler(taskManager, gson));
    }
    public void start() {
        httpServer.start();
    }

    public void stop() {
        httpServer.stop(1);
    }

}
