package tests;

import manager.FileBackedTasksManager;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileBackedTasksManagerTest extends TaskManagerTest {
    @BeforeEach
    void testFileManager(){
        String file = "C:\\Users\\boyar\\mySprint\\src\\tasks.csv";
        manager = new FileBackedTasksManager(file);
    }

    @Test
    void mustSaveToFile(){
        File file = new File("C:\\Users\\boyar\\mySprint\\src\\tasks.csv");
        Task task = new Task("test Task","test save Task");
        Epic epic = new Epic("test Epic","test save Epic");
        Subtask subtask = new Subtask("test Subtask" ,"test save Subtask", 2);
        manager.newTask(task);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            while (reader.ready()) {
                tasks.add(reader.readLine());
            }
            assertTrue(tasks.get(0).contains("TASK"), "Нет такой задачи в файле.");
            assertTrue(tasks.get(1).contains("EPIC"), "Нет такого эпика в файле.");
            assertTrue(tasks.get(2).contains("SUBTASK"), "Нет такой подзадачи в файле.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldRestoreTaskFromFile(){
        Task task = new Task("test Task","test save Task");
        Epic epic = new Epic("test Epic","test save Epic");
        Subtask subtask = new Subtask("test Subtask" ,"test save Subtask", 2);
        manager.newTask(task);
        manager.newEpic(epic);
        manager.newSubtask(subtask);
        assertEquals(task, manager.getTaskById(1), "Задача не восстановлена.");
        assertEquals(epic, manager.getEpicById(2), "Эпик не восстановлен.");
        assertEquals(subtask, manager.getSubtaskById(3), "Подзадача не восстановлена.");
    }
}
