package manager;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

   private static class Node<Task> {
        Node<Task> prev;
        Task data;
        Node<Task> next;

        public Node(Node<Task> prev, Task data, Node<Task> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;

        }
    }


    private static final Map<Integer, Node<Task>> map = new HashMap<>();
    private static Node<Task> head;
    private static Node<Task> tail;

    @Override
    public void add(Task task) {
        linkLast(task);
    }

    public void linkLast(Task task) {
        if (map.containsKey(task.getId())) {
            remove(task.getId());
        }
        addNodeToMap(task);
    }

    public void addNodeToMap(Task task) {
        Node<Task> l = tail;
        Node<Task> newNode = new Node<>(l, task, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        map.put(task.getId(), tail);
    }

    @Override
    public void remove(int id) {
        removeNode(map.get(id), id);
    }

    public void removeNode(Node<Task> node, int key) {
        Node<Task> p = node.prev;
        Node<Task> n = node.next;
        if (p == null) {
            head = n;
        } else {
            p.next = n;
        }
        if (n == null) {
            tail = p;
        } else {
            n.prev = p;
        }
        map.remove(key);
    }

    @Override
    public List<Task> getHistory() {
        return getTask();
    }

    public List<Task> getTask() {
        List<Task> tasks = new ArrayList<>();
        Node<Task> node = head;
        while (node != null) {
            tasks.add(node.data);
            node = node.next;
        }
        return tasks;
    }

    static String toString(HistoryManager manager) {
        List<Task> list = manager.getHistory();
        StringBuilder builder = new StringBuilder();
        for (Task task : list) {
            builder.append(task.getId()).append(",");
        }
        return builder.toString();
    }

    static List<Integer> fromString(String value) {
        String[] arr = value.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }
}
