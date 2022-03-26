package model;

import java.util.Objects;

import static model.Status.NEW;
import static model.TaskTypes.TASK;

public class Task {
    protected String name;
    protected String decision;
    protected int id;
    protected Status status;
    protected TaskTypes type;

    public Task(String name, String decision, int id, Status status) {
        this.name = name;
        this.decision = decision;
        this.id = id;
        this.status = status;
        this.type = TASK;
    }

    public Task(String name, String decision) {
        this.name = name;
        this.decision = decision;
        this.status = NEW;
        this.type = TASK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TaskTypes getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id
                && Objects.equals(name, task.name)
                && Objects.equals(decision, task.decision)
                && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, decision, id, status);
    }

    @Override
    public String toString() {
        return " Задача " + '\n' +
                "Название - " + name + '\n' +
                "Описание - " + decision + '\n' +
                "ID - " + id + '\n' +
                "Статус - " + status + '\n' +
                "----------------------------------------------------------------------------" + '\n' +
                '\n';
    }
}


