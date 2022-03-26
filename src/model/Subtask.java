package model;

import static model.TaskTypes.SUBTASK;

public class Subtask extends Task {
    int epicId;

    public Subtask(String name, String decision, int epicId) {
        super(name, decision);
        this.type = SUBTASK;
        this.epicId = epicId;
    }

    public Subtask(String name, String decision, int id, Status status, int epicId) {
        super(name, decision, id, status);
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
                "Описание - " + decision + '\n' +
                "ID - " + id + '\n' +
                "Статус - " + status + '\n' +
                "--------------------------------------" + '\n' +
                '\n';
    }
}
