package model;

import java.util.ArrayList;
import java.util.Collection;

public class Epic extends Task {
    private Collection<Subtask> subtask = new ArrayList<>();


    public Epic(String task, String description, int id) {
        super(task, description, id);
    }

    public Epic(String task, String description, int id, ArrayList<Subtask> subtask) {
        super(task, description, id);
        for (Subtask subTask : subtask) {
            subTask.setAncestor(this);
            this.subtask.add(subTask);
        }
    }


    public ArrayList<Integer> getSubtaskId() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Subtask subtask : subtask) {
            ids.add(subtask.getId());
        }
        return ids;
    }

    @Override
    public String toString() {
        return "model.Epic{" +
                "subtasks=" + subtask +
                ", task='" + task + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public Collection<Subtask> getSubtask() {
        return subtask;
    }

    public void setSubtask(Collection<Subtask> subtask) {
        this.subtask = subtask;
    }

}