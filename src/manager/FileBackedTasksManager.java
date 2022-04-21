package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import static model.TaskTypes.EPIC;
import static model.TaskTypes.SUBTASK;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    String fileName;

    public FileBackedTasksManager(String filename) {
        this.fileName = filename;
    }

    public static void main(String[] args) {
        TaskManager manager = new FileBackedTasksManager("C:\\Users\\boyar\\mySprint\\src\\tasks.csv");

        Task taskTime = new Task(
                "Сходить в магазин",
                "Купить продукты",
                LocalDateTime.of(2022, 7, 10, 10, 0),
                Duration.ofMinutes(60));

        Task task2 = new Task(
                "Сходить в магазин",
                "Купить продукты",
                LocalDateTime.of(2022, 7, 12, 13, 0),
                Duration.ofMinutes(60));

        Task task3 = new Task(
                "Сходить в магазин",
                "Купить продукты",
                LocalDateTime.of(2022, 7, 14, 16, 0),
                Duration.ofMinutes(60));

        Epic epicTime = new Epic(
                "Сходить в бассейн",
                "Сегодня релакс");

        Subtask subtaskTime = new Subtask(
                "Купить полотенце для бассейна",
                "Желательно со львом",
                LocalDateTime.of(2022, 7, 16, 19, 0),
                Duration.ofMinutes(60),
                4);

        Subtask secondSubtaskTime = new Subtask(
                "Купить тапочки для бассейна",
                "Кроксы само собой",
                LocalDateTime.of(2022, 7, 18, 22, 0),
                Duration.ofMinutes(60),
                4);

        manager.newTask(taskTime);
        manager.newTask(task2);
        manager.newTask(task3);
        manager.newEpic(epicTime);
        manager.newSubtask(subtaskTime);
        manager.newSubtask(secondSubtaskTime);
        for (Task task : manager.getPrioritizedTasks()) {
            System.out.println(task);
        }
    }

    public void save() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ID,Type,Name,Status,Description,Epic \n");
            for (Map.Entry<Integer, Task> task : tasks.entrySet()) {
                String str = toString(task.getValue());
                writer.write(str + '\n');
            }
            for (Map.Entry<Integer, Epic> epic : epics.entrySet()) {
                String str = toString(epic.getValue());
                writer.write(str + '\n');
            }
            for (Map.Entry<Integer, Subtask> subtask : subtasks.entrySet()) {
                String str = toString(subtask.getValue());
                writer.write(str + '\n');
            }
            String history = InMemoryHistoryManager.toString(historyManager);
            writer.write('\n');
            writer.write(history);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String toString(Task task) {
        if (task.getType().equals(SUBTASK)) {
            return task.getId() + "," +
                    task.getType() + "," +
                    task.getName() + "," +
                    task.getStatus() + "," +
                    task.getDescription() + "," +
                    ((Subtask) task).getEpicId();
        } else {
            return task.getId() + "," +
                    task.getType() + "," +
                    task.getName() + "," +
                    task.getStatus() + "," +
                    task.getDescription();
        }
    }

    public Task fromString(String task) {
        if (task.equals("")) return null;
        String[] arr = task.split(",");
        if (arr[1].equals("SUBTASK")) {
            return new Subtask(arr[2], arr[4], Integer.parseInt(arr[5]));
        } else if (arr[1].equals("EPIC")) {
            return new Epic(arr[2], arr[4]);
        } else {
            return new Task(arr[2], arr[4]);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager backed = new FileBackedTasksManager(file.toString());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            while (reader.ready()) {
                String str = reader.readLine();
                Task task = backed.fromString(str);
                if (task == null) continue;
                if (task.getType().equals(SUBTASK)) {
                    backed.newSubtask((Subtask) task);
                } else if (task.getType().equals(EPIC)) {
                    backed.newEpic((Epic) task);
                } else {
                    backed.newTask(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return backed;
    }

    @Override
    public Map<Integer, Task> getAllTasks() {
        return super.getAllTasks();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public Task getTaskById(int id) {
        super.getTaskById(id);
        save();
        return super.getTaskById(id);
    }

    @Override
    public void newTask(Task task) {
        super.newTask(task);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void endTask(Task task) {
        super.endTask(task);
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public Map<Integer, Epic> getAllEpics() {
        return super.getAllEpics();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public Epic getEpicById(int id) {

        super.getEpicById(id);
        save();
        return super.getEpicById(id);
    }

    @Override
    public void newEpic(Epic epic) {
        super.newEpic(epic);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public Map<Integer, Subtask> getAllSubtask() {
        return super.getAllSubtask();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public Subtask getSubtaskById(int id) {
        super.getSubtaskById(id);
        save();
        return super.getSubtaskById(id);
    }

    @Override
    public void newSubtask(Subtask subtask) {
        super.newSubtask(subtask);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void endSubtask(Subtask subtask) {
        super.endSubtask(subtask);
        save();
    }

    @Override
    public void checkEpicStatus(Subtask subtask) {
        super.checkEpicStatus(subtask);
    }

    @Override
    public void deleteSubtaskById(int id) {
        super.deleteSubtaskById(id);
        save();
    }

    @Override
    public void deleteAllSubtasksByEpic(int epicId) {
        super.deleteAllSubtasksByEpic(epicId);
        save();
    }
}
