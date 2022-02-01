import java.util.Objects;

public class Task {


    protected String task;
    protected String description;
    protected int id;
    protected Status status;

    public Task(String task, String description, int id) {
        this.task = task;
        this.description = description;
        this.id = id;
        this.status = Status.NEW;
    }

    public String getTask() {
        return task;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return id == task1.id &&
                Objects.equals(task, task1.task) &&
                Objects.equals(description, task1.description) &&
                status == task1.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, description, id, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

}