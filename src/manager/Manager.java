package manager;

public class Manager {
    public TaskManager getDefault(){
        return new IsMemoryTaskManager();
    }

    HistoryManager getDefaultHistory(){
        return new IsMemoryHistoryManager();
    }
}
