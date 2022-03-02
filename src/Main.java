
import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.Manager;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.LinkedList;
import java.util.List;

import static model.Status.*;

public class Main {
    public static void main(String[] args) {
        List<Subtask> subtaskList = new LinkedList<>();
        InMemoryTaskManager manager = new InMemoryTaskManager();
        InMemoryHistoryManager isMemoryHistoryManager = new InMemoryHistoryManager();

        Task task1 = new Task("Задача 1", "Описание", NEW);
        Task task2 = new Task("Задача 1", "Описание", NEW);
        manager.newTask(task1);
        manager.newTask(task2);
        isMemoryHistoryManager.add(task1);
        isMemoryHistoryManager.add(task2);


        Subtask subtask1 = new Subtask("Подзадача 1", "Описание", NEW, 1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание", DONE, 1);
        subtaskList.add(subtask1);
        subtaskList.add(subtask2);
        manager.newSubtask(subtask1);
        manager.newSubtask(subtask2);
        isMemoryHistoryManager.add(subtask1);
        isMemoryHistoryManager.add(subtask2);


        Epic epic1 = new Epic("Эпик 1", "Описание", NEW, subtaskList);
        manager.newEpic(epic1);
        isMemoryHistoryManager.add(epic1);


        System.out.println(isMemoryHistoryManager.getHistory());
    }
}


