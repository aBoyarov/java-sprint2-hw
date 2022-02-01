
import manager.Manager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        int id = 1;
        ArrayList<Subtask> subtasks = new ArrayList<>();

        manager.newTask(new Task("Задача 1", "Описание задачи 1", ++id));
        manager.newTask(new Task("Задача 2", "Описание задачи 2", ++id));


        subtasks.add(manager.newSubtask(new Subtask("Подзадача 1","Описание подзадачи 1",++id)));
        subtasks.add(manager.newSubtask(new Subtask("Подзадача 2","Описание подзадачи 2",++id)));
        manager.newEpic(new Epic("Эпик 1","Описание эпика 1", ++id, subtasks));

        subtasks.clear();
        subtasks.add(manager.newSubtask(new Subtask("Подзадача 3","Описание подзадачи 3",++id)));
        manager.newEpic(new Epic("Эпик 2","Описание эпика 2", ++id, subtasks));

        for (Epic epic: manager.getEpic()) {
            Collection<Subtask> ar = epic.getSubtask();
            System.out.println(ar.size());
        }

        for (Epic epic: manager.getEpic()) {
            System.out.println(epic);
        }
        for (Task task: manager.getTask()) {
            System.out.println(task);
        }
        for (Subtask subtask: manager.getSubtask()) {
            System.out.println(subtask);
        }

        System.out.println("В процессе");
        for (Task task: manager.getTask()) {
            manager.setStatusTask(task, Status.IN_PROGRESS);
        }
        System.out.println("Новый статус");
        for (Task task: manager.getTask()) {
            System.out.println(task);
        }

        System.out.println("Завершено");
        for (Subtask subtask: manager.getSubtask()) {
            manager.setStatusSubtask(subtask, Status.DONE);
        }
        System.out.println("Новый статус подзадач");
        for (Epic epic: manager.getEpic()) {
            System.out.println(epic);
        }
        for (Subtask subtask: manager.getSubtask()) {
            System.out.println(subtask);
        }

        System.out.println("удаление задачи");
        for (Task task: manager.getTask()) {
            System.out.println(task);
            manager.deleteTaskId(task.id);

        }

        System.out.println("удаляем эпик");
        for (Epic epic: manager.getEpic()) {
            System.out.println(epic);
            manager.deleteEpicId(epic.id);

        }

    }
}