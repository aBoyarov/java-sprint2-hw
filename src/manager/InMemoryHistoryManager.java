package manager;

import model.Task;
import org.w3c.dom.Node;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    class Node<Task> {
        public Node<Task> prev;
        public Task data;
        public Node<Task> next;
        public Node(Node<Task> prev, Task data, Node<Task> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    private Map<Integer, Node<Task>> map = new HashMap<>();
    private Node<Task> first;
    private Node<Task> last;
    @Override
    public void add(Task task) {
        linkLast(task);
    }
    void linkLast(Task task) {
        if (map.containsKey(task.getId())) {
            Node<Task> node = map.get(task.getId());
            removeNode(node);
        }
        addNode(task);
    }
    public void addNode(Task data) {
        final Node<Task> lastt = last;
        final Node<Task> newNode = new Node<>(lastt, data, null);
        last = newNode;
        if (lastt == null)
            first = newNode;
        else
            lastt.next = newNode;
        map.put(data.getId(), last);
    }
    @Override
    public void remove(int id) {
        Node<Task> rem = map.get(id);
        removeNode(rem);
    }
    public void removeNode(Node<Task> data) {
        Node<Task> prev = data.prev;
        Node<Task> next = data.next;
        if (next == null) {
            next.prev = prev;
        } else {
            last = prev;
        }
        if (prev == null) {
            prev.next = next;
        } else {
            first = next;
        }
    }
    @Override
    public List<Task> getHistory() {
        List<Task> tasks = new ArrayList<>();
        Node<Task> node = first;
        while (node != null) {
            tasks.add(node.data);
            node = node.next;
        }
        return tasks;
    }
}

