package tests;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Status.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EpicTest {
    private TaskManager manager;
    private Epic epic;

    @BeforeEach
    void testsEpic() {
        manager = new InMemoryTaskManager();
        epic = new Epic("Test newEpic", "Test newEpic description");
        manager.newEpic(epic);
    }

    @Test
    void emptyListOfSubtaskByEpic() {
        assertTrue(epic.getSubtasksId().isEmpty(), "Список подзадач не пуст.");
    }

    @Test
    void allSubtasksByEpicWithStatusNew() {
        manager.newSubtask(new Subtask("Test subtaskStatus", "statusNew", 1));
        manager.newSubtask(new Subtask("another test subtaskStatus", "statusNew", 1));
        final List<Subtask> subtasks = manager.getEpicById(epic.getId()).getSubtasksId();
        for (Subtask subtask : subtasks) {
            assertEquals(NEW, subtask.getStatus(), "Статус подзадачи не совпадает.");
            assertEquals(NEW, epic.getStatus(), "Статус эпика не совпадает.");
        }
    }

    @Test
    void allSubtasksByEpicWithStatusDone() {
        Subtask firstSubtask = new Subtask("Test subtaskStatus", "statusNew", 1);
        Subtask secondSubtask = new Subtask("another test subtaskStatus", "statusNew", 1);
        manager.newSubtask(firstSubtask);
        manager.newSubtask(secondSubtask);
        manager.endSubtask(firstSubtask);
        manager.endSubtask(secondSubtask);
        final List<Subtask> subtasks = manager.getEpicById(epic.getId()).getSubtasksId();
        for (Subtask subtask : subtasks) {
            assertEquals(DONE, subtask.getStatus(), "Статус подзадачи не совпадает.");
        }
        assertEquals(DONE, epic.getStatus(), "Статус эпика не совпадает.");
    }

    @Test
    void allSubtasksByEpicWithStatusNewAndDone(){
        Subtask firstSubtask = new Subtask("Test subtaskStatus", "statusNew", 1);
        Subtask secondSubtask = new Subtask("another test subtaskStatus", "statusNew", 1);
        manager.newSubtask(firstSubtask);
        manager.newSubtask(secondSubtask);
        manager.endSubtask(firstSubtask);
        assertEquals(DONE, firstSubtask.getStatus(), "Статус подзадачи не совпадает.");
        assertEquals(NEW, secondSubtask.getStatus(), "Статус подзадачи не совпадает.");
        assertEquals(IN_PROGRESS, epic.getStatus(), "Статус эпика не совпадает.");
    }

    @Test
    void allSubtasksByEpicWithStatusInProgress(){
        Subtask firstSubtask = new Subtask("Test subtaskStatus", "statusNew", 1);
        manager.newSubtask(firstSubtask);
        manager.updateSubtask(firstSubtask);
        assertEquals(IN_PROGRESS, firstSubtask.getStatus(), "Статус подзадачи не совпадает.");
        assertEquals(IN_PROGRESS, epic.getStatus(), "Статус эпика не совпадает.");

    }
}