package model;

import java.time.Duration;
import java.time.LocalDateTime;

import static model.TaskTypes.SUBTASK;

public class Subtask extends Task {
    int epicId;

    public Subtask(String name, String description, int epicId) {
        super(name, description);
        this.type = SUBTASK;
        this.epicId = epicId;
    }

    public Subtask(String name, String description, LocalDateTime startTime, Duration duration, int epicId) {
        super(name, description, startTime, duration);
        this.epicId = epicId;
    }

    public Subtask(String name, String description, int id, Status status, int epicId) {
        super(name, description, id, status);
        this.epicId = epicId;
        this.type = SUBTASK;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Подзадача в эпике - " + epicId + '\n' +
                "Название - " + name + '\n' +
                "Описание - " + description + '\n' +
                "ID - " + id + '\n' +
                "Статус - " + status + '\n' +
                "Предполагаемое время начала задачи - " + formatter.format(startTime) + '\n' +
                "Время окончания задачи - " + formatter.format(getEndTime()) + '\n' +
                "--------------------------------------" + '\n' +
                '\n';
    }
}
