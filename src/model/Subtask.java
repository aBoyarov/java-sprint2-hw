package model;
import static model.Status.*;



public class Subtask extends Task {

    private Integer epicId;

    public Subtask(String task, String description) {
        super(task, description);
    }

    public Subtask(String task, String description, Status status, Integer subtaskId, Integer epicId) {
        super(task, description, status, subtaskId);
        this.epicId = epicId;
    }

    public Subtask(String task, String description, Status status) {
        super(task, description, status);
    }

    public Subtask(String task, String description, Integer epicId) {
        super(task, description, epicId);
        this.epicId = epicId;
    }


    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "tasks.Subtask{" +
                "task='" + getTask() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", id='" + getTaskId() + '\'' +
                ", epicId=" + epicId +
                '}';
    }
}




