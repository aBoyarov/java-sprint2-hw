
import manager.IsMemoryHistoryManager;
import manager.IsMemoryTaskManager;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

import static model.Status.*;

public class Main {
    public static void main(String[] args) {
        List<Subtask> subtaskList = new ArrayList<>();
        IsMemoryTaskManager manager = new IsMemoryTaskManager();
        IsMemoryHistoryManager isMemoryHistoryManager = new IsMemoryHistoryManager();

        Task task1 = new Task("Задача 1", "Описание", NEW);
        Task task2 = new Task("Задача 1", "Описание", NEW);
        manager.newTask(task1);
        manager.newTask(task2);
        System.out.println(manager.getTasks());
        isMemoryHistoryManager.add(task1);
        isMemoryHistoryManager.add(task2);
        System.out.println(isMemoryHistoryManager.getHistory().size());

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание", NEW, 1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание", DONE, 1);
        manager.newSubtask(subtask1);
        manager.newSubtask(subtask2);
        System.out.println(manager.getSubtasks());
        isMemoryHistoryManager.add(subtask1);
        isMemoryHistoryManager.add(subtask2);
        System.out.println(isMemoryHistoryManager.getHistory().size());

        Epic epic1 = new Epic("Эпик 1", "Описание", NEW, subtaskList);
        manager.newEpic(epic1);
        System.out.println(manager.getEpics());
        isMemoryHistoryManager.add(epic1);
        System.out.println(isMemoryHistoryManager.getHistory().size());

    }
}


