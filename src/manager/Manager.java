package manager;

import http.HttpTaskManager;

import java.net.URI;

public class Manager {
    static String filename = "";
    static URI url = null;
    public static TaskManager getDefault(){
        return new HttpTaskManager(filename, url);
    }

    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
