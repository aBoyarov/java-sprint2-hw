package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static model.Status.NEW;
import static model.TaskTypes.EPIC;

public class Epic extends Task {
    LocalDateTime endTime;
    private List<Subtask> subtasksId = new LinkedList<>();

    public Epic(String name, String description) {
        super(name, description);
        this.type = EPIC;
        this.status = NEW;
    }

    public Epic(String name, String description, int id, Status status) {
        super(name, description, id, status);
        this.type = EPIC;

    }

    public Epic(String name, String description, int id, Status status, List<Subtask> subtasksId) {
        super(name, description, id, status);
        this.subtasksId = subtasksId;
    }

    public Epic(String name, String description, List<Subtask> subtasksId) {
        super(name, description);
        this.subtasksId = subtasksId;
    }


    public List<Subtask> getSubtasksId() {
        return subtasksId;
    }

    @Override
    public Duration getDuration() {
        return Duration.between(getStartTime(), getEndTime());
    }

    @Override
    public LocalDateTime getStartTime() {
        List<Subtask> list = getSubtasksId();
        if(list == null) return null;
        else {
            list.sort((Subtask o1, Subtask o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
            return list.get(0).getStartTime();
        }
    }

    @Override
    public LocalDateTime getEndTime() {
        List<Subtask> list = getSubtasksId();
        if(list == null) return null;
        else {
            list.sort((Subtask o1, Subtask o2) -> o2.getEndTime().compareTo(o1.getEndTime()));
            return list.get(0).getEndTime();
        }
    }

    @Override
    public String toString() {
        return "Эпик" + '\n' +
                "Название - " + name + '\n' +
                "Описание - " + description + '\n' +
                "ID - " + id + '\n' +
                "Статус - " + status + '\n' +
                "Предполагаемое время начала задачи - " + formatter.format(getStartTime()) + '\n' +
                "Время окончания задачи - " + formatter.format(getEndTime()) + '\n' +
                "----------------------------------------------------------------------------" + '\n' +
                "" + subtasksId + '\n' +
                '\n' +
                '\n';



    }
}
