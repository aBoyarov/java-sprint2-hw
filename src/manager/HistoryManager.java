package manager;
import model.Task;

import java.util.List;

public interface HistoryManager {
    public void add(Task task);

    List<Integer> getHistory();
}

