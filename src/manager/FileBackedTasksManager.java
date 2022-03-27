package manager;

import exception.ManagerSaveException;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.io.*;
import java.util.Map;

import static model.TaskTypes.SUBTASK;

public class FileBackedTasksManager extends InMemoryTaskManager {

    String fileName;

    public FileBackedTasksManager(String filename) {
        this.fileName = filename;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("id,type,name,status,description,epic \n");
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
        } catch (IOException e) {
            throw new ManagerSaveException();
        }
    }


    public String toString(Task task) {
        if (task.getType().equals(SUBTASK)) {
            return task.getId() + "," +
                    task.getType() + "," +
                    task.getName() + "," +
                    task.getStatus() + "," +
                    task.getDecision() + "," +
                    ((Subtask) task).getEpicId();
        } else {
            return task.getId() + "," +
                    task.getType() + "," +
                    task.getName() + "," +
                    task.getStatus() + "," +
                    task.getDecision();
        }
    }

    public Task fromString(String task) {
        String[] arr = task.split(",");
        if (arr[1].equals("SUBTASK")) {
            subtasks.put(Integer.valueOf(arr[0]), new Subtask(arr[2], arr[4],
                    Integer.parseInt(arr[0]), Status.valueOf(arr[3]), Integer.parseInt(arr[5])));
            return new Subtask(arr[2], arr[4], Integer.parseInt(arr[5]));
        } else if (arr[1].equals("EPIC")) {
            epics.put(Integer.valueOf(arr[0]), new Epic(arr[2], arr[4], Integer.parseInt(arr[0]),
                    Status.valueOf(arr[3])));
            return new Epic(arr[2], arr[4]);
        } else {
            tasks.put(Integer.valueOf(arr[0]), new Task(arr[2], arr[4], Integer.parseInt(arr[0]),
                    Status.valueOf(arr[3])));
            return new Task(arr[2], arr[4]);
        }
    }

    public static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager backed = new FileBackedTasksManager(file.toString());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            while (reader.ready()) {
                String str = reader.readLine();
                backed.fromString(str);
            }
        } catch (IOException e) {
            throw new ManagerSaveException();
        }
        return backed;
    }


    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
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
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }


    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
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
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
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
