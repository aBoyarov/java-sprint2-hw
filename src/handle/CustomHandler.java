package handle;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import manager.Manager;
import manager.TaskManager;
import model.Task;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CustomHandler implements HttpHandler {
    private TaskManager taskManager;
    private Gson gson;

    public CustomHandler(TaskManager taskManager, Gson gson) {
        this.taskManager = taskManager;
        this.gson = gson;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();
        switch (method) {
            case "GET":
                response = getHandle(httpExchange);
                break;
            case "POST":
                response = postHandle(httpExchange);
                break;
            case "DELETE":
                response = deteleHandle(httpExchange);
                break;
        }
    }

    private String getHandle(HttpExchange httpExchange) {
        String response = "";
        String[] path = httpExchange.getRequestURI().getPath().split("/");
        String query = httpExchange.getRequestURI().getQuery();
        if (query != null) {
            String[] request = query.split("=");
            if (path[2].equals("subtask")) {
                int id = Integer.parseInt(request[1]);
                response = gson.toJson(taskManager.getEpicById(id).getSubtasksId());
            } else if (path[2].equals("task")) {
                int id = Integer.parseInt(request[1]);
                response = gson.toJson(taskManager.getTaskById(id));
            }
        } else {
            if (path[2] == null) {
                response = gson.toJson(taskManager.getPrioritizedTasks());
            } else if (path[2].equals("task")) {
                response = gson.toJson(taskManager.getAllTasks());
            } else if (path[2].equals("history")) {
                response = gson.toJson(Manager.getDefaultHistory().getHistory());
            }
        }
        return response;
    }

    private String postHandle(HttpExchange httpExchange) throws IOException {
        InputStream inputStream = httpExchange.getRequestBody();
        String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        taskManager.newTask(gson.fromJson(body, Task.class));
        return "Задача " + body + " успешно добавлена";
    }

    private String deteleHandle(HttpExchange httpExchange) {
        String[] path = httpExchange.getRequestURI().getPath().split("/");
        String response = "";
        String query = httpExchange.getRequestURI().getQuery();
        if (query != null) {
            String[] request = query.split("=");
            int id = Integer.parseInt(request[1]);
            taskManager.deleteTaskById(id);
            response = "Задача с индексом " + id + " удалена";
        } else if (path[2].equals("task")) {
            taskManager.deleteAllTasks();
            response = "Все задачи удалены";
        }
        return response;
    }
}