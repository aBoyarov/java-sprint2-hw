package model;
import static model.Status.*;



public class Subtask extends Task {

    public int EpicId;

    public Subtask(String name, String description, Status status, int epicId) {
        super(name, description, status);
        EpicId = epicId;
    }


    public int getEpicId() {
        return EpicId;
    }


    @Override
    public String toString() {
        return "Subtask{" +
                "EpicId=" + EpicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
