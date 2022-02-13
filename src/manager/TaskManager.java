package manager;

import java.util.*;
import model.*;

public interface TaskManager {
        public void addTask(Task task);

        public void updateTask(Task task);

        public void removeTasks(int id);

        public void clearAllTasks();

        public Task getTask(int id);

        public List<Task> getAllTask();

        public void addSubtask(Subtask subtask);

        public void updateSubtask(Subtask subtask);

        public void removeSubtask(int id);

        public void clearAllSubtasks();

        public Subtask getSubtask(int id);

        public ArrayList<Subtask> getAllSubtask();

        public void addEpic(Epic epic);

        public void updateEpic(Epic epic);

        public void removeEpic(int id);

        public void clearAllEpic();

        public Epic getEpic(int id);

        public ArrayList<Epic> getAllSubTaskEpic();

        public void updateEpicStatus(Epic epic);

        public int generateId();



}
