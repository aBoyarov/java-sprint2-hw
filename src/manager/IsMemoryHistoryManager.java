package manager;

import model.Task;

import java.util.LinkedList;
import java.util.List;

public class IsMemoryHistoryManager implements HistoryManager {
    private static List<Integer> history = new LinkedList<>();
    private static final int SIZE = 10;

    @Override
    public void add(Task task) {
        if(history.size() < SIZE){
            history.add(task.getTaskId());
        } else {
            history.remove(0);
        }

    }

    public List<Integer> getHistory() {
        return history;
    }


}
