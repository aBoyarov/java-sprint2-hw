package model;

import manager.IsMemoryTaskManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Epic extends Subtask {
    private final List<Subtask> subTasks = new ArrayList<>();

    public Epic(String task, String descriptionTask) {
        super(task, descriptionTask);
    }

    public Epic(String task, String descriptionTask, Integer epicId) {
        super(task, descriptionTask, epicId);
    }

    public void addSubtask(Subtask subTask) {
        for (Subtask epicSubtask : subTasks) {
            if (epicSubtask.getTaskId().equals(subTask.getTaskId())) {
                return;
            }
        }
        subTasks.add(subTask);
    }

    public List<Subtask> getSubTasks() {
        return subTasks;
    }

    @Override
    public String toString() {
        return "tasks.Epic{" +
                "task='" + getTask() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", id='" + getEpicId() + '\'' +
                ", subTasks='" + subTasks + '\'' +
                '}';
    }
}

