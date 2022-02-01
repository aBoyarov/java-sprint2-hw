package manager;

import java.util.*;
import model.*;

import static model.Status.DONE;
import static model.Status.NEW;

public class Manager {

        private final HashMap<Integer, Task> tasks;
        private final HashMap<Integer, Subtask> subtasks;
        private final HashMap<Integer, Epic> epics;

        public Manager() {
            tasks = new HashMap();
            subtasks = new HashMap();
            epics = new HashMap();
        }

        //методы получения списка всех задач
        public Collection<Task> getTask() {
            return tasks.values();
        }

        public Collection<Epic> getEpic() {
            return epics.values();
        }

        public Collection<Subtask> getSubtask() {
            return subtasks.values();
        }

        // методы удаления всех задач
        public void deleteTask() {
            tasks.clear();
        }

        public void deleteEpic() {
            epics.clear();
        }

        public void deleteSubtas() {
            subtasks.clear();
        }

        //методы получения по идентификатору
        public Task getTaskId(int id) {
            return tasks.get(id);
        }

        public Epic getEpicId(int id) {
            return epics.get(id);
        }

        public Task getSubtaskId(int id) {
            return subtasks.get(id);
        }

        //методы создания задач
        public void newTask(Task task) {
            this.tasks.put(task.getId(), task);
        }

        public void newEpic(Epic epic) {
            this.epics.put(epic.getId(), epic);
        }

        public Subtask newSubtask(Subtask subtask) {
            return this.subtasks.put(subtask.getId(), subtask);
        }

        //методы обновления
        public void updateTask(Task task) {
            this.tasks.put(task.getId(), task);
        }

        public void updateEpic(Epic epic) {
            this.epics.put(epic.getId(), epic);
        }

        public void updateSubtask(Subtask subtask) {
            this.subtasks.put(subtask.getId(), subtask);
        }


        //методы удаления по идентификатору
        public void deleteTaskId(int id) {
            this.tasks.remove(id);
        }

        public void deleteEpicId(int id) {
            ArrayList<Integer> numberId = getEpicId(id).getSubtaskId();
            for (Integer integer : numberId) {
                subtasks.remove(integer);
            }
            this.epics.remove(id);
        }

        public void deleteSubtaskId(int id) {
            this.subtasks.remove(id);
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
            Epic epic = subtask.getAncestor();
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
                epic.setStatus(NEW);
            } else if (newStatus == count) {
                epic.setStatus(NEW);
            } else if (doneStatus == count) {
                epic.setStatus(DONE);
            } else epic.setStatus(Status.IN_PROGRESS);
        }
    }
