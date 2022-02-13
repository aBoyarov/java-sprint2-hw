package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static model.Status.DONE;
import static model.Status.NEW;
import static model.Status.IN_PROGRESS;

public class IsMemoryTaskManager implements TaskManager {
    private int id;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subTasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    @Override
    public void addTask(Task task) {
        task.setIdTask(generateId());
        tasks.put(task.getTaskId(), task);
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    @Override
    public void removeTasks(int id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Такой задачи нет");
        } else {
            tasks.remove(id);
            System.out.println("Задача удалена");
        }
    }

    @Override
    public void clearAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTask(int id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Такой задачи нет");
            return null;
        } else {
            return tasks.get(id);
        }
    }

    @Override
    public List<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }


    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setIdTask(generateId());
        subTasks.put(subtask.getTaskId(), subtask);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subTasks.put(subtask.getTaskId(), subtask);
        if (subtask.getEpicId() != null && epics.get(subtask.getEpicId()) != null) {
            epics.get(subtask.getEpicId()).addSubtask(subtask);
            updateEpicStatus(epics.get(subtask.getEpicId()));
        }
    }

    @Override
    public void removeSubtask(int id) {
        if (!subTasks.containsKey(id)) {
            System.out.println("Такой задачи нет");
        } else {
            subTasks.remove(id);
            System.out.println("Задача удалена");
        }
    }

    @Override
    public void clearAllSubtasks() {
        subTasks.clear();
    }


    @Override
    public Subtask getSubtask(int id) {
        if (!subTasks.containsKey(id)) {
            System.out.println("Такой задачи нет");
            return null;
        } else {
            return subTasks.get(id);
        }
    }

    @Override
    public ArrayList<Subtask> getAllSubtask() {
        return new ArrayList<>(subTasks.values());
    }


    @Override
    public void addEpic(Epic epic) {
        epic.setStatus(NEW);
        epic.setEpicId(generateId());
        epics.put(epic.getEpicId(), epic);
    }

    @Override
    public void updateEpic(Epic epic) {
        updateEpicStatus(epic);
        epics.put(epic.getEpicId(), epic);
    }

    @Override
    public void removeEpic(int id) {
        if (!epics.containsKey(id)) {
            System.out.println("Такой задачи нет");
        } else {
            epics.remove(id);
            System.out.println("Задача удалена");
        }
    }

    @Override
    public void clearAllEpic() {
        epics.clear();
    }

    @Override
    public Epic getEpic(int id) {
        if (!epics.containsKey(id)) {
            System.out.println("Такой задачи нет");
            return null;
        } else {
            return epics.get(id);
        }
    }

    @Override
    public ArrayList<Epic> getAllSubTaskEpic() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        List<Status> generalStatus = new ArrayList<>();
        for (Subtask subTask : epic.getSubTasks()) {
            generalStatus.add(subTask.getStatus());
        }
        if (generalStatus.contains(IN_PROGRESS)) {
            epic.setStatus(IN_PROGRESS);
        } else if (generalStatus.contains(DONE)) {
            epic.setStatus(DONE);
        } else {
            epic.setStatus(NEW);
        }
    }

    @Override
    public int generateId() {
        return ++id;
    }
}



