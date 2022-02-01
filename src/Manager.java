import java.util.*;
public class Manager {

        private final HashMap<Integer, Task> task;
        private final HashMap<Integer, Subtask> subtask;
        private final HashMap<Integer, Epic> epic;

        public Manager() {
            task = new HashMap();
            subtask = new HashMap();
            epic = new HashMap();
        }

        //методы получения списка всех задач
        public Collection<Task> getTask() {
            return task.values();
        }

        public Collection<Epic> getEpic() {
            return epic.values();
        }

        public Collection<Subtask> getSubtask() {
            return subtask.values();
        }

        // методы удаления всех задач
        public void deleteTask() {
            task.clear();
        }

        public void deleteEpic() {
            epic.clear();
        }

        public void deleteSubtas() {
            subtask.clear();
        }

        //методы получения по идентификатору
        public Task getTaskId(int id) {
            return task.get(id);
        }

        public Epic getEpicId(int id) {
            return epic.get(id);
        }

        public Task getSubtaskId(int id) {
            return subtask.get(id);
        }

        //методы создания задач
        public void newTask(Task task) {
            this.task.put(task.getId(), task);
        }

        public void newEpic(Epic epic) {
            this.epic.put(epic.getId(), epic);
        }

        public Subtask newSubtask(Subtask subtask) {
            return this.subtask.put(subtask.getId(), subtask);
        }

        //методы обновления
        public void updateTask(Task task) {
            this.task.put(task.getId(), task);
        }

        public void updateEpic(Epic epic) {
            this.epic.put(epic.getId(), epic);
        }

        public void updateSubtask(Subtask subtask) {
            this.subtask.put(subtask.getId(), subtask);
        }


        //методы удаления по идентификатору
        public void deleteTaskId(int id) {
            this.task.remove(id);
        }

        public void deleteEpicId(int id) {
            ArrayList<Integer> numberId = getEpicId(id).getSubtaskId();
            for (Integer integer : numberId) {
                subtask.remove(integer);
            }
            this.epic.remove(id);
        }

        public void deleteSubtaskId(int id) {
            this.subtask.remove(id);
        }

        //методы получения подзадач эпика
        public Collection<Subtask> getSubtasks(Epic epic) {
            return epic.getSubtask();
        }

        public void setStatusTask(Task task, Status status) {
            task.setStatus(status);
        }

        public void setStatusSubtask(Subtask subtask, Status status) {
            subtask.setStatus(status);
            setStatusEpic(subtask);
        }


        private void setStatusEpic(Subtask subtask) {
            Epic epic = subtask.getParent();
            int newStatus = 0;
            int doneStatus = 0;
            int count = getSubtasks(epic).size();
            for (Subtask lSubtask : getSubtasks(epic)) {
                switch (lSubtask.getStatus()) {
                    case NEW:
                        newStatus++;
                        break;
                    case DONE:
                        doneStatus++;
                        break;
                }
            }
            if (count == 0) {
                epic.setStatus(Status.NEW);
            } else if (newStatus == count) {
                epic.setStatus(Status.NEW);
            } else if (doneStatus == count) {
                epic.setStatus(Status.DONE);
            } else epic.setStatus(Status.IN_PROGRESS);
        }
    }
