package manager;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private static List<Integer> list = new LinkedList<>();
    private static final int SIZE = 10;

    @Override
    public void add(Task task) {
        if(list.size() < SIZE){
            list.add(task.getId());
        }else {
            list.remove(0);
        }
    }

    @Override
    public List<Integer> getHistory() {
        return list;
    }
}