package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.*;

import static model.Status.DONE;
import static model.Status.NEW;
import static model.Status.IN_PROGRESS;

public class InMemoryTaskManager implements TaskManager {

    int id = 0;
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    public InMemoryTaskManager(int id) {
        this.id = id;
    }

    //генерируем Id
    private int createId() {
        return ++id;
    }

    //tasks
//Получить список всех задач
    @Override
    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    //Удалить все задачи
    @Override
    public void clearTasks() {
        tasks.clear();
    }

    //Получить задачу по Id
    @Override
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    //Создать новую задачу
    @Override
    public void newTask(Task task) {
        task.setId(createId());
        tasks.put(task.getId(), task);
    }

    //Обновить задачу
    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    //Удалить задачу по Id
    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
    }


    //epics
//Получить список всех эпиков
    @Override
    public Map<Integer, Epic> getEpics() {
        return epics;
    }

    //Удалить все эпики
    @Override
    public void clearEpics() {
        epics.clear();
    }

    //Получить эпик по Id
    @Override
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    //Создать новый эпик
    @Override
    public void newEpic(Epic epic) {
        epic.setId(createId());
        epics.put(epic.getId(), epic);

    }

    //Обновить эпик
    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);

    }

    //Удалить эпик по Id
    @Override
    public void removeEpicById(int id) {
        epics.remove(id);

    }

    @Override
    public void updateStatusByEpic(Epic epic) {
        for (Subtask subtask : epic.getSubtasks()) {
            if (subtask.getStatus().equals(NEW)) {
                epic.setStatus(NEW);
            } else if (subtask.getStatus().equals(DONE)) {
                epic.setStatus(DONE);
            } else {
                epic.setStatus(IN_PROGRESS);
            }
        }
    }

    //subtasks
//Получить список всех подзадач
    @Override
    public Map<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    //Удалить все подзадачи
    @Override
    public void clearSubtask() {
        subtasks.clear();
        System.out.println("Все подзадачи удалены");
    }

    //Получить подзадачу по Id
    @Override
    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    //Создать новую подзадачу
    @Override
    public void newSubtask(Subtask subtask) {
        subtask.setId(createId());
        subtasks.put(subtask.getId(), subtask);

    }

    //Обновить подзадачу
    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    //Удалить подзадачу по Id
    @Override
    public void removeSubtaskById(int id) {
        subtasks.remove(id);
    }

    @Override
    public List<Subtask> getAllSubtasksByEpic(Epic epic) {
        return epic.getSubtasks();
    }


}



