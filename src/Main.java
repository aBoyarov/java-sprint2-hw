
import manager.IsMemoryHistoryManager;
import manager.IsMemoryTaskManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;

import static model.Status.*;

public class Main {
    public static void main(String[] args) {
        IsMemoryTaskManager manager = new IsMemoryTaskManager();
        IsMemoryHistoryManager history = new IsMemoryHistoryManager();

        System.out.println("Проверка задач");

        manager.addTask(new Task("Задача 1", "Описание", NEW));
        manager.addTask(new Task("Задача 2", "Описание", NEW));
        System.out.println(manager.getTask(1));
        System.out.println(manager.getTask(2));

        manager.updateTask(new Task("Задача 3", "Описание", NEW, 1));
        System.out.println(manager.getAllTask());
        manager.removeTasks(1);
        manager.clearAllTasks();



        System.out.println("Проверка подзадач");
        manager.addSubtask(new Subtask("ПодЗадача 1", "Описание", NEW));
        manager.addSubtask(new Subtask("ПодЗадача 2", "Описание", NEW));
        System.out.println(manager.getSubtask(1));
        System.out.println(manager.getSubtask(2));

        manager.updateSubtask(new Subtask("ПодЗадача 3", "Описание", NEW, 1, 1));
        System.out.println(manager.getAllSubtask());
        manager.removeSubtask(1);
        manager.clearAllSubtasks();


        System.out.println("Проверка эпиков");

        manager.addEpic(new Epic("Эпик 1", "Описание"));
        manager.addEpic(new Epic("Эпик 2", "Описание"));


        manager.addSubtask(new Subtask("Подзадача 1", "Описание", NEW));
        manager.addSubtask(new Subtask("Подзадача 2", "Описание", NEW));
        manager.addSubtask(new Subtask("Подзадача 3", "Описание", NEW));


        //Обновляем подзадачу, меняем статус подзадачи на IN_PROGRESS и DONE, говорим какому эпику будет принадлежать
        manager.updateSubtask(new Subtask("Подзадача 1", "Описание", DONE, 1, 5));
        manager.updateSubtask(new Subtask("Подзадача 2", "Описание", DONE, 2, 5));
        manager.updateSubtask(new Subtask("Подзадача 3", "Описание", IN_PROGRESS, 3, 6));

        System.out.println(manager.getEpic(5));
        System.out.println(manager.getEpic(6));

        manager.updateEpic(new Epic("Эпик 5", "Описание", 6));
        manager.updateSubtask(new Subtask("Подзадача 3", "Описание", IN_PROGRESS, 3, 6));
        manager.removeEpic(5);
        System.out.println(manager.getAllSubTaskEpic());
        manager.clearAllEpic();

        history.getHistory();
    }
}
}