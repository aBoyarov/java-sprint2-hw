package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static model.Status.DONE;
import static model.Status.NEW;
import static model.Status.IN_PROGRESS;

public class InMemoryTaskManager implements TaskManager {
    Map<Integer, Task> tasks = new HashMap<>();
    Map<Integer, Epic> epics = new HashMap<>();
    Map<Integer, Subtask> subtasks = new HashMap<>();
    int id = 0;


    private int createId() {
        return ++id;
    }

    /**
     * TASKS методы
     * 1. Полученик списка всех задач
     */
    @Override
    public Map<Integer, Task> getAllTasks() {
        return tasks;
    }

    /**
     * 2. Удаление всех задач
     */
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * 3. Получение задачи по идентификатору
     */
    @Override
    public Task getTaskById(int id) {
        if (id == 0) {
            System.out.println("Индексы всех задач начинаются с 1");
        }
        Manager.getDefaultHistory().add(tasks.get(id));
        return tasks.get(id);
    }

    /**
     * 4. Создание задачи
     */
    @Override
    public void newTask(Task task) {
        task.setId(createId());
        tasks.put(task.getId(), task);
    }

    /**
     * 5. Обновление задачи
     */
    @Override
    public void updateTask(Task task) {
        if (tasks.get(task.getId()).getStatus().equals(NEW)) {
            task.setStatus(IN_PROGRESS);
        }
        tasks.put(task.getId(), task);
    }

    /**
     * 6. Удаление задачи по идентификатору
     */
    @Override
    public void deleteTaskById(int id) {
        if (id == 0) {
            System.out.println("Индексы всех задач начинаются с 1");
        } else {
            Manager.getDefaultHistory().remove(id);
            tasks.remove(id);
        }
    }

    /**
     * EPICS методы
     * 1. Получение списка всех эпиков
     */
    @Override
    public Map<Integer, Epic> getAllEpics() {
        return epics;
    }

    /**
     * 2. Удаление всех эпиков
     */
    @Override
    public void deleteAllEpics() {
        epics.clear();
    }

    /**
     * 3. Получение эпика по идентификатору
     */
    @Override
    public Epic getEpicById(int id) {
        Manager.getDefaultHistory().add(epics.get(id));
        return epics.get(id);
    }

    /**
     * 4. Создание эпика
     */
    @Override
    public void newEpic(Epic epic) {
        epic.setId(createId());
        epics.put(epic.getId(), epic);
    }


    /**
     * 5. Удаление эпика по идентификатору
     */
    @Override
    public void deleteEpicById(int id) {
        if (id == 0) {
            System.out.println("Индексы всех эпиков начинаются с 1" + '\n');
        } else {
            List<Subtask> list = epics.get(id).getSubtasksId();
            for (Subtask subtask : list) {
                subtasks.remove(subtask.getId());
            }
            epics.get(id).getSubtasksId().clear();
            Manager.getDefaultHistory().remove(id);
            epics.remove(id);
        }
    }

    /**
     * SUBTASK методы
     * 1. Получение списка всех подзадач
     */
    @Override
    public Map<Integer, Subtask> getAllSubtask() {
        return subtasks;
    }

    /**
     * 2. Удаление всех подзадач
     */
    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Map.Entry<Integer, Epic> epic : epics.entrySet()) {
            epic.getValue().getSubtasksId().clear();
        }
    }

    /**
     * 3. Получение подзадачи по идентификатору
     */
    @Override
    public Subtask getSubtaskById(int id) {
        Manager.getDefaultHistory().add(subtasks.get(id));
        return subtasks.get(id);
    }

    /**
     * 4. Создание подзадачи
     */
    @Override
    public void newSubtask(Subtask subtask) {
        for (Map.Entry<Integer, Epic> epic : epics.entrySet()) {
            if (epic.getKey().equals(subtask.getEpicId())) {
                subtask.setId(createId());
                subtasks.put(subtask.getId(), subtask);
                epics.get(subtask.getEpicId()).getSubtasksId().add(subtask);
            }
        }
    }


    /**
     * 5. Обновление подзадачи
     */
    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtasks.get(subtask.getId()).getStatus().equals(NEW)) {
            subtask.setStatus(IN_PROGRESS);
        }
        subtasks.get(subtask.getId()).setStatus(IN_PROGRESS);
        epics.get(subtask.getEpicId()).setStatus(IN_PROGRESS);
        List<Status> list = new ArrayList<>();
        for (Subtask subtask1 : epics.get(subtask.getEpicId()).getSubtasksId()) {
            list.add(subtask1.getStatus());
        }
        if (!list.contains(IN_PROGRESS) && !list.contains(NEW)) {
            epics.get(subtask.getEpicId()).setStatus(DONE);
        }
    }


    /**
     * 6. Удаление подзадачи по идентификатору
     */
    @Override
    public void deleteSubtaskById(int id) {
        if (id != 0) {
            subtasks.remove(id);
            Manager.getDefaultHistory().remove(id);
            for (Map.Entry<Integer, Epic> epic : epics.entrySet()) {
                epic.getValue().getSubtasksId().removeIf(subtask -> subtask.getId() == id);
            }
        } else {
            System.out.println("Индексы всех подзадач начинаются с 1" + '\n');
        }
    }

    /**
     * 7. Удаление всех подзадач в эпике
     */
    @Override
    public void deleteAllSubtasksByEpic(int epicId) {
        if (epicId != 0) {
            List<Subtask> list = epics.get(epicId).getSubtasksId();
            for (Subtask subtask : list) {
                subtasks.remove(subtask.getId());
            }
            epics.get(epicId).getSubtasksId().clear();
        } else {
            System.out.println("Индексы всех эпиков начинаются с 1" + '\n');
        }
    }
}

