import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.Manager;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager();
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        Task taskTime = new Task(
                "Сходить в магазин",
                "Купить продукты",
                LocalDateTime.of(2022, 7, 14, 11, 12),
                Duration.ofMinutes(30));

        Task task2 = new Task(
                "Сходить в магазин",
                "Купить продукты",
                LocalDateTime.of(2022, 7, 14, 11, 12),
                Duration.ofMinutes(30));

        Task task3 = new Task(
                "Сходить в магазин",
                "Купить продукты",
                LocalDateTime.of(2022, 7, 14, 11, 12),
                Duration.ofMinutes(30));

        Epic epicTime = new Epic(
                "Сходить в бассейн",
                "Сегодня релакс");

        Subtask subtaskTime = new Subtask(
                "Купить полотенце для бассейна",
                "Желательно со львом",
                LocalDateTime.of(2022, 7, 14, 17, 0),
                Duration.ofMinutes(55),
                2);

        Subtask secondSubtaskTime = new Subtask(
                "Купить тапочки для бассейна",
                "Кроксы само собой",
                LocalDateTime.of(2022, 7, 14, 18, 0),
                Duration.ofMinutes(20),
                2);

        manager.newTask(taskTime);
        manager.newTask(task2);
        manager.newEpic(epicTime);
        manager.newSubtask(subtaskTime);
        manager.newSubtask(secondSubtaskTime);
        manager.getTaskById(1);
        System.out.println(Manager.getDefaultHistory().getHistory());
    }
}
