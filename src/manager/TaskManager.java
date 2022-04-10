package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface TaskManager {

    /**
     * TASKS
     */

    public List<Task> getPrioritizedTasks();

    Map<Integer, Task> getAllTasks();

    void deleteAllTasks();

    Task getTaskById(int id);

    void newTask(Task task);

    void updateTask(Task task);

    public void endTask(Task task);

    void deleteTaskById(int id);

    /**
     * EPICS
     */

    Map<Integer, Epic> getAllEpics();

    void deleteAllEpics();

    Epic getEpicById(int id);

    void newEpic(Epic epic);

    void deleteEpicById(int id);

    /**
     * SUDTASK
     */

    Map<Integer, Subtask> getAllSubtask();

    void deleteAllSubtasks();

    Subtask getSubtaskById(int id);

    void newSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void endSubtask(Subtask subtask);

    void checkEpicStatus(Subtask subtask);

    void deleteSubtaskById(int id);

    void deleteAllSubtasksByEpic(int epicId);

}
