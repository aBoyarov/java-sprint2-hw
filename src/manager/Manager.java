package manager;

public class Manager {
    private static HistoryManager historyManager;

    public static HistoryManager getDefaultHistory() {
        return historyManager;
    }
//я случайно))
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
