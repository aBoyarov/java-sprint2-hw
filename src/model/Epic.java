package model;

import manager.IsMemoryTaskManager;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    List<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description, Status status, List<Subtask> subtasks) {
        super(name, description, status);
        for(Subtask subtask : subtasks)
        this.subtasks = subtasks;
    }




    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + subtasks +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}