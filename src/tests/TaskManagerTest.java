package tests;

import manager.Manager;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;

import static model.Status.DONE;
import static model.Status.IN_PROGRESS;
import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {
    T manager;

    /**
     * TASKS
     */

    @Test
    void shouldGetAllTasks() {
        Task task = new Task("testTask", "testGetTask");
        manager.newTask(task);
        Task newTasks = manager.getAllTasks().get(1);
        assertEquals(task, newTasks, "Задачи не найдены.");
    }

    @Test
    void shouldDeleteAllTasks() {
        Task task = new Task("testTask", "testGetTask");
        manager.newTask(task);
        manager.deleteAllTasks();
        assertTrue(manager.getAllTasks().isEmpty(), "Задачи не были удалены.");
    }

    @Test
    void shouldGetTaskById() {
        Task task = new Task("testTask", "testGetTask");
        manager.newTask(task);
        Task newTask = manager.getTaskById(1);
        assertEquals(task, newTask, "Задача не найдена.");
    }


    @Test
    void shouldBeUpdateTask() {
        Task task = new Task("testTask", "testGetTask");
        manager.newTask(task);
        manager.updateTask(task);
        assertEquals(IN_PROGRESS, manager.getTaskById(1).getStatus(), "Статусы задач не совпадают.");
    }

    @Test
    void shouldBeEndTask() {
        Task task = new Task("testTask", "testGetTask");
        manager.newTask(task);
        manager.endTask(task);
        assertEquals(DONE, manager.getTaskById(1).getStatus(), "Статусы задач не совпадают.");
    }

    @Test
    void shouldDeleteTaskById() {
        Task task = new Task("testTask", "testGetTask");
        manager.newTask(task);
        manager.getTaskById(1);
        manager.deleteTaskById(1);
        assertNull(manager.getTaskById(1), "Задача не была удалена.");

    }

    /**
     * EPICS
     */

    @Test
    void shouldGetAllEpics() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        manager.newEpic(epic);
        Epic newEpic = manager.getAllEpics().get(1);
        assertEquals(epic, newEpic, "Эпики не найдены.");
    }

    @Test
    void shouldDeleteAllEpics() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        manager.newEpic(epic);
        manager.deleteAllEpics();
        assertTrue(manager.getAllEpics().isEmpty(), "Эпики не были удалены.");
    }

    @Test
    void shouldGetEpicById() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        manager.newEpic(epic);
        Epic newEpic = manager.getEpicById(1);
        assertEquals(epic, newEpic, "Эпик не найден.");
    }

    @Test
    void shouldDeleteEpicById() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        manager.newEpic(epic);
        manager.getEpicById(1);
        manager.deleteEpicById(1);
        assertNull(manager.getEpicById(1), "Эпик не был удален.");
    }


    /**
     * SUBTASKS
     */

    @Test
    void shouldGetAllSubtask() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        Subtask subtask = new Subtask("testEpic", "testGetAllEpics", 1);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        Subtask newSubtask = manager.getSubtaskById(2);
        assertEquals(subtask, newSubtask, "Подзадача не найдена.");


    }

    @Test
    void shouldDeleteAllSubtasks() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        Subtask subtask = new Subtask("testEpic", "testGetAllEpics", 1);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        manager.deleteAllSubtasks();
        assertTrue(manager.getAllTasks().isEmpty(), "Подзадачи не были удалены.");
    }

    @Test
    void shouldGetSubtaskById() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        Subtask subtask = new Subtask("testEpic", "testGetAllEpics", 1);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        Subtask newSubtask = manager.getSubtaskById(2);
        assertEquals(subtask, newSubtask, "Подзадача не найдена.");
    }

    @Test
    void shouldBeUpdateSubtask() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        Subtask subtask = new Subtask("testEpic", "testGetAllEpics", 1);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        manager.updateSubtask(subtask);
        assertEquals(IN_PROGRESS, manager.getSubtaskById(2).getStatus(), "Статусы задач не совпадают.");
    }

    @Test
    void shouldBeEndSubtask() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        Subtask subtask = new Subtask("testEpic", "testGetAllEpics", 1);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        manager.endSubtask(subtask);
        assertEquals(DONE, manager.getSubtaskById(2).getStatus(), "Статусы задач не совпадают.");
    }

    @Test
    void shouldDeleteSubtaskById() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        Subtask subtask = new Subtask("testEpic", "testGetAllEpics", 1);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        manager.getSubtaskById(2);
        manager.deleteSubtaskById(2);
        assertNull(manager.getSubtaskById(2), "Задача не была удалена.");
    }

    @Test
    void shouldDeleteAllSubtasksByEpic() {
        Epic epic = new Epic("testEpic", "testGetAllEpics");
        Subtask subtask = new Subtask("testEpic", "testGetAllEpics", 1);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        manager.deleteAllSubtasksByEpic(1);
        assertTrue(manager.getEpicById(1).getSubtasksId().isEmpty(), "Подзадачи не были удалены.");
    }

    /**
     * HISTORY
     */

    @Test
    void shouldBeHistoryIsEmpty() {
        Task task = new Task("test task", "test History");
        manager.newTask(task);
        assertTrue(Manager.getDefaultHistory().getHistory().isEmpty(), "История не пуста.");
    }

    @Test
    void shouldRemoveTaskFromHistory() {
        Task task = new Task("test task", "test History");
        manager.newTask(task);
        manager.getTaskById(1);
        manager.deleteTaskById(1);
        assertTrue(Manager.getDefaultHistory().getHistory().isEmpty(), "Задача не удалена.");
    }
}
