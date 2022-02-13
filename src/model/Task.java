package model;

import java.util.Objects;

public class Task {
    private final String task;
    private final String description;
    private Status status;
    private Integer id;

    public Task(String task, String descriptionTask) {
        this.task = task;
        this.description = descriptionTask;
    }

    public Task(String task, String descriptionTask, Status status) {
        this.task = task;
        this.description = descriptionTask;
        this.status = status;
    }

    public Task(String task, String descriptionTask, Status status, Integer id) {
        this.task = task;
        this.description = descriptionTask;
        this.status = status;
        this.id = id;
    }

    public Task(String task, String descriptionTask, Integer id) {
        this.task = task;
        this.description = descriptionTask;
        this.id = id;

    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getTaskId() {
        return id;
    }

    public void setIdTask(Integer idTask) {
        this.id = idTask;
    }

    @Override
    public String toString() {
        return "tasks.Task{" +
                "task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return Objects.equals(task, task1.task) &&
                Objects.equals(description, task1.description) &&
                status == task1.status &&
                Objects.equals(id, task1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, description, status, id);
    }
}


