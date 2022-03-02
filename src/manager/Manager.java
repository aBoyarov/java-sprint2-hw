package manager;

public class Manager {
    private static InMemoryHistoryManager inMemoryHistoryManager;


    public static HistoryManager getDefaultHistory() {
        return inMemoryHistoryManager;
    }


    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }
}

