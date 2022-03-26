import manager.*;
import model.Epic;
import model.Subtask;
import model.Task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new InMemoryTaskManager();
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        FileBackedTasksManager manager = new FileBackedTasksManager("C:\\Users\\boyar\\java-sprint2-hw\\src\\task.csv");

//
        Task task1 = new Task("Стирка", "Постирать всю одежду");
        Task task2 = new Task("Уборка", "Сделать генеральную уборку");
        Task task3 = new Task("Готовка", "Приготовить на неделю");
        Task task4 = new Task("Прогулка", "Сходить в парк");

        Epic epic1 = new Epic("Переезд", "Хочу переехать в другой город");
        Epic epic2 = new Epic("Поиск работы", "Пора идти в IT");
        Epic epic3 = new Epic("Поступление в ВУЗ", "Надо браться за голову");
        Epic epic4 = new Epic("Поход в театр", "ЭТО ШЕДЕВР!");


        Subtask subtask1 = new Subtask("Собрать вещи", "ВСЕ ВЕЩИ", 5);
        Subtask subtask2 = new Subtask("Написать резюме", "Или скачать))", 6);
        Subtask subtask3 = new Subtask("Подготовиться к экзаменам", "Опять не спать всю ночь", 7);
        Subtask subtask4 = new Subtask("Купить билеты", "Через приложение удобней", 8);


        manager.newTask(task1);
        manager.newTask(task2);
        manager.newTask(task3);
        manager.newTask(task4);
        manager.newEpic(epic1);
        manager.newEpic(epic2);
        manager.newEpic(epic3);
        manager.newEpic(epic4);
        manager.newSubtask(subtask1);
        manager.newSubtask(subtask2);
        manager.newSubtask(subtask3);
        manager.newSubtask(subtask4);

//        File file = new File("C:\\Users\\boyar\\java-sprint2-hw\\src\\task.csv");
//
//        System.out.println(FileBackedTasksManager.loadFromFile(file).getAllEpics());
//        System.out.println(FileBackedTasksManager.loadFromFile(file).getAllSubtask());
//        System.out.println(FileBackedTasksManager.loadFromFile(file).getAllTasks());

    }
}
