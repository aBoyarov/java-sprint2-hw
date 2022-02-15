package manager;

import model.Task;

public class Manager {
    TaskManager getDefault(){
        return new IsMemoryTaskManager();
    }

    HistoryManager getDefaultHistory(){
        return new IsMemoryHistoryManager();
    }

}
