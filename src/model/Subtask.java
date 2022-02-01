package model;

public class Subtask extends Epic {
    private Epic ancestor;

    public Subtask(String task, String description, int id) {
        super(task, description, id);
    }

    public Epic getAncestor() {
        return ancestor;
    }

    public void setAncestor(Epic ancestor) {
        this.ancestor = ancestor;
    }

    @Override
    public String toString() {
        return "model.Subtask{" +
                "parent=" + ancestor +
                ", task='" + task + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
