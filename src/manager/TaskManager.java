package manager;

import java.util.*;
import model.*;

public interface TaskManager {
    public Map<Integer, Task> getTasks();

    public void clearTasks();

    public Task getTaskById (int id);

    public void newTask(Task task);

    public void updateTask(Task task);

    public void removeTaskById(int id);

    public Map<Integer, Epic> getEpics();

    public void clearEpics();

    public Epic getEpicById (int id);

    public void newEpic(Epic epic);

    public void updateEpic(Epic epic);

    public void removeEpicById(int id);

    public Map<Integer, Subtask> getSubtasks();

    public void clearSubtask();

    public Subtask getSubtaskById (int id);

    public void newSubtask(Subtask subtask);

    public void updateSubtask(Subtask subtask);

    public void removeSubtaskById(int id);

    public List<Subtask> getAllSubtasksByEpic(Epic epic);

    public void updateStatusByEpic(Epic epic);


    }
