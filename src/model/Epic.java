package model;

import java.util.LinkedList;
import java.util.List;
import static model.TaskTypes.EPIC;

public class Epic extends Task {
    private List<Subtask> subtasksId = new LinkedList<>();

    public Epic(String name, String decision) {
        super(name, decision);
        this.type = EPIC;
    }

    public Epic(String name, String decision, int id, Status status) {
        super(name, decision, id, status);
        this.type = EPIC;

    }



    public List<Subtask> getSubtasksId() {
        return subtasksId;
    }

    public void setSubtasksId(List<Subtask> subtasksId) {
        this.subtasksId = subtasksId;
    }


    @Override
    public String toString() {
        return "Эпик" + '\n' +
                "Название - " + name + '\n' +
                "Описание - " + decision + '\n' +
                "ID - " + id + '\n' +
                "Статус - " + status + '\n' +
                "----------------------------------------------------------------------------" + '\n' +
                "" + subtasksId + '\n' +
                '\n' +
                '\n';



    }
}
