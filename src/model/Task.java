package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static model.Status.NEW;
import static model.TaskTypes.TASK;

public class Task implements Comparable<Task>{
    protected String name;
    protected String description;
    protected int id;
    protected Status status;
    protected TaskTypes type;
    protected LocalDateTime startTime;
    protected Duration duration;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    public Task(String name, String description, int id, Status status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
        this.type = TASK;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = NEW;
        this.type = TASK;
    }

    public Task(String name, String description, LocalDateTime startTime, Duration duration) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.duration = duration;
        this.status = NEW;
        this.type = TASK;

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getEndTime() {
        return startTime.plus(duration);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                && Objects.equals(description, task.description)
                && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }


    @Override
    public String toString() {
        return "Задача " + '\n' +
                "Название - " + name + '\n' +
                "Описание - " + description + '\n' +
                "ID - " + id + '\n' +
                "Статус - " + status + '\n' +
                "Предполагаемое время начала задачи - " + formatter.format(startTime) + '\n' +
                "Время окончания задачи - " + formatter.format(getEndTime()) + '\n' +
                "Продолжительность - " + getDuration().toMinutes() + " минут" + '\n' +
                "----------------------------------------------------------------------------" + '\n' +
                '\n';
    }


    @Override
    public int compareTo(Task o) {
        if(this.getStartTime().isBefore(o.startTime))return -1;
        else return 1;
    }
}


