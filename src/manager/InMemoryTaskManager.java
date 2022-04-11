package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;

import static model.Status.DONE;
import static model.Status.NEW;
import static model.Status.IN_PROGRESS;

public class InMemoryTaskManager implements TaskManager {
    protected Map<Integer, Task> tasks = new HashMap<>();
    protected Map<Integer, Epic> epics = new HashMap<>();
    protected Map<Integer, Subtask> subtasks = new HashMap<>();
    private int id = 0;


    private int createId() {
        return ++id;
    }

    public TreeSet<Task> getPrioritizedTasks() {
        TreeSet<Task> set = new TreeSet<>();
        for (Map.Entry<Integer, Task> task : tasks.entrySet()) {
            set.add(task.getValue());
        }
        for (Map.Entry<Integer, Subtask> subtask : subtasks.entrySet()) {
            set.add(subtask.getValue());
        }
        return set;
    }

    /**
     * TASKS методы
     * 1. Получение списка всех задач
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
        if (tasks.get(id) != null) {
            Manager.getDefaultHistory().add(tasks.get(id));
            return tasks.get(id);
        } else {
            throw new IllegalArgumentException("Передан id несуществующей задачи.");
        }
    }

    /**
     * 4. Создание задачи
     */
    @Override
    public void newTask(Task task) {
        for (Task taskTime : getPrioritizedTasks()) {
            if (task.getStartTime().isBefore(taskTime.getEndTime())
                    && task.getEndTime().isAfter(taskTime.getStartTime())) {
                System.out.println("Задачи не должны пересекаться по времени.");
                return;
            }
        }
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
     * 6. Завершение задачи
     */
    @Override
    public void endTask(Task task) {
        tasks.get(task.getId()).setStatus(DONE);
    }

    /**
     * 7. Удаление задачи по идентификатору
     */
    @Override
    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            Manager.getDefaultHistory().remove(id);
            System.out.println("Задача с индексом " + id + " удалена");
        } else {
            System.out.println("Нет задачи с индексом " + id);
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
        if (epics.get(id) != null) {
            Manager.getDefaultHistory().add(epics.get(id));
            return epics.get(id);
        } else {
            throw new IllegalArgumentException("Передан id несуществующего эпика");
        }
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
        if (epics.containsKey(id)) {
            List<Subtask> list = epics.get(id).getSubtasksId();
            for (Subtask subtask : list) {
                subtasks.remove(subtask.getId());
            }
            epics.get(id).getSubtasksId().clear();
            epics.remove(id);
            Manager.getDefaultHistory().remove(id);
        } else {
            System.out.println("Нет эпика с индексом" + id);
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
       if(subtasks.get(id) != null) {
           Manager.getDefaultHistory().add(subtasks.get(id));
           return subtasks.get(id);
       }else {
           throw new IllegalArgumentException("Передан id несуществующей подзадачи.");
       }
    }

    /**
     * 4. Создание подзадачи
     */
    @Override
    public void newSubtask(Subtask subtask) {
        for (Task subtaskTime : getPrioritizedTasks()) {
            if (subtask.getStartTime().isBefore(subtaskTime.getEndTime())
                    && subtask.getEndTime().isAfter(subtaskTime.getStartTime())) {
                System.out.println("Подзадачи не должны пересекаться по времени.");
                return;
            }
        }
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
        epics.get(subtask.getEpicId()).setStatus(IN_PROGRESS);
        checkEpicStatus(subtask);
    }

    /**
     * 6. Завершение подзадачи
     */
    @Override
    public void endSubtask(Subtask subtask) {
        subtask.setStatus(DONE);
        epics.get(subtask.getEpicId()).setStatus(IN_PROGRESS);
        checkEpicStatus(subtask);
    }

    /**
     * 7. Проверка статуса эпика
     */
    @Override
    public void checkEpicStatus(Subtask subtask) {
        List<Status> list = new ArrayList<>();
        for (Subtask sub : epics.get(subtask.getEpicId()).getSubtasksId()) {
            list.add(sub.getStatus());
        }
        if (!list.contains(IN_PROGRESS) && !list.contains(NEW)) {
            epics.get(subtask.getEpicId()).setStatus(DONE);
        }
    }

    /**
     * 8. Удаление подзадачи по идентификатору
     */
    @Override
    public void deleteSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            Manager.getDefaultHistory().remove(id);
            for (Map.Entry<Integer, Epic> epic : epics.entrySet()) {
                epic.getValue().getSubtasksId().removeIf(subtask -> subtask.getId() == id);
            }
        } else {
            System.out.println("Нет подзадачи с индексом " + id);
        }
    }

    /**
     * 9. Удаление всех подзадач в эпике
     */
    @Override
    public void deleteAllSubtasksByEpic(int epicId) {
        if (epics.containsKey(epicId)) {
            for (Subtask subtask : epics.get(epicId).getSubtasksId()) {
                subtasks.remove(subtask.getId());
            }
            epics.get(epicId).getSubtasksId().clear();
        } else {
            System.out.println("Нет эпика с индексом " + epicId);
        }
    }
}

